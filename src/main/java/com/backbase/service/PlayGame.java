package com.backbase.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.backbase.exception.InvalidPitNumberException;
import com.backbase.model.Game;
import com.backbase.model.Player;
import com.backbase.service.util.GameValidator;

public class PlayGame {

	public GameValidator checker = new GameValidator();
	private CreatorService creatorService = new CreatorService();

	public ArrayList<Integer> play(Game selectedGame, int pit) {
		ArrayList<Integer> gameBoard = selectedGame.getBoard();

		// find who is the player which have to play
		Player thePlayer = creatorService.createPlayer(gameBoard.get(0));

		// if wrong player is playing or an empty pit is chosen then don't do anything

		checkPitStatus(thePlayer, gameBoard, pit);

		// make the movements
		int selectedPitStones = gameBoard.get(pit);
		gameBoard.set(pit, 0);
		ArrayList<Integer> lastGameBoard = sowingStones(gameBoard, thePlayer, pit, selectedPitStones);

		// collect all stones of opponent's pits and finish the game
		if (checker.isGameFinish(gameBoard, thePlayer)) {
			collectAllStonesEOG(gameBoard, thePlayer);
		}
		return lastGameBoard;
	}

	// sowing stones to pits and the player own house.
	public ArrayList<Integer> sowingStones(ArrayList<Integer> board, Player thePlayer, int pit, int stones) {
		if (!checker.isEndOfSowing(stones)) {
			for (int i = pit + 1; i < 15; i++) {
				if (!checker.isOpponentHouse(thePlayer, i)) { // pass trough opponent house(Kalah)
					if (checker.isLastStone(stones)) {
						setNextPlayer(board, thePlayer, i);
						if (checker.isLastPitEmpty(board, thePlayer, i)) { // capture stones if the last pit is empty
							stones--;
							capturePit(board, thePlayer, i);
							continue;
						}
					} else if (checker.isEndOfSowing(stones)) {
						break;
					}
					board.set(i, board.get(i) + 1);
					stones--;
				}
			}
			// if u reached the end of board but you still have stones continue to
			// sowing.this time you can put stone to the selected pit.
			sowingStones(board, thePlayer, 0, stones);
		}
		return board;
	}

	// collect all the opponent side stones and put his own house-KALAH at the end
	// of game (EOG)
	public ArrayList<Integer> collectAllStonesEOG(ArrayList<Integer> currentGameBoard, Player thePlayer) {
		// create the opponent to collect his/her side's stones
		Player opponentPlayer = creatorService.createPlayer(3 - thePlayer.getPlayerId());
		int allStonsOfSides = currentGameBoard.get(opponentPlayer.getPlayerHouse())
				+ collectAllStonesOfOtherSide(currentGameBoard, opponentPlayer);

		currentGameBoard.set(opponentPlayer.getPlayerHouse(), allStonsOfSides);
		clearAllPits(currentGameBoard, opponentPlayer);

		// set the players score by getting from their House at the end of game
		thePlayer.setPlayerScore(currentGameBoard.get(thePlayer.getPlayerHouse()));
		opponentPlayer.setPlayerScore(currentGameBoard.get(opponentPlayer.getPlayerHouse()));

		return currentGameBoard;
	}

	// give extra turn if the last stone is sowed in player House. If not change the
	// player
	public ArrayList<Integer> setNextPlayer(ArrayList<Integer> board, Player thePlayer, int lastPit) {
		if (checker.isLastStoneAtHouse(board, thePlayer, lastPit)) {
			board.set(0, thePlayer.getPlayerId());
		} else {
			board.set(0, thePlayer.getPlayerId() == 1 ? 2 : 1);
		}
		return board;
	}

	// capture the opponent pit and collect the stones then put in your House
	public ArrayList<Integer> capturePit(ArrayList<Integer> board, Player thePlayer, int selectedPit) {
		int captureTheStones = 1;
		int capturedIndex = findCapturedIndex(selectedPit);
		captureTheStones += board.get(capturedIndex);
		board.set(capturedIndex, 0);
		board.set(thePlayer.getPlayerHouse(), board.get(thePlayer.getPlayerHouse()) + captureTheStones);
		return board;
	}

	public int findCapturedIndex(int pitId) {
		HashMap<Integer, Integer> captureIndex = new HashMap<Integer, Integer>();
		for (int i = 1; i < 14; i++) {
			captureIndex.put(i, 14 - i);
		}
		return captureIndex.get(pitId);
	}

	// when a player finish a game (when he/she finished the stones of his/her side)
	// the opponent collect all the stone of his/her side and put the his/her
	// House-KALAH
	public int collectAllStonesOfOtherSide(ArrayList<Integer> currentGameBoard, Player thePlayer) {
		int allStonsOfyourSide = 0;
		for (Integer pit : thePlayer.getPlayerPits()) {
			allStonsOfyourSide += currentGameBoard.get(pit);
		}
		return allStonsOfyourSide;
	}

	public void clearAllPits(ArrayList<Integer> currentGameBoard, Player thePlayer) {
		for (Integer pit : thePlayer.getPlayerPits()) {
			currentGameBoard.set(pit, 0);
		}
	}

	private void checkPitStatus(Player thePlayer, ArrayList<Integer> gameBoard, int pit) {
		if (!checker.pitCanBeSelected(thePlayer, pit)) {
			throw new InvalidPitNumberException(
					"pit number is wrong. The pits you can choose : " + thePlayer.getPlayerPits());
		} else if (checker.isEmptyPit(gameBoard, pit)) {
			throw new InvalidPitNumberException("the selected pit is empty. Please choose another pit.");
		}
	}

}
