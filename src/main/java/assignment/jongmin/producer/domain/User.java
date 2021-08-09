package assignment.jongmin.producer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Setter @Getter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;

    private Integer age;

    private String status;

    private LocalDateTime updated_dt;

}
