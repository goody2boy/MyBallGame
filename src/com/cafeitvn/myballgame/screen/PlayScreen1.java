package com.cafeitvn.myballgame.screen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sun.font.CreatedFontTracker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.cafeitvn.myballgame.MyBallGame;
import com.cafeitvn.myballgame.entities.Player;
import com.cafeitvn.myballgame.entity.BallEntity;
import com.cafeitvn.myballgame.utils.Box2DUtil;
import com.cafeitvn.myballgame.utils.MapBodyBuilder;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class PlayScreen1 extends BaseScreen {

	// ---- Tiled Map ------------
	TiledMap map;
	private OrthogonalTiledMapRenderer render;
	private ShapeRenderer shapeRender;
	// TileAtlas tileAtlas;
	// TileMapRenderer tileMapRenderer;

	World world;
	Box2DDebugRenderer debugRenderer;

	// Ball
	BallEntity ball;
	List<BallEntity> listBall = new ArrayList<BallEntity>();
	//
	int verloc = 0;
	// input processor
	MyTouchController control ;

	public PlayScreen1(MyBallGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		super.show();
		
		//
		world = new World(new Vector2(0, 0), true);
		debugRenderer = new Box2DDebugRenderer();
		// set positon camera
		getCamera().position.set(0 + getCamera().viewportWidth / 2,
				getCamera().viewportHeight / 2, 0f);
		getCamera().update();
		// Gdx.app.log("CameraX: ", ""+getCamera().viewportWidth);
		// Gdx.app.log("CameraY: ", ""+getCamera().viewportHeight);
		// Gdx.app.log("Graphics X: ",""+ Gdx.graphics.getWidth());
		// Gdx.app.log("Graphics Y: ", ""+Gdx.graphics.getHeight());
		//

		map = new TmxMapLoader().load("map/map.tmx");
		render = new OrthogonalTiledMapRenderer(map);
		// map = new TmxMapLoader().load("map/isomapDemo.tmx");
		// render = new IsometricTiledMapRenderer(map);
		shapeRender = new ShapeRenderer();
		ball = new BallEntity(new Sprite(new Texture("images/ball.png")), 400,
				240);
		ball.setLinearVelocity_x(2);
		ball.setLinearVelocity_y(0);
		listBall.add(ball);
		// load animated tile in map
		// loadAnimatedMapTile();
		//
		//
		Box2DUtil.CreateBallDynamicBody(world, render.getSpriteBatch(), ball);
		loadAllStaticBodies(world, map, "objects", render);
		// Array<Body> bodies = MapBodyBuilder.buildShapes(map, 16,
		// world,"objects");
		
		// input handle
		control = new MyTouchController(stage, getAtlas(),world);
		GestureDetector gd = new GestureDetector(control);
//		Gdx.input.setInputProcessor(gd);
		Gdx.input.setInputProcessor(ball);
		

	}

	public void loadAllStaticBodies(World world, TiledMap map,
			String objectLayername, OrthogonalTiledMapRenderer render) {
		for (MapObject object : map.getLayers().get(objectLayername)
				.getObjects()) {
			if (object instanceof RectangleMapObject) {
				Rectangle rect = ((RectangleMapObject) object).getRectangle();

				Box2DUtil.createRectStaticBody(world, rect);
				shapeRender.begin(ShapeType.Line);
				shapeRender.rect(rect.x, rect.y, rect.width, rect.height);
				shapeRender.end();
			} else if (object instanceof EllipseMapObject) {
				Ellipse ellipse = ((EllipseMapObject) object).getEllipse();

				// Box2DUtil.createCircleStaticBody(world, ellipse);
				shapeRender.begin(ShapeType.Line);
				shapeRender.ellipse(ellipse.x, ellipse.y, ellipse.width,
						ellipse.height);
				shapeRender.end();
			} else if (object instanceof PolygonMapObject) {
				Polygon polygon = ((PolygonMapObject) object).getPolygon();

				Box2DUtil.createPolygonStaticBody(world, polygon);
				shapeRender.setColor(Color.BLACK);
				shapeRender.begin(ShapeType.Line);
				shapeRender.polygon(polygon.getTransformedVertices());
				shapeRender.end();
				shapeRender.setColor(Color.PINK);
			} else if (object instanceof PolylineMapObject) {
				Polyline polyline = ((PolylineMapObject) object).getPolyline();

				Box2DUtil.createPolylineStaticBody(world, polyline);
				shapeRender.begin(ShapeType.Line);
				shapeRender.polyline(polyline.getTransformedVertices());
				shapeRender.end();
			} else if (object instanceof CircleMapObject) {
				Circle circle = ((CircleMapObject) object).getCircle();

				Box2DUtil.createCircleStaticBody(world, circle);
				shapeRender.begin(ShapeType.Line);
				shapeRender.circle(circle.x, circle.y, circle.radius);
				shapeRender.end();
			}
		}
	}

	@Override
	public void render(float delta) {

		// handler event touch
		
		Array<Float> time = control.timeArr;
		Array<Image> imgArr = control.imgArr;

		for (int i = 0; i < time.size; i++) {
			if (time.get(i) > GameInfo.MAX_TIME) {
				imgArr.removeIndex(i);
				time.removeIndex(i);
			} else {
				float timeTemp = time.get(i);
				timeTemp += Gdx.graphics.getDeltaTime();
				time.set(i, timeTemp);
			}
		}
//		for (int i = 0; i < imgArr.size; i++) {
//			Image imgtemp = imgArr.get(i);
//			render.getSpriteBatch().begin();
//			imgtemp.draw(render.getSpriteBatch(), 1);
//			render.getSpriteBatch().end();
//		}

		// clear
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// handle event
		handleInput();

		render.setView(getCamera());

		// update camera follow palayer or map :))
		// camera.position.set(400,240,0f);
		// if (Gdx.input.justTouched()) {
		//
		// // Gdx.app.log("Touch x :",""+Gdx.input.getX());
		// // Gdx.app.log("Touch y :",""+Gdx.input.getY());
		// float x = Gdx.input.getX();
		// float y = GameInfo.VIEW_PORT_HEIGHT - Gdx.input.getY();
		// BallEntity ball1 = new BallEntity(new Sprite(new Texture(
		// "images/ball.png")), x, y);
		// ball1.setLinearVelocity_x(2);
		// ball1.setLinearVelocity_y(0);
		// listBall.add(ball1);
		// // load animated tile in map
		// // loadAnimatedMapTile();
		// //
		// //
		// Box2DUtil.CreateBallDynamicBody(world, render.getSpriteBatch(),
		// ball1);
		//
		// }

		// if(Gdx.input.isKeyPressed(Keys.UP)){
		// verloc += 1;
		// }else if(Gdx.input.isKeyPressed(Keys.DOWN)){
		// verloc -= 1;
		// }

		// Render map
		render.render();
		getCamera().update();

		// AnimatedTiledMapTile.updateAnimationBaseTime();
		// render.render(background);
		//

		// draw ball
		render.getSpriteBatch().begin();
		for (BallEntity b : listBall) {
			b.draw(render.getSpriteBatch());
		}
		render.getSpriteBatch().end();

		// render shape object
		shapeRender.setProjectionMatrix(camera.combined);
		shapeRender.setColor(Color.WHITE);
		//
		shapeRender(shapeRender, map, "objects");

		debugRenderer.render(world, getCamera().combined);

		// Thục thi các hoạt động của các vật thể trong thế giới Box2D
		world.step(GameInfo.BOX_STEP, GameInfo.BOX_VELOCITY_ITERATIONS,
				GameInfo.BOX_POSITION_ITERATIONS);
		// world.clearForces();
		//
		// Duyệt từng bodies trong thế giới box2D để cập nhật tương ứng cho nhân
		// vật của chúng ta trên sân khấu

		Array<Body> bodies = new Array<Body>();
		world.getBodies(bodies);
		for (Body b : bodies) {
			// b.setLinearVelocity(b.getLinearVelocity().x+verloc,
			// b.getLinearVelocity().y);
			BallEntity ballTemp = (BallEntity) b.getUserData();

			// Gdx.app.log("0in get body render", "0get palyer sprite");
			if (ballTemp != null) {

				// Gdx.app.log("in get body render", "get palyer sprite");
				ballTemp.setX(b.getPosition().x - ballTemp.getRadius());
				float x = ballTemp.getX() * GameInfo.BOX_TO_WORLD;
				ballTemp.setY(b.getPosition().y - ballTemp.getRadius());
				float y = ballTemp.getY() * GameInfo.BOX_TO_WORLD;
				// Gdx.app.log("Ball X:" ,""+ballTemp.getX());
				// Gdx.app.log("Ball Y:" ,""+ballTemp.getY());
				// Gdx.app.log("Sprite X:" ,""+x);
				// Gdx.app.log("Sprite Y:" ,""+x);
				ballTemp.getSprite().setPosition(x, y);

				// sprite.setRotation(MathUtils.radiansToDegrees *
				// b.getAngle());
			}
		}

		stage.draw();

	}

	private void handleInput() {
		// TODO Auto-generated method stub

	}

	public void shapeRender(ShapeRenderer shapeRender, TiledMap map,
			String objectLayername) {
		for (MapObject object : map.getLayers().get(objectLayername)
				.getObjects()) {
			if (object instanceof RectangleMapObject) {
				Rectangle rect = ((RectangleMapObject) object).getRectangle();
				shapeRender.begin(ShapeType.Line);
				shapeRender.rect(rect.x, rect.y, rect.width, rect.height);
				shapeRender.end();
			} else if (object instanceof EllipseMapObject) {
				Ellipse ellipse = ((EllipseMapObject) object).getEllipse();
				shapeRender.begin(ShapeType.Line);
				shapeRender.ellipse(ellipse.x, ellipse.y, ellipse.width,
						ellipse.height);
				shapeRender.end();
			} else if (object instanceof PolygonMapObject) {
				Polygon polygon = ((PolygonMapObject) object).getPolygon();
				shapeRender.begin(ShapeType.Line);
				shapeRender.polygon(polygon.getTransformedVertices());
				shapeRender.end();
			} else if (object instanceof PolylineMapObject) {
				Polyline polyline = ((PolylineMapObject) object).getPolyline();
				shapeRender.begin(ShapeType.Line);
				shapeRender.polyline(polyline.getTransformedVertices());
				shapeRender.end();
			} else if (object instanceof CircleMapObject) {
				com.badlogic.gdx.math.Circle circle = ((CircleMapObject) object)
						.getCircle();
				shapeRender.begin(ShapeType.Line);
				shapeRender.circle(circle.x, circle.y, circle.radius);
				shapeRender.end();
			}
		}
	}

	public void loadAnimatedMapTile() {
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
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
		getCamera().viewportWidth = width;
		getCamera().viewportHeight = height;

		getCamera().update();

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		map.dispose();
		render.dispose();
	}

}
