package org.una.tramites.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.Permiso;
import org.una.tramites.entities.Usuario;

/**
 *
 * @author adria
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class PermisoOtorgadoDTO {
 
    private Long id; 
    private Date fechaRegistro;
    private boolean estado;
    private Usuario usuario;
    private Permiso permiso;
}

