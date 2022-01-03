package com.moviestowatch.controller;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import com.moviestowatch.moviebyuser.GenersMain;
//import com.example.demo.GenersMain;
//import com.moviestowatch.moviebyuser.Genres;
//import com.moviestowatch.moviebyuser.GenresMain;
//import com.moviestowatch.moviebyuser.Gn;
import com.moviestowatch.moviebyuser.Language;
import com.moviestowatch.moviebyuser.LanguageTypes;
import com.moviestowatch.movies.MovieRepo;
import com.moviestowatch.movies.Movies;
import com.moviestowatch.movies.Result;
import com.moviestowatch.movies.Results;
import com.moviestowatch.user.UserMovie;
import com.moviestowatch.user.UserMovieRating;
import com.moviestowatch.user.UserMovieRatingRepo;
import com.moviestowatch.user.UserMovieRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
 public class HomeController {
	@Autowired
	UserMovieRepo usermovierepo;
	String id;
	String type;
	
	@Autowired
	UserMovieRatingRepo usermovieratingrepo;
	private String coverUrl="https://image.tmdb.org/t/p/w185/";
	WebClient webclient;
	
	
	WebClient webclient2;
	public HomeController (WebClient.Builder webclientbuilder) {
		this.webclient=webclientbuilder
				.baseUrl("https://api.themoviedb.org/3")
				.build();
		
		
		/*this.webclient2=webclientbuilder
				.baseUrl("https://api.themoviedb.org/3/discover/movie?")
				.build();
		*/
	}
	 @RequestMapping("/")
	    public String home(@AuthenticationPrincipal OAuth2User principal, Model model) {
		 
		 
		 if(principal.getAttribute("iss")!=null) {
			 type="Google";
			 id=principal.getAttribute("email");
		 }
		 else if(principal.getAttribute("login")!=null) {
			 type="GitHub";
			 id=principal.getAttribute("login");
		 }	
	        if (principal == null ) {
	        	
	    return "index";
	  }
	        
	        
	        
	        
	        
	        
	//String id=principal.getAttribute("login");
	Optional<List<UserMovie>> oplist= usermovierepo.findByUsernameAndLogType(id,type);
	
	if(oplist.isPresent()) {
		List<Movies> listmovies=oplist.get().stream().map(t->
		{
		   Optional<Movies> om=Optional.of(webclient.get()
		  .uri("/movie/{movieid}?api_key=a288e1f8b4ac86ceb7b0dc6078901a88",t.getMovieid())
		  .retrieve()
		  .bodyToMono(Movies.class).block());
		   Optional<List<UserMovie>> um=usermovierepo.findByMovieid(t.getMovieid());
		   OptionalDouble od=um.get().stream().mapToInt(y->
		   {
			//y.getRating();
			return y.getRating();
		   }).filter(f->f>0).
				   
				   
				   
				   average();
		   
		   
		   od.getAsDouble();
		   om.get().setUserRating(od.getAsDouble());
		   return om.get();
		   
		} 
		  ).collect(Collectors.toList());
		
		
	
		
		//listmovies.stream().forEach(t->System.out.println(t.getOriginal_title()));
		
	
		model.addAttribute("listmovies", listmovies);
		//model.addAttribute("movieids", movieids);
	}
	 
	 
	/* model.addAttribute("name",name);
	 model.addAttribute("email",email);
	 model.addAttribute("id",id);*/
	
	
	Mono<List<LanguageTypes>> opl=this.webclient.get()
	.uri("/configuration/languages?api_key=a288e1f8b4ac86ceb7b0dc6078901a88")
	.retrieve()
	.bodyToFlux(LanguageTypes.class)
	.collectList()
	.log();
	
	
	/*Mono<List<Genres>> opg=this.webclient.get()
			.uri("/genre/movie/list?api_key=a288e1f8b4ac86ceb7b0dc6078901a88&language=en-US")
			.retrieve()
			.bodyToFlux(Genres.class)
			.collectList()
			.log();
	*/
	
	Mono<GenersMain> mg=this.webclient.get().uri("/genre/movie/list?api_key=a288e1f8b4ac86ceb7b0dc6078901a88&language=en-US").retrieve()
			.bodyToMono(GenersMain.class);
			mg.block().getGenres().forEach(t->System.out.println(t.getName()));
	
	//opg.block().forEach(t->System.out.println(t.getId()));
	
	
	//opl.block().forEach(t->System.out.println(t.getEnglish_name()));
	model.addAttribute("lan",opl.block());
	model.addAttribute("gen",mg.block().getGenres());
	model.addAttribute("coverurl", coverUrl); 
	
	 return "home";
	 
	 
	 
	 
	 }
	 
	/* @GetMapping("/getmoviesbytypes")
	 public String getmoviesbytypes(@RequestParam("la") String la,@RequestParam("ge") String ge) {
		 //System.out.println(gen+" "+lan);
		 
		 Mono<Result> monores=this.webclient.get()
		 .uri("api_key=a288e1f8b4ac86ceb7b0dc6078901a88&with_genres={la}&with_original_language={ge}",la,ge)
		 .retrieve()
		 .bodyToMono(Result.class);
		 monores.block().getResults().forEach(t->System.out.println(t.getTitle()));
		 
		 
		 
		 Mono<Result> monr=this.webclient2.get()
				 .uri(u->u.path("api_key=a288e1f8b4ac86ceb7b0dc6078901a88&with_genres={la}&with_original_language={ge}").build(ge,la))
				 .retrieve()
				 .bodyToMono(Result.class);
		 
		 
		 monr.block().getResults().forEach(t->System.out.println(t.getTitle()));
		 
		 System.out.println(ge+" " + la);
		 
		 return "bytypes";
		 
		 
	 }
	 
	 
	 */
	 
	
	}
	

