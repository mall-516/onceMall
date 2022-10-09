package com.mall.db.entity.vo;

import com.mall.db.domain.LitemallGoodsSpecification;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: hu chang
 * Date: 2022/9/29
 * Time: 16:00
 * Description:
 */
@Data
public class GoodsSpecificationVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private List<LitemallGoodsSpecification> valueList;
}
