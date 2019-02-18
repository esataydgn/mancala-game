package com.backbase.store;

import com.backbase.model.Game;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class GameStore {
	//keep the all created games in order to game ids
    private HashMap<Integer, Game> games = new HashMap<>();

    public HashMap<Integer, Game> getGames() {
        return games;
    }

    public void setGames(HashMap<Integer, Game> games) {
        this.games = games;
    }
}
