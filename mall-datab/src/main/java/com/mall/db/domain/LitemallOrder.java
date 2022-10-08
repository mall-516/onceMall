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
 * 订单表
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Getter
@Setter
  @Accessors(chain = true)
  @TableName("litemall_order")
@ApiModel(value = "LitemallOrder对象", description = "订单表")
public class LitemallOrder implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("用户表的用户ID")
      private Integer userId;

      @ApiModelProperty("订单编号")
      private String orderSn;

      @ApiModelProperty("订单状态")
      private Integer orderStatus;

      @ApiModelProperty("售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消")
      private Integer aftersaleStatus;

      @ApiModelProperty("收货人名称")
      private String consignee;

      @ApiModelProperty("收货人手机号")
      private String mobile;

      @ApiModelProperty("收货具体地址")
      private String address;

      @ApiModelProperty("用户订单留言")
      private String message;

      @ApiModelProperty("商品总费用")
      private BigDecimal goodsPrice;

      @ApiModelProperty("配送费用")
      private BigDecimal freightPrice;

      @ApiModelProperty("优惠券减免")
      private BigDecimal couponPrice;

      @ApiModelProperty("用户积分减免")
      private BigDecimal integralPrice;

      @ApiModelProperty("团购优惠价减免")
      private BigDecimal grouponPrice;

      @ApiModelProperty("订单费用， = goods_price + freight_price - coupon_price")
      private BigDecimal orderPrice;

      @ApiModelProperty("实付费用， = order_price - integral_price")
      private BigDecimal actualPrice;

      @ApiModelProperty("微信付款编号")
      private String payId;

      @ApiModelProperty("微信付款时间")
      private LocalDateTime payTime;

      @ApiModelProperty("发货编号")
      private String shipSn;

      @ApiModelProperty("发货快递公司")
      private String shipChannel;

      @ApiModelProperty("发货开始时间")
      private LocalDateTime shipTime;

      @ApiModelProperty("实际退款金额，（有可能退款金额小于实际支付金额）")
      private BigDecimal refundAmount;

      @ApiModelProperty("退款方式")
      private String refundType;

      @ApiModelProperty("退款备注")
      private String refundContent;

      @ApiModelProperty("退款时间")
      private LocalDateTime refundTime;

      @ApiModelProperty("用户确认收货时间")
      private LocalDateTime confirmTime;

      @ApiModelProperty("待评价订单商品数量")
      private Integer comments;

      @ApiModelProperty("订单关闭时间")
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
