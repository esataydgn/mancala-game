package com.backbase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backbase.model.Game;
import com.backbase.model.response.ResponseBody;
import com.backbase.repository.GameRepository;
import com.backbase.service.util.GameValidator;
import com.backbase.service.util.ResponseBuilder;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	private GameValidator gameValidator = new GameValidator();

	public ResponseBody create(String urlPath) {
		Game game = gameRepository.create();
		return ResponseBuilder.buildResponse(game, urlPath, false);
	}

	public ResponseBody move(Integer gameId, Integer pit, String urlPath) {
		Game game = gameRepository.findOne(gameId);
		// check pit and game is created or valid
		gameValidator.isGameExist(game);
		gameValidator.isPitNumberValid(pit);
		PlayGame playGame = new PlayGame();
		playGame.play(game, pit);

		// set appropriate return body as required

		return ResponseBuilder.buildResponse(game, urlPath, true);
	}

	public ResponseBody retrieveGame(Integer gameId, String urlPath) {
		Game game = gameRepository.findOne(gameId);
		gameValidator.isGameExist(game);
		return ResponseBuilder.buildResponse(game, urlPath, true);
	}
}
