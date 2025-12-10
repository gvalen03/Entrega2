/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.edu.UserMicroservice.Controller;

import co.unicauca.edu.UserMicroservice.Entity.User;
import co.unicauca.edu.UserMicroservice.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JUANDA
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/Users")
public class UserController {
    private UserService userService;
    
    public UserController(UserService userService){
        this.userService = userService;
    }
    
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente de nuevo mas tarde.\"");
            
        }
    }
    
    @GetMapping("/{Email}")
    public ResponseEntity<?> getOne(@PathVariable String Email){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.findByMail(Email));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se pudo encontrar al usuario.\"");
            
        }
    }
    
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody User User){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.SaveUser(User));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se pudo registrar al usuario.\"");
            
        }
    }
}
