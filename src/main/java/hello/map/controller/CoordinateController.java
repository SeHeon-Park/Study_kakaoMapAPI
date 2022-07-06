package hello.map.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import hello.map.MapRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoordinateController {

    @PostMapping("/map/coordinate")
    public ResponseEntity<?> showMap(@RequestBody MapRequestDto mapRequestDto){
        System.out.println("mapRequestDto = " + mapRequestDto);
        String a = "good";
        return ResponseEntity.ok(a);
    }
}
