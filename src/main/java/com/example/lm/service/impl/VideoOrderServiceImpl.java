package com.example.lm.service.impl;

import com.example.lm.mapper.VideoOrderMapper;
import com.example.lm.model.VideoOrder;
import com.example.lm.model.VideoOrderExample;
import com.example.lm.service.VideoOrderService;
import com.example.lm.utils.StringUtil;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Override
    public List<VideoOrder> findVideoOrder(Integer id, String nickName) {

        VideoOrderExample example = new VideoOrderExample();
        VideoOrderExample.Criteria criteria = example.createCriteria();

        if(StringUtil.isNotBlank(nickName)){
            criteria.andNicknameEqualTo(nickName);
        }

        if(id != null ){
            criteria.andIdEqualTo(id);
        }


        List<VideoOrder> videoOrders = this.videoOrderMapper.selectByExample(example);
        return videoOrders;
    }

}
