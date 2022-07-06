package hello.map.repository;


import hello.map.entity.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {
    Coordinate findFirstByOrderByIdDesc();
}
