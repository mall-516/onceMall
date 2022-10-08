package com.mall.wx.controller;




import com.mall.core.system.SystemConfig;
import com.mall.core.util.ResponseUtil;
import com.mall.db.domain.*;
import com.mall.db.mapper.*;
import com.mall.db.service.impl.*;
import com.mall.wx.util.RedisCache;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * <p>
 * 商品基本信息表 前端控制器
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@RestController
@RequestMapping("/wx/goods")
@Slf4j
@Validated
public class LitemallGoodsController {



    @Autowired
    private LitemallGoodsMapper goodsMapper;

    @Autowired
    private LitemallGoodsAttributeServiceImpl goodsAttributeService;

    @Autowired
    private LitemallGoodsSpecificationServiceImpl specificationService;

    @Autowired
    private LitemallGoodsProductServiceImpl goodsProductService;

    @Autowired
    private LitemallIssueServiceImpl issueService;

    @Autowired
    private LitemallCommentServiceImpl commentService;

    @Autowired
    private LitemallBrandServiceImpl brandService;

    @Autowired
    private LitemallGrouponRulesServiceImpl rulesService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private LitemallUserMapper userMapper;

    private final static ArrayBlockingQueue<Runnable> WORK_QUEUE= new ArrayBlockingQueue<>(9);

    private final static RejectedExecutionHandler HANDLER=new ThreadPoolExecutor.CallerRunsPolicy();

    private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(16, 16, 1000, TimeUnit.MILLISECONDS, WORK_QUEUE, HANDLER);

    /**
     * 商品详情
     * <p>
     * 用户可以不登录。
     * 如果用户登录，则记录用户足迹以及返回用户收藏信息。
     *
     * @param userId 用户ID
     * @param id     商品ID
     * @return 商品详情
     * 为什么要用线程的方式来传递值，如果用原来的是怎么的
     * 1.获取商品详情信息，商品属性，规格，数量价格，问题，品牌商，评论，团购，用户收藏
     *
     */

    @GetMapping("/detail")
    public Object detail(Integer userId, @NotNull Integer id){

        Object o = redisCache.getCacheObject("goods" + id);
//        Object o = redisTemplate.opsForValue().get("goods" + id);
        if (!Objects.isNull(o)){
            log.info("diaoyogn{}","d");
            return ResponseUtil.ok(o);
        }


        //        商品详情
        LitemallGoods litemallGoods = goodsMapper.selectById(id);

        String goodId = litemallGoods.getGoodsSn();
//        商品属性
        Callable<List> listCallable  =()->goodsAttributeService.getGoodsAttributeList(litemallGoods.getGoodsSn());

//        商品规格
        Callable<Object> specificationCallable =()->  specificationService.getGoodsSpecification(litemallGoods.getGoodsSn());

//      商品规格对应的数量和价格

        Callable<List> productList =()-> goodsProductService.getGoodProduct(goodId);

//        商品问题
        Callable<List> issueCallable  =()-> issueService.list();



//        品牌问题
        Callable<LitemallBrand> brandCallable = () ->{
            LitemallBrand byId=null;
            Integer brandId = litemallGoods.getBrandId();
            if (brandId==0) {
                byId = new LitemallBrand();
            }else {
                byId = brandService.getById(litemallGoods.getBrandId());
            }
            return byId;
        };


//        评论
        Callable<Map> commentCallable=()-> commentService.getComment(goodId);


//        团购信息
        Callable<List> GrouponRulesCallable = () -> rulesService.getGrouponRule(goodId);

        // 用户收藏    只是查询
        int count = 0;

        //		继承futuretask  submit 开启执行

        FutureTask<List> goodsAttributeListTask = new FutureTask<>(listCallable);
        FutureTask<Object> objectCallableTask = new FutureTask<>(specificationCallable);
        FutureTask<List> productListCallableTask = new FutureTask<>(productList);
        FutureTask<List> issueCallableTask = new FutureTask<>(issueCallable);
        FutureTask<Map> commentsCallableTsk = new FutureTask<>(commentCallable);
        FutureTask<LitemallBrand> brandCallableTask = new FutureTask<>(brandCallable);
        FutureTask<List> grouponRulesCallableTask = new FutureTask<>(GrouponRulesCallable);

        executorService.submit(goodsAttributeListTask);
        executorService.submit(objectCallableTask);
        executorService.submit(productListCallableTask);
        executorService.submit(issueCallableTask);
        executorService.submit(commentsCallableTsk);
        executorService.submit(brandCallableTask);
        executorService.submit(grouponRulesCallableTask);

        Map<Object, Object> data = new HashMap<>();
        try {
            data.put("info", litemallGoods);
            data.put("userHasCollect", count);
            data.put("issue", issueCallableTask.get());
            data.put("comment", commentsCallableTsk.get());
            data.put("specificationList", objectCallableTask.get());
            data.put("productList", productListCallableTask.get());
            data.put("attribute", goodsAttributeListTask.get());
            data.put("brand", brandCallableTask.get());
            data.put("groupon", grouponRulesCallableTask.get());
            //SystemConfig.isAutoCreateShareImage()
            data.put("share", SystemConfig.isAutoCreateShareImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //商品分享图片地址
        data.put("shareImage",litemallGoods.getShareUrl());

        redisCache.setCacheObject("goods"+id,data);

        return ResponseUtil.ok(data);
    }

}

