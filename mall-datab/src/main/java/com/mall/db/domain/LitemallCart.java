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
 * 购物车商品表
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Getter
@Setter
  @Accessors(chain = true)
  @TableName("litemall_cart")
@ApiModel(value = "LitemallCart对象", description = "购物车商品表")
public class LitemallCart implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("用户表的用户ID")
      private Integer userId;

      @ApiModelProperty("商品表的商品ID")
      private Integer goodsId;

      @ApiModelProperty("商品编号")
      private String goodsSn;

      @ApiModelProperty("商品名称")
      private String goodsName;

      @ApiModelProperty("商品货品表的货品ID")
      private Integer productId;

      @ApiModelProperty("商品货品的价格")
      private BigDecimal price;

      @ApiModelProperty("商品货品的数量")
      private Integer number;

      @ApiModelProperty("商品规格值列表，采用JSON数组格式")
      private String specifications;

      @ApiModelProperty("购物车中商品是否选择状态")
      private Boolean checked;

      @ApiModelProperty("商品图片或者商品货品图片")
      private String picUrl;

      @ApiModelProperty("创建时间")
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime addTime;

      @ApiModelProperty("更新时间")
      private LocalDateTime updateTime;

      @ApiModelProperty("逻辑删除")
      @TableLogic
    private Boolean deleted;


}
