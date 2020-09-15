package org.una.tramites.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.TramiteEstado;
import org.una.tramites.entities.TramiteRegistrado;

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
    
    private TramiteRegistrado tramiteRegistrado;
    private TramiteEstado tramiteEstado;
}

