package br.com.dekagames.artistasealbunsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = "br.com.dekagames.artistasealbunsapi")
@EntityScan(basePackages = "br.com.dekagames.artistasealbunsapi.models")
@EnableSwagger2
public class ArtistasEAlbunsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtistasEAlbunsApiApplication.class, args);
	}
}
