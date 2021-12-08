package com.bookmymovie.movie.show.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookmymovie.movie.show.model.Show;

public interface ShowDao extends JpaRepository<Show, Integer> {

	public List<Show> findByTheatreIdAndScreenId(int theatreId, int screenId);
	public List<Show> findByMovieIdAndCityIdAndShowDate(int movieId, int cityId, LocalDate showDate);
	public List<Show> findByTheatreId(int theatreId);
	@Query("SELECT e.movieId FROM Show e WHERE e.theatreId IN (:theatreIds)")
	public List<Integer> getMoviesByTheatreIds(@Param("theatreIds") List<Integer> theatreIds );
	@Query("SELECT e.movieId FROM Show e WHERE e.theatreId = ?1")
	public List<Integer> getMoviesByTheatreId(int theatreId);
}

