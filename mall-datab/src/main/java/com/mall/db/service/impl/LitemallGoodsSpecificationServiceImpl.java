package com.mall.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.db.VO.GoodsSpecificationVO;
import com.mall.db.domain.LitemallGoodsSpecification;
import com.mall.db.mapper.LitemallGoodsSpecificationMapper;
import com.mall.db.service.LitemallGoodsSpecificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品规格表 服务实现类
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Service
public class LitemallGoodsSpecificationServiceImpl extends ServiceImpl<LitemallGoodsSpecificationMapper, LitemallGoodsSpecification> implements LitemallGoodsSpecificationService {

    public List<GoodsSpecificationVO> getGoodsSpecification(String goodId){
        LambdaQueryWrapper<LitemallGoodsSpecification> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(LitemallGoodsSpecification::getGoodsId,goodId);
        List<LitemallGoodsSpecification> list = list(queryWrapper1);

        Map<String, GoodsSpecificationVO> map = new HashMap<>();
        List<GoodsSpecificationVO> specificationVOList =new ArrayList<>();

        for (LitemallGoodsSpecification goodsSpecification: list) {
            String specification = goodsSpecification.getSpecification();
            GoodsSpecificationVO goodsSpecificationVO = map.get(specification);
            if (goodsSpecificationVO==null){
                goodsSpecificationVO =new GoodsSpecificationVO();
                goodsSpecificationVO.setName(specification);
                List<LitemallGoodsSpecification> valueList = new ArrayList<>();
                valueList.add(goodsSpecification);
                goodsSpecificationVO.setValueList(valueList);
                map.put(specification,goodsSpecificationVO);
                specificationVOList.add(goodsSpecificationVO);

            }else {
                List<LitemallGoodsSpecification> valueList = goodsSpecificationVO.getValueList();
                valueList.add(goodsSpecification);
            }
        }

        return specificationVOList;

    }

}
