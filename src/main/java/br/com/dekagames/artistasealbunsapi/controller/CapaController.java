package br.com.dekagames.artistasealbunsapi.controller;


import br.com.dekagames.artistasealbunsapi.MinIOConstants;
import br.com.dekagames.artistasealbunsapi.models.Album;
import br.com.dekagames.artistasealbunsapi.models.Capa;
import br.com.dekagames.artistasealbunsapi.repository.CapaRepository;
import io.minio.*;
import io.minio.http.Method;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/capa")
public class CapaController {
    private final CapaRepository capaRepository;

    private final String MinIO_URL;
    private final String MinIO_ACCESS_KEY;
    private final String MinIO_SECRET_KEY;
    private final String MinIO_BUCKET_NAME;

    public CapaController(@Autowired MinIOConstants constants, CapaRepository capaRepository)
    {
        this.capaRepository = capaRepository;
        this.MinIO_URL = constants.getUrl();
        this.MinIO_ACCESS_KEY = constants.getAccessKey();
        this.MinIO_SECRET_KEY = constants.getSecretKey();
        this.MinIO_BUCKET_NAME = constants.getBucketName();
    }

    @GetMapping("/{albumUID}")
    public List<String> getLinksCapa(@PathVariable("albumUID") Long albumUID)
    {
        var capas = capaRepository.findByAlbum(albumUID);
        if(capas != null && !capas.isEmpty()) {
            try {
                MinioClient minioClient =
                        MinioClient.builder()
                                .endpoint(MinIO_URL)
                                .credentials(MinIO_ACCESS_KEY, MinIO_SECRET_KEY)
                                .build();
                List<String> links = new ArrayList<>();
                for (var capa : capas) {
                    links.add(minioClient.getPresignedObjectUrl(
                            GetPresignedObjectUrlArgs.builder()
                                    .method(Method.GET)
                                    .bucket(MinIO_BUCKET_NAME)
                                    .object(capa.getObjeto())
                                    .expiry(2, TimeUnit.HOURS)
                                    .build()));
                }
                return links;
            }
            catch (Exception ex)
            {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()
                );
            }
        }
        else
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Capas não encontradas para este álbum"
            );
        }
    }

    @PostMapping("/{albumUID}")
    public void uploadCapa(@PathVariable("albumUID") Long albumUID, @RequestParam("file") MultipartFile file)
    {
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(MinIO_URL)
                        .credentials(MinIO_ACCESS_KEY, MinIO_SECRET_KEY)
                        .build();
        try
        {
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(MinIO_BUCKET_NAME).build()))
            {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(MinIO_BUCKET_NAME).build());
            }

            //for(MultipartFile file : files)
            //{
                String fileName = albumUID + "-" + new Date().getTime() + "-" + file.getName();

                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(MinIO_BUCKET_NAME)
                                .object(fileName)
                                .stream(file.getInputStream(), file.getSize(), -1)
                                .contentType(file.getContentType())
                                .build());

                var capa = new Capa();
                capa.setObjeto(fileName);

                var album = new Album();
                album.setAlbumUID(albumUID);

                capa.setAlbum(album);
                capaRepository.save(capa);
            //}
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()
            );
        }
    }
}
