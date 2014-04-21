package com.cafeitvn.myballgame.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BallEntity implements InputProcessor{

	
	public enum State{
		FLYING, COLLISION,DYING
	}
	private State state = State.FLYING;
	private float denisty = 0f; // do dam dac -> bong xoay tit neu =0
	private float friction = 0f; // ma sat
	private float restitution = 1f; // do dan hoi

	// position : ban dau =0,0
	private float x = 0;
	private float y = 0;
	private float radius = 0.1f;

	//
	private float linearDamping = 0f; // do mem cua bong
	private float angularVelocity = 0f; //-150 goc tac dung luc gay ra cua van
											// toc
	private float linearVelocity_x, linearVelocity_y; // = (x,y) huong cua van
														// toc
	// Graphic for ball

	private Sprite sprite;
	
	public BallEntity(Sprite sprite, float x, float y) {
		this.x =x;
		this.y =y;
		this.sprite = sprite;
	}

	public void draw(SpriteBatch spriteBatch) {
		update(Gdx.graphics.getDeltaTime());
		sprite.draw(spriteBatch);
	}
	private void update(float deltaTime) {
		// TODO Auto-generated method stub
//		verlocity.y -= gravity * deltaTime;
//		if (verlocity.y > speed) {
//			verlocity.y = speed;
//		} else if (verlocity.y < -speed) {
//			verlocity.y = -speed;
//		}
//		
//		setX(getX() + verlocity.x * deltaTime);
//		setY(getY() + verlocity.y * deltaTime);
	}
	
	public float getDenisty() {
		return denisty;
	}

	public void setDenisty(float denisty) {
		this.denisty = denisty;
	}

	public float getFriction() {
		return friction;
	}

	public void setFriction(float friction) {
		this.friction = friction;
	}

	public float getRestitution() {
		return restitution;
	}

	public void setRestitution(float restitution) {
		this.restitution = restitution;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float getLinearDamping() {
		return linearDamping;
	}

	public void setLinearDamping(float linearDamping) {
		this.linearDamping = linearDamping;
	}

	public float getAngularVelocity() {
		return angularVelocity;
	}

	public void setAngularVelocity(float angularVelocity) {
		this.angularVelocity = angularVelocity;
	}

	public float getLinearVelocity_x() {
		return linearVelocity_x;
	}

	public void setLinearVelocity_x(float linearVelocity_x) {
		this.linearVelocity_x = linearVelocity_x;
	}

	public float getLinearVelocity_y() {
		return linearVelocity_y;
	}

	public void setLinearVelocity_y(float linearVelocity_y) {
		this.linearVelocity_y = linearVelocity_y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
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
