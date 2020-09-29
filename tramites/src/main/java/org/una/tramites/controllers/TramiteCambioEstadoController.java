package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.Optional;
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
import org.una.tramites.dto.TramiteCambioEstadoDTO;
import org.una.tramites.services.ITramiteCambioEstadoService;

import javax.validation.Valid;

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

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los tramites de cambio de estado ", response = TramiteCambioEstadoDTO.class, responseContainer = "List", tags = "Tramites Cambios Estados")
    @PreAuthorize("hasAuthority('TRA6')")
    public ResponseEntity<?> findAll() {
        try{
                return new ResponseEntity( tramiteCambioEstadoService.findAll(), HttpStatus.OK);
            }
         catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{termino}")
    @ApiOperation(value = "Obtiene una lista con el tramite de cambio de estado por medio del id", response = TramiteCambioEstadoDTO.class, responseContainer = "List", tags = "Tramites Cambios Estados")
    @PreAuthorize("hasAuthority('TRA5')")
    public ResponseEntity<?> findById(@PathVariable(value = "termino") Long id) {
        try {
                return new ResponseEntity(tramiteCambioEstadoService.findById(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear un tramite de cambio de estado", response = TramiteCambioEstadoDTO.class, tags = "Tramites Cambios Estados")
    @PreAuthorize("hasAuthority('TRA1')")
    public ResponseEntity<?> create(@Valid @RequestBody TramiteCambioEstadoDTO TramiteCambioEstadoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {

        try {
            return new ResponseEntity(tramiteCambioEstadoService.create(TramiteCambioEstadoDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }else {
                return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
            }
        }
    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un tramite de cambio de estado a partir de su Id", response = TramiteCambioEstadoDTO.class, tags = "Tramites Cambios Estados")
    @PreAuthorize("hasAuthority('TRA2')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id,@Valid @RequestBody TramiteCambioEstadoDTO tramiteCambioEstadoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {

            try {
                Optional<TramiteCambioEstadoDTO> tramiteCambioEstadoUpdated = tramiteCambioEstadoService.update(tramiteCambioEstadoDTO, id);
                if (tramiteCambioEstadoUpdated.isPresent()) {
                    return new ResponseEntity(tramiteCambioEstadoUpdated, HttpStatus.OK);

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

    @GetMapping("/{fecha}")
    @ApiOperation(value = "Obtiene una lista con los tramites de cambio de estado, entre las fechas especificadas", response = TramiteCambioEstadoDTO.class, responseContainer = "List", tags = "Tramites Cambios Estados")
    @PreAuthorize("hasAuthority('TRA5')")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date FechIni, @PathVariable(value = "Fecha final") Date FechFin) {
        try {
                return new ResponseEntity<>(tramiteCambioEstadoService.findByFechaRegistroBetween(FechIni,FechFin), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Permite eliminar un tramite de cambio de estado", response = TramiteCambioEstadoDTO.class, tags = "Tramites Cambios Estados")
    @PreAuthorize("hasAuthority('TRA3')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            tramiteCambioEstadoService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Permite eliminar todos los tramites de cambio de estado", response = TramiteCambioEstadoDTO.class, tags = "Tramites Cambios Estados")
    @PreAuthorize("hasAuthority('TRA3')")
    public ResponseEntity<?> deleteAll() {
        try {
            tramiteCambioEstadoService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

