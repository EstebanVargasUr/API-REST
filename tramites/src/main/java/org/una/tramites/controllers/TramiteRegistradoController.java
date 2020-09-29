package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.TramiteRegistradoDTO;
import org.una.tramites.services.ITramiteRegistradoService;

@RestController
@RequestMapping("/tramitesRegistrados") 
@Api(tags = {"Tramites Registrados"})
public class TramiteRegistradoController {

    @Autowired
    private ITramiteRegistradoService tramite_registradoService;
  
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
 
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todos los Tramites Registrados", response = TramiteRegistradoDTO.class, responseContainer = "List", tags = "Tramites Registrados")
    @PreAuthorize("hasAuthority('TRA6')")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(tramite_registradoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un Tramite Registrado por su Id", response =  TramiteRegistradoDTO.class, tags = "Tramites Registrados")
    @PreAuthorize("hasAuthority('TRA5')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(tramite_registradoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/cliente/{id}") 
    @ApiOperation(value = "Obtiene una lista de los Tramites Registrados por Id de Cliente", response = TramiteRegistradoDTO.class, responseContainer = "List", tags = "Tramites Registrados")
    @PreAuthorize("hasAuthority('TRA5')")
    public ResponseEntity<?> findByClienteId(@PathVariable(value = "id") Long id){
        try {
            return new ResponseEntity(tramite_registradoService.findByClienteId(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tramiteTipo/{id}") 
    @ApiOperation(value = "Obtiene una lista de los Tramites Registrados por Id de Tipo de Tramite", response = TramiteRegistradoDTO.class, responseContainer = "List", tags = "Tramites Registrados")
    @PreAuthorize("hasAuthority('TRA5')")
    public ResponseEntity<?> findByTramiteTipoId(@PathVariable(value = "id") Long id){
        try {
            return new ResponseEntity(tramite_registradoService.findByTramiteTipoId(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Tramite Registrado", response = TramiteRegistradoDTO.class, tags = "Tramites Registrados")
    @PreAuthorize("hasAuthority('TRA1')")
    public ResponseEntity<?> create(@Valid @RequestBody TramiteRegistradoDTO tramiteRegistradoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(tramite_registradoService.create(tramiteRegistradoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar un Tramite Registrado a partir de su Id", response = TramiteRegistradoDTO.class, tags = "Tramites Registrados")
    @PreAuthorize("hasAuthority('TRA2')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody TramiteRegistradoDTO tramiteRegistradoDTO,  BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<TramiteRegistradoDTO> tramiteregistradoUpdated = tramite_registradoService.update(tramiteRegistradoDTO, id);
                if (tramiteregistradoUpdated.isPresent()) {
                    return new ResponseEntity(tramiteregistradoUpdated, HttpStatus.OK);
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
}

