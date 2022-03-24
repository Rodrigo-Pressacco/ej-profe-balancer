package com.example.clientbankapi.controller;

import com.example.clientbankapi.model.Client;
import com.example.clientbankapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/servicioNegocio")
public class ClientController {
    private final ClientService clientService;

    @Value("${server.port}")
    private int serverPort;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/addClient")
    public void addClient(@RequestBody Client client){
        clientService.addClient(client);
    }

    @GetMapping("/getClients")
    public List<Client> getClients(HttpServletResponse response) {
        response.addHeader("port", String.valueOf(serverPort));
        return clientService.getClients();
    }
}
