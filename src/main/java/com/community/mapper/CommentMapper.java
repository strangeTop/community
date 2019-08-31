package com.community.mapper;

import com.community.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("insert into comment (type,creator,comment_to,comment_text,gmt_create) values(#{type},#{creator},#{commentTo},#{commentText},#{gmtCreate})")
    void insert(Comment comment);

    @Select("select * from comment where type=0 and comment_to=#{id}")
    List<Comment> selectByCommentToQ(@RequestParam(value = "id") int id);

    @Select("select * from comment where type=1 and comment_to=#{id}")
    List<Comment> selectByCommentToC(@RequestParam(value = "id") int id);

    @Update("update comment set comment_count=comment_count+1 where id=#{id}")
    void updateComment(@RequestParam(value = "id") Integer commentTo);
}
