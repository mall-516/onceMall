package com.mall.db.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优惠券信息及规则表
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Getter
@Setter
  @Accessors(chain = true)
  @TableName("litemall_coupon")
@ApiModel(value = "LitemallCoupon对象", description = "优惠券信息及规则表")
public class LitemallCoupon implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("优惠券名称")
      private String name;

      @ApiModelProperty("优惠券介绍，通常是显示优惠券使用限制文字")
      private String desc;

      @ApiModelProperty("优惠券标签，例如新人专用")
      private String tag;

      @ApiModelProperty("优惠券数量，如果是0，则是无限量")
      private Integer total;

      @ApiModelProperty("优惠金额，")
      private BigDecimal discount;

      @ApiModelProperty("最少消费金额才能使用优惠券。")
      private BigDecimal min;

      @ApiModelProperty("用户领券限制数量，如果是0，则是不限制；默认是1，限领一张.")
      private Integer limit;

      @ApiModelProperty("优惠券赠送类型，如果是0则通用券，用户领取；如果是1，则是注册赠券；如果是2，则是优惠券码兑换；")
      private Integer type;

      @ApiModelProperty("优惠券状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架。")
      private Integer status;

      @ApiModelProperty("商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。")
      private Integer goodsType;

      @ApiModelProperty("商品限制值，goods_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合。")
      private String goodsValue;

      @ApiModelProperty("优惠券兑换码")
      private String code;

      @ApiModelProperty("有效时间限制，如果是0，则基于领取时间的有效天数days；如果是1，则start_time和end_time是优惠券有效期；")
      private Integer timeType;

      @ApiModelProperty("基于领取时间的有效天数days。")
      private Integer days;

      @ApiModelProperty("使用券开始时间")
      private LocalDateTime startTime;

      @ApiModelProperty("使用券截至时间")
      private LocalDateTime endTime;

      @ApiModelProperty("创建时间")
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime addTime;

      @ApiModelProperty("更新时间")
      private LocalDateTime updateTime;

      @ApiModelProperty("逻辑删除")
      @TableLogic
    private Boolean deleted;


}
