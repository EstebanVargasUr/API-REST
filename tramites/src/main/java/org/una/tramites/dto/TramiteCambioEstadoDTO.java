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
public class TramiteCambioEstadoDTO {
    
    private Long id; 
 
    private Date fechaRegistro; 
    
   /* private TramiteRegistrado tramiteRegistrado;
    private TramiteEstado tramiteEstado;*/
}

