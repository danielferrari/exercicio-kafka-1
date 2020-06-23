package br.com.mastertech.acesso;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class AcessoController {

    @Autowired
    private AcessoProducer acessoProducer;

    @GetMapping("/acesso/{clientId}/{porta}")
    public boolean getAcesso(@PathVariable Long clientId, @PathVariable Integer porta) {
        boolean possuiAcesso = new Random().nextBoolean();

        Acesso acesso = new Acesso();
        acesso.setClienteId(clientId);
        acesso.setPorta(porta);
        acesso.setAcesso(possuiAcesso);

        acessoProducer.enviarAoKafka(acesso);

        return possuiAcesso;
    }
}
