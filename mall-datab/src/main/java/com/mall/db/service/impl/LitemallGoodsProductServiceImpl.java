package com.mall.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.db.domain.LitemallGoodsProduct;
import com.mall.db.domain.LitemallGoodsSpecification;
import com.mall.db.mapper.LitemallGoodsProductMapper;
import com.mall.db.service.LitemallGoodsProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品货品表 服务实现类
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Service
public class LitemallGoodsProductServiceImpl extends ServiceImpl<LitemallGoodsProductMapper, LitemallGoodsProduct> implements LitemallGoodsProductService {
    public List<LitemallGoodsProduct> getGoodProduct(String goodId){
        LambdaQueryWrapper<LitemallGoodsProduct> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(LitemallGoodsProduct::getGoodsId,goodId);
        List<LitemallGoodsProduct> list = list(queryWrapper1);
        return list;
    }
}
