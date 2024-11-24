package ma.xproce.inventoryserivce.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CreatorDTO {
    private Long id;
    private String name;
    private String email;
    private List<VideoDTO> videos;
}
