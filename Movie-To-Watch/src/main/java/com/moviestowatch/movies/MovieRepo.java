package com.moviestowatch.movies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface MovieRepo extends JpaRepository<Results,Integer> {


}
