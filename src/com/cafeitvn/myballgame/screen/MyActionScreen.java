package com.cafeitvn.myballgame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.cafeitvn.myballgame.MyBallGame;

public class MyActionScreen implements Runnable {

	MyBallGame game;
	int type;

	public MyActionScreen(MyBallGame game, int type) {
		// TODO Auto-generated constructor stub
		this.game = game;
		this.type = type;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (type == ActionType.GOTO_PLAY) {
			((Game) Gdx.app.getApplicationListener())
					.setScreen(new PlayScreen1(game));
		} else if (type == ActionType.GOTO_OPTIONS) {
			((Game) Gdx.app.getApplicationListener())
					.setScreen(new PlayScreen1(game));
		} else if (type == ActionType.GOTO_HELP) {
			((Game) Gdx.app.getApplicationListener())
					.setScreen(new PlayScreen1(game));
		}
	}

}
