package com.mall.db.service;

import com.mall.db.domain.LitemallGrouponRules;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 团购规则表 服务类
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
public interface LitemallGrouponRulesService extends IService<LitemallGrouponRules> {
    public List<LitemallGrouponRules> getGrouponRule(String goodId);
}
