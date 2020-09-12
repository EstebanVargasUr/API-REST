package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.una.tramites.dto.TramiteCambioEstadoDTO;
import org.una.tramites.entities.TramiteCambioEstado;
import org.una.tramites.services.ITramiteCambioEstadoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Esteban Vargas
 */
@RestController
@RequestMapping("/tramiteCambioEstado") 
@Api(tags = {"Tramites Cambios Estados"})
public class TramiteCambioEstadoController {
    
    @Autowired
    private ITramiteCambioEstadoService tramiteCambioEstadoService;
    
    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos los tramites de cambio de estado ", response = TramiteCambioEstadoDTO.class, responseContainer = "List", tags = "Tramites Cambios Estados")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<TramiteCambioEstado>> result = tramiteCambioEstadoService.findAll();
            if (result.isPresent()) {
                List<TramiteCambioEstadoDTO> tramiteCambioEstadoDTO = MapperUtils.DtoListFromEntityList(result.get(), TramiteCambioEstadoDTO.class);
                return new ResponseEntity<>(tramiteCambioEstadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una lista con el tramite de cambio de estado por medio del id", response = TramiteCambioEstadoDTO.class, responseContainer = "List", tags = "Tramites Cambios Estados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<TramiteCambioEstado> tramiteCambioEstadoFound = tramiteCambioEstadoService.findById(id);
            if (tramiteCambioEstadoFound.isPresent()) {
                TramiteCambioEstadoDTO tramiteCambioEstadoDTO = MapperUtils.DtoFromEntity(tramiteCambioEstadoFound.get(), TramiteCambioEstadoDTO.class);
                return new ResponseEntity<>(tramiteCambioEstadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/") 
    @ApiOperation(value = "Permite crear un tramite de cambio de estado", response = TramiteCambioEstadoDTO.class, tags = "Tramites Cambios Estados")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody TramiteCambioEstado tramiteCambioEstado) {
        try {
            TramiteCambioEstado tramiteCambioEstadoCreated = tramiteCambioEstadoService.create(tramiteCambioEstado);
            TramiteCambioEstadoDTO tramiteCambioEstadoDto = MapperUtils.DtoFromEntity(tramiteCambioEstadoCreated, TramiteCambioEstadoDTO.class);
            return new ResponseEntity<>(tramiteCambioEstadoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar un tramite de cambio de estado a partir de su Id", response = TramiteCambioEstadoDTO.class, tags = "Tramites Cambios Estados")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TramiteCambioEstado tramiteCambioEstadoModified) {
        try {
            Optional<TramiteCambioEstado> tramiteCambioEstadoUpdated = tramiteCambioEstadoService.update(tramiteCambioEstadoModified, id);
            if (tramiteCambioEstadoUpdated.isPresent()) {
                TramiteCambioEstadoDTO tramiteCambioEstadoDto = MapperUtils.DtoFromEntity(tramiteCambioEstadoUpdated.get(), TramiteCambioEstadoDTO.class);
                return new ResponseEntity<>(tramiteCambioEstadoDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{fecha}") 
    @ApiOperation(value = "Obtiene una lista con los tramites de cambio de estado, entre las fechas especificadas", response = TramiteCambioEstadoDTO.class, responseContainer = "List", tags = "Tramires Cambios Estados")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date FechIni,@PathVariable(value = "Fecha final") Date FechFin) {
        try {

            Optional<List<TramiteCambioEstado>> tramiteCambioEstadoFound = tramiteCambioEstadoService.findByFechaRegistroBetween(FechIni,FechFin);
            if (tramiteCambioEstadoFound.isPresent()) {
                TramiteCambioEstadoDTO tramiteCambioEstadoDto = MapperUtils.DtoFromEntity(tramiteCambioEstadoFound.get(), TramiteCambioEstadoDTO.class);
                return new ResponseEntity<>(tramiteCambioEstadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    @DeleteMapping("/{id}") 
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        return null;
    }

    @DeleteMapping("/") 
    public ResponseEntity<?> deleteAll() {
        return null;
    } 
}

