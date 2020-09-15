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
import org.una.tramites.dto.ClienteDTO;
import org.una.tramites.entities.Cliente;
import org.una.tramites.services.IClienteService;
import org.una.tramites.utils.MapperUtils;

@RestController
@RequestMapping("/clientes") 
@Api(tags = {"Clientes"})
public class ClienteController {

    @Autowired
    private IClienteService clienteService;
  

    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos los clientes", response = ClienteDTO.class, responseContainer = "List", tags = "Clientes")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Cliente>> result = clienteService.findAll();
            if (result.isPresent()) {
                List<ClienteDTO> clienteDTO = MapperUtils.DtoListFromEntityList(result.get(), ClienteDTO.class);
                return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una lista con el cliente por medio del Id", response = ClienteDTO.class, responseContainer = "List", tags = "Clientes")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Cliente> clienteFound = clienteService.findById(id);
            if (clienteFound.isPresent()) {
                ClienteDTO clientesDto = MapperUtils.DtoFromEntity(clienteFound.get(), ClienteDTO.class);
                return new ResponseEntity<>(clientesDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{cedula}{aproximado}") 
    @ApiOperation(value = "Obtiene una lista con el cliente por medio de la cédula", response = ClienteDTO.class, responseContainer = "List", tags = "Clientes")
    public ResponseEntity<?> findByCedulaAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Cliente>> result = clienteService.findByCedulaAproximate(term);
            if (result.isPresent()) {
                List<ClienteDTO> clienteDTO = MapperUtils.DtoListFromEntityList(result.get(), ClienteDTO.class);
                return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{nombre}")
    @ApiOperation(value = "Obtiene una lista con el cliente por medio del nombre", response = ClienteDTO.class, responseContainer = "List", tags = "Clientes")
    public ResponseEntity<?> findByNombreCompletoAproximateIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Cliente>> result = clienteService.findByNombreCompletoAproximateIgnoreCase(term);
            if (result.isPresent()) {
                List<ClienteDTO> clientesDTO = MapperUtils.DtoListFromEntityList(result.get(), ClienteDTO.class);
                return new ResponseEntity<>(clientesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
     @GetMapping("/{estado}") 
    @ApiOperation(value = "Obtiene una lista de los clientes por estado", response = ClienteDTO.class, responseContainer = "List", tags = "Clientes")
    @ResponseBody
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado){
        try {
            Optional<List<Cliente>> result = clienteService.findByEstado(estado);
            if (result.isPresent()) {
                List<ClienteDTO> clienteDTO = MapperUtils.DtoListFromEntityList(result.get(), ClienteDTO.class);
                return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{fecha}") 
    @ApiOperation(value = "Obtiene una lista con los clientes, entre las fechas especificadas", response = ClienteDTO.class, responseContainer = "List", tags = "Clientes")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date FechIni,@PathVariable(value = "Fecha final") Date FechFin) {
        try {

            Optional<List<Cliente>> clienteFound = clienteService.findByFechaRegistroBetween(FechIni,FechFin);
            if (clienteFound.isPresent()) {
                ClienteDTO clienteDto = MapperUtils.DtoFromEntity(clienteFound.get(), ClienteDTO.class);
                return new ResponseEntity<>(clienteDto, HttpStatus.OK);
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
    @ApiOperation(value = "Permite crear un cliente", response = ClienteDTO.class, tags = "Clientes")
    public ResponseEntity<?> create(@RequestBody Cliente cliente) {
        try {
            Cliente clienteCreated = clienteService.create(cliente);
            ClienteDTO clienteDto = MapperUtils.DtoFromEntity(clienteCreated, ClienteDTO.class);
            return new ResponseEntity<>(clienteDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ResponseBody
    @ApiOperation(value = "Permite modificar un cliente a partir de su Id", response = ClienteDTO.class, tags = "Clientes")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Cliente clienteModified) {
        try {
            Optional<Cliente> clienteUpdated = clienteService.update(clienteModified, id);
            if (clienteUpdated.isPresent()) {
                ClienteDTO clienteDto = MapperUtils.DtoFromEntity(clienteUpdated.get(), ClienteDTO.class);
                return new ResponseEntity<>(clienteDto, HttpStatus.OK);

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

