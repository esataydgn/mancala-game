package com.backbase.model;

import java.util.ArrayList;

public class Game {
	private Integer id;
	private ArrayList<Integer> board;

	public Game(Integer id, ArrayList<Integer> board) {
		this.id = id;
		this.board = board;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArrayList<Integer> getBoard() {
		return board;
	}

	public void setBoard(ArrayList<Integer> board) {
		this.board = board;
	}
}
