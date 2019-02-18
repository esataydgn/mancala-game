package com.backbase.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.backbase.model.Game;
import com.backbase.service.CreatorService;
import com.backbase.store.GameStore;

//this class response to find game, or create game.
@Repository
public class GameRepository {

	@Autowired
	private CreatorService creatorService;
	@Autowired
	private GameStore gameStore;


	public Game findOne(Integer id) {
		return gameStore.getGames().get(id);
	}

	public Game create() {
		// create an unique game and put the game store
		int id = (int) (System.currentTimeMillis() & 0xfffffff);
		Game game = new Game(id, creatorService.createBoard());
		gameStore.getGames().put(id, game);
		return game;
	}

}
