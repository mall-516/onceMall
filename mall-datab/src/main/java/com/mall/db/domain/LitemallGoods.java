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
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品基本信息表
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Data
  @Accessors(chain = true)
  @TableName("litemall_goods")
@ApiModel(value = "LitemallGoods对象", description = "商品基本信息表")
public class LitemallGoods implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("商品编号")
      private String goodsSn;

      @ApiModelProperty("商品名称")
      private String name;

      @ApiModelProperty("商品所属类目ID")
      private Integer categoryId;

    private Integer brandId;

      @ApiModelProperty("商品宣传图片列表，采用JSON数组格式")
      private String gallery;

      @ApiModelProperty("商品关键字，采用逗号间隔")
      private String keywords;

      @ApiModelProperty("商品简介")
      private String brief;

      @ApiModelProperty("是否上架")
      private Boolean isOnSale;

    private Integer sortOrder;

      @ApiModelProperty("商品页面商品图片")
      private String picUrl;

      @ApiModelProperty("商品分享海报")
      private String shareUrl;

      @ApiModelProperty("是否新品首发，如果设置则可以在新品首发页面展示")
      private Boolean isNew;

      @ApiModelProperty("是否人气推荐，如果设置则可以在人气推荐页面展示")
      private Boolean isHot;

      @ApiModelProperty("商品单位，例如件、盒")
      private String unit;

      @ApiModelProperty("专柜价格")
      private BigDecimal counterPrice;

      @ApiModelProperty("零售价格")
      private BigDecimal retailPrice;

      @ApiModelProperty("商品详细介绍，是富文本格式")
      private String detail;

      @ApiModelProperty("创建时间")
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime addTime;

      @ApiModelProperty("更新时间")
      private LocalDateTime updateTime;

      @ApiModelProperty("逻辑删除")
      @TableLogic
    private Boolean deleted;


}
