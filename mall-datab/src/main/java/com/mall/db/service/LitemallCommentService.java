package com.mall.db.service;

import com.mall.db.domain.LitemallComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
public interface LitemallCommentService extends IService<LitemallComment> {
    public Map<String, Object> getComment(String goodId);
}
