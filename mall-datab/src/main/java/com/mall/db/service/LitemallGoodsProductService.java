package com.mall.db.service;

import com.mall.db.domain.LitemallGoodsProduct;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品货品表 服务类
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
public interface LitemallGoodsProductService extends IService<LitemallGoodsProduct> {
    public List<LitemallGoodsProduct> getGoodProduct(String goodId);

}
