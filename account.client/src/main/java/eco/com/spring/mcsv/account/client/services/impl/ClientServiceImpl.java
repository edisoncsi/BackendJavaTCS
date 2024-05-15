package eco.com.spring.mcsv.account.client.services.impl;

import eco.com.spring.mcsv.account.client.dtos.UserDto;
import eco.com.spring.mcsv.account.client.models.Client;
import eco.com.spring.mcsv.account.client.models.Person;
import eco.com.spring.mcsv.account.client.repositories.ClientRepository;
import eco.com.spring.mcsv.account.client.repositories.PersonRepository;
import eco.com.spring.mcsv.account.client.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PersonRepository personRepository;
    @Override
    public List<Client> listClients() {
        return (List<Client>) clientRepository.findAll();
    }

    @Override
    public Optional<Client> listClient(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public int listNameClient(String nameClient) {
        return clientRepository.findByNombre(nameClient);
    }

    @Override
    public void insertClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Optional<Optional<Client>> updateClient(Long id, UserDto clientDto) {
        return clientRepository.findById(id).map(client -> {
            personRepository.findById(client.getPersonId().getId()).ifPresent(person -> {
                person.setNombre(clientDto.getNombre());
                person.setGenero(clientDto.getGenero());
                person.setEdad(clientDto.getEdad());
                person.setIdentificacion(clientDto.getIdentificacion());
                person.setDireccion(clientDto.getDireccion());
                person.setTelefono(clientDto.getTelefono());

                client.setPassword(clientDto.getPassword());
                client.setEstado(clientDto.getEstado());
                clientRepository.save(client);
            });
            return Optional.of(client);
        });
    }

    @Override
    public Optional<Client> partialUpdateClient(Long id, UserDto clientDto) {
        return clientRepository.findById(id).map(client -> {
            personRepository.findById(client.getPersonId().getId()).ifPresent(person -> {
                updatePersonFields(person, clientDto);
                updateClientFields(client, clientDto);
                clientRepository.save(client);
            });
            return client;
        });
    }

    private void updatePersonFields(Person person, UserDto clientDto) {
        if (clientDto.getNombre() != null) {
            person.setNombre(clientDto.getNombre());
        }
        if (clientDto.getGenero() != null) {
            person.setGenero(clientDto.getGenero());
        }
        if (clientDto.getEdad() != 0) {
            person.setEdad(clientDto.getEdad());
        }
        if (clientDto.getIdentificacion() != null) {
            person.setIdentificacion(clientDto.getIdentificacion());
        }
        if (clientDto.getDireccion() != null) {
            person.setDireccion(clientDto.getDireccion());
        }
        if (clientDto.getTelefono() != null) {
            person.setTelefono(clientDto.getTelefono());
        }
    }

    private void updateClientFields(Client client, UserDto clientDto) {
        if (clientDto.getPassword() != null) {
            client.setPassword(clientDto.getPassword());
        }
        if (clientDto.getEstado() != null) {
            client.setEstado(clientDto.getEstado());
        }
    }


    @Override
    public boolean getClientById(Long id) {
        return clientRepository.findById(id).isPresent();
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
