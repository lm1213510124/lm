package com.example.lm.provider;


import com.example.lm.model.Video;
import org.apache.ibatis.jdbc.SQL;


//参考链接https://www.cnblogs.com/zhangminghui/p/4903351.html
public class VideoProvider {

    public String updateVideo(final Video video){

        return new SQL(){{
            UPDATE("video");

            //条件写法.
            if(video.getId()!= null){
                SET("id=#{id}");
            }
            if(video.getSummary()!= null){
                SET("summary=#{summary}");
            }

            WHERE("id=#{id}");
        }}.toString();
    }


}
