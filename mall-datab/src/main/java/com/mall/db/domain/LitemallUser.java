package com.mall.db.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Data
  @Accessors(chain = true)
  @TableName("litemall_user")
@ApiModel(value = "LitemallUser对象", description = "用户表")
public class LitemallUser implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("用户名称")
      private String username;

      @ApiModelProperty("用户密码")
      private String password;

      @ApiModelProperty("性别：0 未知， 1男， 1 女")
      private Integer gender;

      @ApiModelProperty("生日")
      private LocalDate birthday;

      @ApiModelProperty("最近一次登录时间")
      private LocalDateTime lastLoginTime;

      @ApiModelProperty("最近一次登录IP地址")
      private String lastLoginIp;

      @ApiModelProperty("0 普通用户，1 VIP用户，2 高级VIP用户")
      private Integer userLevel;

      @ApiModelProperty("用户昵称或网络名称")
      private String nickname;

      @ApiModelProperty("用户手机号码")
      private String mobile;

      @ApiModelProperty("用户头像图片")
      private String avatar;

      @ApiModelProperty("微信登录openid")
      private String weixinOpenid;

      @ApiModelProperty("微信登录会话KEY")
      private String sessionKey;

      @ApiModelProperty("0 可用, 1 禁用, 2 注销")
      private Integer status;

      @ApiModelProperty("创建时间")
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime addTime;

      @ApiModelProperty("更新时间")
      private LocalDateTime updateTime;

      @ApiModelProperty("逻辑删除")
      @TableLogic
    private Boolean deleted;


}
