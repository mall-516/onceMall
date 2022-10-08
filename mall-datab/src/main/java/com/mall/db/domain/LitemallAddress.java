package com.mall.db.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 收货地址表
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Getter
@Setter
  @Accessors(chain = true)
  @TableName("litemall_address")
@ApiModel(value = "LitemallAddress对象", description = "收货地址表")
public class LitemallAddress implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("收货人名称")
      private String name;

      @ApiModelProperty("用户表的用户ID")
      private Integer userId;

      @ApiModelProperty("行政区域表的省ID")
      private String province;

      @ApiModelProperty("行政区域表的市ID")
      private String city;

      @ApiModelProperty("行政区域表的区县ID")
      private String county;

      @ApiModelProperty("详细收货地址")
      private String addressDetail;

      @ApiModelProperty("地区编码")
      private String areaCode;

      @ApiModelProperty("邮政编码")
      private String postalCode;

      @ApiModelProperty("手机号码")
      private String tel;

      @ApiModelProperty("是否默认地址")
      private Boolean isDefault;

      @ApiModelProperty("创建时间")
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime addTime;

      @ApiModelProperty("更新时间")
      private LocalDateTime updateTime;

      @ApiModelProperty("逻辑删除")
      @TableLogic
    private Boolean deleted;


}
