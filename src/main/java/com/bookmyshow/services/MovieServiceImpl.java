package com.bookmyshow.services;

import com.bookmyshow.models.Movie;
import com.bookmyshow.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findMovieById(long id) {
        return movieRepository.findMovieById(id);
    }
}
