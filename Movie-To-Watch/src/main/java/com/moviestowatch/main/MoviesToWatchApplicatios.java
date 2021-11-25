package com.moviestowatch.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviestowatch.controller.NavGationServiceClass;

@SpringBootApplication
@RestController
@EnableJpaRepositories({"com.moviestowatch.movies","com.moviestowatch.user"})
@EntityScan({"com.moviestowatch.movies","com.moviestowatch.user","com.moviestowatch.moviebyuser"})
@ComponentScan(basePackages = {"com.moviestowatch.controller","com.moviestowatch.movies","com.moviestowatch.user"})
public class MoviesToWatchApplicatios {
	@Autowired
	NavGationServiceClass  navgser;

	public static void main(String[] args) {
		SpringApplication.run(MoviesToWatchApplicatios.class, args);
	}

	@RequestMapping("/user")
	public String user(@AuthenticationPrincipal OAuth2User principal) {
		System.out.println(principal);
		return principal.getAttribute("username");
	}
	

}
