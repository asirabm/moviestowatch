package com.moviestowatch.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMovieRatingRepo extends JpaRepository<UserMovieRating, String> {

}
