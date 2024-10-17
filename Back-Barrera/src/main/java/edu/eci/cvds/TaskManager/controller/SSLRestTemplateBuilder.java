package edu.eci.cvds.TaskManager.controller;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.security.KeyStore;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.ssl.SSLContextBuilder;


public class SSLRestTemplateBuilder {

    public RestTemplate restTemplate() throws Exception {
        // Cargar el trustStore (almacén de confianza)
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream inputStream = new FileInputStream("\\NuevoBack\\Back-Barrera\\src\\main\\resources\\keystore"); 
        String trustStorePassword = "Team Ciclos"; 
        trustStore.load(inputStream, trustStorePassword.toCharArray());

        // Construir el SSLContext usando el trustStore
        SSLContext sslContext = SSLContextBuilder.create()
                .loadTrustMaterial(trustStore, null)
                .build();

        // Crear la fábrica de sockets SSL
        SSLConnectionSocketFactory socketFactory = SSLConnectionSocketFactoryBuilder.create()
                .setSslContext(sslContext)
                .build();

        // Crear el HttpClient con la fábrica de sockets SSL
        HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();

        // Crear la fábrica de solicitudes HTTP con el HttpClient
        HttpComponentsClientHttpRequestFactory factory = 
                new HttpComponentsClientHttpRequestFactory(httpClient);

        // Devolver el RestTemplate configurado con SSL
        return new RestTemplate(factory);
    }
}
