package com.skilldistillery.film.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.skilldistillery.film.dao.FilmDAO;

@Controller
public class FilmController {
	
	@Autowired
	private FilmDAO filmDao;
	
	

}