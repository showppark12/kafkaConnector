package assignment.jongmin.consumer.kafka;

import assignment.jongmin.consumer.domain.ManagedUser;
import assignment.jongmin.consumer.repository.ManagedUserRepository;
import assignment.jongmin.producer.kafka.PublishData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaConsumer {

    private final ManagedUserRepository managedUserRepository;

    @Autowired
    public KafkaConsumer(ManagedUserRepository managedUserRepository) {
        this.managedUserRepository = managedUserRepository;
    }

    @KafkaListener(topics = "user", groupId = "users", containerFactory = "dataListener")
    public void consume(PublishData publishData) {

        ObjectMapper mapper = new ObjectMapper();

        List<ManagedUser> users = mapper.convertValue(
                publishData.getData(),
                new TypeReference<>() {
                });

        for (ManagedUser u : users) {
            ManagedUser user = new ManagedUser();
            user.setName(u.getName());
            user.setAge(u.getAge());
            managedUserRepository.save(user);
        }
    }
}
