package io.javabrains.CourseApidata.topics;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "Topic Rest Endpoint")
public class TopicController {

    @Autowired
    private TopicService topicService;
    @GetMapping("/topics")
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }


    @GetMapping("/topics/{id}")
    @Operation(summary = "Returns a Tiopic",description = "Takes id returns single Topic")
    public @ApiResponse(description = "Id of Topic") Topic getTopic(@Parameter(description = "Id of Topic")@PathVariable String id) {
        return topicService.getTopic(id);
    }
    @PostMapping(value="/topics")
    public Topic addTopic(@Valid @RequestBody Topic topic) {

        return topicService.addTopic(topic);
    }
    @PutMapping(value="/topics")
    public Topic updateTopics(@RequestBody Topic topic/*,@PathVariable String id*/) {

        return topicService.updateTopics(/*id,*/topic);
    }

    @DeleteMapping(value="/topics/{id}")
    @Hidden
    public void deleteTopic(@PathVariable String id) {
        topicService.deleteTopic(id);
    }
    /*
    @PatchMapping(value="/topics/{id}")
    public void updateTopic(@RequestBody Topic topic,@PathVariable String id) {

        topicService.updateTopic(id,topic);
    }*/
}
