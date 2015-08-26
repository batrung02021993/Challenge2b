package com.challenge2;

public class Movie {
	private int id;
	private String original_title;
	private String release_date;
	private String poster_path;
	private double vote_average;

	public Movie() {
		super();
	}

	public Movie(int id, String original_title, String release_date,
			String poster_path, double vote_average) {
		super();
		this.id = id;
		this.original_title = original_title;
		this.release_date = release_date;
		this.poster_path = poster_path;
		this.vote_average = vote_average;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOriginal_title() {
		return original_title;
	}

	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public double getVote_average() {
		return vote_average;
	}

	public void setVote_average(double vote_average) {
		this.vote_average = vote_average;
	}

}
