package com.example.lm;

import com.example.lm.model.Video;
import com.example.lm.service.VideoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LmApplicationTests {

	@Autowired
	private VideoService videoService;

	@Test
	void contextLoads() {
	}

	@Test
	public void Test1() {

		List<Video> videoList = this.videoService.findVideoList();

		System.out.println(videoList);
	}

}
