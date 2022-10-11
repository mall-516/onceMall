package com.mall.wx.controller;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.core.util.ResponseUtil;
import com.mall.db.domain.LitemallUser;
import com.mall.db.entity.vo.WxUserInfo;
import com.mall.db.service.impl.LitemallUserServiceImpl;
import com.mall.db.entity.vo.UserInfo;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.mall.wx.util.WxResponseCode.AUTH_INVALID_ACCOUNT;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@RestController
@RequestMapping("/wx/auth")
public class LitemallUserController {

    @Autowired
    private LitemallUserServiceImpl litemallUserService;

    @Autowired
    private WxMaService wxService;
    /**
     *
     * @param body  {
     *     "username":"admin",
     *     "password":"123456"
     * }
     * @return
     */

    @PostMapping("/login")
    public Object WX_AccountLogin(@RequestBody LitemallUser body){
//        1.获取账号和密码
        String password = body.getPassword();
        String username = body.getUsername();
        if (Objects.isNull(password)||Objects.isNull(username)){
            return ResponseUtil.badArgument();
        }
//        2.账号和密码
        LitemallUser user = litemallUserService.getUsername(body);
        if (Objects.isNull(user)){
            return ResponseUtil.fail(AUTH_INVALID_ACCOUNT,"账号错误");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(password,user.getPassword())){
            return ResponseUtil.fail(AUTH_INVALID_ACCOUNT,"密码错误");
        }

//        更新登陆情况
        user.setAddTime(LocalDateTime.now());
        if (!litemallUserService.updateLogin(user)){
            return ResponseUtil.updatedDataFailed();
        }

//        返回token，userinfo
        UserInfo userInfo = new UserInfo();
        userInfo.setAvatarUrl(user.getAvatar());
        userInfo.setNickName(user.getNickname());

//        String token = StpUtil.getTokenValueByLoginId(user.getId());


//        TODO token为UUID生成（自定义与jwt生成）,访问某些页面时要判断是否带有token
        StpUtil.login(user.getId());
        String token = StpUtil.getTokenValue();

        Map<String, Object> map = new HashMap<>();
        map.put("token",token);
        map.put("userInfo",userInfo);
        return ResponseUtil.ok(map);

    }



    /**
     * 微信登陆
     * @param wxUserInfo
     */
    @PostMapping("/login_by_weixin")
    public Object WX_login(@RequestBody WxUserInfo wxUserInfo){
        String code = wxUserInfo.getCode();
        UserInfo userInfo = wxUserInfo.getUserInfo();

        if (Objects.isNull(code)||Objects.isNull(userInfo)){
            return ResponseUtil.badArgument();
        }


        WxMaJscode2SessionResult info = null;
        String openid=null;
        String sessionKey=null;
        try {
            info =this.wxService.getUserService().getSessionInfo(code);
            openid = info.getOpenid();
            sessionKey = info.getSessionKey();
        } catch (WxErrorException e) {
                e.printStackTrace();
        }

        if (openid==null||sessionKey==null){
            return ResponseUtil.badArgument();
        }

        LitemallUser user = litemallUserService.selectOpenid(openid);

        //注册
        if (Objects.isNull(user)){
            user = new LitemallUser();
            user.setUsername(openid);
            user.setPassword(openid);
            user.setWeixinOpenid(openid);
            user.setAvatar(userInfo.getAvatarUrl());
            user.setNickname(userInfo.getNickName());
            user.setGender(0);
            user.setUserLevel(0);
            user.setStatus(0);
//            可以全局设置
            user.setAddTime(LocalDateTime.now());
            user.setSessionKey(sessionKey);

            if (!litemallUserService.save(user)){
                return ResponseUtil.serious();
            }

            // TODO 赠送优惠券
        }else {
//            每次登陆时会话密钥不一样
            user.setSessionKey(sessionKey);
            user.setUpdateTime(LocalDateTime.now());
            boolean update = litemallUserService.update(user, new LambdaQueryWrapper<LitemallUser>()
                    .eq(LitemallUser::getWeixinOpenid, openid));
            if (!update){
                return ResponseUtil.serious();
            }
        }

        StpUtil.login(user.getId());
        String token = StpUtil.getTokenValue();
        Map<String, Object> map = new HashMap<>();
        map.put("token",token);
        map.put("userInfo",userInfo);
        return ResponseUtil.ok(map);

    }




}

