package com.paololauria.cinema;
import com.paololauria.cinema.dtos.RegisterRequestDto;
import com.paololauria.cinema.model.repository.abstractions.UserRepository;
import com.paololauria.cinema.services.implementations.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.paololauria.cinema.model.entities.Role.ADMIN;

@SpringBootApplication
public class CinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service,
			UserRepository userRepository
	) {
		return args -> {
			String email = "p@gmail.com";

			if (userRepository.findByEmail(email).isEmpty()) {
				RegisterRequestDto paolo = new RegisterRequestDto(
						"Paolo", "Lauria",
						email, "1234",
						"1996-12-20", ADMIN);

				System.out.println("Token: " + service.register(paolo).getAccessToken());
			} else {
				System.out.println("L'utente con l'email " + email + " esiste già nel database.");
			}
		};
	}
}
