package co.edu.unicauca.notification_microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.notification_microservice.entity.FormatoA;

@Repository
public interface FormatoARepository extends JpaRepository<FormatoA, Long> {
}