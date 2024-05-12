package epsi.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import epsi.exam.entity.Client;
import epsi.exam.repository.ClientRepository;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/create")
    public ResponseEntity<Client> createClient(@RequestParam String nom, @RequestParam String prenom) {
        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setSponsor(nom + " " + prenom);
        return ResponseEntity.ok(clientRepository.save(client));
    }

    @GetMapping("/get")
    public ResponseEntity<Client> getClient(@RequestParam Long id) {
        return ResponseEntity.ok(clientRepository.findById(id).orElse(null));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientRepository.findAll());
    }
} 