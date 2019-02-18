package com.backbase.service.util;

import java.util.ArrayList;

import com.backbase.exception.GameNotFoundException;
import com.backbase.exception.InvalidPitNumberException;
import com.backbase.model.Game;
import com.backbase.model.Player;

public class GameValidator {

	public void isGameExist(Game game) {
		if (game == null) {
			throw new GameNotFoundException();
		}
	}

	public void isPitNumberValid(Integer pit) {
		if (pit < 1 || pit > 13 || pit == 7) {
			throw new InvalidPitNumberException("pit number is invalid");
		}
	}
	// check if the last stone at the player house give him another turn,if not

	// change the turn
	public boolean isLastStoneAtHouse(ArrayList<Integer> currentGameBoard, Player thePlayer, int theLastPit) {
		return theLastPit == thePlayer.getPlayerHouse();
	}

	public boolean isLastStone(int stones) {
		return stones == 1;
	}
	// check if the last stone at the an empty pit to capture the opposite pit's

	// stones
	public boolean isLastPitEmpty(ArrayList<Integer> currentGameBoard, Player thePlayer, int theLastPit) {
		return currentGameBoard.get(theLastPit) == 0 && pitCanBeSelected(thePlayer, theLastPit);
	}
	// Whether is a pit or the player who play house(KALAH-MANCALA)

	public boolean isOpponentHouse(Player thePlayer, int theNextPit) {
		return thePlayer.getPlayerHouse() != theNextPit && theNextPit % 7 == 0;
	}
	

	// check the selected pit is belong to the player who is playing.
	public boolean pitCanBeSelected(Player thePlayer, int pit) {
		return thePlayer.getPlayerPits().contains(pit);
	}

	public boolean isEmptyPit(ArrayList<Integer> currentGameBoard, int pit) {
		return currentGameBoard.get(pit) == 0;
	}
	
	// if The player who play this turn, has no stone in his pits game is finished

	public boolean isGameFinish(ArrayList<Integer> currentGameBoard, Player thePlayer) {
		boolean isFinish = true;
		for (Integer pit : thePlayer.getPlayerPits()) {
			if(currentGameBoard.get(pit) != 0) {
				isFinish = false;
			}
		}
		return isFinish;
	}

	public boolean isEndOfSowing(int stones) {
		return stones < 1;
	}
}
