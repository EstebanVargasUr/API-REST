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
public class VariacionDTO {
    
    private Long id; 
    private String descripcion;   
    private boolean estado; 
    private boolean grupo; 
    private Date fechaRegistro; 
    private Long tramite_tipoId; 
  //  private Tramite_Tipo tramite_tipo;
}
