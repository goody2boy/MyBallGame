package com.cafeitvn.myballgame.screen;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.cafeitvn.myballgame.entities.Player;
import com.cafeitvn.myballgame.utils.Box2DUtil;

public class PlayScreen implements Screen {

	private TiledMap map;
	
	private OrthogonalTiledMapRenderer render;
	// private IsometricTiledMapRenderer render;
	private OrthographicCamera camera;

	private Player player;

	private int[] background = new int[] { 0 }, foreground = new int[] { 1 };

	private ShapeRenderer shapeRender;

	@Override
	public void show() {
		// TODO Auto-generated method stub
		map = new TmxMapLoader().load("map/map.tmx");
		render = new OrthogonalTiledMapRenderer(map);
		// map = new TmxMapLoader().load("map/isomapDemo.tmx");
		// render = new IsometricTiledMapRenderer(map);
		camera = new OrthographicCamera();
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
		camera.update();
//		set(0+getCamera().viewportWidth/2, getCamera().viewportHeight/2,0f);
		shapeRender = new ShapeRenderer();
		player = new Player(new Sprite(new Texture("images/still1.png")));
		Gdx.input.setInputProcessor(player);
		
		// begin load animate tiles 
		loadAnimatedMapTile();
		// end load animate tiles
	
	}
	public void loadAnimatedMapTile(){
		// begin load animate tiles 
				Array<StaticTiledMapTile> frameTiles = new Array<StaticTiledMapTile>(2);
				Iterator<TiledMapTile> tiles = map.getTileSets().getTileSet("tiles")
						.iterator();
			
				while (tiles.hasNext()) {
					TiledMapTile tile = tiles.next();
					if (tile.getProperties().containsKey("animation")
							&& tile.getProperties().get("animation", String.class)
									.equals("flower"))
						frameTiles.add((StaticTiledMapTile) tile);
				}
			
				AnimatedTiledMapTile animateTiles = new AnimatedTiledMapTile(1 / 3f,
						frameTiles);
				TiledMapTileLayer backgroundLayer = (TiledMapTileLayer) map.getLayers()
						.get("background");
				for (int x = 0; x < backgroundLayer.getWidth(); x++) {
					for (int y = 0; y < backgroundLayer.getHeight(); y++) {
						Cell cell = backgroundLayer.getCell(x, y);
						if (cell.getTile().getProperties().containsKey("animation")
								&& cell.getTile().getProperties()
										.get("animation", String.class)
										.equals("flower")) {
							cell.setTile(animateTiles);
						}
					}
				}
				// end load animate tiles
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		render.setView(camera);
		
		render.render();

		// AnimatedTiledMapTile.updateAnimationBaseTime();
		render.render(background);

		// draw player
		render.getSpriteBatch().begin();
		player.draw(render.getSpriteBatch());
		render.getSpriteBatch().end();

		render.render(foreground);
		
		shapeRender.setProjectionMatrix(camera.combined);
		shapeRender.setColor(Color.WHITE);
		shapeRender(shapeRender, map, "objects"); 
		
		// update camera

		
	}
	
	
	
	public void shapeRender(ShapeRenderer shapeRender, TiledMap map, String objectLayername){
		for (MapObject object : map.getLayers().get(objectLayername).getObjects()) {
			if (object instanceof RectangleMapObject) {
				Rectangle rect = ((RectangleMapObject) object).getRectangle();
				shapeRender.begin(ShapeType.Filled);
				shapeRender.rect(rect.x, rect.y, rect.width, rect.height);
				shapeRender.end();
			} else if (object instanceof EllipseMapObject) {
				Ellipse ellipse = ((EllipseMapObject)object).getEllipse();
				shapeRender.begin(ShapeType.Filled);
				shapeRender.ellipse(ellipse.x,ellipse.y,ellipse.width,ellipse.height);
				shapeRender.end();
			} else if (object instanceof PolygonMapObject) {
				Polygon polygon = ((PolygonMapObject)object).getPolygon();
				shapeRender.begin(ShapeType.Line);
				shapeRender.polygon(polygon.getTransformedVertices());
				shapeRender.end();
			} else if (object instanceof PolylineMapObject) {
				Polyline polyline = ((PolylineMapObject)object).getPolyline();
				shapeRender.begin(ShapeType.Line);
				shapeRender.polyline(polyline.getTransformedVertices());
				shapeRender.end();
			}else if (object instanceof CircleMapObject) {
				Circle circle = ((CircleMapObject)object).getCircle();
				shapeRender.begin(ShapeType.Filled);
				shapeRender.circle(circle.x,circle.y,circle.radius);
				shapeRender.end();
			}
		}
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
		map.dispose();
		render.dispose();

	}

}
