package assignment.jongmin.producer.kafka;

import assignment.jongmin.producer.domain.User;
import assignment.jongmin.producer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KafkaProducer {
    private static final String TOPIC = "user";
    private final KafkaTemplate<String, PublishData> kafkaTemplate;
    private final UserService userService;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, PublishData> kafkaTemplate, UserService userService) {
        this.kafkaTemplate = kafkaTemplate;
        this.userService = userService;
    }

    public List<UpdateData> sendData(List<User> userList){
        List<UpdateData> collect = userList.stream()
                .map(u -> new UpdateData(
                        u.getId(),
                        u.getName(),
                        u.getAge()
                )).collect(Collectors.toList());
        PublishData publishData = new PublishData(collect);
        kafkaTemplate.send(TOPIC, publishData);
        return collect;
    }


}
