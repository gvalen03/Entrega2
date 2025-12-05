package co.edu.unicauca.project_microservice.entity.estados;

import co.edu.unicauca.project_microservice.entity.ProyectoGrado;
import co.edu.unicauca.project_microservice.entity.EstadoProyecto;
import org.springframework.stereotype.Component;

@Component
public class EnTerceraEvaluacionAnteState implements EstadoProyecto{
    @Override
    public void evaluar(ProyectoGrado proyecto, boolean aprobado, String observaciones) {
        proyecto.setObservacionesEvaluacion(observaciones);
        if (aprobado) {
            proyecto.setEstado(new AnteproyectoAprobadoState());
        } else {
            proyecto.setEstado(new AnteproyectoRechazadoState());
        }
    }

    @Override
    public void reintentar(ProyectoGrado proyecto) {
        throw new IllegalStateException("No se puede reintentar en tercera evaluación.");
    }

    @Override
    public String getNombreEstado() {
        return "EN_TERCERA_EVALUACION_ANTEPROYECTO";
    }
}
