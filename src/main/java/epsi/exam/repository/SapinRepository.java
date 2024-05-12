package epsi.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import epsi.exam.entity.Sapin;

@Repository
public interface SapinRepository extends JpaRepository<Sapin, Long> {
} 