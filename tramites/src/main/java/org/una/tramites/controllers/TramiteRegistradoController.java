package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.TramiteRegistradoDTO;
import org.una.tramites.entities.TramiteRegistrado;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.services.ITramiteRegistradoService;

@RestController
@RequestMapping("/tramitesregistrados") 
@Api(tags = {"TramitesRegistrados"})
public class TramiteRegistradoController {

    @Autowired
    private ITramiteRegistradoService tramite_registradoService;
  

    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos los tramites registrados", response = TramiteRegistradoDTO.class, responseContainer = "List", tags = "TramitesRegistrados")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<TramiteRegistrado>> result = tramite_registradoService.findAll();
            if (result.isPresent()) {
                List<TramiteRegistradoDTO> tramite_registradoDTO = MapperUtils.DtoListFromEntityList(result.get(), TramiteRegistradoDTO.class);
                return new ResponseEntity<>(tramite_registradoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una lista con el tramite registrado por medio del Id", response =  TramiteRegistradoDTO.class, responseContainer = "List", tags = "TramitesRegistrados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<TramiteRegistrado> tramite_registradoFound = tramite_registradoService.findById(id);
            if (tramite_registradoFound.isPresent()) {
                 TramiteRegistradoDTO tramiteRegistradoDto = MapperUtils.DtoFromEntity(tramite_registradoFound.get(), TramiteRegistradoDTO.class);
                return new ResponseEntity<>(tramiteRegistradoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
  /*   @GetMapping("/{cliente}") 
    @ApiOperation(value = "Obtiene una lista de los tramites registrado por cliente", response = TramiteRegistradoDTO.class, responseContainer = "List", tags = "TramitesRegistrados")
    @ResponseBody
    public ResponseEntity<?> findByClienteId(@PathVariable(value = "cliente") Long id){
        try {
            Optional<List<TramiteRegistrado>> result = tramite_registradoService.findByClienteId(id);
            if (result.isPresent()) {
                List<TramiteRegistradoDTO> tramite_registradoDTO = MapperUtils.DtoListFromEntityList(result.get(), TramiteRegistradoDTO.class);
                return new ResponseEntity<>(tramite_registradoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
/*
    @GetMapping("/{cliente}") 
    @ApiOperation(value = "Obtiene una lista de los tramites registrado por el tipo de tramite", response = TramiteRegistradoDTO.class, responseContainer = "List", tags = "Tramites Registrados")
    @ResponseBody
    public ResponseEntity<?> findByTramiteTipo(@PathVariable(value = "tramiteTipo") Long id){
        try {
            Optional<List<TramiteRegistrado>> result = tramite_registradoService.findByTramiteTipoId(id);
            if (result.isPresent()) {
                List<TramiteRegistradoDTO> tramite_registradoDTO = MapperUtils.DtoListFromEntityList(result.get(), TramiteRegistradoDTO.class);
                return new ResponseEntity<>(tramite_registradoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    
    @PutMapping("/{id}") 
    @ResponseBody
    @ApiOperation(value = "Permite modificar un tramite registrado a partir de su Id", response = TramiteRegistradoDTO.class, tags = "Tramites Registrados")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TramiteRegistrado tramite_registradoModified) {
        try {
            Optional<TramiteRegistrado> tramite_registradoUpdated = tramite_registradoService.update(tramite_registradoModified, id);
            if (tramite_registradoUpdated.isPresent()) {
                TramiteRegistradoDTO Tramite_RegistradoDto = MapperUtils.DtoFromEntity(tramite_registradoUpdated.get(), TramiteRegistradoDTO.class);
                return new ResponseEntity<>(Tramite_RegistradoDto, HttpStatus.OK);

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

