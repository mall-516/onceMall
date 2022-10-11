package com.mall.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.db.domain.LitemallUser;
import com.mall.db.mapper.LitemallUserMapper;
import com.mall.db.service.LitemallUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Service
public class LitemallUserServiceImpl extends ServiceImpl<LitemallUserMapper, LitemallUser> implements LitemallUserService {



    @Override
    public LitemallUser getUsername(LitemallUser user) {
        LambdaQueryWrapper<LitemallUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LitemallUser::getUsername,user.getUsername());
        LitemallUser one = getOne(queryWrapper);
        return one;
    }

    @Override
    public boolean updateLogin(LitemallUser user) {
        if (!updateById(user))
            return false;
        return true;
    }

    @Override
    public LitemallUser selectOpenid(String openid) {

        LambdaQueryWrapper<LitemallUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LitemallUser::getWeixinOpenid,openid);
        LitemallUser one = getOne(queryWrapper, true);
        return one;
    }


}
