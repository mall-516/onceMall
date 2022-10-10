package com.mall.wx.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.mall.core.util.ResponseUtil;
import com.mall.db.domain.LitemallUser;
import com.mall.db.service.impl.LitemallUserServiceImpl;
import com.mall.db.entity.vo.UserInfo;
import com.mall.db.entity.vo.WxUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/auth")
public class LitemallUserController {

    @Autowired
    private LitemallUserServiceImpl litemallUserService;

    /**
     *
     * @param body  {
     *     "username":"admin",
     *     "password":"123456"
     * }
     * @return
     */

    @GetMapping("/login")
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
     * 1.获取code，判断是否为空，从wxService.getUserService().getSessionInfo(code);方法获取openid和sessionkey
     * 2. 保存新用户，给新用户发优惠券
     * 3. 生成token 返回token和userInfo
     *
     */

    @GetMapping("/login_by_weixin")
    public Object WX_login(@RequestBody WxUserInfo wxUserInfo){

        String code = wxUserInfo.getCode();
        UserInfo userInfo = wxUserInfo.getUserInfo();
        if (Objects.isNull(code)||Objects.isNull(userInfo)){
            return ResponseUtil.badArgument();
        }

        return null;


    }






}

