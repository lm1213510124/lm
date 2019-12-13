package com.example.lm;

import com.example.lm.model.User;
import com.example.lm.model.Video;
import com.example.lm.service.VideoService;
import com.example.lm.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentTest {



    @Test
    public void TestJwt(){

        User user = new User();
        user.setId(88);
        user.setName("linmi");
        user.setHeadImg("www.baidu.com");

        String token = JwtUtils.getJsonWebToken(user);
        System.out.println(token);
    }


    @Test
    public void TestCheckJwt(){

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ4ZGNsYXNzIiwiaWQiOjg4LCJuYW1lIjoibGlubWkiLCJpbWciOiJ3d3cuYmFpZHUuY29tIiwiaWF0IjoxNTc2MjE4MDA5LCJleHAiOjE1NzY4MjI4MDl9.bebhmR-pH5LlpdqxoCl5vBffaf2HVuJH5YpjL1pSmKI";
        Claims claims = JwtUtils.checkJWT(token);
        if(claims != null){
            Integer id = (Integer) claims.get("id");
            String name = (String) claims.get("name");
            String img = (String) claims.get("img");
            System.out.println(id+"---"+name+"----"+img);
        }else {
            System.out.println("非法token");
        }

    }


}
