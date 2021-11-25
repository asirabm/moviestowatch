package com.moviestowatch.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class UserMovieRating {
	private float rating;
	
public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
public String getMovieid() {
		return movieid;
	}
	public void setMovieid(String movieid) {
		this.movieid = movieid;
	}
	public int getTotal_rating() {
		return total_rating;
	}
	public void setTotal_rating(int total_rating) {
		this.total_rating = total_rating;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
@Id
private String movieid;
private int total_rating;
private int count;
}
