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
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品参数表
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Data
  @Accessors(chain = true)
  @TableName("litemall_goods_attribute")
@ApiModel(value = "LitemallGoodsAttribute对象", description = "商品参数表")
public class LitemallGoodsAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("商品表的商品ID")
      private Integer goodsId;

      @ApiModelProperty("商品参数名称")
      private String attribute;

      @ApiModelProperty("商品参数值")
      private String value;

      @ApiModelProperty("创建时间")
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime addTime;

      @ApiModelProperty("更新时间")
      private LocalDateTime updateTime;

      @ApiModelProperty("逻辑删除")
      @TableLogic
    private Boolean deleted;


}
