package com.backbase.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backbase.model.response.ResponseBody;
import com.backbase.service.GameService;

@RestController
public class MancalaController {

	private final GameService gameService;

	// initialize all service layer in constructor
	public MancalaController(GameService gameService) {
		this.gameService = gameService;
	}

	@PostMapping("/games")
	public HttpEntity<ResponseBody> startGame(HttpServletRequest request) {
		ResponseBody game = gameService.create(request.getRequestURL().toString());
		return new ResponseEntity<>(game, HttpStatus.CREATED);
	}

	@PutMapping("/games/{gameId}/pits/{pit}")
	public HttpEntity<ResponseBody> move(@PathVariable Integer gameId, @PathVariable Integer pit, HttpServletRequest request) {
		String urlPath = request.getRequestURL().toString().replace(request.getServletPath(), "/games");
		ResponseBody game = gameService.move(gameId, pit, urlPath);
		return new ResponseEntity<>(game, HttpStatus.OK);
	}

	@GetMapping("/games/{gameId}")
	public HttpEntity<ResponseBody> getCreatedGame(@PathVariable Integer gameId, HttpServletRequest request) {
		String urlPath = request.getRequestURL().toString().replace(request.getServletPath(), "/games");
		ResponseBody game = gameService.retrieveGame(gameId, urlPath);
		return new ResponseEntity<>(game, HttpStatus.OK);
	}
}
