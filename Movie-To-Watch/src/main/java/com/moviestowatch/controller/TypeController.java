package com.moviestowatch.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import com.moviestowatch.movies.Result;
import com.moviestowatch.movies.Results;

import reactor.core.publisher.Mono;

@Controller
public class TypeController {
	WebClient webclient;
	String coverURL="https://image.tmdb.org/t/p/w185/";
	
	public TypeController(WebClient.Builder webclienBuilder) {
		this.webclient=webclienBuilder
				.baseUrl("https://api.themoviedb.org/3/discover/movie?")
				.build();
	}
	
	

	 @GetMapping("/getmoviesbytypes")
	 public String getmoviesbytypes(@RequestParam("l") String la,@RequestParam("g") String ge,Model model,@RequestParam("p") String page) {
		System.out.println(page);
		 //System.out.println(gen+" "+lan);
		 
		/* Mono<Result> monores=this.webclient.get()
		 .uri("?api_key=a288e1f8b4ac86ceb7b0dc6078901a88&with_genres={}&with_original_language={}",ge,la)
		 .retrieve()
		 .bodyToMono(Result.class);
		 monores.block().getResults().forEach(t->System.out.println(t.getTitle()));
		 
		System.out.println(monores.block().getResults()); 
		 
		*/
		 
		 
	 model.addAttribute("l",la);
	 model.addAttribute("g",ge);
		
				 
		
		 
		
						 
		 
				 
				 
		 
		 Mono<Result> result = webclient.get()
		         .uri("?api_key=a288e1f8b4ac86ceb7b0dc6078901a88&with_genres={gen}&with_original_language={name}&page={page}", ge,la,page).
		         retrieve().bodyToMono(Result.class);
		 //result.block().getResults().forEach(t->System.out.println(t.getTitle()));
		 
		 
		 
		 
		//result.block().getResults().forEach(t->System.out.println(t.getTitle()));
		 
		 //System.out.println(ge+" " + la);
		 
		 if(result.block().getResults().isEmpty()) {
			 return "movienotfound";
		 }
		 
		 
		 else {
			 List<Results> listmovie=result.block().getResults();
			 listmovie= listmovie.stream().map(t->{
			     if( t.getPoster_path()!=null) {
			    	 t.setPoster_path(coverURL+t.getPoster_path());
			    	 System.out.println(t.getPoster_path());
			     }
			     else {
			    	 t.setPoster_path("/images/no-image.png");
			     }
					 
					return t; 
			 } ).collect(Collectors.toList());
			 
			 
			 
			 
			 
			 model.addAttribute("movies",listmovie);
			 model.addAttribute("result",result.block());
			 
			 return "movie";
		 }
		 
		 
	 }
	
	
	
	
}
