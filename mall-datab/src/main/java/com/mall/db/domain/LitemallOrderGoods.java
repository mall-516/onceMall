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
 * 订单商品表
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Getter
@Setter
  @Accessors(chain = true)
  @TableName("litemall_order_goods")
@ApiModel(value = "LitemallOrderGoods对象", description = "订单商品表")
public class LitemallOrderGoods implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("订单表的订单ID")
      private Integer orderId;

      @ApiModelProperty("商品表的商品ID")
      private Integer goodsId;

      @ApiModelProperty("商品名称")
      private String goodsName;

      @ApiModelProperty("商品编号")
      private String goodsSn;

      @ApiModelProperty("商品货品表的货品ID")
      private Integer productId;

      @ApiModelProperty("商品货品的购买数量")
      private Integer number;

      @ApiModelProperty("商品货品的售价")
      private BigDecimal price;

      @ApiModelProperty("商品货品的规格列表")
      private String specifications;

      @ApiModelProperty("商品货品图片或者商品图片")
      private String picUrl;

      @ApiModelProperty("订单商品评论，如果是-1，则超期不能评价；如果是0，则可以评价；如果其他值，则是comment表里面的评论ID。")
      private Integer comment;

      @ApiModelProperty("创建时间")
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime addTime;

      @ApiModelProperty("更新时间")
      private LocalDateTime updateTime;

      @ApiModelProperty("逻辑删除")
      @TableLogic
    private Boolean deleted;


}
