package hello.map.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class StartCoordinate {
    @Id
    @GeneratedValue
    @Column(name = "startCoordinate_id")
    private Long id;

    private Number latitude;
    private Number longitude;
}
