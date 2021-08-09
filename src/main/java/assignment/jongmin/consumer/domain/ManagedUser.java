package assignment.jongmin.consumer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class ManagedUser {

    @Id
    @GeneratedValue
    @Column(name = "manageduser_id")
    private Long id;

    private String name;

    private Integer age;

}