package br.com.mastertech.logacesso;

import br.com.mastertech.acesso.Acesso;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.stream.Stream;

@Component
public class LogAcessoConsumer {

    @KafkaListener(topics = "spec2-daniel-victor-1", groupId="1")
    public void gravarLogs(@Payload Acesso acesso) throws IOException {
        File csvFile = new File("log.csv");

        try (PrintWriter pw = new PrintWriter(new FileOutputStream(csvFile, true))) {
            pw.println(
                acesso.getClienteId() + "," +
                acesso.getPorta().toString() + "," +
                acesso.getAcesso().toString()
            );

            System.out.println("Log gravado com sucesso em " + csvFile.getAbsolutePath());
        }

        System.out.println("Tentativa de acesso do cliente " + acesso.getClienteId() + " na porta " + acesso.getPorta() + ": " + acesso.getAcesso());
    }
}
