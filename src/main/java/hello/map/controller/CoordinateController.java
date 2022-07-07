package hello.map.controller;

import hello.map.dto.CoordinateRequestDto;
import hello.map.dto.CoordinateResponseDto;
import hello.map.entity.Coordinate;
import hello.map.entity.StartCoordinate;
import hello.map.service.CoordinateService;
import hello.map.service.StartCoordinateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CoordinateController {

    private final CoordinateService coordinateService;
    private final StartCoordinateService startCoordinateService;

    @PostMapping("/map/coordinate")
    public ResponseEntity<?> showMap(@RequestBody CoordinateRequestDto mapRequestDto) {
        Number latitude = mapRequestDto.getLatitude();
        Number longitude = mapRequestDto.getLongitude();

        Coordinate coordinate = new Coordinate();
        coordinate.setLatitude(latitude);
        coordinate.setLongitude(longitude);
        coordinateService.save(coordinate);

        StartCoordinate startCoordinate = new StartCoordinate();
        latitude = (Math.round(latitude.doubleValue()*10000))/10000.0;
        longitude = (Math.round(longitude.doubleValue()*10000))/10000.0;
        startCoordinate.setLatitude(latitude);
        startCoordinate.setLongitude(longitude);
        startCoordinateService.save(startCoordinate);
        System.out.println("현재 위도: " + latitude);
        System.out.println("현재 경도: " + longitude);
        String response = "good : " + latitude + ", "+ longitude;
        return ResponseEntity.ok(response);
    }

    @PostMapping("/map/drawLine")
    public CoordinateResponseDto drawLine(@RequestBody CoordinateRequestDto mapRequestDto) {
        Number latitude = mapRequestDto.getLatitude();
        Number longitude = mapRequestDto.getLongitude();

        CoordinateResponseDto mapResponseDto = new CoordinateResponseDto();
        mapResponseDto.setNextLatitude(latitude);
        mapResponseDto.setNextLongitude(longitude);
        mapResponseDto.setStartLocation(false);

        // 처음 위치인지 확인
        if (startCoordinateService.checkStartLocation(latitude, longitude)){
            mapResponseDto.setStartLocation(true);
        }
        if (coordinateService.count() == 0){  // 처음 시작
            mapResponseDto.setPrevLatitude(latitude);
            mapResponseDto.setPrevLongitude(longitude);
        }
        else {
            Coordinate findCoordinate = coordinateService.recentCoordinate(); // 제일 최근 데이터
            mapResponseDto.setPrevLatitude(findCoordinate.getLatitude());
            mapResponseDto.setPrevLongitude(findCoordinate.getLongitude());
        }
        Coordinate coordinate = new Coordinate();
        coordinate.setLatitude(mapRequestDto.getLatitude());
        coordinate.setLongitude(mapRequestDto.getLongitude());
        coordinateService.save(coordinate);
        return mapResponseDto;
    }
}
