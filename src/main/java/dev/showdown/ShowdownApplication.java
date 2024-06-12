package dev.showdown;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		tags = {
				@Tag(name = "User"),
				@Tag(name = "Table"),
				@Tag(name = "Game"),
				@Tag(name = "Vote")
		},
		info = @Info(description = "Showdown — planning poker application")
)
@SpringBootApplication
public class ShowdownApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowdownApplication.class, args);
	}

}
