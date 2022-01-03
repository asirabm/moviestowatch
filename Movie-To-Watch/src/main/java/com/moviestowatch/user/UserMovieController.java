package com.moviestowatch.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import com.moviestowatch.movies.MovieRepo;
import com.moviestowatch.movies.Movies;

import reactor.core.publisher.Mono;

@Controller
public class UserMovieController {
	
	String logType;
	WebClient webclient;	
	@Autowired
	UserMovieRepo usermovierepo;
	
	@Autowired
	MovieRepo movierepo;
	
	@Autowired
	UserMovieRatingRepo usermovieratingrepo;
	String username;
	
	public UserMovieController(WebClient.Builder webclientbuilder) {
		this.webclient=webclientbuilder
				.baseUrl("https://api.themoviedb.org/3/movie/")
				.build();
	}
	
	@PostMapping("/addUserMovie")
	public ModelAndView addUserMovie( @RequestBody MultiValueMap<String, String> formData, 
	        @AuthenticationPrincipal OAuth2User principal) {
		if (principal == null) {
            return null;
        }
		
		 if(principal.getAttribute("iss")!=null) {
			 logType="Google";
			 System.out.println(logType);
		 }
		 else if(principal.getAttribute("login")!=null) {
			 logType="GitHub";
		 }
		
		
		String movieid=formData.getFirst("movieid");
		Mono<Movies> movies=this.webclient
			    
				.get()
				.uri("{movieid}?api_key=a288e1f8b4ac86ceb7b0dc6078901a88",movieid)
				.retrieve()
				.bodyToMono(Movies.class);
				 Movies mos=movies.block();
			if(logType=="Google") {
				 username=principal.getAttribute("email"); 
				 System.out.println(username);
			}
			else if(logType=="GitHub") {
				 username=principal.getAttribute("login"); 
			}
				 
		   // String username=principal.getAttribute("login"); 
		    System.out.println("ajscbajksncjknacjanscjknasjkcn ncoa sco onasioc");
		    System.out.println(principal.getAttributes());
			UserMovie usermovie=new UserMovie();
			
			//UserMoviePrimaryKey usmpk=new UserMoviePrimaryKey(username,movieid);
			//Optional<UserMovie> op=usermovierepo.findById(usmpk);
			//UserMoviePrimaryKey umpk=new UserMoviePrimaryKey(username,movieid);
			
			
			
			int rating = Integer.parseInt(formData.getFirst("rating"));
			//Boolean x=usermovierepo.existsById(umpk);
			String review=formData.getFirst("review");
			usermovie.setMovieid(movieid);
			usermovie.setUsername(username);
			usermovie.setRating(rating);
			usermovie.setReview(review);
			usermovie.setLogType(logType);
			usermovierepo.save(usermovie);
			
			
			/*Optional<UserMovieRating> usermovieraingop=usermovieratingrepo.findById(movieid);
			if(usermovieraingop.isPresent()) {
				
				
				int totalrating=usermovieraingop.get().getTotal_rating();
				int count=usermovieraingop.get().getCount();
				
				
				System.out.println(x);
				if(!x) {
					count+=1;
					totalrating=totalrating+rating;
					System.out.println(count+" "+totalrating);
					float rat=((float)totalrating)/count;
				}
				else {
					
				}
				
				//totalrating=totalrating+rating;
				
			    UserMovieRating usermovierating=new UserMovieRating();
			    usermovierating.setMovieid(movieid);
			    usermovierating.setCount(count);
			    usermovierating.setRating(rat);
			    usermovierating.setTotal_rating(totalrating);
			    usermovieratingrepo.save(usermovierating);
				
			}
			else {
				 UserMovieRating usermovierating=new UserMovieRating();
				 usermovierating.setMovieid(movieid);
				 usermovierating.setCount(1);
				 usermovierating.setRating(rating);
				 usermovierating.setTotal_rating(rating);
				 usermovieratingrepo.save(usermovierating); */
				 
		/*	}*/
			
			
			//return "home";	
		return new ModelAndView("redirect:/");
	}

}
