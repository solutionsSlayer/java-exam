package epsi.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import epsi.exam.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
} 