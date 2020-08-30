package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.TransaccionDTO;
import org.una.tramites.dto.UsuarioDTO;
import org.una.tramites.entities.Transaccion;
import org.una.tramites.services.ITransaccionService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author adria
 */
@RestController
@RequestMapping("/transacciones") 
@Api(tags = {"Transacciones"})
public class TransaccionController {
    
    @Autowired
    private ITransaccionService transaccionService;
    
    
    @GetMapping("/usuario/{id}{fecha}") 
    @ApiOperation(value = "Obtiene una transaccion por el Id del usuario entre la fecha especificacda", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public @ResponseBody
    ResponseEntity<?> findByUsuarioIdAndFechaRegistroBetween(@PathVariable(value = "id") Long idUsu, @PathVariable(value = "Fecha inicial") Date startDate, @PathVariable(value = "Fecha final") Date endDate) {
        try {
            Optional<List<Transaccion>> result = transaccionService.findByUsuarioIdAndFechaRegistroBetween(idUsu, startDate, endDate);
            if (result.isPresent()) {
                List<TransaccionDTO> transaccionDTO = MapperUtils.DtoListFromEntityList(result.get(), TransaccionDTO.class);
                return new ResponseEntity<>(transaccionDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/permiso/{id}{fecha}") 
    @ApiOperation(value = "Obtiene una transaccion por medio del Id del permiso entre la fecha especificacda", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public @ResponseBody
    ResponseEntity<?> findByPermisoIdAndFechaRegistroBetween(@PathVariable(value = "id") Long id, @PathVariable(value = "Fecha inicial") Date startDate, @PathVariable(value = "Fecha final") Date endDate) {
        try {
            Optional<List<Transaccion>> result = transaccionService.findByPermisoIdAndFechaRegistroBetween(Long.MIN_VALUE, startDate, endDate);
            if (result.isPresent()) {
                List<TransaccionDTO> transaccionDTO = MapperUtils.DtoListFromEntityList(result.get(), TransaccionDTO.class);
                return new ResponseEntity<>(transaccionDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{objeto}{fecha}") 
    @ApiOperation(value = "Obtiene una transaccion por medio de un objeto entre la fecha especificacda", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public @ResponseBody
    ResponseEntity<?> findByObjetoAndFechaRegistroBetween(@PathVariable(value = "Objeto") String objeto, @PathVariable(value = "Fecha inicial") Date startDate, @PathVariable(value = "Fecha final") Date endDate) {
        try {
            Optional<List<Transaccion>> result = transaccionService.findByObjetoAndFechaRegistroBetween(objeto, startDate, endDate);
            if (result.isPresent()) {
                List<TransaccionDTO> transaccionDTO = MapperUtils.DtoListFromEntityList(result.get(), TransaccionDTO.class);
                return new ResponseEntity<>(transaccionDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{fecha}") 
    @ApiOperation(value = "Obtiene una lista de las transacciones entre la fecha especificacda", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public @ResponseBody
    ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date startDate, @PathVariable(value = "Fecha final") Date endDate) {
        try {
            Optional<List<Transaccion>> result = transaccionService.findByFechaRegistroBetween(startDate, endDate);
            if (result.isPresent()) {
                List<TransaccionDTO> transaccionDTO = MapperUtils.DtoListFromEntityList(result.get(), TransaccionDTO.class);
                return new ResponseEntity<>(transaccionDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/") 
    @ApiOperation(value = "Permite crear una transaccion", response = TransaccionDTO.class, tags = "Transacciones")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Transaccion transaccion) {
        try {
            Transaccion transaccionCreated = transaccionService.create(transaccion);
            TransaccionDTO transaccionDto = MapperUtils.DtoFromEntity(transaccionCreated, TransaccionDTO.class);
            return new ResponseEntity<>(transaccionDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
  
}

