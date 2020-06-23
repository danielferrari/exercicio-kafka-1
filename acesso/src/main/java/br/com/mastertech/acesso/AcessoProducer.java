package br.com.mastertech.acesso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AcessoProducer {
    @Autowired
    private KafkaTemplate<String, Acesso> producer;

    public void enviarAoKafka(Acesso acesso) {
        producer.send("spec2-daniel-victor-1", acesso);
    }
}
