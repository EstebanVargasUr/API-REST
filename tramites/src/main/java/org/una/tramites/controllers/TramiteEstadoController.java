package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.TramiteEstadoDTO;
import org.una.tramites.services.ITramiteEstadoService;

@RestController
@RequestMapping("/tramitesEstados") 
@Api(tags = {"Tramites Estados"})
public class TramiteEstadoController {
    
    @Autowired
    private ITramiteEstadoService tramiteEstadoService;
    
     final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
    
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todos los Estados de Tramites ", response = TramiteEstadoDTO.class, responseContainer = "List", tags = "Tramites Estados")
    @PreAuthorize("hasAuthority('TRA6')")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(tramiteEstadoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una Estado del Tramite por su Id", response = TramiteEstadoDTO.class, tags = "Tramites Estados")
    @PreAuthorize("hasAuthority('TRA5')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(tramiteEstadoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/nombre/{termino}")
    @ApiOperation(value = "Obtiene una lista con el Estado del Tramite por nombre", response = TramiteEstadoDTO.class, responseContainer = "List", tags = "Tramites Estados")
    @PreAuthorize("hasAuthority('TRA5')")
    public ResponseEntity<?> findByNombreContainingIgnoreCase(@PathVariable(value = "termino") String term) {
        try {
            return new ResponseEntity(tramiteEstadoService.findByNombreContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/") 
    @ApiOperation(value = "Permite crear un Estado de Tramite", response = TramiteEstadoDTO.class, tags = "Tramites Estados")
    @PreAuthorize("hasAuthority('TRA1')")
    public ResponseEntity<?> create(@Valid @RequestBody TramiteEstadoDTO tramiteEstadoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(tramiteEstadoService.create(tramiteEstadoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar un Estado de Tramite a partir de su Id", response = TramiteEstadoDTO.class, tags = "Tramites Estados")
    @PreAuthorize("hasAuthority('TRA2')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody TramiteEstadoDTO tramiteEstadoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<TramiteEstadoDTO> usuarioUpdated = tramiteEstadoService.update(tramiteEstadoDTO, id);
                if (usuarioUpdated.isPresent()) {
                    return new ResponseEntity(usuarioUpdated, HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
}

