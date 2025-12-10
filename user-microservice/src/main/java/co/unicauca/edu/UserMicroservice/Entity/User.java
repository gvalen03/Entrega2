/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.edu.UserMicroservice.Entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *
 * @author JUANDA
 */
@Entity
@Table(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    
    @Column(nullable = false, name="Name")
    private String name;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Program-User",
            joinColumns = @JoinColumn(name = "IdUser"),
            inverseJoinColumns = @JoinColumn(name = "IdProgram"))
    private List<Program> programs;
    
    @Column(unique = true, nullable = false, name="Email")
    private String email;
    
    @Column(nullable = false, name="Password")
    private String password;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Rol-User",
            joinColumns = @JoinColumn(name = "IdUser"),
            inverseJoinColumns = @JoinColumn(name = "IdRol"))
    private List<Rol> roles;
    
    @ElementCollection
    @CollectionTable(name = "Proyect-User",
            joinColumns = @JoinColumn(name = "idUser"))
    private List<Long> idProjects;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdFaculty", nullable = false)
    private Faculty faculty;
}
