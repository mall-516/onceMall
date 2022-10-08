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
 * 售后表
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Getter
@Setter
  @Accessors(chain = true)
  @TableName("litemall_aftersale")
@ApiModel(value = "LitemallAftersale对象", description = "售后表")
public class LitemallAftersale implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("售后编号")
      private String aftersaleSn;

      @ApiModelProperty("订单ID")
      private Integer orderId;

      @ApiModelProperty("用户ID")
      private Integer userId;

      @ApiModelProperty("售后类型，0是未收货退款，1是已收货（无需退货）退款，2用户退货退款")
      private Integer type;

      @ApiModelProperty("退款原因")
      private String reason;

      @ApiModelProperty("退款金额")
      private BigDecimal amount;

      @ApiModelProperty("退款凭证图片链接数组")
      private String pictures;

      @ApiModelProperty("退款说明")
      private String comment;

      @ApiModelProperty("售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消")
      private Integer status;

      @ApiModelProperty("管理员操作时间")
      private LocalDateTime handleTime;

      @ApiModelProperty("添加时间")
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime addTime;

      @ApiModelProperty("更新时间")
      private LocalDateTime updateTime;

      @ApiModelProperty("逻辑删除")
      @TableLogic
    private Boolean deleted;


}
