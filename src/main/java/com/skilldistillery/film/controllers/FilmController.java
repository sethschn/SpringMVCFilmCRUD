package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDao;

	@RequestMapping(path = "AddFilm.do", method = RequestMethod.POST)
	public ModelAndView addFilm(Film film, RedirectAttributes redir) {
		Film newFilm = filmDao.createFilm(film);
		ModelAndView mv = new ModelAndView();
		redir.addFlashAttribute("film", newFilm);
		mv.setViewName("redirect:FilmAdded.do");
		return mv;
	}

	@RequestMapping(path = "FilmAdded.do", method = RequestMethod.GET)
	public ModelAndView createdFilm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/addedFilmDisplay.jsp");
		return mv;
	}

	@RequestMapping(path = "GetFilmDataById.do", method = RequestMethod.GET)
	public ModelAndView getFilmById(@RequestParam("id") int filmId) {
		ModelAndView mv = new ModelAndView();
		Film filmName = filmDao.findFilmById(filmId);
		mv.addObject("film", filmName);
		mv.setViewName("WEB-INF/filmIdDisplay.jsp");
		return mv;
	}

	@RequestMapping(path = "SearchFilmKeyword.do", method = RequestMethod.GET)
	public ModelAndView getFilmByKeyword(@RequestParam String keywords) {
		ModelAndView mv = new ModelAndView();
		List<Film> filmList = filmDao.findFilmByKeyword(keywords);
		mv.addObject("films", filmList);
		mv.setViewName("WEB-INF/filmKeywordDisplay.jsp");
		return mv;
	}

	@RequestMapping(path = "UpdateFilmData.do", method = RequestMethod.POST)
	public ModelAndView updateFilmData(Film film) {
		ModelAndView mv = new ModelAndView();
		System.out.println(film);
		Film filmInfo = filmDao.updateFilm(film);
		System.out.println("*************" + filmInfo);
		mv.addObject("film", filmInfo);
		mv.setViewName("WEB-INF/updatedFilmInfo.jsp");
		return mv;
	}

	@RequestMapping(path = "DeleteFilm.do", method = RequestMethod.POST)
	public ModelAndView deleteFilm(@RequestParam("filmid") int filmId) {
		ModelAndView mv = new ModelAndView();
		String deletedFilm = filmDao.deleteFilm(filmId);
		mv.addObject("deleted", deletedFilm);
		mv.setViewName("WEB-INF/delete.jsp");
		return mv;

	}
}
