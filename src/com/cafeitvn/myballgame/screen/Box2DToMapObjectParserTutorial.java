package com.cafeitvn.myballgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DToMapObjectParserTutorial implements Screen {

	private World world;
	private Box2DDebugRenderer boxRender;
	private OrthogonalTiledMapRenderer mapRender;
	private OrthographicCamera camera;
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		world = new World(new Vector2(0, -9.81f),true);
		boxRender = new Box2DDebugRenderer();
		camera = new OrthographicCamera();
		
		TiledMap map = new TmxMapLoader().load("map/Box2DMapObjectParserTutorial.tmx");
		
		Box2DMapObjectParser parser = new Box2DMapObjectParser();
		parser.load(world, map);
		
		
		mapRender = new OrthogonalTiledMapRenderer(map);
	
	
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		world.step(1/60f, 8, 3);
		
		mapRender.setView(camera);
		mapRender.render();
		boxRender.render(world, camera.combined);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.update();
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
		world.dispose();
		mapRender.dispose();
	}

}
