package com.example.ep_projekt.config;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;

@Component
public class WebClientConfig {

    // Skapar en WebClient.Builder som är konfigurerad med en anpassad HttpClient.
    @Bean
    public WebClient.Builder webClientBuilder() {

        // Skapar en HttpClient-instans och konfigurerar SSL-inställningar för att tillåta osäkra certifikat (inte rekommenderat i produktion).
        HttpClient httpClient = HttpClient.create()
                .secure(sslContextSpec -> {
                    try {
                        // Använder en "InsecureTrustManagerFactory" för att skapa en SSL-kontext som ignorerar certifikatvalidering.
                        sslContextSpec.sslContext(SslContext.newClientContext(InsecureTrustManagerFactory.INSTANCE));
                    } catch (SSLException e) {
                        // Om ett fel uppstår vid skapandet av SSL-kontexten kastas ett RuntimeException.
                        throw new RuntimeException(e);
                    }
                });

        // Returnerar en WebClient.Builder som använder den konfigurerade HttpClient.
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient));
    }
}
