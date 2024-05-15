package eco.com.spring.mcsv.account.client.services;

import eco.com.spring.mcsv.account.client.dtos.UserDto;
import eco.com.spring.mcsv.account.client.models.Client;

import java.util.List;
import java.util.Optional;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

public interface ClientService {

    List<Client> listClients();

    Optional<Client> listClient(Long id);

    Optional<Optional<Client>> updateClient(Long id, UserDto client);

    Optional<Client> partialUpdateClient(Long id, UserDto client);

    int listNameClient(String nombreCliente);

    void insertClient(Client cliente);

    boolean getClientById(Long id);

    void deleteClient(Long id);
}
