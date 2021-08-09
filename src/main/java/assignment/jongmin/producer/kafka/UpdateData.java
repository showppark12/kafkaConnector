package assignment.jongmin.producer.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateData {
    private Long id;
    private String Name;
    private Integer age;
}
