package com.moviestowatch.user;

import java.io.Serializable;
import java.util.Objects;

///import javax.persistence.PrimaryKeyJoinColumn;

public class UserMoviePrimaryKey implements Serializable {
	
 private String username;
 private String movieid;
 private String logType;
 
 
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
public String getLogType() {
	return logType;
}
public void setLogType(String logType) {
	this.logType = logType;
}
public UserMoviePrimaryKey(String username, String movieid,String logType) {
	this.logType=logType;
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
            movieid.equals(usermoviepri.movieid) && logType.equals(usermoviepri.logType);
}

@Override
public int hashCode() {
    return Objects.hash(username, movieid);
}
}





 
 

