package com.example.lm.controller;

import com.example.lm.model.Video;
import com.example.lm.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestExtendController {

    @Autowired
    private VideoService videoService;


    @GetMapping("/getVideoList")
    @ResponseBody
    public Object getVideoList () throws Exception {
        return videoService.getVideoList();
    }

    @GetMapping("/removeVideo")
    @ResponseBody
    public Object removeVideo (@RequestParam(value = "id",defaultValue = "") Integer id) {
        return videoService.removeVideo(id);
    }


    @RequestMapping(value = "/updateVideo",method = RequestMethod.POST)
    @ResponseBody
    public Object updateVideo (@RequestParam(value = "id",defaultValue = "") Integer id,
                               @RequestParam(value = "summary",defaultValue = "") String summary) {

        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("summary",summary);


        return videoService.updateVideo(map);
    }
}
