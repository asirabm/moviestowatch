package com.moviestowatch.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.moviestowatch.movies.Movies;

import reactor.core.publisher.Mono;

@Configuration
public class RatingService {
	WebClient webclient;
	
	public RatingService(WebClient.Builder webBuilder){
		this.webclient=webBuilder.baseUrl("https://api.themoviedb.org/3/movie/").build();
	}
	
	
	
	
    @Bean(name = "urlService")
    public UrlService urlService() {
        return (String y) -> {
        	Mono<Movies> movies=this.webclient
        		    
        			.get()
        			.uri("{movieid}?api_key=a288e1f8b4ac86ceb7b0dc6078901a88",y)
        			.retrieve()
        			.bodyToMono(Movies.class);
        			Movies mos=movies.block();
        	
             return mos;
        	
        };
    }
}


