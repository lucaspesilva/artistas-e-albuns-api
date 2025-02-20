package br.com.dekagames.artistasealbunsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ConfigurationPropertiesScan
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = "br.com.dekagames.artistasealbunsapi")
@EntityScan(basePackages = "br.com.dekagames.artistasealbunsapi.models")
public class ArtistasEAlbunsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtistasEAlbunsApiApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
