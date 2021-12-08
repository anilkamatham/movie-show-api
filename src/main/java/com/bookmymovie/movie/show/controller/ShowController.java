package com.bookmymovie.movie.show.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmymovie.movie.show.model.Show;
import com.bookmymovie.movie.show.model.Theatre;
import com.bookmymovie.movie.show.service.ShowService;

@RestController
@RequestMapping("/api/show")
public class ShowController {

	@Autowired
	private ShowService showService;
	
	@GetMapping("/theaters/{cityName}")
	public ResponseEntity<List<Theatre>> getTheatresByCity(@PathVariable String cityName){
	   return new ResponseEntity<List<Theatre>>(showService.getTheatres(cityName), HttpStatus.OK);	
	}
	
	@PostMapping("/add")
	public ResponseEntity<Show> addShow(@RequestBody Show show){
		System.out.println("inside addshow");
		return new ResponseEntity<Show>(showService.addShow(show), HttpStatus.CREATED);
	}
	
	@GetMapping("/getShow/{showId}")
	public ResponseEntity<Show> getShow(@PathVariable int showId ){
		return new ResponseEntity<Show>(showService.getShow(showId), HttpStatus.OK);
	}
	
	@GetMapping("/getShows/{theatreId}/{screenId}")
	public ResponseEntity<List<Show>> getShows(@PathVariable("theatreId") int theatreId, @PathVariable("screenId") int screenId){
	    return new ResponseEntity<List<Show>>(showService.getShows(theatreId, screenId), HttpStatus.OK);	
	}
	
	@GetMapping("/getShowsByMovie/{movieId}/{cityId}/{showDate}")
	public ResponseEntity<List<Show>> getShowsByMovie(@PathVariable("movieId") int movieId, @PathVariable("cityId") int cityId, @PathVariable("showDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate showDate){
	    return new ResponseEntity<List<Show>>(showService.getShowsByMovie(movieId,cityId,showDate), HttpStatus.OK);	
	}
	
	@GetMapping("/getShowsByTheatre/{theatreId}")
	public ResponseEntity<List<Show>> getShowsByTheatre(@PathVariable("theatreId") int theatreId){
	    return new ResponseEntity<List<Show>>(showService.getShowsByTheater(theatreId), HttpStatus.OK);	
	}
	
	@PutMapping("/update")
	public ResponseEntity<Show> updateShow(@RequestBody Show show){
		return new ResponseEntity<Show>(showService.updateShow(show), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{showId}")
	public void DeleteShow(@PathVariable int showId){
		showService.deleteShow(showId);
	}
	
	
	@GetMapping("/getAllShows")
	public ResponseEntity<List<Show>> getAllShows(){
		return new ResponseEntity<List<Show>>(showService.getAllShows(),HttpStatus.OK);
	}
	
	@GetMapping("/getMovies/{theatreId}")
	public ResponseEntity<List<Integer>> getMoviesByTheatreId(@PathVariable int theatreId){
		return new ResponseEntity<List<Integer>>(showService.getMoviesByTheatreId(theatreId),HttpStatus.OK);
	}
	
		
}
