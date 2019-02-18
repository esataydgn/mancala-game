package com.backbase.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.backbase.model.Player;

@Service
public class CreatorService {

	// Create the board for every player 6 pit and 1 house.
	// The first element keeps the turns of players(as player1 = 1, player2: 2)
	// pits have 6 stones
	public ArrayList<Integer> createBoard() {
		ArrayList<Integer> board = new ArrayList<Integer>();
		board.add(1);// initial player1 as the first player
		for (int i = 1; i <= 14; i++) {
			if (i % 7 == 0) {
				board.add(0);
			} else {
				board.add(6);
			}
		}
		return board;
	}

	// initialize the house and the pits which players can play with
	public Player createPlayer(Integer playerId) {

		Player player = new Player();
		player.setPlayerId(playerId);
		player.setPlayerPits(new ArrayList<>(6));
		player.setPlayerScore(0);

		// set players own pits
		for (int i = 1; i < 14; i++) {
			if (playerId == 1) {
				if (i < 7) {
					player.getPlayerPits().add(i);
				}
				player.setPlayerHouse(7);
			} else {
				if (i > 7) {
					player.getPlayerPits().add(i);
				}
				player.setPlayerHouse(14);
			}
		}

		return player;
	}

}
