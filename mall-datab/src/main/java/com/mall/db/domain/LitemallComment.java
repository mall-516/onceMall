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
 * 评论表
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Data
  @Accessors(chain = true)
  @TableName("litemall_comment")
@ApiModel(value = "LitemallComment对象", description = "评论表")
public class LitemallComment implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("如果type=0，则是商品评论；如果是type=1，则是专题评论。")
      private Integer valueId;

      @ApiModelProperty("评论类型，如果type=0，则是商品评论；如果是type=1，则是专题评论；")
      private Integer type;

      @ApiModelProperty("评论内容")
      private String content;

      @ApiModelProperty("管理员回复内容")
      private String adminContent;

      @ApiModelProperty("用户表的用户ID")
      private Integer userId;

      @ApiModelProperty("是否含有图片")
      private Boolean hasPicture;

      @ApiModelProperty("图片地址列表，采用JSON数组格式")
      private String picUrls;

      @ApiModelProperty("评分， 1-5")
      private Integer star;

      @ApiModelProperty("创建时间")
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime addTime;

      @ApiModelProperty("更新时间")
      private LocalDateTime updateTime;

      @ApiModelProperty("逻辑删除")
      @TableLogic
    private Boolean deleted;


}
