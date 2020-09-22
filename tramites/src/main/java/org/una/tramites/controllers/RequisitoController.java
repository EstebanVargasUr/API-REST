package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
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
import org.una.tramites.dto.RequisitoDTO;
import org.una.tramites.entities.Requisito;
import org.una.tramites.services.IRequisitoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Esteban Vargas
 */
@RestController
@RequestMapping("/requisitos") 
@Api(tags = {"Requisitos"})
public class RequisitoController {
    
    @Autowired
    private IRequisitoService requisitoService;

    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todas los Requisitos", response = RequisitoDTO.class, responseContainer = "List", tags = "Requisitos")
    @PreAuthorize("hasAuthority('REQUISITO_CONSULTAR_TODO')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<RequisitoDTO>> result = requisitoService.findAll();
            if (result.isPresent()) {
                List<RequisitoDTO> requisitoDTO = MapperUtils.DtoListFromEntityList(result.get(), RequisitoDTO.class);
                return new ResponseEntity<>(requisitoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una lista con el requisito por medio del Id", response = RequisitoDTO.class, responseContainer = "List", tags = "Requisitos")
    @PreAuthorize("hasAuthority('REQUISITO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<RequisitoDTO> requisitoFound = requisitoService.findById(id);
            if (requisitoFound.isPresent()) {
                RequisitoDTO variacionDto = MapperUtils.DtoFromEntity(requisitoFound.get(), RequisitoDTO.class);
                return new ResponseEntity<>(variacionDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{estado}") 
    @ApiOperation(value = "Obtiene una lista de los Requisitos por estado", response = RequisitoDTO.class, responseContainer = "List", tags = "Requisitos")
    @ResponseBody
    @PreAuthorize("hasAuthority('REQUISITO_CONSULTAR')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado){
        try {
            Optional<List<RequisitoDTO>> result = requisitoService.findByEstado(estado);
            if (result.isPresent()) {
                List<RequisitoDTO> requisitoDTO = MapperUtils.DtoListFromEntityList(result.get(), RequisitoDTO.class);
                return new ResponseEntity<>(requisitoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{fecha}") 
    @ApiOperation(value = "Obtiene una lista de Requisitos entre la fecha especificada", response = RequisitoDTO.class, responseContainer = "List", tags = "Requisitos")
    @PreAuthorize("hasAuthority('REQUISITO_CONSULTAR')")
    public @ResponseBody
    ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date startDate, @PathVariable(value = "Fecha final") Date endDate) {
        try {
            Optional<List<RequisitoDTO>> result = requisitoService.findByFechaRegistroBetween(startDate, endDate);
            if (result.isPresent()) {
                List<RequisitoDTO> fechaDTO = MapperUtils.DtoListFromEntityList(result.get(), RequisitoDTO.class);
                return new ResponseEntity<>(fechaDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/variaciones/{id}")
    @ApiOperation(value = "Obtiene una lista con los Requisitos por Variación", response = RequisitoDTO.class, responseContainer = "List", tags = "Requisitos")
    @PreAuthorize("hasAuthority('REQUISITO_CONSULTAR')")
    public ResponseEntity<?> findByTramite_tipoId(@PathVariable(value = "term") long term) {
        try {
            Optional<List<RequisitoDTO>> result = requisitoService.findByVariacionId(term);
            if (result.isPresent()) {
                List<RequisitoDTO> requisitoDto = MapperUtils.DtoListFromEntityList(result.get(), RequisitoDTO.class);
                return new ResponseEntity<>(requisitoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/") 
    @ResponseBody
    @ApiOperation(value = "Permite crear un Requisito", response = RequisitoDTO.class, tags = "Requisitos")
    @PreAuthorize("hasAuthority('REQUISITO_CONSULTAR')")
    public ResponseEntity<?> create(@RequestBody RequisitoDTO requisito) {
        try {
            RequisitoDTO variacionCreated = requisitoService.create(requisito);
            RequisitoDTO requisitoDto = MapperUtils.DtoFromEntity(variacionCreated, RequisitoDTO.class);
            return new ResponseEntity<>(requisitoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ResponseBody
    @ApiOperation(value = "Permite modificar un Requisito a partir de su Id", response = RequisitoDTO.class, tags = "Requisitos")
    @PreAuthorize("hasAuthority('REQUISITO_CONSULTAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody RequisitoDTO requisitoModified) {
        try {
            Optional<RequisitoDTO> requisitoUpdated = requisitoService.update(requisitoModified, id);
            if (requisitoUpdated.isPresent()) {
                RequisitoDTO variacionDto = MapperUtils.DtoFromEntity(requisitoUpdated.get(), RequisitoDTO.class);
                return new ResponseEntity<>(variacionDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}") 
    @PreAuthorize("hasAuthority('REQUISITO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        return null;
        //TODO: Implementar este método
    }

    @DeleteMapping("/") 
    @PreAuthorize("hasAuthority('REQUISITO_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        return null;
 	//TODO: Implementar este método
    } 
}
