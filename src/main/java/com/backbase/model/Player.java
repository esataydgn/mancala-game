package com.backbase.model;

import java.util.List;

public class Player {

	private Integer playerId;
	private Integer playerScore;
	private List<Integer> playerPits;
	private Integer playerHouse;

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public Integer getPlayerScore() {
		return playerScore;
	}

	public void setPlayerScore(Integer playerScore) {
		this.playerScore = playerScore;
	}

	public List<Integer> getPlayerPits() {
		return playerPits;
	}

	public void setPlayerPits(List<Integer> playerPits) {
		this.playerPits = playerPits;
	}

	public Integer getPlayerHouse() {
		return playerHouse;
	}

	public void setPlayerHouse(Integer playerHouse) {
		this.playerHouse = playerHouse;
	}

//	//set ever players their own allowed pits to play with.
//	public Player(Integer playerId) {
//		this.setPlayerId(playerId);
//		setPlayerPits(new ArrayList<Integer>(6));
//		// set players own pits
//		for (int i = 1; i < 14; i++) {
//			if (playerId == 1) {
//				if (i < 7) {
//					getPlayerPits().add(i);
//				}
//				setPlayerHouse(7);
//			} else {
//				if (i > 7) {
//					getPlayerPits().add(i);
//				}
//				setPlayerHouse(14);
//			}
//		}
//	}

}
