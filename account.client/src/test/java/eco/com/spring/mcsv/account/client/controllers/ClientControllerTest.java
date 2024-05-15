package eco.com.spring.mcsv.account.client.controllers;

import eco.com.spring.mcsv.account.client.models.Client;
import eco.com.spring.mcsv.account.client.models.Person;
import eco.com.spring.mcsv.account.client.repositories.ClientRepository;
import eco.com.spring.mcsv.account.client.services.ClientService;
import eco.com.spring.mcsv.account.client.services.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;


/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientServiceImpl clientServiceImpl;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void testListById() {
        // Simula el nombre del cliente y el cliente encontrado
        String nombreCliente = "ClienteExistente";
        Client cliente = new Client(1,"AAAAA","TRUE",new Person());

        // Define el comportamiento del servicio de cliente
        when(clientService.listNameClient(nombreCliente)).thenReturn(1);

        // Llama al método del controlador
        ResponseEntity<?> response = clientController.listById(nombreCliente);

        // Verifica que se devuelva una respuesta exitosa con el código de estado HTTP 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Verifica que el cuerpo de la respuesta contiene el cliente esperado
        assertEquals(1, response.getBody());
    }


    @Test
    void testListClients() {
        // Mocking del método de servicio para devolver una lista de clientes
        List<Client> mockCliente = Arrays.asList(
                createMockClient(1L, "","",new Person()),
                createMockClient(2L, "","",new Person())
        );
        when(clientService.listClients()).thenReturn(mockCliente);

        // Llamar al endpoint para obtener ResponseEntity
        ResponseEntity<?> responseEntity = clientController.list();

        // Assertions
        assertEquals(200, responseEntity.getStatusCodeValue()); // Assuming 200 for OK status
        assertEquals(mockCliente, responseEntity.getBody()); // Check if the returned list matches the mock data
    }

    @Test
    void testListClientsUsingRepositoryClass() {
        // crea data de ejemplo
        List<Client> mockCliente = Arrays.asList(
                createMockClient(1L, "","",new Person()),
                createMockClient(2L, "","",new Person())

        );

        // Mock del comportamiento del repositorio
        when(clientRepository.findAll()).thenReturn(mockCliente);

        // Llame al método para probar
        List<Client> result = clientServiceImpl.listClients();
        assertEquals(2,result.size());
    }

    // Método auxiliar para crear un objeto cliente simulado
    private Client createMockClient(long id, String password, String estate, Person personId) {
        Client cliente = new Client();
        cliente.setId(id);
        cliente.setPassword(password);
        cliente.setEstado(estate);
        cliente.setPersonId(personId);
        return cliente;
    }
}