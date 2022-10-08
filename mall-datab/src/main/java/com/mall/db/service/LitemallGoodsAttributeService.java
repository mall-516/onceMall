package com.mall.db.service;

import com.mall.db.domain.LitemallGoodsAttribute;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品参数表 服务类
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
public interface LitemallGoodsAttributeService extends IService<LitemallGoodsAttribute> {

    public List<LitemallGoodsAttribute> getGoodsAttributeList(String goodId);

}
