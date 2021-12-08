package com.bookmymovie.movie.show.service;

import java.time.LocalDate;
import java.util.List;

import com.bookmymovie.movie.show.model.Show;
import com.bookmymovie.movie.show.model.Theatre;

public interface ShowService {

	public Show addShow(Show show);
	public List<Theatre> getTheatres(String city);
	public List<Show> getShows(int theatreId, int screenId);
	public Show getShow(int showId);
	public Show updateShow(Show show);
	public void deleteShow(int showId);
	public List<Show> getAllShows();
	public List<Show> getShowsByMovie(int movieId, int cityId, LocalDate showDate);
	public List<Show> getShowsByTheater(int theatreId);
	public List<Integer> getMoviesByTheatre(List<Integer> theatreIds);
	public List<Integer> getMoviesByTheatreId(int theatreId);
}
