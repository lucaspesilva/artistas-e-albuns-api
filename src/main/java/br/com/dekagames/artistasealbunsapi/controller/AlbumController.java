package br.com.dekagames.artistasealbunsapi.controller;

import br.com.dekagames.artistasealbunsapi.dto.AlbumRequest;
import br.com.dekagames.artistasealbunsapi.dto.AlbumResponse;
import br.com.dekagames.artistasealbunsapi.dto.ArtistaRequest;
import br.com.dekagames.artistasealbunsapi.dto.ArtistaResponse;
import br.com.dekagames.artistasealbunsapi.models.Album;
import br.com.dekagames.artistasealbunsapi.models.Artista;
import br.com.dekagames.artistasealbunsapi.repository.AlbumRepository;
import io.minio.*;
import io.minio.http.Method;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/album")
public class AlbumController {
    private final AlbumRepository albumRepository;

    public AlbumController(AlbumRepository albumRepository) {this.albumRepository = albumRepository;}

    @GetMapping("/")
    public List<AlbumResponse> findAll(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        var albuns = albumRepository.findAll(pageable);
        return albuns.stream().map(AlbumResponse::getAlbumDTO).collect(Collectors.toList());
    }

    @GetMapping("/artista/{artistaUID}")
    public List<AlbumResponse> findByArtista(@PathVariable("artistaUID") Long artistaUID, @RequestParam("page") int page, @RequestParam("pageSize") int pageSize)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        var albuns = albumRepository.findByArtista(artistaUID, pageable);
        return albuns.stream().map(AlbumResponse::getAlbumDTO).collect(Collectors.toList());
    }

    @GetMapping("/{albumUID}")
    public AlbumResponse findById(@PathVariable("albumUID") Long albumUID)
    {
        var album = albumRepository.findById(albumUID);
        if(album.isPresent()) {
            return AlbumResponse.getAlbumDTO(album.get());
        }
        else
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Álbum não encontrado"
            );
        }
    }

    @PostMapping("/")
    public void newAlbum(@RequestBody AlbumRequest albumRequest)
    {
        var album = new Album();
        album.setNome(albumRequest.getNome());

        var artista = new Artista();
        artista.setArtistaUID(albumRequest.getArtista().getArtistaUID());

        album.setArtista(artista);
        albumRepository.save(album);
    }

    @PostMapping("/capa/{albumUID}")
    public void uploadCapa(@PathVariable("albumUID") Long albumUID, @RequestParam("file") MultipartFile[] files)
    {
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint("https://play.min.io")
                        .credentials("Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG")
                        .build();
        try
        {
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket("albuns-api").build()))
            {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("albuns-api").build());
            }

            for(MultipartFile file : files)
            {
                String fileName = albumUID + "-" + new Date().getTime() + "-" + file.getName();

                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket("albuns-api")
                                .object(fileName)
                                .stream(file.getInputStream(), file.getSize(), -1)
                                .contentType(file.getContentType())
                                .build());
            }

            //return minioClient.getPresignedObjectUrl(
            //                GetPresignedObjectUrlArgs.builder()
            //                        .method(Method.GET)
            //                        .bucket("albuns-api")
            //                        .object(fileName)
            //                        .expiry(2, TimeUnit.HOURS)
            //                        .build());
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()
            );
        }
    }

    @PutMapping("/{albumUID}")
    public void updateAlbum(@PathVariable("albumUID") Long albumUID, @RequestBody AlbumRequest albumRequest)
    {
        var album = albumRepository.findById(albumUID);
        if(album.isPresent()) {
            var updateAlbum = album.get();
            updateAlbum.setNome(albumRequest.getNome());

            var artista = new Artista();
            artista.setArtistaUID(albumRequest.getArtista().getArtistaUID());

            updateAlbum.setArtista(artista);
            albumRepository.save(updateAlbum);
        }
        else
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Álbum não encontrado"
            );
        }
    }

}
