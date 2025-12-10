/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.edu.UserMicroservice.Services;

import co.unicauca.edu.UserMicroservice.Entity.User;
import co.unicauca.edu.UserMicroservice.Entity.Rol;
import co.unicauca.edu.UserMicroservice.Repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author JUANDA
 */
@Service
public class UserService implements IUserService{
    
    private UserRepository UserRepository;
    
    public UserService(UserRepository UserRepository){
        this.UserRepository = UserRepository;
    }
    
    @Override
    @Transactional
    public Long getId(String Email){
        return UserRepository.findByEmail(Email).map(User::getIdUser)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con Email: " + Email));
    }

    @Override
    @Transactional
    public List<User> getAll(){
        return UserRepository.findAll();
    }

    @Override
    @Transactional
    public User findByMail(String Email){
        return UserRepository.findByEmail(Email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con Email: " + Email));
    }

    @Override
    @Transactional
    public User SaveUser(User User){
        User = UserRepository.save(User);
        return User;
    }

    @Override
    @Transactional
    public boolean UserExists(String Email){
        return UserRepository.existsByEmail(Email);
    }

    @Override
    @Transactional
    public List<String> getRol(String Email) throws Exception {
        return UserRepository.findByEmail(Email)
                .map(User -> User.getRoles().stream().map(Rol::getRolName).toList())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el Email: " + Email));
        
    }
    
}
