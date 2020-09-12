package org.una.tramites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.Cliente;

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
    private Long clienteId; 
    private Cliente cliente;
     
}
