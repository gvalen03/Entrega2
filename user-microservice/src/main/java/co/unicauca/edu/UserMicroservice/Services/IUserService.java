/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.edu.UserMicroservice.Services;

import co.unicauca.edu.UserMicroservice.Entity.User;
import java.util.List;

/**
 *Interface that acts as facade to get user related services externally
 * @author JUANDA
 * 
 */
public interface IUserService {
    public Long getId(String Mail) throws Exception;
    public List<User> getAll();
    public User findByMail(String Email);
    public User SaveUser(User User);
    public boolean UserExists(String Email);
    public List<String> getRol(String Email) throws Exception;
}
