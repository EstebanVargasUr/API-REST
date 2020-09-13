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
import org.una.tramites.dto.ParametroGeneralDTO;
import org.una.tramites.entities.ParametroGeneral;
import org.una.tramites.services.IParametroGeneralService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Esteban Vargas
 */
@RestController
@RequestMapping("/parametros_generales") 
@Api(tags = {"Parametros Generales"})
public class ParametroGeneralController {
    
    @Autowired
    private IParametroGeneralService parametrosGeneralService;
     
    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos los Parametros Generales", response = ParametroGeneralDTO.class, responseContainer = "List", tags = "Parametros Generales")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<ParametroGeneral>> result = parametrosGeneralService.findAll();
            if (result.isPresent()) {
                List<ParametroGeneralDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), ParametroGeneralDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{nombre}")
    @ApiOperation(value = "Obtiene una lista de parametro por medio del nombre", response = ParametroGeneralDTO.class, responseContainer = "List", tags = "Parametros Generales")
    public ResponseEntity<?> findByNombreAproximateIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            Optional<List<ParametroGeneral>> result = parametrosGeneralService.findByNombreAproximateIgnoreCase(term);
            if (result.isPresent()) {
                List<ParametroGeneralDTO> ParametrosGeneralesDTO = MapperUtils.DtoListFromEntityList(result.get(), ParametroGeneralDTO.class);
                return new ResponseEntity<>(ParametrosGeneralesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
      @GetMapping("/{fecha}") 
    @ApiOperation(value = "Obtiene una lista con los Parametros Generales, entre las fechas especificadas", response = ParametroGeneralDTO.class, responseContainer = "List", tags = "Parametros Generales")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date FechIni,@PathVariable(value = "Fecha final") Date FechFin) {
        try {

            Optional<List<ParametroGeneral>> permisoOtorgadoFound = parametrosGeneralService.findByFechaRegistroBetween(FechIni,FechFin);
            if (permisoOtorgadoFound.isPresent()) {
                ParametroGeneralDTO permisoOtorgadoDto = MapperUtils.DtoFromEntity(permisoOtorgadoFound.get(), ParametroGeneralDTO.class);
                return new ResponseEntity<>(permisoOtorgadoDto, HttpStatus.OK);
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
    @ApiOperation(value = "Permite crear un Parametro", response = ParametroGeneralDTO.class, tags = "Parametros Generales")
    public ResponseEntity<?> create(@RequestBody ParametroGeneral parametroGeneral) {
        try {
            ParametroGeneral parametroGeneralCreated = parametrosGeneralService.create(parametroGeneral);
            ParametroGeneralDTO parametroGeneralDto = MapperUtils.DtoFromEntity(parametroGeneralCreated, ParametroGeneralDTO.class);
            return new ResponseEntity<>(parametroGeneralDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ResponseBody
    @ApiOperation(value = "Permite modificar un Parametro a partir de su Id", response = ParametroGeneralDTO.class, tags = "Parametros Generales")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ParametroGeneral parametroGeneralModified) {
        try {
            Optional<ParametroGeneral> parametroGeneralUpdated = parametrosGeneralService.update(parametroGeneralModified, id);
            if (parametroGeneralUpdated.isPresent()) {
                ParametroGeneralDTO parametroGeneralDto = MapperUtils.DtoFromEntity(parametroGeneralUpdated.get(), ParametroGeneralDTO.class);
                return new ResponseEntity<>(parametroGeneralDto, HttpStatus.OK);

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
