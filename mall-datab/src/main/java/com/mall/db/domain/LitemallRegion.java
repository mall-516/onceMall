package com.mall.db.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 行政区域表
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Getter
@Setter
  @Accessors(chain = true)
  @TableName("litemall_region")
@ApiModel(value = "LitemallRegion对象", description = "行政区域表")
public class LitemallRegion implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("行政区域父ID，例如区县的pid指向市，市的pid指向省，省的pid则是0")
      private Integer pid;

      @ApiModelProperty("行政区域名称")
      private String name;

      @ApiModelProperty("行政区域类型，如如1则是省， 如果是2则是市，如果是3则是区县")
      private Integer type;

      @ApiModelProperty("行政区域编码")
      private Integer code;


}
