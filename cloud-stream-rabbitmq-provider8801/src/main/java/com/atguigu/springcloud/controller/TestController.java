package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.didi.TestTopic;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *@project:
 *@description:
 *@author:zhangwenqiang
 *@date: 2020/4/27
 */
@RestController
@Slf4j
@EnableBinding(TestTopic.class)
public class TestController {

	@Autowired
	TestTopic testTopic;

	@GetMapping("/sendMessage")
	public String messageWithMQ(@RequestParam String message) {
		log.info("发送消息{}", message);
		testTopic.output().send(MessageBuilder.withPayload(message).build());
		return "ok";
	}
}
