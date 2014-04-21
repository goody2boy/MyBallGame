package com.cafeitvn.myballgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.cafeitvn.myballgame.utils.Box2DUtil;

public class MyTouchController implements GestureListener {

	Stage stage;
	TextureAtlas atlas;
	World world;
	Array<Image> imgArr = new Array<Image>();
	Array<Float> timeArr = new Array<Float>();
	// test Touch
	Vector2 startPoint;
	Vector2 endPoint;// = new Vector2(100, 100);

	public MyTouchController(Stage stage, TextureAtlas atlas, World world) {
		// TODO Auto-generated constructor stub
		this.stage = stage;
		this.atlas = atlas;
		this.world = world;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		if (imgArr.size < GameInfo.MAX_TOUCH) {
			startPoint = new Vector2(x, Gdx.graphics.getHeight() - y);
			Image i = new Image(atlas.findRegion("hinh3/square"));
			i.setPosition(startPoint.x, startPoint.y);
			imgArr.add(i);
			Rectangle rect = new Rectangle(i.getX(), i.getY(), i.getWidth(),
					i.getHeight());
			Box2DUtil.createRectStaticBody(world, rect);
			stage.addActor(i);
		}
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		if (imgArr.size < GameInfo.MAX_TOUCH) {
			endPoint = new Vector2(x, Gdx.graphics.getHeight() - y);
			imgArr.get(imgArr.size - 1).setSize(endPoint.x - startPoint.x, 100);
		}
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		if (imgArr.size < GameInfo.MAX_TOUCH) {
			endPoint = new Vector2(x, y);
			imgArr.get(imgArr.size - 1).setSize(endPoint.x - startPoint.x, 100);
			timeArr.add(0f);
		}
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
