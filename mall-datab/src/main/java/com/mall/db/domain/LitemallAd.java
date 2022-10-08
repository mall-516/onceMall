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
 * 广告表
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Getter
@Setter
  @Accessors(chain = true)
  @TableName("litemall_ad")
@ApiModel(value = "LitemallAd对象", description = "广告表")
public class LitemallAd implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("广告标题")
      private String name;

      @ApiModelProperty("所广告的商品页面或者活动页面链接地址")
      private String link;

      @ApiModelProperty("广告宣传图片")
      private String url;

      @ApiModelProperty("广告位置：1则是首页")
      private Integer position;

      @ApiModelProperty("活动内容")
      private String content;

      @ApiModelProperty("广告开始时间")
      private LocalDateTime startTime;

      @ApiModelProperty("广告结束时间")
      private LocalDateTime endTime;

      @ApiModelProperty("是否启动")
      private Boolean enabled;

      @ApiModelProperty("创建时间")
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime addTime;

      @ApiModelProperty("更新时间")
      private LocalDateTime updateTime;

      @ApiModelProperty("逻辑删除")
      @TableLogic
    private Boolean deleted;


}
