package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
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
import org.una.tramites.dto.TramiteTipoDTO;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.services.ITramiteTipoService;

@RestController
@RequestMapping("/tramitesTipos") 
@Api(tags = {"Tramites Tipos"})
public class TramiteTipoController {

    @Autowired
    private ITramiteTipoService tramiteTipoService;

    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos los Tipos de Tramites", response = TramiteTipoDTO.class, responseContainer = "List", tags = "Tramites Tipos")
    @PreAuthorize("hasAuthority('TRAMITE_CONSULTAR_TODO')")
    ResponseEntity<?> findAll() {
         try {
            return new ResponseEntity(tramiteTipoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una Tipo de Tramite por medio de su Id", response = TramiteTipoDTO.class, tags = "Tramites Tipos")
    @PreAuthorize("hasAuthority('TRAMITE_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(tramiteTipoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/estado/{termino}") 
    @ApiOperation(value = "Obtiene una lista de Tipos de Tramites por el estado", response = TramiteTipoDTO.class, responseContainer = "List", tags = "Tramites Tipos")
    @PreAuthorize("hasAuthority('TRAMITE_CONSULTAR')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "termino") boolean st) {
        try {
            return new ResponseEntity(tramiteTipoService.findByEstado(st), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/fecha/{termino}") 
    @ApiOperation(value = "Obtiene una lista de Tipos de Tramites entre la fecha especificada", response = TramiteTipoDTO.class, responseContainer = "List", tags = "Tramites Tipos")
    @PreAuthorize("hasAuthority('TRAMITE_CONSULTAR')")
    public @ResponseBody
    ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date startDate, @PathVariable(value = "Fecha final") Date endDate) {
        try {
            return new ResponseEntity(tramiteTipoService.findByFechaRegistroBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/departamento/{id}")
    @ApiOperation(value = "Obtiene una lista con los Tipos de Tramites por Departamento", response = TramiteTipoDTO.class, responseContainer = "List", tags = "Tramites Tipos")
    @PreAuthorize("hasAuthority('TRAMITE_CONSULTAR')")
    public ResponseEntity<?> findByDepartamentoId(@PathVariable(value = "term") long id) {
        try {
            return new ResponseEntity(tramiteTipoService.findByDepartamentoId(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/") 
    @ResponseBody
    @ApiOperation(value = "Permite crear un tipo de tramite", response = TramiteTipoDTO.class, tags = "Tramites Tipos")
    @PreAuthorize("hasAuthority('TRAMITE_REGISTRAR')")
    public ResponseEntity<?> create(@RequestBody TramiteTipoDTO tramite_tipo) {
        try {
            TramiteTipoDTO tramite_tipoCreated = tramiteTipoService.create(tramite_tipo);
            TramiteTipoDTO tramite_tipoDto = MapperUtils.DtoFromEntity(tramite_tipoCreated, TramiteTipoDTO.class);
            return new ResponseEntity<>( tramite_tipoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ResponseBody
    @ApiOperation(value = "Permite modificar un tipo de tramite", response = TramiteTipoDTO.class, tags = "Tramites Tipos")
     @PreAuthorize("hasAuthority('TRAMITE_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TramiteTipoDTO tramite_tipoModified) {
        try {
            Optional<TramiteTipoDTO> tramite_tipoUpdated = tramiteTipoService.update(tramite_tipoModified, id);
            if (tramite_tipoUpdated.isPresent()) {
                TramiteTipoDTO tramite_tipoDto = MapperUtils.DtoFromEntity(tramite_tipoUpdated.get(), TramiteTipoDTO.class);
                return new ResponseEntity<>(tramite_tipoDto, HttpStatus.OK);

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
        try {
            tramiteTipoService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
}

