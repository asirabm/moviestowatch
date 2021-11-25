package com.moviestowatch.user;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

//import java.util.Map;
@Entity
@IdClass(UserMoviePrimaryKey.class)
public class UserMovie implements Serializable {
	@Id
	private String username;
	@Id
	private String movieid;
	
	
	public UserMovie() {
		
	}
	
	public UserMovie(String username, String movieid, int rating, String review) {
		
		this.username = username;
		this.movieid = movieid;
		this.rating = rating;
		this.review = review;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMovieid() {
		return movieid;
	}
	public void setMovieid(String movieid) {
		this.movieid = movieid;
	}
	private int rating;
	private String review;
	
	

	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	

}
