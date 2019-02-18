package com.backbase.service.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.backbase.model.Game;
import com.backbase.model.response.ResponseBody;

public class ResponseBuilder {

	// create response body of all services
	public static ResponseBody buildResponse(Game game, String urlPath, boolean showStatus) {

		ResponseBody responseObject = new ResponseBody();
		responseObject.setId(game.getId());

		responseObject.setUri(urlPath + "/" + game.getId());
		responseObject.setStatus(new HashMap<Integer, String>());
		ArrayList<Integer> board = game.getBoard();

		if (showStatus) {
			for (int i = 1; i <= board.size() - 1; i++) {
				responseObject.getStatus().put(i, board.get(i).toString());
			}
		}
		return responseObject;
	}
}
