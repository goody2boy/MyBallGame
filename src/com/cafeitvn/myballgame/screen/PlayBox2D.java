package com.cafeitvn.myballgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class PlayBox2D implements Screen {

	
	static final float WORLD_TO_BOX = 0.01f;
	static final float BOX_TO_WORLD = 100f;
	World world;
	Box2DDebugRenderer boxRender;
	OrthographicCamera camera;
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		world = new World(new Vector2(0, -10), true);
		boxRender = new Box2DDebugRenderer();
		camera = new OrthographicCamera();
		//
		/*---------------------------Create ground - dynamic body--------------------------------------*/
		BodyDef bodyDef = new BodyDef();
		bodyDef.type= BodyType.DynamicBody;
		bodyDef.position.set(100, 300);
		//
		Body body = world.createBody(bodyDef);
		//
		CircleShape circle = new CircleShape();
		circle.setRadius(0.5f);
		//
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.friction= 0.4f;
		fixtureDef.density = 0.5f;
		fixtureDef.restitution = 0.6f;
		
		// 
//		Fixture fixture= body.createFixture(fixtureDef);
		/*---------------------------Create ground - static body--------------------------------------*/
		BodyDef groundbodyDef = new BodyDef();
		groundbodyDef.type= BodyType.StaticBody;
		groundbodyDef.position.set(0, 10);
		//
		Body groundbody = world.createBody(groundbodyDef);
		//
		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(camera.viewportWidth	, 10);
		//
		groundbody.createFixture(groundBox, 0.0f);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		boxRender.render(world, camera.combined);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		camera.viewportWidth = width/10f;
		camera.viewportHeight = height/10f;
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
