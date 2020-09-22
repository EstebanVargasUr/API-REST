package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.TramiteEstadoDTO;
import org.una.tramites.entities.TramiteEstado;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.services.ITramiteEstadoService;

@RestController
@RequestMapping("/tramitesEstados") 
@Api(tags = {"Tramites Estados"})
public class TramiteEstadoController {
    
    @Autowired
    private ITramiteEstadoService tramite_estadoService;
    
    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos los estados de los tramites ", response = TramiteEstadoDTO.class, responseContainer = "List", tags = "Tramites Estados")
    @PreAuthorize("hasAuthority('TRAMITE_CONSULTAR_TODO')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<TramiteEstadoDTO>> result = tramite_estadoService.findAll();
            if (result.isPresent()) {
                List<TramiteEstadoDTO> tramite_estadoDTO = MapperUtils.DtoListFromEntityList(result.get(), TramiteEstadoDTO.class);
                return new ResponseEntity<>(tramite_estadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una lista con el estado del tramite por medio del id", response = TramiteEstadoDTO.class, responseContainer = "List", tags = "Tramites Estados")
    @PreAuthorize("hasAuthority('TRAMITE_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<TramiteEstadoDTO> tramite_estadoFound = tramite_estadoService.findById(id);
            if (tramite_estadoFound .isPresent()) {
               TramiteEstadoDTO departamentoDTO = MapperUtils.DtoFromEntity(tramite_estadoFound .get(), TramiteEstadoDTO.class);
                return new ResponseEntity<>(departamentoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{nombre}")
    @ApiOperation(value = "Obtiene una lista con el Usuario por medio del nombre", response = TramiteEstadoDTO.class, responseContainer = "List", tags = "Tramites Estados")
    @PreAuthorize("hasAuthority('TRAMITE_CONSULTAR')")
    public ResponseEntity<?> findByNombreContainingIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            Optional<List<TramiteEstadoDTO>> result = tramite_estadoService.findByNombreContainingIgnoreCase(term);
            if (result.isPresent()) {
                List<TramiteEstadoDTO> tramite_estadoDTO = MapperUtils.DtoListFromEntityList(result.get(), TramiteEstadoDTO.class);
                return new ResponseEntity<>(tramite_estadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/") 
    @ApiOperation(value = "Permite crear un Departamento", response = TramiteEstadoDTO.class, tags = "Tramites Estados")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_REGISTRAR')")
    public ResponseEntity<?> create(@RequestBody TramiteEstadoDTO departamento) {
        try {
            TramiteEstadoDTO tramite_estadoCreated = tramite_estadoService.create(departamento);
            TramiteEstadoDTO tramite_estadoDto = MapperUtils.DtoFromEntity(tramite_estadoCreated, TramiteEstadoDTO.class);
            return new ResponseEntity<>( tramite_estadoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar un Departamento a partir de su Id", response = TramiteEstadoDTO.class, tags = "Tramites Estados")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TramiteEstadoDTO tramite_estadoModified) {
        try {
            Optional<TramiteEstadoDTO> tramite_estadoUpdated = tramite_estadoService.update(tramite_estadoModified, id);
            if (tramite_estadoUpdated.isPresent()) {
                TramiteEstadoDTO departamentoDto = MapperUtils.DtoFromEntity(tramite_estadoUpdated.get(), TramiteEstadoDTO.class);
                return new ResponseEntity<>(departamentoDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{id}") 
    @PreAuthorize("hasAuthority('TRAMITE_INACTIVAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        return null;
//TODO: Implementar este método
    }

    @DeleteMapping("/") 
    @PreAuthorize("hasAuthority('TRAMITE_INACTIVAR')")
    public ResponseEntity<?> deleteAll() {
        return null;
 	//TODO: Implementar este método
    } 
}

