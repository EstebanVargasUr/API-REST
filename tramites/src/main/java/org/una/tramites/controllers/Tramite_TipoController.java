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
import org.una.tramites.dto.Tramite_TipoDTO;
import org.una.tramites.entities.Tramite_Tipo;
import org.una.tramites.services.ITramite_TipoService;
import org.una.tramites.utils.MapperUtils;

@RestController
@RequestMapping("/tramites_tipos") 
@Api(tags = {"Tramites_Tipos"})
public class Tramite_TipoController {

    @Autowired
    private ITramite_TipoService tramite_tipoService;

    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos los tipos de Tramites", response = Tramite_TipoDTO.class, responseContainer = "List", tags = "Tramites_Tipos")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Tramite_Tipo>> result = tramite_tipoService.findAll();
            if (result.isPresent()) {
                List< Tramite_TipoDTO> tramite_tipoDto = MapperUtils.DtoListFromEntityList(result.get(), Tramite_TipoDTO.class);
                return new ResponseEntity<>(tramite_tipoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una lista con el tipo de Tramite por medio del Id", response = Tramite_TipoDTO.class, responseContainer = "List", tags = "Tramites_Tipos")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Tramite_Tipo> tramite_tipoFound = tramite_tipoService.findById(id);
            if (tramite_tipoFound.isPresent()) {
                 Tramite_TipoDTO tramite_tipoDto = MapperUtils.DtoFromEntity(tramite_tipoFound.get(), Tramite_TipoDTO.class);
                return new ResponseEntity<>(tramite_tipoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{estado}") 
    @ApiOperation(value = "Obtiene una lista con los estados del tipo de Tramite", response = Tramite_TipoDTO.class, responseContainer = "List", tags = "Tramites_Tipos")
    public ResponseEntity<?> findByfindByEstado(@PathVariable(value = "term") boolean st) {
        try {

            Optional<List<Tramite_Tipo>> tramite_tipoFound = tramite_tipoService.findByEstado(st);
            if (tramite_tipoFound.isPresent()) {
                 Tramite_TipoDTO tramite_tipoDto = MapperUtils.DtoFromEntity(tramite_tipoFound.get(), Tramite_TipoDTO.class);
                return new ResponseEntity<>(tramite_tipoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/{fecha}") 
    @ApiOperation(value = "Obtiene una lista de tipos de Tramites entre la fecha especificada", response = Tramite_TipoDTO.class, responseContainer = "List", tags = "Tramites_Tipos")
    public @ResponseBody
    ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date startDate, @PathVariable(value = "Fecha final") Date endDate) {
        try {
            Optional<List<Tramite_Tipo>> result =tramite_tipoService.findByFechaRegistroBetween(startDate,endDate);
            if (result.isPresent()) {
                List<Tramite_TipoDTO> tramite_tipoDTO = MapperUtils.DtoListFromEntityList(result.get(), Tramite_TipoDTO.class);
                return new ResponseEntity<>(tramite_tipoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/departamento/{id}")
    @ApiOperation(value = "Obtiene una lista con los tipos de tramites por Departamento", response = Tramite_TipoDTO.class, responseContainer = "List", tags = "Tramites_Tipos")
    public ResponseEntity<?> findByDepartamentoId(@PathVariable(value = "term") long term) {
        try {
            Optional<List<Tramite_Tipo>> result = tramite_tipoService.findByDepartamentoId(term);
            if (result.isPresent()) {
                List<Tramite_TipoDTO> tramite_tipoDto = MapperUtils.DtoListFromEntityList(result.get(), Tramite_TipoDTO.class);
                return new ResponseEntity<>(tramite_tipoDto, HttpStatus.OK);
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
    @ApiOperation(value = "Permite crear un tipo de tramite", response = Tramite_TipoDTO.class, tags = "Tramites_Tipos")
    public ResponseEntity<?> create(@RequestBody Tramite_Tipo tramite_tipo) {
        try {
            Tramite_Tipo tramite_tipoCreated = tramite_tipoService.create(tramite_tipo);
            Tramite_TipoDTO tramite_tipoDto = MapperUtils.DtoFromEntity(tramite_tipoCreated, Tramite_TipoDTO.class);
            return new ResponseEntity<>( tramite_tipoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ResponseBody
    @ApiOperation(value = "Permite modificar un tipo de tramite", response = Tramite_TipoDTO.class, tags = "Tramites_Tipos")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Tramite_Tipo tramite_tipoModified) {
        try {
            Optional<Tramite_Tipo> tramite_tipoUpdated = tramite_tipoService.update(tramite_tipoModified, id);
            if (tramite_tipoUpdated.isPresent()) {
                Tramite_TipoDTO tramite_tipoDto = MapperUtils.DtoFromEntity(tramite_tipoUpdated.get(), Tramite_TipoDTO.class);
                return new ResponseEntity<>(tramite_tipoDto, HttpStatus.OK);

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

