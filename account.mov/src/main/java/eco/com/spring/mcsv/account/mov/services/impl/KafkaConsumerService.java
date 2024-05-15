package eco.com.spring.mcsv.account.mov.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

@Service
public class KafkaConsumerService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${client.kafka.topic}")
    private String topic;

    @Value("${client.api.url}")
    private String clientApiUrl;

    @KafkaListener(topics = "${client.kafka.topic}")
    public Long getDataByNameClient(String nameClient) {
        // Realizar una solicitud HTTP GET para obtener los datos del cliente
        ResponseEntity<Long> response = restTemplate.getForEntity(clientApiUrl + "/" + nameClient, Long.class);
        return Objects.requireNonNull(response.getBody());
    }
}
