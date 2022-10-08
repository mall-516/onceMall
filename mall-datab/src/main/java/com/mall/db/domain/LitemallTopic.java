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
 * 专题表
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Getter
@Setter
  @Accessors(chain = true)
  @TableName("litemall_topic")
@ApiModel(value = "LitemallTopic对象", description = "专题表")
public class LitemallTopic implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("专题标题")
      private String title;

      @ApiModelProperty("专题子标题")
      private String subtitle;

      @ApiModelProperty("专题内容，富文本格式")
      private String content;

      @ApiModelProperty("专题相关商品最低价")
      private BigDecimal price;

      @ApiModelProperty("专题阅读量")
      private String readCount;

      @ApiModelProperty("专题图片")
      private String picUrl;

      @ApiModelProperty("排序")
      private Integer sortOrder;

      @ApiModelProperty("专题相关商品，采用JSON数组格式")
      private String goods;

      @ApiModelProperty("创建时间")
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime addTime;

      @ApiModelProperty("更新时间")
      private LocalDateTime updateTime;

      @ApiModelProperty("逻辑删除")
      @TableLogic
    private Boolean deleted;


}
