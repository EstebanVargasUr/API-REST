package org.una.tramites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.Cliente;
import org.una.tramites.entities.TramiteTipo;

/**
 *
 * @author adria
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class TramiteRegistradoDTO {
 
    private Long id; 
    private TramiteTipo tramiteTipo;
    private Cliente cliente;
     
}
