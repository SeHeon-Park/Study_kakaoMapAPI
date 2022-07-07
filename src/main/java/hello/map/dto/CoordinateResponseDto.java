package hello.map.dto;

import lombok.Data;

@Data
public class CoordinateResponseDto {
    private Number prevLatitude;
    private Number prevLongitude;
    private Number nextLatitude;
    private Number nextLongitude;
    private boolean startLocation;
}
