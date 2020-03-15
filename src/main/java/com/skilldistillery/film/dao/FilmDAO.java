package com.skilldistillery.film.dao;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	Film findFilmById(int filmID);
	Actor findActorById(int actorId);
	List<Actor> findActorsByFilmId(int filmId);
	List<Film> findFilmsByActorId(int actorId);
	List<Film> findFilmByKeyword(String regexString);
	Film createFilm(Film film);
	String deleteFilm(int filmId);
	Film updateFilm(Film film);



}
