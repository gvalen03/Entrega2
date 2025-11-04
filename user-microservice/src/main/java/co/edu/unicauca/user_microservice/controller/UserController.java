package co.edu.unicauca.user_microservice.controller;

import co.edu.unicauca.user_microservice.entity.Coordinator;
import co.edu.unicauca.user_microservice.entity.Student;
import co.edu.unicauca.user_microservice.entity.Teacher;
import co.edu.unicauca.user_microservice.entity.DepartmentHead;
import co.edu.unicauca.user_microservice.infra.dto.CoordinatorRequest;
import co.edu.unicauca.user_microservice.infra.dto.TeacherRequest;
import co.edu.unicauca.user_microservice.infra.dto.StudentRequest;
import co.edu.unicauca.user_microservice.infra.dto.DepartmentHeadRequest;
import co.edu.unicauca.user_microservice.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Controlador REST para gestionar el registro y consulta de usuarios.
 * Soporta los roles: Docente, Estudiante, Coordinador y Jefe de Departamento.
 */
@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Gestión de Usuarios", description = "API para registrar y consultar usuarios del sistema")
public class UserController {

    @Autowired
    private IUserService usuarioService;

        @Operation(summary = "Validar existencia y rol de un usuario")
    @GetMapping("/validar")
    public ResponseEntity<Map<String, Object>> validarUsuario(@RequestParam String email) {
        try {
            boolean existe = usuarioService.existeUsuario(email);
            String rol = existe ? usuarioService.obtenerRol(email) : "DESCONOCIDO";
            Map<String, Object> respuesta = Map.of("existe", existe, "rol", rol);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("error", e.getMessage()));
        }
    }
    // ========== DOCENTES ==========
    @Operation(
        summary = "Registrar un nuevo docente",
        description = "Registra un docente en el sistema con validación de email institucional y contraseña fuerte.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del docente a registrar",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = TeacherRequest.class),
                examples = @ExampleObject(
                    name = "Ejemplo de docente",
                    value = """
                    {
                      "email": "juan.perez@unicauca.edu.co",
                      "password": "Pass123!",
                      "nombres": "Juan Carlos",
                      "apellidos": "Pérez Gómez",
                      "celular": "3101234567",
                      "programa": "INGENIERIA_SISTEMAS",
                      "tipoDocente": "PLANTA"
                    }
                    """
                )
            )
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Docente registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
        }
    )
    @PostMapping("/docentes")
    public ResponseEntity<?> registrarDocente(@RequestBody TeacherRequest request) {
        try {
            Teacher teacher = new Teacher();
            teacher.setEmail(request.getEmail());
            teacher.setPassword(request.getPassword());
            teacher.setNombres(request.getNombres());
            teacher.setApellidos(request.getApellidos());
            teacher.setCelular(request.getCelular());
            teacher.setPrograma(request.getPrograma());
            teacher.setTypeTeacher(request.getTypeTeacher());

            Teacher resultado = (Teacher) usuarioService.registrarDocente(teacher);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    // ========== ESTUDIANTES ==========
    @Operation(
        summary = "Registrar un nuevo estudiante",
        description = "Registra un estudiante en el sistema.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del estudiante a registrar",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = StudentRequest.class),
                examples = @ExampleObject(
                    name = "Ejemplo de estudiante",
                    value = """
                    {
                      "email": "ana.gomez@unicauca.edu.co",
                      "password": "Stud123!",
                      "nombres": "Ana María",
                      "apellidos": "Gómez López",
                      "celular": "3209876543",
                      "programa": "INGENIERIA_SISTEMAS"
                    }
                    """
                )
            )
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Estudiante registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
        }
    )
    @PostMapping("/estudiantes")
    public ResponseEntity<?> registrarEstudiante(@RequestBody StudentRequest request) {
        try {
            Student student = new Student();
            student.setEmail(request.getEmail());
            student.setPassword(request.getPassword());
            student.setNombres(request.getNombres());
            student.setApellidos(request.getApellidos());
            student.setCelular(request.getCelular());
            student.setPrograma(request.getPrograma());

            Student resultado = (Student) usuarioService.registrarEstudiante(student);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    // ========== COORDINADORES ==========
    @Operation(
        summary = "Registrar un nuevo coordinator",
        description = "Registra un coordinator de programa en el sistema.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del coordinator a registrar",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = CoordinatorRequest.class),
                examples = @ExampleObject(
                    name = "Ejemplo de coordinator",
                    value = """
                    {
                      "email": "coordinator.sistemas@unicauca.edu.co",
                      "password": "Coord123!",
                      "nombres": "Carlos Alberto",
                      "apellidos": "Ramírez Ruiz",
                      "celular": "3151122334",
                      "programa": "INGENIERIA_SISTEMAS"
                    }
                    """
                )
            )
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Coordinador registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
        }
    )
    @PostMapping("/coordinadores")
    public ResponseEntity<?> registrarCoordinador(@RequestBody CoordinatorRequest request) {
        try {
            Coordinator coordinator = new Coordinator();
            coordinator.setEmail(request.getEmail());
            coordinator.setPassword(request.getPassword());
            coordinator.setNombres(request.getNombres());
            coordinator.setApellidos(request.getApellidos());
            coordinator.setCelular(request.getCelular());
            coordinator.setPrograma(request.getPrograma());

            Coordinator resultado = (Coordinator) usuarioService.registrarCoordinador(coordinator);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    // ========== JEFES DE DEPARTAMENTO ==========
    @Operation(
        summary = "Registrar un nuevo jefe de departamento",
        description = "Registra un jefe de departamento en el sistema.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del jefe de departamento a registrar",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DepartmentHeadRequest.class),
                examples = @ExampleObject(
                    name = "Ejemplo de jefe de departamento",
                    value = """
                    {
                      "email": "jefe.sistemas@unicauca.edu.co",
                      "password": "Jefe123!",
                      "nombres": "María Fernanda",
                      "apellidos": "López Sánchez",
                      "celular": "3161122334",
                      "programa": "INGENIERIA_SISTEMAS"
                    }
                    """
                )
            )
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Jefe de departamento registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
        }
    )
    @PostMapping("/jefes-departamento")
    public ResponseEntity<?> registrarJefeDepartamento(@RequestBody DepartmentHeadRequest request) {
        try {
            DepartmentHead jefe = new DepartmentHead();
            jefe.setEmail(request.getEmail());
            jefe.setPassword(request.getPassword());
            jefe.setNombres(request.getNombres());
            jefe.setApellidos(request.getApellidos());
            jefe.setCelular(request.getCelular());
            jefe.setPrograma(request.getPrograma());

            DepartmentHead resultado = (DepartmentHead) usuarioService.registrarJefeDepartamento(jefe);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    // ========== CONSULTA GENÉRICA ==========
    @Operation(
        summary = "Obtener un usuario por email",
        description = "Recupera los datos de un usuario registrado usando su email.",
        parameters = {
            @io.swagger.v3.oas.annotations.Parameter(
                name = "email",
                description = "Email del usuario a consultar",
                required = true,
                example = "juan.perez@unicauca.edu.co"
            )
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
        }
    )
    @GetMapping("/{email}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable String email) {
        try {
            var usuario = usuarioService.obtenerPorEmail(email);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}