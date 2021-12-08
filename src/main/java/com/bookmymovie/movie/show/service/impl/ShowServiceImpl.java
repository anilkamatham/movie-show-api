package com.bookmymovie.movie.show.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookmymovie.movie.show.exception.ErrorMessage;
import com.bookmymovie.movie.show.exception.ScreenOccupiedException;
import com.bookmymovie.movie.show.exception.ShowNotFoundException;
import com.bookmymovie.movie.show.model.Show;
import com.bookmymovie.movie.show.model.Theatre;
import com.bookmymovie.movie.show.repository.ShowDao;
import com.bookmymovie.movie.show.service.ShowService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ShowServiceImpl implements ShowService {

	@Autowired
	private ShowDao showDao;
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Show addShow(Show show) {
		if (!showCreationAllowed(show))
			throw new ScreenOccupiedException("Can not create show: Screen already occupied");
		return showDao.save(show);
	}

	@Override
	public List<Theatre> getTheatres(String city) {
		ResponseEntity<String> response = restTemplate.getForEntity("http://BOOKMYMOVIE-MOVIE-THEATRE-SERVICE/api/theater/byCity/" + city,
				String.class);
		ObjectMapper mapper = new ObjectMapper();
		List<Theatre> theatres = null;
		try {
			if (response.getStatusCode().is2xxSuccessful()) {
				theatres = mapper.readValue(response.getBody(), new TypeReference<List<Theatre>>() {
				});

			}
		} catch (JsonParseException je) {

		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return theatres;
	}

	@Override
	public Show getShow(int showId) {
		return showDao.findById(showId).orElseThrow(() -> new ShowNotFoundException("Show does not exits"));
	}
	
	@Override
	public List<Show> getShows(int theatreId, int screenId) {
		return showDao.findByTheatreIdAndScreenId(theatreId, screenId);
	}
	
	@Override
	public Show updateShow(Show show) {
		 if(showDao.existsById(show.getId())) {
			 return showDao.save(show);
		 }
		 else
			 throw new ShowNotFoundException("Show does not exits");
			 
	}

	@Override
	public void deleteShow(int showId) {
		 if(showDao.existsById(showId)) {
			 showDao.deleteById(showId);
		 }
		 else
			 throw new ShowNotFoundException("Show does not exits");
		
	}

	@Override
	public List<Show> getAllShows() {
		return showDao.findAll();
	}
	
	
	private boolean showCreationAllowed(Show show) {
		List<Show> shows = showDao.findByTheatreIdAndScreenId(show.getTheatreId(), show.getScreenId());
		if (shows != null & shows.size() > 0) {
			for (Show currentShow : shows) {
				LocalTime lt = currentShow.getStartTime();
				lt = lt.plusHours(currentShow.getDuration().getHour())
						.plusMinutes(currentShow.getDuration().getMinute());
				if (lt.isAfter(show.getStartTime()))
					return false;
			}
		}
		return true;
	}

	@Override
	public List<Show> getShowsByMovie(int movieId, int cityId, LocalDate showDate) {
		return showDao.findByMovieIdAndCityIdAndShowDate(movieId, cityId,showDate);
	}

	@Override
	public List<Show> getShowsByTheater(int theatreId) {
		return showDao.findByTheatreId(theatreId);
		
	}

	@Override
	public List<Integer> getMoviesByTheatre(List<Integer> theatreIds) {
		return showDao.getMoviesByTheatreIds(theatreIds);
	}

	@Override
	public List<Integer> getMoviesByTheatreId(int theatreId) {
		return showDao.getMoviesByTheatreId(theatreId);
	}
	
}
