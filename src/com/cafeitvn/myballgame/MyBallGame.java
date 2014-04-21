package com.cafeitvn.myballgame;

import com.badlogic.gdx.Game;
import com.cafeitvn.myballgame.screen.MenuScreen;
import com.cafeitvn.myballgame.screen.PlayScreen;
import com.cafeitvn.myballgame.screen.PlayScreen1;

public class MyBallGame extends Game {

	
	
	@Override
	public void create() {		
//		super.setScreen(new PlayScreen());
//		super.setScreen(new PlayScreen1(this));
//		super.setScreen(new PlayBox2D());
		super.setScreen(new MenuScreen(this));
		
	}

	@Override
	public void dispose() {
	super.dispose();
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
	
}
