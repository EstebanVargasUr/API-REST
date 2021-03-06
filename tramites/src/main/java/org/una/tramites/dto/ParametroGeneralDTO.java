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
public class ParametroGeneralDTO {

    private Long id;
    private String nombre;   
    private String valor;
    private String descripcion;   
    private Date fechaRegistro; 
    private Date fechaModificacion; 
}
