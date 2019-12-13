package com.example.lm.service;

import com.example.lm.model.Video;

import java.util.List;
import java.util.Map;

public interface VideoService {

    List<Video> findVideoList();


    List<Video> getVideoList() throws Exception;

    int removeVideo(Integer id);

    int updateVideo(Map<String,Object> map);
}
