package com.cafeitvn.myballgame.screen;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class InputDemo2 implements ApplicationListener, InputProcessor {
	private SpriteBatch batch;
	private BitmapFont font;
	private String message = "Touch something already!";
	private int w, h;
	Array<Vector2> touch = new Array<Vector2>();
	Vector2 temp = new Vector2();

	class TouchInfo {
		public float touchX = 0;
		public float touchY = 0;
		public boolean touched = false;
	}

	private Map<Integer, TouchInfo> touches = new HashMap<Integer, TouchInfo>();

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont(
				Gdx.files.internal("data/skinpacker/font/android.fnt"), false);
		font.setColor(Color.RED);
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		Gdx.input.setInputProcessor(this);
		for (int i = 0; i < 5; i++) {
			touches.put(i, new TouchInfo());
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();

		message = "";
//		for (int i = 0; i < 5; i++) {
//			if (touches.get(i).touched)
//				message += "Finger:" + Integer.toString(i) + "touch at:"
//						+ Float.toString(touches.get(i).touchX) + ","
//						+ Float.toString(touches.get(i).touchY) + "\n";
//
//		}
		float x = temp.x;
		float y = temp.y;
		if (Gdx.input.isTouched()) {
			float xnew =x;
			float ynew =y;
			if (x != Gdx.input.getX()) {
				xnew = Gdx.input.getX();
			}
			if (y != Gdx.input.getY()) {
				ynew = Gdx.input.getY();
			}
			temp = new Vector2(xnew,ynew);
			touch.add(new Vector2(xnew,ynew));
			message += "("+ xnew +"," +ynew+ ")"   ;
		}
		

		TextBounds tb = font.getBounds(message);
		float messx = w / 2 - tb.width / 2;
		float messy = h / 2 + tb.height / 2;
		font.drawMultiLine(batch, message, messx, messy);

		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
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
		if (pointer < 5) {
			touches.get(pointer).touchX = screenX;
			touches.get(pointer).touchY = screenX;
			touches.get(pointer).touched = true;
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (pointer < 5) {
			touches.get(pointer).touchX = 0;
			touches.get(pointer).touchY = 0;
			touches.get(pointer).touched = false;
		}
		return true;
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