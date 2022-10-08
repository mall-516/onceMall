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
 * 团购规则表
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Data
  @Accessors(chain = true)
  @TableName("litemall_groupon_rules")
@ApiModel(value = "LitemallGrouponRules对象", description = "团购规则表")
public class LitemallGrouponRules implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("商品表的商品ID")
      private Integer goodsId;

      @ApiModelProperty("商品名称")
      private String goodsName;

      @ApiModelProperty("商品图片或者商品货品图片")
      private String picUrl;

      @ApiModelProperty("优惠金额")
      private BigDecimal discount;

      @ApiModelProperty("达到优惠条件的人数")
      private Integer discountMember;

      @ApiModelProperty("团购过期时间")
      private LocalDateTime expireTime;

      @ApiModelProperty("团购规则状态，正常上线则0，到期自动下线则1，管理手动下线则2")
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
