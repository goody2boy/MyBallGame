package com.cafeitvn.myballgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.cafeitvn.myballgame.MyBallGame;

public class BaseScreen implements Screen {

	Stage stage ;
	MyBallGame game;
	
	public OrthographicCamera camera;
	public SpriteBatch batch;
	private TextureAtlas atlas;
	private TextureAtlas skinAtlas;
	private Skin skin;
	
	public BaseScreen(MyBallGame game) {
		// TODO Auto-generated constructor stub
		this.game = game;
		this.stage = new Stage(GameInfo.VIEW_PORT_WIDTH, GameInfo.VIEW_PORT_HEIGHT, true);
	}
	
	public OrthographicCamera getCamera(){
		if(camera==null){
			camera=new OrthographicCamera(800, 480);
		}
		return camera;
	}
	
	public SpriteBatch getBatch() {
		if (batch == null) {
			batch = new SpriteBatch();
		}
		return batch;
	}
	
	public TextureAtlas getAtlas(){
		if(atlas==null){
			atlas=new TextureAtlas(Gdx.files.internal("data/datapacker/FileMoTa.txt"));
		}
		return atlas;
	}
	
	
	public TextureAtlas getSkinAtlas(){
		if(skinAtlas==null){
			skinAtlas=new TextureAtlas(Gdx.files.internal("data/skinpacker/uiskin.atlas"));
		}
		return skinAtlas;
	}
	
	public Skin getSkin(){
		if(skin==null){
			skin=new Skin(Gdx.files.internal("data/skinpacker/uiskin.json"), getSkinAtlas());
		}
		return skin;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		// (1) process the game logic

        // update the actors
        stage.act( delta );

        // (2) draw the result

        // clear the screen with the given RGB color (black)
        Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        // draw the actors
        stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		stage.setViewport( width, height, true );
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		// set the stage as the input processor
        Gdx.input.setInputProcessor( stage );
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
