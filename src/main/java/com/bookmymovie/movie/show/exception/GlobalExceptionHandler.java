package com.bookmymovie.movie.show.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private ErrorMessage errorMessage;
	
	@ExceptionHandler(value = ShowNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleMoviesNotFoundException(ShowNotFoundException sc){
				
		errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
		Map<String, Object> error = new HashMap<>();
		error.put("error", sc.getMessage());
		errorMessage.setErrorMessages(error);
		return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(value = ScreenOccupiedException.class)
	public ResponseEntity<ErrorMessage> handleMoviesNotFoundException(ScreenOccupiedException th){
				
		errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
		Map<String, Object> error = new HashMap<>();
		error.put("error", th.getMessage());
		errorMessage.setErrorMessages(error);
		return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.NOT_FOUND);
		
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		errorMessage.setStatus(status.value());		
		Map<String, Object> error = new HashMap<>();
		List<String> fieldErrors =ex.getBindingResult().getFieldErrors().stream().map(msg -> msg.getDefaultMessage()).collect(Collectors.toList());
		error.put("error", fieldErrors);
		errorMessage.setErrorMessages(error);
		return new ResponseEntity<>(errorMessage,headers,status);
		
	}
}
