package eco.com.spring.mcsv.account.client.controllers;

import eco.com.spring.mcsv.account.client.dtos.UserDto;
import eco.com.spring.mcsv.account.client.errors.ClientError;
import eco.com.spring.mcsv.account.client.models.Client;
import eco.com.spring.mcsv.account.client.models.Person;
import eco.com.spring.mcsv.account.client.responses.BaseResponse;

import eco.com.spring.mcsv.account.client.services.ClientService;
import eco.com.spring.mcsv.account.client.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "http://host.docker.internal:5001")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private PersonService personService;

    @Autowired
    private ClientError clientError;


    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(clientService.listClients());
    }

    @GetMapping({"/{nameClient}"})
    public ResponseEntity<?> listById(@PathVariable("nameClient") String nameClient) {
        return ResponseEntity.ok(clientService.listNameClient(nameClient));
    }

    @PostMapping
    public ResponseEntity<?> createUserClient(@Valid @RequestBody UserDto command, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(clientError.validationErrors(result));
        }

        try {
            Client client = createClientFromPerson(createPersonFromDto(command));
            clientService.insertClient(client);
            return new ResponseEntity<>(new BaseResponse("Datos creados exitosamente"), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return clientError.handleBadRequest(e);
        } catch (DataAccessException e) {
            return clientError.handleInternalError(e);
        }
    }

    @PutMapping({"/{clientId}"})
    public ResponseEntity<?> updateClient(@PathVariable("clientId") Long clientId, @Valid @RequestBody UserDto command, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(clientError.validationErrors(result));
        }

        try {
            Optional<Optional<Client>> client = clientService.updateClient(clientId, command);
            if (client.isPresent())
                return new ResponseEntity<>(new BaseResponse("Se actualizo exitosamente"), HttpStatus.OK);
            else
                return new ResponseEntity<>(new BaseResponse("El cliente no existe"), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return clientError.handleBadRequest(e);
        } catch (DataAccessException e) {
            return clientError.handleInternalError(e);
        }
    }
    @PatchMapping({"/{clientId}"})
    public ResponseEntity<?> partialUpdateClient(@PathVariable("clientId") Long clientId, @Valid @RequestBody UserDto command, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(clientError.validationErrors(result));
        }

        try {
            Optional<Client> client = clientService.partialUpdateClient(clientId, command);
            if (client.isPresent())
                return new ResponseEntity<>(new BaseResponse("Se actualizo exitosamente"), HttpStatus.OK);
            else
                return new ResponseEntity<>(new BaseResponse("El cliente no existe"), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return clientError.handleBadRequest(e);
        } catch (DataAccessException e) {
            return clientError.handleInternalError(e);
        }
    }

    @DeleteMapping({"/{clientId}"})
    public ResponseEntity<?> deleteClient(@PathVariable("clientId") Long clientId) {
        try {
            if (clientService.getClientById(clientId)){
                clientService.deleteClient(clientId);
                return new ResponseEntity<>(new BaseResponse("Se ha eliminado exitosamente"), HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(new BaseResponse("El cliente no existe"), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return clientError.handleBadRequest(e);
        } catch (DataAccessException e) {
            return clientError.handleInternalError(e);
        }
    }

    private Person createPersonFromDto(UserDto command) {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        return new Person(uuidAsString, command.getNombre(), command.getGenero(), command.getEdad(), command.getIdentificacion(), command.getDireccion(), command.getTelefono());
    }

    private Client createClientFromPerson(Person person) {
        return Client.builder()
                .password("contraseña")
                .estado("activo")
                .personId(person)
                .build();
    }
}
