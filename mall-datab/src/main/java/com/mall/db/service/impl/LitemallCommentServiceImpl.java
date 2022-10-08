package com.mall.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.mall.db.domain.LitemallComment;
import com.mall.db.domain.LitemallUser;
import com.mall.db.mapper.LitemallCommentMapper;
import com.mall.db.mapper.LitemallUserMapper;
import com.mall.db.service.LitemallCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author mall
 * @since 2022-09-29
 */
@Service
public class LitemallCommentServiceImpl extends ServiceImpl<LitemallCommentMapper, LitemallComment> implements LitemallCommentService {
    @Autowired
    LitemallUserMapper userMapper;

    public Map<String, Object> getComment(String goodId){
        Page<LitemallComment> page = new Page<>(1,2);
        LambdaQueryWrapper<LitemallComment> queryWrapper3 = new LambdaQueryWrapper<>();
        queryWrapper3.eq(LitemallComment::getValueId,goodId).eq(LitemallComment::getType,0).orderByDesc(LitemallComment::getAddTime);

        Page<LitemallComment> commentPage = page(page, queryWrapper3);
//        page查询为零， 在同一包下可以
//        System.out.println("总页数"+commentPage.getPages());
//        System.out.println("总记录数"+commentPage.getTotal());

//        commentPage.getRecords().forEach(System.out::println);

        List<LitemallComment> comments = commentPage.getRecords();
        List<Map<String, Object>> commentsVo = new ArrayList<>(comments.size());
        long commentCount = PageInfo.of(comments).getTotal();
//        System.out.println(" ::"+commentCount);
        for (LitemallComment comment : comments) {
            Map<String, Object> c = new HashMap<>();
            c.put("id", comment.getId());
            c.put("addTime", comment.getAddTime());
            c.put("content", comment.getContent());
            c.put("adminContent", comment.getAdminContent());
            LitemallUser user = userMapper.selectById(comment.getUserId());
            c.put("nickname", user == null ? "" : user.getNickname());
            c.put("avatar", user == null ? "" : user.getAvatar());
            c.put("picList", comment.getPicUrls());
            commentsVo.add(c);
//            System.out.println(c.toString());
        }
//        评论的总记录数，评论的数据
        Map<String, Object> map1 = new HashMap<>();
        map1.put("count",commentCount);
        map1.put("data",commentsVo);

        return map1;
    }
}
