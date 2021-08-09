package assignment.jongmin.producer.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PublishData<T> {
    private T data;
    public PublishData() {
    }
}
