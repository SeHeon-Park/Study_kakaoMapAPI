package hello.map.repository;

import hello.map.entity.Coordinate;
import hello.map.entity.StartCoordinate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StartCoordinateRepository extends JpaRepository<StartCoordinate, Long> {
    StartCoordinate findFirstByOrderByIdDesc();
}
