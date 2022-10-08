package com.mall.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.db.domain.LitemallGrouponRules;
import com.mall.db.mapper.LitemallGrouponRulesMapper;
import com.mall.db.service.LitemallGrouponRulesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 团购规则表 服务实现类
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Service
public class LitemallGrouponRulesServiceImpl extends ServiceImpl<LitemallGrouponRulesMapper, LitemallGrouponRules> implements LitemallGrouponRulesService {
    public List<LitemallGrouponRules> getGrouponRule(String goodId){
        LambdaQueryWrapper<LitemallGrouponRules> queryWrapper4 = new LambdaQueryWrapper<>();
        queryWrapper4.eq(LitemallGrouponRules::getGoodsId,goodId).eq(LitemallGrouponRules::getStatus,0);
        List<LitemallGrouponRules> list = list(queryWrapper4);
        return list;
    }
}
