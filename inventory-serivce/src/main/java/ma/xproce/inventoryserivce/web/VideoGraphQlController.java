package ma.xproce.inventoryserivce.web;

import ma.xproce.inventoryserivce.dao.Creator;
import ma.xproce.inventoryserivce.dao.CreatorRequest;
import ma.xproce.inventoryserivce.dao.Video;
import ma.xproce.inventoryserivce.dao.VideoRequest;
import ma.xproce.inventoryserivce.dtos.CreatorDTO;
import ma.xproce.inventoryserivce.dtos.VideoDTO;
import ma.xproce.inventoryserivce.mappers.VideoMapper;
import ma.xproce.inventoryserivce.repositories.CreatorRepository;
import ma.xproce.inventoryserivce.repositories.VideoRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class VideoGraphQlController {

    private final CreatorRepository creatorRepository;
    private final VideoRepository videoRepository;

    public VideoGraphQlController(CreatorRepository creatorRepository, VideoRepository videoRepository) {
        this.creatorRepository = creatorRepository;
        this.videoRepository = videoRepository;
    }

    @QueryMapping
    public List<VideoDTO> videoList() {
        return videoRepository.findAll().stream()
                .map(VideoMapper::toVideoDTO)
                .collect(Collectors.toList());
    }

    @QueryMapping
    public CreatorDTO creatorById(@Argument Long id) {
        Creator creator = creatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Creator %s not found", id)));
        return VideoMapper.toCreatorDTO(creator);
    }

    @MutationMapping
    public CreatorDTO saveCreator(@Argument CreatorRequest creatorRequest) {
        Creator creator = new Creator();
        creator.setName(creatorRequest.getName());
        creator.setEmail(creatorRequest.getEmail());
        Creator savedCreator = creatorRepository.save(creator);
        return VideoMapper.toCreatorDTO(savedCreator);
    }

    @MutationMapping
    public VideoDTO saveVideo(@Argument("video") VideoRequest videoRequest) {
        Video video = new Video();
        video.setName(videoRequest.getName());
        video.setUrl(videoRequest.getUrl());
        video.setDescription(videoRequest.getDescription());
        video.setDatePublication(videoRequest.getDatePublication());

        // Set the creator for the video
        Creator creator = new Creator();
        creator.setName(videoRequest.getCreator().getName());
        creator.setEmail(videoRequest.getCreator().getEmail());
        creatorRepository.save(creator);
        video.setCreator(creator);

        Video savedVideo = videoRepository.save(video);
        return VideoMapper.toVideoDTO(savedVideo);
    }
}
