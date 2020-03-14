package com.skilldistillery.film.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDao;
<<<<<<< Updated upstream
=======
	
	@RequestMapping(path = "GetFilmData.do", params = "FILMID", method = RequestMethod.GET)
	public ModelAndView getByFilmID(@RequestParam("FILMID") int filmid) {
		System.out.println(filmid);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/result.jsp");
		mv.addObject("film", filmDao.findFilmById(filmid));
		return mv;
	}
>>>>>>> Stashed changes

	@RequestMapping(path = "AddFilm.do", method = RequestMethod.POST)
	public ModelAndView addFilm(Film film) {
		ModelAndView mv = new ModelAndView();
		Film newFilm = filmDao.createFilm(film);
		mv.addObject("film", newFilm);
		mv.setViewName("/WEB-INF/addedFilmDisplay.jsp");
		return mv;
	}

	@RequestMapping(path = "GetFilmData.do", method = RequestMethod.GET)
	public ModelAndView getFilmById(@RequestParam("id") int n) {
		ModelAndView mv = new ModelAndView();
		Film filmName = filmDao.findFilmById(n);
		mv.addObject("film", filmName);
		mv.setViewName("WEB-INF/filmIdDisplay.jsp");
		return mv;
	}

	@RequestMapping(path = "GetFilmData.do", method = RequestMethod.GET)
	public ModelAndView getFilmByKeyword(@RequestParam("keyword") String keyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> filmList = filmDao.findFilmByKeyword(keyword);
		mv.addObject("films", filmList);
		mv.setViewName("WEB-INF/filmKeywordDisplay.jsp");
		return mv;
	}
	//@RequestMapping(path = "")
	
	//delete FILM
	//EDIT FILM
	//THE CAST ACTORS
	// 6 total
}
