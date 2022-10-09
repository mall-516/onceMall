package com.mall.db.service;

import com.mall.db.domain.LitemallUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
public interface LitemallUserService extends IService<LitemallUser> {
    public LitemallUser getUsername(LitemallUser user);


    boolean updateLogin(LitemallUser user);
}
