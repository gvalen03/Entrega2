package co.edu.unicauca.notification_microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.unicauca.notification_microservice.entity.Evaluacion;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long>{

}
