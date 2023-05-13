package app.cta4j.config;

import app.cta4j.client.BusClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Objects;

@Configuration
public class HttpClientConfiguration {
    @Bean
    public BusClient busClient(@Value("${cta.bus-api-key}") String apiKey) {
        Objects.requireNonNull(apiKey);

        String baseUrl = "https://ctabustracker.com/bustime/api/v2?key=%s&format=json".formatted(apiKey);

        WebClient webClient = WebClient.create(baseUrl);

        WebClientAdapter webClientAdapter = WebClientAdapter.forClient(webClient);

        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(webClientAdapter)
                                                                                 .build();

        return httpServiceProxyFactory.createClient(BusClient.class);
    }
}
