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
import org.una.tramites.dto.VariacionDTO;
import org.una.tramites.entities.Variacion;
import org.una.tramites.services.IVariacionService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Esteban Vargas
 */
@RestController
@RequestMapping("/variaciones") 
@Api(tags = {"Variaciones"})
public class VariacionController {
    
    @Autowired
    private IVariacionService variacionService;

    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todas las Variaciones", response = VariacionDTO.class, responseContainer = "List", tags = "Variaciones")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Variacion>> result = variacionService.findAll();
            if (result.isPresent()) {
                List<VariacionDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), VariacionDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una lista con la Variacion por medio del Id", response = VariacionDTO.class, responseContainer = "List", tags = "Variaciones")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Variacion> usuarioFound = variacionService.findById(id);
            if (usuarioFound.isPresent()) {
                VariacionDTO variacionDto = MapperUtils.DtoFromEntity(usuarioFound.get(), VariacionDTO.class);
                return new ResponseEntity<>(variacionDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{estado}") 
    @ApiOperation(value = "Obtiene una lista de las Variaciones por estado", response = VariacionDTO.class, responseContainer = "List", tags = "Variaciones")
    @ResponseBody
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado){
        try {
            Optional<List<Variacion>> result = variacionService.findByEstado(estado);
            if (result.isPresent()) {
                List<VariacionDTO> variacionDTO = MapperUtils.DtoListFromEntityList(result.get(), VariacionDTO.class);
                return new ResponseEntity<>(variacionDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{grupo}") 
    @ApiOperation(value = "Obtiene una lista de las Variaciones por grupo", response = VariacionDTO.class, responseContainer = "List", tags = "Variaciones")
    @ResponseBody
    public ResponseEntity<?> findByGrupo(@PathVariable(value = "grupo") boolean grupo){
        try {
            Optional<List<Variacion>> result = variacionService.findByEstado(grupo);
            if (result.isPresent()) {
                List<VariacionDTO> variacionDTO = MapperUtils.DtoListFromEntityList(result.get(), VariacionDTO.class);
                return new ResponseEntity<>(variacionDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{fecha}") 
    @ApiOperation(value = "Obtiene una lista de Variaciones entre la fecha especificada", response = VariacionDTO.class, responseContainer = "List", tags = "Variaciones")
    public @ResponseBody
    ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date startDate, @PathVariable(value = "Fecha final") Date endDate) {
        try {
            Optional<List<Variacion>> result = variacionService.findByFechaRegistroBetween(startDate, endDate);
            if (result.isPresent()) {
                List<VariacionDTO> fechaDTO = MapperUtils.DtoListFromEntityList(result.get(), VariacionDTO.class);
                return new ResponseEntity<>(fechaDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tramite/{id}")
    @ApiOperation(value = "Obtiene una lista con las Variaciones por Tipo de Tramite", response = VariacionDTO.class, responseContainer = "List", tags = "Variaciones")
    public ResponseEntity<?> findByTramite_tipoId(@PathVariable(value = "term") long term) {
        try {
            Optional<List<Variacion>> result = variacionService.findByTramite_tipoId(term);
            if (result.isPresent()) {
                List<VariacionDTO> variacionDto = MapperUtils.DtoListFromEntityList(result.get(), VariacionDTO.class);
                return new ResponseEntity<>(variacionDto, HttpStatus.OK);
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
    @ApiOperation(value = "Permite crear una Variacion", response = VariacionDTO.class, tags = "Variaciones")
    public ResponseEntity<?> create(@RequestBody Variacion variacion) {
        try {
            Variacion variacionCreated = variacionService.create(variacion);
            VariacionDTO variacionDto = MapperUtils.DtoFromEntity(variacionCreated, VariacionDTO.class);
            return new ResponseEntity<>(variacionDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ResponseBody
    @ApiOperation(value = "Permite modificar una Variacion a partir de su Id", response = VariacionDTO.class, tags = "Variaciones")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Variacion variacionModified) {
        try {
            Optional<Variacion> variacionUpdated = variacionService.update(variacionModified, id);
            if (variacionUpdated.isPresent()) {
                VariacionDTO variacionDto = MapperUtils.DtoFromEntity(variacionUpdated.get(), VariacionDTO.class);
                return new ResponseEntity<>(variacionDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}") 
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        return null;
        //TODO: Implementar este método
    }

    @DeleteMapping("/") 
    public ResponseEntity<?> deleteAll() {
        return null;
 	//TODO: Implementar este método
    } 
    
}
