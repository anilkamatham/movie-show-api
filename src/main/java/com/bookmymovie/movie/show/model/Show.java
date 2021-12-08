package com.bookmymovie.movie.show.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "MOVIE_SHOW")
public class Show {
    
	@Id
  	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "show-generator")
 	@SequenceGenerator(name = "show-generator" , sequenceName = "show_generator", initialValue = 1000, allocationSize = 1)
	private int id;
	@Column(name = "SCREEN_ID")
	private int screenId;
	@Column(name = "THEATRE_ID")
	private int theatreId;
	@Column(name = "SHOW_CITY")
	private int cityId;
	@Column(name = "SHOW_DATE")
	private LocalDate showDate;
	@Column(name = "START_TIME")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime startTime;
	@Column(name = "MOVIE_ID")
	private int movieId;
	@Column(name = "SHOW_DURATION")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime duration;
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getScreenId() {
		return screenId;
	}
	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}
	public int getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public LocalTime getDuration() {
		return duration;
	}
	public void setDuration(LocalTime duration) {
		this.duration = duration;
	}
	public LocalDate getShowDate() {
		return showDate;
	}
	public void setShowDate(LocalDate showDate) {
		this.showDate = showDate;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
		
}
