package com.moviestowatch.controller;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import com.moviestowatch.movies.MovieRepo;
import com.moviestowatch.movies.Result;
import com.moviestowatch.movies.Results;

//import com.movies.Movie;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Controller

public class MovieControl {
	@Autowired
	MovieRepo movi;
	WebClient webclient;
	private final String movieurl="https://api.themoviedb.org/3/search/movie";
	private String poster_url;
	
	MovieControl(WebClient.Builder webclientbuilder){
		this.webclient=webclientbuilder
				
				.baseUrl(movieurl)
				.clientConnector(new ReactorClientHttpConnector(
				        HttpClient.create().responseTimeout(Duration.ofMillis(2500000))
					      ))
				.build();
	}
	
	 
	@GetMapping("/search/")
	public String getMovies(Model model,@RequestParam("query") String query) {
        Mono<Result> monomovie= webclient.get()
         .uri("?api_key=a288e1f8b4ac86ceb7b0dc6078901a88&query={query}",query)
         .retrieve()
         .bodyToMono(Result.class);
        Result result=monomovie.block();
     
        List<Results> movies=result.getResults();
       movies= movies.stream().map(
        		movie->
        		{
        		if(movie.getPoster_path()!=null) {
        			poster_url="https://image.tmdb.org/t/p/w185/"+movie.getPoster_path();
        		}
        		else {
        			poster_url="\\images\\no-image.png";
        		}
        			
        		movie.setPoster_path(poster_url);
        		return movie;
        		}
        		
        		).collect(Collectors.toList());
       
       
      
       
       
       
        
        
       System.out.println(movies.get(0).getPoster_path());
        model.addAttribute("movies", movies);
        //movies.forEach(t->System.out.println(t));
		return "movie";
	}
}
