package co.edu.unicauca.project_microservice.entity.estados;

import co.edu.unicauca.project_microservice.entity.ProyectoGrado;
import co.edu.unicauca.project_microservice.entity.EstadoProyecto;
import org.springframework.stereotype.Component;

@Component
public class MonografiaAprobadoState implements EstadoProyecto{
    @Override
    public void evaluar(ProyectoGrado proyecto, boolean aprobado, String observaciones) {
        throw new IllegalStateException("El proyecto ya fue aprobado.");
    }

    @Override
    public void reintentar(ProyectoGrado proyecto) {
        throw new IllegalStateException("No se puede reintentar un proyecto aprobado.");
    }

    @Override
    public String getNombreEstado() {
        return "MONOGRAFIA_APROBADO";
    }
}
