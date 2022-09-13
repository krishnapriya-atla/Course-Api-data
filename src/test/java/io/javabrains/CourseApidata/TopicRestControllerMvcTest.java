package io.javabrains.CourseApidata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hazelcast.com.fasterxml.jackson.jr.ob.JSON;
import io.javabrains.CourseApidata.topics.Topic;
import io.javabrains.CourseApidata.topics.TopicRepository;
import io.javabrains.CourseApidata.topics.TopicService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TopicRestControllerMvcTest {
    private static final String TOPIC_URL = "/topicapi/topics/";

    private static final String CONTEXT_URL = "/topicapi";

    private static final String TOPIC_DESCRIPTION_STRING = "java des";

    private static final String TOPIC_NAME_STRING  = "java lang";

    private static final String TOPIC_ID = "java";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicRepository topicrepository;

    @Test
    public void testFindAll() throws Exception {
        Topic topic = buildTopic();
        List<Topic> topics = Arrays.asList(topic);
        when(topicrepository.findAll()).thenReturn(topics);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        mockMvc.perform(get(TOPIC_URL).
                contextPath(CONTEXT_URL)).andExpect(status().isOk()).andExpect(content()
                .json(objectWriter.writeValueAsString(topics)));
    }
    @Test
    public void testFindTopic() throws JsonProcessingException, Exception {
        Topic topic = buildTopic();
        doReturn(Optional.of(topic)).when(topicrepository).findById(TOPIC_ID);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(get(TOPIC_URL+TOPIC_ID).
                        contextPath(CONTEXT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectWriter.writeValueAsString(topic)))
                .andExpect(status().isOk()).andExpect(content()
                        .json(objectWriter.writeValueAsString(topic)));
    }
    @Test
    public void testCreateTopic() throws Exception {
        Topic topic = buildTopic();
        when(topicrepository.save(any())).thenReturn(topic);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TOPIC_URL).
                        contextPath(CONTEXT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectWriter.writeValueAsString(topic)))
                .andExpect(status().isOk()).andExpect(content()
                        .json(objectWriter.writeValueAsString(topic)));
    }
    @Test
    public void testUpdateTopic() throws JsonProcessingException, Exception {
        Topic topic = buildTopic();
        topic.setDescription("java description");
        when(topicrepository.save(any())).thenReturn(topic);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(put(TOPIC_URL).
                        contextPath(CONTEXT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectWriter.writeValueAsString(topic)))
                .andExpect(status().isOk()).andExpect(content()
                        .json(objectWriter.writeValueAsString(topic)));
    }
    @Test
    public void testDeleteTopic() throws Exception {
        doNothing().when(topicrepository).deleteById(TOPIC_ID);
        mockMvc.perform(delete(TOPIC_URL+TOPIC_ID).contextPath(CONTEXT_URL)).andExpect(status().isOk());
    }
    private Topic buildTopic() {
        Topic topic=new Topic();
        topic.setId(TOPIC_ID);
        topic.setName(TOPIC_NAME_STRING);
        topic.setDescription(TOPIC_DESCRIPTION_STRING);
        return topic;
    }

}
