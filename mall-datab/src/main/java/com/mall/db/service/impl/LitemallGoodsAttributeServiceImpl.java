package com.mall.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.db.domain.LitemallGoodsAttribute;
import com.mall.db.mapper.LitemallGoodsAttributeMapper;
import com.mall.db.service.LitemallGoodsAttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品参数表 服务实现类
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Service
public class LitemallGoodsAttributeServiceImpl extends ServiceImpl<LitemallGoodsAttributeMapper, LitemallGoodsAttribute> implements LitemallGoodsAttributeService {

    public List<LitemallGoodsAttribute> getGoodsAttributeList(String goodId){
        LambdaQueryWrapper<LitemallGoodsAttribute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LitemallGoodsAttribute::getGoodsId,goodId);
        List<LitemallGoodsAttribute> list = list(queryWrapper);
        return list;
    }
}
