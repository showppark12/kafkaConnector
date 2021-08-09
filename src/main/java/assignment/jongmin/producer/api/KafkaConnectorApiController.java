package assignment.jongmin.producer.api;

import assignment.jongmin.producer.domain.User;
import assignment.jongmin.producer.kafka.KafkaProducer;
import assignment.jongmin.producer.kafka.UpdateData;
import assignment.jongmin.producer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KafkaConnectorApiController {
    private final KafkaProducer producer;
    private final UserService userService;

    @Autowired
    public KafkaConnectorApiController(KafkaProducer producer, UserService userService) {
        this.producer = producer;
        this.userService = userService;
    }

    @GetMapping("api/kafka/v1/users")
    public List<UpdateData> publishUsers() {
        List<User> userList = userService.findUsersByStatus("N");
        List<UpdateData> updateData = this.producer.sendData(userList);
        for (User user : userList) {
            userService.updateStatusDateTime(user.getId());
        }
        return updateData;
    }
}
