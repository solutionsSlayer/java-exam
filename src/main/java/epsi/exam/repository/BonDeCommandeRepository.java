package epsi.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import epsi.exam.entity.BonDeCommande;

@Repository
public interface BonDeCommandeRepository extends JpaRepository<BonDeCommande, Long> {
} 