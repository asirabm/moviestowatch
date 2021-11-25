package com.moviestowatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.moviestowatch.movies.Movies;
@Service
public class NavGationServiceClass {
	
	@Bean
	public Movies getMovieDetails() {
		System.out.println("Hello From asir");		
     Movies mo=new Movies();
     mo.setId("1345");
     return mo;
	}
	
}
