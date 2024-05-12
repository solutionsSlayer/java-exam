package epsi.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import epsi.exam.entity.Sapin;
import epsi.exam.entity.BonDeCommande;
import epsi.exam.entity.Client;
import epsi.exam.repository.SapinRepository;
import epsi.exam.repository.BonDeCommandeRepository;
import epsi.exam.repository.ClientRepository;

@RestController
@RequestMapping("/sapin")
public class SapinController {

    @Autowired
    private SapinRepository sapinRepository;
    
    @Autowired
    private BonDeCommandeRepository bonDeCommandeRepository;
    
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/get")
    public ResponseEntity<Sapin> getSapin(@RequestParam Long id) {
        return ResponseEntity.ok(sapinRepository.findById(id).orElse(null));
    }

    @PostMapping("/create")
    public ResponseEntity<Sapin> createSapin() {
        Sapin sapin = new Sapin();
        return ResponseEntity.ok(sapinRepository.save(sapin));
    }

    @PostMapping("/addDecoration")
    public ResponseEntity<Boolean> addDecoration(@RequestParam Long idSapin, @RequestParam Long idDecoration) {
        Sapin sapin = sapinRepository.findById(idSapin).orElse(null);
        if (sapin == null || sapin.isVendu()) {
            return ResponseEntity.ok(false);
        }
        
        return ResponseEntity.ok(true);
    }

    @PostMapping("/vente")
    public ResponseEntity<BonDeCommande> vendreSapin(@RequestParam Long idSapin, @RequestParam Long idClient) {
        Sapin sapin = sapinRepository.findById(idSapin).orElse(null);
        Client client = clientRepository.findById(idClient).orElse(null);
        
        if (sapin == null || sapin.isVendu() || client == null) {
            return ResponseEntity.ok(null);
        }

        BonDeCommande bonDeCommande = new BonDeCommande();
        bonDeCommande.setSapin(sapin);
        bonDeCommande.setClient(client);
        
        sapin.setVendu(true);
        sapinRepository.save(sapin);
        
        int pointsFidelite = (int) Math.ceil(bonDeCommande.getCoutTotal() * 0.1);
        client.setPointsFidelite(client.getPointsFidelite() + pointsFidelite);
        clientRepository.save(client);

        return ResponseEntity.ok(bonDeCommandeRepository.save(bonDeCommande));
    }

    @GetMapping("/commande/get")
    public ResponseEntity<BonDeCommande> getBonDeCommande(@RequestParam Long idBonDeCommande) {
        return ResponseEntity.ok(bonDeCommandeRepository.findById(idBonDeCommande).orElse(null));
    }
} 