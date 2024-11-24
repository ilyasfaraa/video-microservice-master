package ma.xproce.inventoryserivce.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class VideoDTO {
    private Long id;
    private String name;
    private String description;
    private String url;
    private Date datePublication;
    private CreatorDTO creator;
}
