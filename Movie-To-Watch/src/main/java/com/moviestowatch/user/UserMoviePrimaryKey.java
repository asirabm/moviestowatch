package com.moviestowatch.user;

import java.io.Serializable;
import java.util.Objects;

///import javax.persistence.PrimaryKeyJoinColumn;

public class UserMoviePrimaryKey implements Serializable {
	
 private String username;
 private String movieid;
 
 
public UserMoviePrimaryKey(String username, String movieid) {
	
	this.username = username;
	this.movieid = movieid;
}
public UserMoviePrimaryKey() {
	
}

public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserMoviePrimaryKey usermoviepri = (UserMoviePrimaryKey) o;
    return username.equals(usermoviepri.username) &&
            movieid.equals(usermoviepri.movieid);
}

@Override
public int hashCode() {
    return Objects.hash(username, movieid);
}
}





 
 

