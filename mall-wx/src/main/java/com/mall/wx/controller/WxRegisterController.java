package com.mall.wx.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.core.util.IpUtil;
import com.mall.core.util.JacksonUtil;
import com.mall.core.util.ResponseUtil;
import com.mall.db.domain.LitemallUser;
import com.mall.db.entity.vo.UserInfo;
import com.mall.db.service.LitemallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.mall.wx.util.WxResponseCode.*;

/**
 * @Author: 雨同我
 * @Description:
 * @DateTime: 2022/10/9 20:00
 **/
@RestController
@RequestMapping("/wx/auth")
@Validated
public class WxRegisterController {

    @Autowired
    private LitemallUserService userService;
    //	引入邮件接口
    @Resource
    private JavaMailSender javaMailSender;

    //	获得发件人信息
    @Value("${spring.mail.username}")
    private String emailName;
//    邮箱验证码
    static String emailAuth;

    /**
     * @Description: 账号注册
     * @Author: 雨同我
     * @DateTime: 2022/10/9 20:01
    */
    @PostMapping("register")
    public Object register(@RequestBody String body, HttpServletRequest request) {
        String username = JacksonUtil.parseString(body, "username");
        String password = JacksonUtil.parseString(body, "password");
//        验证码
        String code = JacksonUtil.parseString(body, "code");
        String wxCode = JacksonUtil.parseString(body, "wxCode");
        String email = JacksonUtil.parseString(body, "email");

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(email)) {
            return ResponseUtil.badArgument();
        }
        List<LitemallUser> userList = userService.getBaseMapper()
                .selectList(new LambdaQueryWrapper<LitemallUser>().eq(LitemallUser::getUsername,username));
        if (userList.size() > 0) {
            return ResponseUtil.fail(AUTH_NAME_REGISTERED, "用户名已注册");
        }
        List<LitemallUser> emailList = userService.getBaseMapper()
                .selectList(new LambdaQueryWrapper<LitemallUser>().eq(LitemallUser::getEmail,email));
        if (emailList.size() > 0) {
            return ResponseUtil.fail(AUTH_EMAIL_UNREGISTERED, "邮箱已注册");
        }
        if (!emailAuth.equals(code)){
            return ResponseUtil.fail(AUTH_INVALID_MOBILE,"验证码错误");
        }
//        TODO
        String openId = "";
        LitemallUser user = null;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        user = new LitemallUser();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setMobile("test");
        user.setWeixinOpenid(openId);
        user.setAvatar("https://yanxuan.nosdn.127.net/80841d741d7fa3073e0ae27bf487339f.jpg?imageView&quality=90&thumbnail=64x64");
        user.setNickname(username);
        user.setGender(0);
        user.setUserLevel(0);
        user.setStatus(0);
        user.setLastLoginTime(LocalDateTime.now());
        user.setLastLoginIp(IpUtil.getIpAddr(request));
        userService.getBaseMapper().insert(user);
//        TODO  给新用户发送注册优惠券

        // userInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(username);
        userInfo.setAvatarUrl(user.getAvatar());

        StpUtil.login(user.getId());
        String token = StpUtil.getTokenValue();

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", token);
        result.put("userInfo", userInfo);
        return ResponseUtil.ok(result);
    }

    @PostMapping("regCaptcha")
    public Object captcha(@RequestParam String email) {
//        TODO 生产验证码 hutool
        emailAuth = String.valueOf(new Random().nextInt(899999) + 100000);
        try {
            sendAttachmentsMail(emailAuth,email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseUtil.ok("发送成功");
    }


    public void sendAttachmentsMail(String emailAuth,String email){
        //        创建邮件消息
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailName);

        message.setTo(email);

        message.setSubject("[mall]@rain-me验证码是");


        message.setText("尊敬的"+email+",您好:\n"
                + "\n本次请求的邮件验证码为:" + emailAuth + ",本验证码 5 分钟内效，请及时输入。（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封通过自动发送的邮件，请不要直接回复）");

        javaMailSender.send(message);
    }
}
