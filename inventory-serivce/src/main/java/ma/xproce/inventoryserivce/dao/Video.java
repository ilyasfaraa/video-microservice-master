package ma.xproce.inventoryserivce.dao;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Video {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    private String description;
    private String url;
    private Date datePublication;
    @ManyToOne
    private Creator creator;
}
