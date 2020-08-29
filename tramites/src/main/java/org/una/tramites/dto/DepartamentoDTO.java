package org.una.tramites.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Esteban Vargas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class DepartamentoDTO {
    
    private Long id; 
    private String nombre;
    private Date fechaRegistro; 
    private Date fechaModificacion;
    private boolean estado; 
    
}
