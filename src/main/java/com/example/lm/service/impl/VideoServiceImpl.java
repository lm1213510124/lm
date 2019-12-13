package com.example.lm.service.impl;

import com.example.lm.mapper.ExtendVideoMapper;
import com.example.lm.mapper.VideoMapper;
import com.example.lm.model.Video;
import com.example.lm.model.VideoExample;
import com.example.lm.service.VideoOrderService;
import com.example.lm.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;


    @Autowired
    private ExtendVideoMapper extendVideoMapper;

    @Override
    public List<Video> findVideoList() {
        VideoExample example = new VideoExample();
        VideoExample.Criteria criteria = example.createCriteria();

        List<Video> videos = this.videoMapper.selectByExample(example);
        return videos;
    }

    @Override
    public List<Video> getVideoList() throws Exception {
        try {
            List<Video> videoList = this.extendVideoMapper.getVideoList();
            return videoList;
        } catch (Exception e){
            throw new Exception("---空指针异常---");
        }
    }

    @Override
    public int removeVideo(Integer id) {
        return this.extendVideoMapper.removeVideo(id);
    }

    @Override
    public int updateVideo(Map<String,Object> map) {
        Integer id = (Integer) map.get("id");
        String summary = (String) map.get("summary");

        Video video = new Video();
        video.setId(id);
        video.setSummary(summary);
        return this.extendVideoMapper.updateVideo(video);
    }

}
