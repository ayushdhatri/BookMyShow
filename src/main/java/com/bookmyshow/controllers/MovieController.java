package com.bookmyshow.controllers;

import com.bookmyshow.models.Movie;
import com.bookmyshow.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/getMovie")
    public List<Movie> getAllMovies(){
        return movieService.findAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable long id){
        return movieService.findMovieById(id).orElse(null);
    }

}
