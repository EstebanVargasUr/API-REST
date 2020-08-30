package org.una.tramites.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.PermisoOtorgado;

/**
 *
 * @author adria
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class TransaccionDTO {
 
    private Long id; 
    private Date fechaRegistro; 
    private String Objeto;
    private String Informacion;
    private PermisoOtorgado permisoOtorgado;
    
    
        
}

