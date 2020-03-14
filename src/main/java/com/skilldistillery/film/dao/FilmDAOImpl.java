package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Component
public class FilmDAOImpl implements FilmDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		try {
			String sql = "select * from film where film.id = ?";
			String user = "student";
			String pass = "student";
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs2 = stmt.executeQuery();
			while (rs2.next() && !rs2.wasNull()) {
				Integer filmNumber = rs2.getInt("id");
				String title = rs2.getString("title");
				String desc = rs2.getString("description");
				Integer releaseYear = (int) rs2.getShort("release_year");
				int langId = rs2.getInt("language_id");
				int rentDur = rs2.getInt("rental_duration");
				double rate = rs2.getDouble("rental_rate");
				int length = rs2.getInt("length");
				double repCost = rs2.getDouble("replacement_cost");
				String rating = rs2.getString("rating");
				String features = rs2.getString("special_features");

				film = new Film(filmNumber, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features);

				List<Actor> actors = findActorsByFilmId(filmNumber);
				film.setActors(actors);
				film.setLangauge(findLanguageByLanguageId(langId));

			}
			rs2.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("findFilmById");
			System.out.println(e);

		}

		return film;
	}

	public Actor findActorById(int actorId) {
		Actor actor = null;
		try {

			String sql = "select actor.first_name, actor.last_name, actor.id from actor where actor.id = ?";
			String user = "student";
			String pass = "student";
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next() && !(rs1.wasNull())) {
				int actorNumber = rs1.getInt("id");
				String actorFirstName = rs1.getString("first_name");
				String actorLastName = rs1.getString("last_name");
				actor = new Actor(actorNumber, actorFirstName, actorLastName);

			}
			rs1.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("findActorById");
			System.out.println(e);
			return null;

		}
		return actor;

	}

	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();

		try {
			String sql = "select actor.id from actor join film_actor on actor.id = film_actor.actor_id join film on film.id = film_actor.film_id where film.id = ?";
			String user = "student";
			String pass = "student";
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs3 = stmt.executeQuery();
			while (rs3.next() && !(rs3.wasNull())) {
				actors.add(findActorById(rs3.getInt("id")));

			}
			rs3.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("findActorsByFilmId");
			System.out.println(e);
		}
		return actors;
	}

	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		String sql = "SELECT film.id, film.title, film.description, film.release_year, "
				+ "film.language_id, film.rental_duration, film.rental_rate, film.length, "
				+ "film.replacement_cost, film.rating, film.special_features" + " FROM film JOIN film_actor "
				+ "ON film.id = film_actor.film_id " + "WHERE film_actor.actor_id = ?";

		try {
			String user = "student";
			String pass = "student";
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs4 = stmt.executeQuery();
			while (rs4.next()) {
				films.add(findFilmById(rs4.getInt("id")));

			}
			rs4.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("findFilmsByActorId");
			System.out.println(e);

		} catch (NullPointerException e) {
			System.out.println("NullPointer in findFilmsByActorId");

		}
		return films;
	}

	public List<Film> findFilmByKeyword(String regexString) {
		List<Film> films = new ArrayList<>();
		try {
			String sql = "select * from film where film.title like ? or film.description like ?";
			String user = "student";
			String pass = "student";
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + regexString + "%");
			stmt.setString(2, "%" + regexString + "%");
			ResultSet rs5 = stmt.executeQuery();
			while (rs5.next()) {
				films.add(findFilmById(rs5.getInt("id")));
			}
			rs5.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return films;
	}

	private String findLanguageByLanguageId(int langId) {
		String language = "";
		try {
			String sql = "select language.name from language join film on language.id = film.language_id where language.id = ?";
			String user = "student";
			String pass = "student";
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, langId);
			ResultSet rs6 = stmt.executeQuery();
			while (rs6.next()) {
				language = rs6.getString("language.name");
			}
			rs6.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Language not found.");
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}

		return language;
	}

	@Override
	public Film createFilm(Film film) {
		Film newFilm = null;
		Connection conn = null;

		try {
			String sql = "insert into film (title, description, release_year, language_id, "
					+ "rental_duration, rental_rate, length, replacement_cost, rating, special_features)" + " VALUES("
					+ ", ?" // 1 Title - String
					+ ", ?" // 2 Description - String
					+ ", ?" // 3 Release Year - Int
					+ ", 1" // Language ID - Int
					+ ", ?" // 4 Rental Duration - Int
					+ ", ?" // 5 Rental Rate - Double
					+ ", ?" // 6 Length - Int
					+ ", ?" // 7 Replacement Cost - Double
					+ ", ?" // 8 Rating - String
					+ ", ?)"; // 9 Special Features - String

			String user = "student";
			String pass = "student";
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getYear());
			stmt.setInt(4, film.getRentalDuration());
			stmt.setDouble(5, film.getRentalRate());
			stmt.setInt(6, film.getLength());
			stmt.setDouble(7, film.getReplacementCost());
			stmt.setString(8, film.getRating());
			stmt.setString(9, film.getSpecialFeatures());
			int uc = stmt.executeUpdate();
			if (uc == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				film.setId(keys.getInt(1));
//				return film;
			}
			conn.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting actor " + film);
		}

		// TODO Auto-generated method stub
		return film;
	}

	@Override
	public String deleteFilm(Film film) {
		String returnStatement = "Film not deleted";
		Connection conn = null;
		String sql = "";
		try {
			sql = "DELETE FROM film where film.id = ?";

			String user = "student";
			String pass = "student";
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());

			String joinerFilmID = "select film_actor.film.id from film_actor where film_actor.film_id = ?";
			PreparedStatement stmt2 = conn.prepareStatement(joinerFilmID);
			stmt2.setInt(1, film.getId());
			ResultSet joinResults = stmt.executeQuery();
			if (joinResults.wasNull()) {
				int uc = stmt.executeUpdate();
				if (uc == 1) {
					returnStatement = film.getTitle() + " successfully removed from database.";
				}
			}

		} catch (SQLException e) {

		}
		return returnStatement;

		// TODO Auto-generated method stub

	}

	@Override
	public Film updateFilm(Film film) {
		// TODO Auto-generated method stub

		return film;

	}

}
