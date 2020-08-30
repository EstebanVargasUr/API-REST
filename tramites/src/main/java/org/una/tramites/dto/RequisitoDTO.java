package org.una.tramites.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.Variacion;

/**
 *
 * @author Esteban Vargas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class RequisitoDTO {
    
    private Long id; 
    private String descripcion;
    private Date fechaRegistro; 
    private boolean estado;
    private Variacion variacion;
}
