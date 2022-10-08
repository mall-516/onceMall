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
 * 优惠券用户使用表
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Getter
@Setter
  @Accessors(chain = true)
  @TableName("litemall_coupon_user")
@ApiModel(value = "LitemallCouponUser对象", description = "优惠券用户使用表")
public class LitemallCouponUser implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("用户ID")
      private Integer userId;

      @ApiModelProperty("优惠券ID")
      private Integer couponId;

      @ApiModelProperty("使用状态, 如果是0则未使用；如果是1则已使用；如果是2则已过期；如果是3则已经下架；")
      private Integer status;

      @ApiModelProperty("使用时间")
      private LocalDateTime usedTime;

      @ApiModelProperty("有效期开始时间")
      private LocalDateTime startTime;

      @ApiModelProperty("有效期截至时间")
      private LocalDateTime endTime;

      @ApiModelProperty("订单ID")
      private Integer orderId;

      @ApiModelProperty("创建时间")
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime addTime;

      @ApiModelProperty("更新时间")
      private LocalDateTime updateTime;

      @ApiModelProperty("逻辑删除")
      @TableLogic
    private Boolean deleted;


}
