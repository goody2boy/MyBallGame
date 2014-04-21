package com.cafeitvn.myballgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cafeitvn.myballgame.MyBallGame;

public class MenuScreen extends BaseScreen implements Screen {

	
	TextButton buttonPlay;
	TextButton buttonHelp;
    TextButtonStyle textButtonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;
    
	public MenuScreen(MyBallGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	Vector2 touchdown ;
	Vector2 touchup ;

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("data/datapacker/FileMoTa.txt"));
        skin.addRegions(buttonAtlas);
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
//        textButtonStyle.up = skin.getDrawable("up-button");
//        textButtonStyle.down = skin.getDrawable("down-button");
//        textButtonStyle.checked = skin.getDrawable("checked-button");
        
        //button Play
        buttonPlay = new TextButton("Play", textButtonStyle);
        buttonPlay.setColor(Color.GREEN);
        buttonPlay.setPosition(getCamera().viewportWidth/2, getCamera().viewportHeight/2);
        stage.addActor(buttonPlay);
        
		buttonPlay.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				stage.addAction(Actions.sequence(Actions.fadeOut(2), Actions.run(new MyActionScreen(game,ActionType.GOTO_PLAY))));
			}
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				touchdown.set(x, y);
				return super.touchDown(event, x, y, pointer, button);
				
			}
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				
				touchup.set(x, y);
				buttonPlay.setPosition(x, y);	
				super.touchUp(event, x, y, pointer, button);
			}
			
		});
		
		//button help
		buttonHelp = new TextButton("Help", textButtonStyle);
		buttonHelp.setColor(Color.GREEN);
		buttonHelp.setPosition(getCamera().viewportWidth/2, getCamera().viewportHeight/2-100);
		stage.addActor(buttonHelp);
		
		buttonHelp.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				stage.addAction(Actions.sequence(Actions.moveBy(-getCamera().viewportWidth/2-100, 0,1), Actions.run(new MyActionScreen(game,ActionType.GOTO_HELP))));
			}
			
		});
		
		
		
		
		
		// cho stage hieern thi len luc dau
		stage.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(2)));
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

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
