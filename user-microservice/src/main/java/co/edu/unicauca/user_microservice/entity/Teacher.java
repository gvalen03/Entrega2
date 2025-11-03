package co.edu.unicauca.user_microservice.entity;

import co.edu.unicauca.user_microservice.entity.enums.TypeTeacher;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Teacher extends User {
    private TypeTeacher typeTeacher;
}
