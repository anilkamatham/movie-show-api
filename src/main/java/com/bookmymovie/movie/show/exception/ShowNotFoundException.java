package com.bookmymovie.movie.show.exception;

public class ShowNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7879525570668426145L;

	public ShowNotFoundException(String msg) {
		super(msg);
	}
}
