package hello.map.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import hello.map.MapRequestDto;
import hello.map.MapResponseDto;
import hello.map.entity.Coordinate;
import hello.map.service.CoordinateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CoordinateController {

    private final CoordinateService coordinateService;

    @PostMapping("/map/coordinate")
    public ResponseEntity<?> showMap(@RequestBody MapRequestDto mapRequestDto) {
        Coordinate coordinate = new Coordinate();
        coordinate.setLatitude(mapRequestDto.getLatitude());
        coordinate.setLongitude(mapRequestDto.getLongitude());
        coordinateService.save(coordinate);
        String a = "good";
        return ResponseEntity.ok(a);
    }

    @PostMapping("/map/draw")
    public MapResponseDto draw(@RequestBody MapRequestDto mapRequestDto) {
        MapResponseDto mapResponseDto = new MapResponseDto();
        mapResponseDto.setNextLatitude(mapRequestDto.getLatitude());
        mapResponseDto.setNextLongitude(mapRequestDto.getLongitude());
        System.out.println(coordinateService.count());
        if (coordinateService.count() == 0){  // 처음 시작
            mapResponseDto.setPrevLatitude(mapRequestDto.getLatitude());
            mapResponseDto.setPrevLongitude(mapRequestDto.getLongitude());
        }
        else {
            Coordinate findCoordinate = coordinateService.recentCoordinate(); // 최근 데이터
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
