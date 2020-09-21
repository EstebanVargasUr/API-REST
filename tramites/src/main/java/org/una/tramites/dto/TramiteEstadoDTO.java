package org.una.tramites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author acer
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class TramiteEstadoDTO {
 
    private Long id; 
    private String nombre;   
    private String descripcion; 
    private String estadoSucesor; 
     
}

