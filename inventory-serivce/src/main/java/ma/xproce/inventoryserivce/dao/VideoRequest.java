package ma.xproce.inventoryserivce.dao;

import lombok.Data;

import java.util.Date;

@Data
public class VideoRequest {
    private String name;
    private String url;
    private String description;
    private Date datePublication;  // Assurez-vous que le format de date est géré correctement
    private CreatorRequest creator;  // L'ID du créateur pour associer une vidéo à un créateur
}
