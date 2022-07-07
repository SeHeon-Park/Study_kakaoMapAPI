package hello.map.service;

import hello.map.entity.Coordinate;
import hello.map.entity.StartCoordinate;
import hello.map.repository.StartCoordinateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StartCoordinateService {
    private final StartCoordinateRepository startCoordinateRepository;

    @Transactional
    public void save(StartCoordinate startCoordinate){
        startCoordinateRepository.save(startCoordinate);
    }

    public StartCoordinate recentCoordinate() {
        return startCoordinateRepository.findFirstByOrderByIdDesc();
    }

    public boolean checkStartLocation(Number latitude, Number longitude){
        latitude = Math.round(latitude.doubleValue()*10000)/10000.0;
        longitude = Math.round(longitude.doubleValue()*10000)/10000.0;
        Number startLatitude = startCoordinateRepository.findFirstByOrderByIdDesc().getLatitude();
        Number startLongitude = startCoordinateRepository.findFirstByOrderByIdDesc().getLongitude();
        return latitude.equals(startLatitude) && longitude.equals(startLongitude);
    }
}
