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
 * 团购活动表
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Getter
@Setter
  @Accessors(chain = true)
  @TableName("litemall_groupon")
@ApiModel(value = "LitemallGroupon对象", description = "团购活动表")
public class LitemallGroupon implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("关联的订单ID")
      private Integer orderId;

      @ApiModelProperty("如果是开团用户，则groupon_id是0；如果是参团用户，则groupon_id是团购活动ID")
      private Integer grouponId;

      @ApiModelProperty("团购规则ID，关联litemall_groupon_rules表ID字段")
      private Integer rulesId;

      @ApiModelProperty("用户ID")
      private Integer userId;

      @ApiModelProperty("团购分享图片地址")
      private String shareUrl;

      @ApiModelProperty("开团用户ID")
      private Integer creatorUserId;

      @ApiModelProperty("开团时间")
      private LocalDateTime creatorUserTime;

      @ApiModelProperty("团购活动状态，开团未支付则0，开团中则1，开团失败则2")
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
