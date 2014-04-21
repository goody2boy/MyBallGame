package com.cafeitvn.myballgame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite implements InputProcessor{

	/**
	 * the movement
	 */
	private Vector2 verlocity = new Vector2();
	private float speed = 60 * 2, gravity = 60 * 1.8f, animationTime = 0;
	private Animation still,left,right;
	private TiledMapTileLayer collisionLayer;
	private String blockedKey = "blocked";
	
	public Player(Sprite sprite) {
		super(sprite);
	}

	public void draw(SpriteBatch spriteBatch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}

	private void update(float deltaTime) {
		// TODO Auto-generated method stub
		verlocity.y -= gravity * deltaTime;
		if (verlocity.y > speed) {
			verlocity.y = speed;
		} else if (verlocity.y < -speed) {
			verlocity.y = -speed;
		}
		
		setX(getX() + verlocity.x * deltaTime);
		setY(getY() + verlocity.y * deltaTime);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
//		switch (keycode) {
//		case Keys.W:
//			
//			break;
//		case Keys.A:
//			verlocity.x = -speed;
//			break;
//		case Keys.D:
//			verlocity.x = speed;
//			break;
//		default:
//			break;
//		}
		return false;
		
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
//		switch (keycode) {
//		case Keys.W:
//			verlocity.y = speed; 
//			break;
//		case Keys.A:
//			verlocity.x = 0;
//			break;
//		case Keys.D:
//			verlocity.x = 0;
//			break;
//		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		switch (character) {
		case 'w':
		case 'W':
			verlocity.y = speed;
			break;
		case 's':
		case 'S':
			verlocity.y = -speed;
			break;
		case 'a':
		case 'A':
			verlocity.x = -speed;
			break;
		case 'd':
		case 'D':
			verlocity.x = speed;
			break;
		default:
			break;
		}
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
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
