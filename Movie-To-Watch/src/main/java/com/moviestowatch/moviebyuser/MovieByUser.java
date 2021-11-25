package com.moviestowatch.moviebyuser;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name="movie_by_user")
public class MovieByUser {
	
private String username;
private String movieid;
private String review;
private String rating;
private String cover_url;



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
public String getReview() {
	return review;
}
public void setReview(String review) {
	this.review = review;
}
public String getRating() {
	return rating;
}
public void setRating(String rating) {
	this.rating = rating;
}
public String getCover_url() {
	return cover_url;
}
public void setCover_url(String cover_url) {
	this.cover_url = cover_url;
}
	

}
