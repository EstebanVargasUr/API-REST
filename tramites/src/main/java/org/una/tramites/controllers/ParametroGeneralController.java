package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.Optional;
import javax.validation.Valid;
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
import org.una.tramites.dto.ParametroGeneralDTO;
import org.una.tramites.services.IParametroGeneralService;

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
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
     
    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos los Parametros Generales", response = ParametroGeneralDTO.class, responseContainer = "List", tags = "Parametros Generales")
    @PreAuthorize("hasAuthority('PARAMETRO_GENERAL_CONSULTAR_TODO')")
    public ResponseEntity<?> findAll() {
        try {
                return new ResponseEntity(parametrosGeneralService.findAll(), HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{nombre}")
    @ApiOperation(value = "Obtiene una lista de parametro por medio del nombre", response = ParametroGeneralDTO.class, responseContainer = "List", tags = "Parametros Generales")
    @PreAuthorize("hasAuthority('PARAMETRO_GENERAL_CONSULTAR')")
    public ResponseEntity<?> findByNombreAproximateIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            
                return new ResponseEntity(parametrosGeneralService, HttpStatus.OK);
           
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{fecha}") 
    @ApiOperation(value = "Obtiene una lista con los Parametros Generales, entre las fechas especificadas", response = ParametroGeneralDTO.class, responseContainer = "List", tags = "Parametros Generales")
    @PreAuthorize("hasAuthority('PARAMETRO_GENERAL_CONSULTAR')")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date FechIni,@PathVariable(value = "Fecha final") Date FechFin) {
        try {
                return new ResponseEntity(parametrosGeneralService.findByFechaRegistroBetween(FechIni, FechFin), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/") 
    @ApiOperation(value = "Permite crear un Parametro General", response = ParametroGeneralDTO.class, tags = "Parametros Generales")
    @PreAuthorize("hasAuthority('PARAMETRO_GENERAL_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody ParametroGeneralDTO parametroGeneralDTO, BindingResult bindingResult)  {
        if (!bindingResult.hasErrors()) {
        try {
            return new ResponseEntity(parametrosGeneralService.create(parametroGeneralDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }else{
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar un Parametro a partir de su Id", response = ParametroGeneralDTO.class, tags = "Parametros Generales")
    @PreAuthorize("hasAuthority('PARAMETRO_GENERAL_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ParametroGeneralDTO parametroGeneralDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
        try {
            Optional<ParametroGeneralDTO> parametroGeneralUpdated = parametrosGeneralService.update(parametroGeneralDTO, id);
            if (parametroGeneralUpdated.isPresent()) {
                return new ResponseEntity(parametroGeneralUpdated, HttpStatus.OK);

            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Permite eliminar un Parametro del sistema a partir de su Id", response = ParametroGeneralDTO.class, tags = "Parametros Generales")
    @PreAuthorize("hasAuthority('PARAMETRO_GENERAL_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
                try {
            parametrosGeneralService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @DeleteMapping("/") 
    @ApiOperation(value = "Permite eliminar todos los parametros generales", response = ParametroGeneralDTO.class, tags = "Parametros Generales")
    @PreAuthorize("hasAuthority('PARAMETRO_GENERAL_ELIMINAR_TODO')")
   public ResponseEntity<?> deleteAll() {
        try {
            parametrosGeneralService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



