package io.javabrains.CourseApidata.topics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicrepository;
    public List<Topic> getAllTopics(){
        List<Topic> topics=new ArrayList<>();
        topicrepository.findAll().forEach(topics::add);
        return topics;
    }

    @Cacheable("Topic-cache")
    @Transactional(readOnly = true)
    public Topic getTopic(String id) {
        return topicrepository.findById(id).get();
    }
    public Topic addTopic(Topic topic) {
        topicrepository.save(topic);
        return topic;
    }
    public Topic updateTopics(/*String id,*/ Topic topic) {
        topicrepository.save(topic);
        return topic;
    }
    /*public void updateTopic(String id, Topic topic) {
        topicrepository.save(topic);
    }*/
   @CacheEvict("Topic-cache")
    public void deleteTopic(String id) {
        topicrepository.deleteById(id);
    }
}
