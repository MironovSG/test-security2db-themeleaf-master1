package com.example.movies.controller;

import com.example.movies.entity.Movie;
import com.example.movies.repository.MovieRepository;
import com.example.movies.service.OwnerActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.Optional;
@Slf4j
@Controller
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private OwnerActService ownerActService;

    @GetMapping("/list")
    public ModelAndView getALlMovies() {
        log.info("/list -> connection");
        ModelAndView mav = new ModelAndView("list-movies");
        mav.addObject("movies",movieRepository.findAll());
        ownerActService.savelog("Owner get all movies");
        return mav;
    }

    @GetMapping("/addMovieForm")
    public ModelAndView addMovieForm(){
        ModelAndView mav = new ModelAndView("add-movie-form");
        Movie movie = new Movie();
        mav.addObject("movie", movie);
        ownerActService.savelog("Owner add movie");
        return mav;
    }
    @RequestMapping(value = "/saveMovie", method = RequestMethod.GET)
    public String showForm(Movie movie){
        return "add-movie-form";
    }

    @RequestMapping(value = "/saveMovie", method = RequestMethod.POST)
    public String saveMovie(@Valid Movie movie, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "add-movie-form";
        }
        else {
            movieRepository.save(movie);
            ownerActService.savelog("Owner save movie");
            return "redirect:/list";
        }
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long movieId){
        ModelAndView mav = new ModelAndView("add-movie-form");
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Movie movie = new Movie();
        if(optionalMovie.isPresent()){
            movie = optionalMovie.get();
        }
        mav.addObject("movie", movie);
        ownerActService.savelog("Owner show update form");
        return mav;
    }

    @GetMapping("/deleteMovie")
    public String deleteMovie(@RequestParam Long movieId){
        movieRepository.deleteById(movieId);
        ownerActService.savelog("Owner delete movie");
        return "redirect:/list";
    }

  /*  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex){
        String error = "Данное поле должно содержать только цифры и не может быть пустым!";
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    } */

}

