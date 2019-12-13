package com.example.lm.controller;

import com.example.lm.config.TestConfig;
import com.example.lm.model.Video;
import com.example.lm.model.VideoOrder;
import com.example.lm.service.VideoOrderService;
import com.example.lm.service.VideoService;
import com.example.lm.vo.JsonData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VideoOrderService videoOrderService;

    @Autowired
    private TestConfig testConfig;



    @GetMapping("/index")
    @ResponseBody
    public String loginTest (){
        System.out.println(testConfig.getAppId());
        return testConfig.getAppId();
    }




    @RequestMapping(value = "/findVideoList",method = RequestMethod.GET)
    @ResponseBody
    public JsonData findVideoList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                  @RequestParam(value = "size",defaultValue = "5") Integer size){

        PageHelper.startPage(page,size);
        List<Video> videoList = videoService.findVideoList();
        PageInfo<Video> pageInfo = new PageInfo<>(videoList);
        Map<String,Object> data = new HashMap<>();
        data.put("total_size",pageInfo.getTotal());//总条数
        data.put("total_page",pageInfo.getPages());//总页数
        data.put("current_page",page);//当前页
        data.put("data",pageInfo.getList());//数据


        return JsonData.buildSuccess(data,"操作成功");
    }


    @RequestMapping(value = "/findVideoOrder",method = RequestMethod.POST)
    @ResponseBody
    public JsonData findVideoOrder (@RequestParam(value = "id",defaultValue = "") Integer id,
                                  @RequestParam(value = "nickName",defaultValue = "小C") String nickName){


        List<VideoOrder> videoOrders = this.videoOrderService.findVideoOrder(id,nickName);

        return JsonData.buildSuccess(videoOrders,"操作成功");
    }

}
