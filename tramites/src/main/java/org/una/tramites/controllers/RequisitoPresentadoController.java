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
import org.una.tramites.dto.RequisitoPresentadoDTO;
import org.una.tramites.entities.RequisitoPresentado;
import org.una.tramites.services.IRequisitoPresentadoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author adrian
 */
@RestController
@RequestMapping("/requisitoPresentado") 
@Api(tags = {"Requisitos Presentados"})
public class RequisitoPresentadoController {
    
    @Autowired
    private IRequisitoPresentadoService requisitoPresentadoService;
    
    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos los requisitos presentados ", response = RequisitoPresentadoDTO.class, responseContainer = "List", tags = "Requisitos Presentados")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<RequisitoPresentado>> result = requisitoPresentadoService.findAll();
            if (result.isPresent()) {
                List<RequisitoPresentadoDTO> requisitoPresentadoDTO = MapperUtils.DtoListFromEntityList(result.get(), RequisitoPresentadoDTO.class);
                return new ResponseEntity<>(requisitoPresentadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una lista los requisitos presentados por medio del id por medio del id", response = RequisitoPresentadoDTO.class, responseContainer = "List", tags = "Requisitos Presentados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<RequisitoPresentado> requisitoPresentadoFound = requisitoPresentadoService.findById(id);
            if (requisitoPresentadoFound.isPresent()) {
                RequisitoPresentadoDTO requisitoPresentadoDTO = MapperUtils.DtoFromEntity(requisitoPresentadoFound.get(), RequisitoPresentadoDTO.class);
                return new ResponseEntity<>(requisitoPresentadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/") 
    @ApiOperation(value = "Permite crear un requisito presentado", response = RequisitoPresentadoDTO.class, tags = "Requisitos Presentados")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody RequisitoPresentado RequisitoPresentado) {
        try {
            RequisitoPresentado requisitoPresentadoCreated = requisitoPresentadoService.create(RequisitoPresentado);
            RequisitoPresentadoDTO requisitoPresentadoDto = MapperUtils.DtoFromEntity(requisitoPresentadoCreated, RequisitoPresentadoDTO.class);
            return new ResponseEntity<>(requisitoPresentadoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar un requisito presentado a partir de su Id", response = RequisitoPresentadoDTO.class, tags = "Requisitos Presentados")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody RequisitoPresentado RequisitoPresentadoModified) {
        try {
            Optional<RequisitoPresentado> requisitoPresentadoUpdated = requisitoPresentadoService.update(RequisitoPresentadoModified, id);
            if (requisitoPresentadoUpdated.isPresent()) {
                RequisitoPresentadoDTO requisitoPresentadoDto = MapperUtils.DtoFromEntity(requisitoPresentadoUpdated.get(), RequisitoPresentadoDTO.class);
                return new ResponseEntity<>(requisitoPresentadoDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{fecha}") 
    @ApiOperation(value = "Obtiene una lista con los requisitos presentados, entre las fechas especificadas", response = RequisitoPresentadoDTO.class, responseContainer = "List", tags = "Requisitos Presentados")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date FechIni,@PathVariable(value = "Fecha final") Date FechFin) {
        try {

            Optional<List<RequisitoPresentado>> requisitoPresentadoFound = requisitoPresentadoService.findByFechaRegistroBetween(FechIni,FechFin);
            if (requisitoPresentadoFound.isPresent()) {
                RequisitoPresentadoDTO requisitoPresentadoDto = MapperUtils.DtoFromEntity(requisitoPresentadoFound.get(), RequisitoPresentadoDTO.class);
                return new ResponseEntity<>(requisitoPresentadoDto, HttpStatus.OK);
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

