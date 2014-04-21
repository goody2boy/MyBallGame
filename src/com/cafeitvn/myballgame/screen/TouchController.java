package com.cafeitvn.myballgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

public class TouchController implements InputProcessor {

	Stage stage;
	TextureAtlas atlas;
	Array<Image> imgArr = new Array<Image>();
	// test Touch
	Vector2 startPoint = new Vector2(200, 100);
	Vector2 endPoint = new Vector2(100, 100);

	public TouchController(Stage stage, TextureAtlas atlas) {
		// TODO Auto-generated constructor stub
		this.stage = stage;
		this.atlas = atlas;
		Image i = new Image(atlas.findRegion("hinh3/square"));
		
		imgArr.add(i);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		stage.addActor(imgArr.get(imgArr.size - 1));
		//
		startPoint.set(Gdx.input.getX(),
				Gdx.graphics.getHeight() - Gdx.input.getY());
		float xtouch = endPoint.x;
		float ytouch = endPoint.y;
//		if (Gdx.input.isTouched()) {

			float xnew = xtouch;
			float ynew = ytouch;
			if (Gdx.input.getX() > endPoint.x) {
				if (xtouch != Gdx.input.getX()) {
					xnew = Gdx.input.getX();
				}
				if (ytouch != Gdx.input.getY()) {
					ynew = Gdx.input.getY();
				}
			}
			endPoint = new Vector2(xnew, ynew);
			imgArr.get(imgArr.size - 1).setPosition(startPoint.x, startPoint.y);
			imgArr.get(imgArr.size - 1).setSize(
					endPoint.x - imgArr.get(imgArr.size - 1).getX(), 100);
			// img.size();
//		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		Image img = new Image(atlas.findRegion("hinh3/square"));
		imgArr.add(img);

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
