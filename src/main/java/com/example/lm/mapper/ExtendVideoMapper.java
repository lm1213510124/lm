package com.example.lm.mapper;

import com.example.lm.model.Video;
import com.example.lm.provider.VideoProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ExtendVideoMapper {


    @Select("select title,cover_img from video")
    /*@Results({@Result(column = "title",property = "title"),
              @Result(column = "cover_img",property = "coverImg")})*/
    List<Video> getVideoList();

    @Delete("delete from video where id =#{id}")
    int removeVideo(Integer id);

    @UpdateProvider(type = VideoProvider.class,method = "updateVideo")
    int updateVideo(Video video);



}
