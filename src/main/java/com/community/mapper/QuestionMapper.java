package com.community.mapper;

import com.community.dto.QuestionDto;
import com.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,tag,gmt_create,gmt_modified,creator) value(#{title},#{description},#{tag},#{gmtCreate},#{gmtModified},#{creator})")
    public void create(Question question);

    @Select("select * from question order by gmt_create desc limit #{page},#{size}")
    List<Question> selectAll(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer getTotal();

    @Select("select count(1) from question where creator=#{id}")
    Integer getTotalById(@RequestParam(value = "id")Integer id);

    @Select("select * from question where creator=#{id} order by gmt_create desc limit #{page},#{size}")
    List<Question> selectByCreateId(@RequestParam(value = "page")int page, @RequestParam(value = "size")Integer size, @RequestParam(value = "id")Integer id);

    @Select("select * from question where id=#{id}")
    Question selectById(@RequestParam(value = "id")int id);

    @Update("update question set view_count=view_count+1 where id=#{id}")
    void updateView(@RequestParam(value = "id")int id);

    @Update("update question set comment_count=comment_count+1 where id=#{id}")
    void updateComment(@RequestParam(value = "id") Integer commentTo);

    @Select("SELECT * FROM question  WHERE tag REGEXP #{tag} and id!=#{id} limit 20")
    List<Question> selectByTag(@RequestParam(value = "tag") String tag,@RequestParam(value = "id")Integer id);

    @Update("update question set title=#{title},description=#{description},tag=#{tag} where id=#{id}")
    void updateQuestion(@RequestParam(value = "id")Integer id, @RequestParam(value = "title")String title,@RequestParam(value = "description") String description, @RequestParam(value = "tag")String tag);
}
