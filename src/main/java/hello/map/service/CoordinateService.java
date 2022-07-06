package hello.map.service;

import hello.map.entity.Coordinate;
import hello.map.repository.CoordinateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CoordinateService {
    private final CoordinateRepository coordinateRepository;

    @Transactional
    public void save(Coordinate coordinate){
        coordinateRepository.save(coordinate);
    }

    public List<Coordinate> findAll(){
        return  coordinateRepository.findAll();
    }

    public Coordinate recentCoordinate() {
        return coordinateRepository.findFirstByOrderByIdDesc();
    }

    public Long count(){
        return coordinateRepository.count();
    }
}
