/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.unicauca.edu.UserMicroservice.Repository;

import co.unicauca.edu.UserMicroservice.Entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author JUANDA
 */
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String Email);
    boolean existsByEmail(String Email);
}
