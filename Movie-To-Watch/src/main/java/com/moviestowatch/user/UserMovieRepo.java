package com.moviestowatch.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserMovieRepo extends JpaRepository<UserMovie, UserMoviePrimaryKey> {
 public Optional<List<UserMovie>> findByUsername(String username);
 public Optional<List<UserMovie>> findByMovieid(String movieid);
 public Optional<List<UserMovie>> findByUsernameAndLogType(String name,String type);
 
}
