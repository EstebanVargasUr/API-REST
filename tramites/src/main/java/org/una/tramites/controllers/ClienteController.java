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
import org.una.tramites.dto.ClienteDTO;
import org.una.tramites.services.IClienteService;

@RestController
@RequestMapping("/clientes") 
@Api(tags = {"Clientes"})
public class ClienteController {

    @Autowired
    private IClienteService clienteService;
  

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/")
    @ApiOperation(value = "Obtiene una lista de todos los clientes", response = ClienteDTO.class, responseContainer = "List", tags = "Clientes")
    @PreAuthorize("hasAuthority('CLIE4')")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(clienteService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIE3')")
    @ApiOperation(value = "Obtiene un cliente por su Id", response = ClienteDTO.class, tags = "Clientes")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(clienteService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cedula/{termino}")
    @ApiOperation(value = "Obtiene una lista de clientes por cedula", response = ClienteDTO.class, responseContainer = "List", tags = "Clientes")
    @PreAuthorize("hasAuthority('CLIE3')")
    public ResponseEntity<?> findByCedulaAproximate(@PathVariable(value = "termino") String cedula) {
        try {
            return new ResponseEntity(clienteService.findByCedulaAproximate(cedula), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/nombre/{termino}")
    @ApiOperation(value = "Obtiene una lista de clientes por nombre", response = ClienteDTO.class, responseContainer = "List", tags = "Clientes")
    @PreAuthorize("hasAuthority('CLIE3')")
    public ResponseEntity<?> findByNombreCompletoAproximateIgnoreCase(@PathVariable(value = "termino") String nombre) {
        try {
            return new ResponseEntity(clienteService.findByNombreCompletoAproximateIgnoreCase(nombre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/estado/{termino}")
    @ApiOperation(value = "Obtiene una lista de clientes por estado", response = ClienteDTO.class, responseContainer = "List", tags = "Clientes")
    @PreAuthorize("hasAuthority('CLIE3')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "termino") boolean estado) {
        try {
            return new ResponseEntity(clienteService.findByEstado(estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 
    @GetMapping("/fecha/{termino1}")
    @ApiOperation(value = "Obtiene una lista de clientes entre fechas", response = ClienteDTO.class, responseContainer = "List", tags = "Clientes")
    @PreAuthorize("hasAuthority('CLIE3')")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "termino1") Date fechaInicio, Date fechaFinal) {
        try {
            return new ResponseEntity(clienteService.findByFechaRegistroBetween(fechaInicio, fechaFinal), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
   @PostMapping("/")
    @ApiOperation(value = "Permite crear un cliente", response = ClienteDTO.class, tags = "Clientes")
    @PreAuthorize("hasAuthority('CLIE1')")
    public ResponseEntity<?> create(@Valid @RequestBody ClienteDTO clienteDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(clienteService.create(clienteDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un cliente a partir de su Id", response = ClienteDTO.class, tags = "Clientes")
    @PreAuthorize("hasAuthority('CLIE2')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ClienteDTO clienteDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ClienteDTO> clienteUpdated = clienteService.update(clienteDTO, id);
                if (clienteUpdated.isPresent()) {
                    return new ResponseEntity(clienteUpdated, HttpStatus.OK);
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

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Permite eliminar un cliente a partir de su Id", response = ClienteDTO.class, tags = "Clientes")
    @PreAuthorize("hasAuthority('CLIE5')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            clienteService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Permite eliminar todos los clientes", response = ClienteDTO.class, tags = "Clientes")
    @PreAuthorize("hasAuthority('CLIE6')")
    public ResponseEntity<?> deleteAll() {
        try {
            clienteService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}