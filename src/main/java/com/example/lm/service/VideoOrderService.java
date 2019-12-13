package com.example.lm.service;

import com.example.lm.model.VideoOrder;

import java.util.List;

public interface VideoOrderService {

    List<VideoOrder> findVideoOrder(Integer id, String nickName);
}
