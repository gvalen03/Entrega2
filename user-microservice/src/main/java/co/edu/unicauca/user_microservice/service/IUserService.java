package co.edu.unicauca.user_microservice.service;

import co.edu.unicauca.user_microservice.entity.User;
import co.edu.unicauca.user_microservice.utilities.exception.InvalidUserDataException;
import co.edu.unicauca.user_microservice.utilities.exception.UserAlreadyExistsException;

public interface IUserService {
    User registrarDocente(User user) throws UserAlreadyExistsException, InvalidUserDataException;
    User registrarEstudiante(User user) throws UserAlreadyExistsException, InvalidUserDataException;
    User registrarCoordinador(User user) throws UserAlreadyExistsException, InvalidUserDataException;
    User registrarJefeDepartamento(User user) throws UserAlreadyExistsException, InvalidUserDataException;
    User obtenerPorEmail(String email) throws InvalidUserDataException;
    boolean existeUsuario(String email);
    String obtenerRol(String email);
}