package co.edu.unicauca.user_microservice.service;

import co.edu.unicauca.user_microservice.entity.*;
import co.edu.unicauca.user_microservice.repository.UserRepository;
import co.edu.unicauca.user_microservice.utilities.exception.InvalidUserDataException;
import co.edu.unicauca.user_microservice.utilities.exception.UserAlreadyExistsException;
import co.edu.unicauca.user_microservice.utilities.security.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registrarDocente(User user) throws UserAlreadyExistsException, InvalidUserDataException {
        validarUsuario(user);
        return userRepository.save(user);
    }

    @Override
    public User registrarEstudiante(User user) throws UserAlreadyExistsException, InvalidUserDataException {
        validarUsuario(user);
        return userRepository.save(user);
    }

    @Override
    public User registrarCoordinador(User user) throws UserAlreadyExistsException, InvalidUserDataException {
        validarUsuario(user);
        return userRepository.save(user);
    }

    @Override
    public User registrarJefeDepartamento(User user) throws UserAlreadyExistsException, InvalidUserDataException {
        validarUsuario(user);
        return userRepository.save(user);
    }

    @Override
    public User obtenerPorEmail(String email) throws InvalidUserDataException {
        return userRepository.findById(email)
                .orElseThrow(() -> new InvalidUserDataException("Usuario no encontrado."));
    }

    @Override
    public boolean existeUsuario(String email) {
        return userRepository.existsById(email);
    }

    @Override
    public String obtenerRol(String email) {
        return userRepository.findById(email)
                .map(usuario -> {
                    if (usuario instanceof Teacher) return "DOCENTE";
                    if (usuario instanceof Student) return "ESTUDIANTE";
                    if (usuario instanceof Coordinator) return "COORDINADOR";
                    if (usuario instanceof DepartmentHead) return "JEFE_DEPARTAMENTO";
                    return "DESCONOCIDO";
                })
                .orElse("DESCONOCIDO");
    }

    private void validarUsuario(User user) throws InvalidUserDataException, UserAlreadyExistsException {
        if (user.getEmail() == null || !user.getEmail().endsWith("@unicauca.edu.co")) {
            throw new InvalidUserDataException("El email debe ser del dominio @unicauca.edu.co");
        }
        if (!PasswordValidator.isValid(user.getPassword())) {
            throw new InvalidUserDataException("La contraseña no cumple con los requisitos.");
        }
        if (userRepository.existsById(user.getEmail())) {
            throw new UserAlreadyExistsException("El usuario ya existe.");
        }
    }
}