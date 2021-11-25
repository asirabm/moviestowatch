package com.moviestowatch.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;

import com.moviestowatch.movies.Movies;
import com.moviestowatch.user.UserMovie;
import com.moviestowatch.user.UserMoviePrimaryKey;
import com.moviestowatch.user.UserMovieRepo;

//import com.movies.Movies;

import reactor.core.publisher.Mono;

@Controller
public class MovieSearchController {
	private String coverUrl;
	WebClient webclient;	
	
	@Autowired
	UserMovieRepo usermovierepo;
	
	public MovieSearchController(WebClient.Builder webcliBuilder) {
		this.webclient=webcliBuilder
				
				.baseUrl("https://api.themoviedb.org/3/movie/")
				.build();
	}
	
	@GetMapping("/movie/{movieid}")
	public String getMovieDetails(@PathVariable("movieid") String movieid,Model model,@AuthenticationPrincipal OAuth2User principal) {
	Mono<Movies> movies=this.webclient
    
	.get()
	.uri("{movieid}?api_key=a288e1f8b4ac86ceb7b0dc6078901a88",movieid)
	.retrieve()
	.bodyToMono(Movies.class);
	Movies mos=movies.block();
	if(mos.getPoster_path()!=null) {
	 coverUrl="https://image.tmdb.org/t/p/w185/"+mos.getPoster_path();	
	}
	else{
		coverUrl="/images/no-image.png";
	}
	 
    if(principal!=null && principal.getAttribute("login")!=null) {
 	   String userid=principal.getAttribute("login");
 	   model.addAttribute("userid", userid);
 	   UserMoviePrimaryKey uspk=new UserMoviePrimaryKey(userid,movieid);
 	   Optional<UserMovie> op=usermovierepo.findById(uspk);
 	   if(op.isPresent()) {
 		   model.addAttribute("usermovie", op.get());
 		   System.out.println("Kkkkkkkkkkkkk");
 	   }
 	   else {
 		   model.addAttribute("usermovie",new UserMovie());
 		  System.out.println("BBBBBBBBB");
 	   }
 	   
    }
	mos.setPoster_path(coverUrl);
	model.addAttribute("movie", mos);
	return "movies";
	}
	

}
