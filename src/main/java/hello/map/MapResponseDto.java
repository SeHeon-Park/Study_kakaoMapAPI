package hello.map;

import lombok.Data;

@Data
public class MapResponseDto {
    private Number prevLatitude;
    private Number prevLongitude;
    private Number nextLatitude;
    private Number nextLongitude;
}
