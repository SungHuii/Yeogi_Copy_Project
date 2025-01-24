package copy.project.demo.repository;

import copy.project.demo.entity.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    Optional<Accommodation> findByName(String name);
}
