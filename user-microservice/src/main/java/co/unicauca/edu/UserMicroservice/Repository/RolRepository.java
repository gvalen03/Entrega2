/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.edu.UserMicroservice.Repository;

import co.unicauca.edu.UserMicroservice.Entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author JUANDA
 */
public interface RolRepository extends JpaRepository<Rol, Long>{
    
}
