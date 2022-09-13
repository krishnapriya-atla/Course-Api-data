package io.javabrains.CourseApidata;

import io.javabrains.CourseApidata.topics.Topic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CourseApidataApplicationTests {
	@Value("${topicrestapi.services.url}")
	private String baseUrl;
	@Test
	void testGetTopic() {
		RestTemplate restTemplate=new RestTemplate();
		Topic topic=restTemplate.getForObject(baseUrl+"java", Topic.class);
		assertNotNull(topic);
		assertEquals("JAvA",topic.getName());
	}
	@Test
	public void testCreateTopic() {
		RestTemplate restTemplate=new RestTemplate();
		Topic topic=new Topic();
		topic.setId("Cpp");
		topic.setName("c++");
		topic.setDescription("learn c++");
		restTemplate.postForObject(baseUrl,topic, Topic.class);
		Topic newtopic=restTemplate.getForObject(baseUrl+topic.getId(), Topic.class);
		assertNotNull(newtopic);
		assertNotNull(newtopic.getId());
		assertEquals("c++",newtopic.getName());
	}
	@Test
	void testUpdateTopic() {
		RestTemplate restTemplate=new RestTemplate();
		Topic topic=restTemplate.getForObject(baseUrl+"java", Topic.class);
		topic.setName("JAvA");
		restTemplate.put(baseUrl+"java", topic);
	}

}
