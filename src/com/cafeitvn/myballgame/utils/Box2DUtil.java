package com.cafeitvn.myballgame.utils;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.cafeitvn.myballgame.entity.BallEntity;
import com.cafeitvn.myballgame.screen.GameInfo;

public class Box2DUtil {

	
	public static void createRectStaticBody(World world,Rectangle obj)
	{
		
		//
	    BodyDef groundBodyDef = new BodyDef();
	    groundBodyDef.type = BodyType.StaticBody;
	    // lay vi tri x,y 
	    float x = obj.getX();
	    float y = obj.getY();
	    // và chuyển đổi sang kích thước của Box2D
	    x = x * GameInfo.WORLD_TO_BOX;
	    y = y * GameInfo.WORLD_TO_BOX;
	        
	    groundBodyDef.position.set(x, y);
	    Body groundBody = world.createBody(groundBodyDef);
	    PolygonShape groundBox = new PolygonShape();
	    //Tạo một static body với mãng toa do vừa thu được.
	    groundBox.setAsBox(obj.getWidth()*GameInfo.WORLD_TO_BOX,obj.getHeight()*GameInfo.WORLD_TO_BOX);
//	    Gdx.app.log("box X : ","" + x*GameInfo.BOX_TO_WORLD);
//	    Gdx.app.log("box Y : ","" + y*GameInfo.BOX_TO_WORLD);
//	    Gdx.app.log("Box W : ","" + obj.getWidth());
//	    Gdx.app.log("Box H : ","" + obj.getHeight());
	    groundBody.createFixture(groundBox, 0.0f);
	    groundBox.dispose();
	}
	
	public static void createPolygonStaticBody(World world, Polygon polygon){
		//
	    BodyDef groundBodyDef = new BodyDef();
	    groundBodyDef.type = BodyType.StaticBody;
	    Rectangle rect = polygon.getBoundingRectangle();
	    // lay vi tri x,y 
	    float x = 40+rect.getX();
	    float y = 100+rect.getY();
	    // và chuyển đổi sang kích thước của Box2D
	    x = x * GameInfo.WORLD_TO_BOX;
	    y = y * GameInfo.WORLD_TO_BOX;
	        
	    groundBodyDef.position.set(x, y);
	    Body groundBody = world.createBody(groundBodyDef);
	    PolygonShape groundBox = new PolygonShape();
	    float[] vertices = new float[polygon.getVertices().length];
        for(int i=0; i<polygon.getVertices().length; i++){
        	vertices[i]=polygon.getVertices()[i]*GameInfo.WORLD_TO_BOX;
        	Gdx.app.log("Vertices ["+ i+"] = ", ""+vertices[i]);
        }
	    //tinh diem polygon để tạo shape
	         
	    //Tạo một static body với mãng toa do vừa thu được.
	    groundBox.set(vertices);
	    groundBox.set(polygon.getTransformedVertices());
	    Gdx.app.log("polygon X : ","" + x*GameInfo.BOX_TO_WORLD);
	    Gdx.app.log("polygon Y : ","" + y*GameInfo.BOX_TO_WORLD);
	    groundBody.createFixture(groundBox, 0.0f);

	    groundBox.dispose();
	}
	
	public static void createPolylineStaticBody(World world, Polyline polyline){
		//
	    BodyDef groundBodyDef = new BodyDef();
	    groundBodyDef.type = BodyType.StaticBody;
	    
	    // lay vi tri x,y 
	    float x = polyline.getX();//rect.getX();
	    float y = polyline.getY();
	    // và chuyển đổi sang kích thước của Box2D
	    x = x * GameInfo.WORLD_TO_BOX;
	    y = y * GameInfo.WORLD_TO_BOX;
	    groundBodyDef.position.set(x, y);
	    Body groundBody = world.createBody(groundBodyDef);
	    
//	    float[] vertices = polyline.getTransformedVertices();
        ChainShape chain = new ChainShape();
        float[] vertices = new float[polyline.getVertices().length];
        for(int i=0; i<polyline.getVertices().length; i++){
        	vertices[i]=polyline.getVertices()[i]*GameInfo.WORLD_TO_BOX;
        }
        chain.createChain(vertices);
	    groundBody.createFixture(chain,0f);
	    chain.dispose();
	    
	}
	
	public static void createCircleStaticBody(World world, Circle circle){
		//
	    BodyDef groundBodyDef = new BodyDef();
	    groundBodyDef.type = BodyType.StaticBody;
	    
	    // lay vi tri x,y 
	    float x = circle.x;//rect.getX();
	    float y = circle.y;
	    // và chuyển đổi sang kích thước của Box2D
	    x = x * GameInfo.WORLD_TO_BOX;
	    y = y * GameInfo.WORLD_TO_BOX;
	    groundBodyDef.position.set(x, y);
	    Body groundBody = world.createBody(groundBodyDef);
	    
//	    float[] vertices = polyline.getTransformedVertices();
//	    com.badlogic.gdx.physics.box2d.
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(circle.radius*GameInfo.WORLD_TO_BOX);
        Gdx.app.log("Circle X : ","" + x*GameInfo.BOX_TO_WORLD);
	    Gdx.app.log("Circle Y : ","" + y*GameInfo.BOX_TO_WORLD);
	    Gdx.app.log("Circle Radius : ","" + circle.radius*GameInfo.BOX_TO_WORLD);
        groundBody.createFixture(circleShape,0.0f);
	    circleShape.dispose();
	    
	}
	
	public static void CreateBallDynamicBody(World world,SpriteBatch batch,BallEntity ball) 
	 {
		    //Dynamic Body
		    BodyDef bodyDef = new BodyDef();
		    bodyDef.type = BodyType.DynamicBody;

		    // Lây ngẫu nhiên vị trí x của "quả cầu" 
//		    float x = MathUtils.random(0, camera.viewportWidth -IMAGESIZE);
		    // luôn luôn nhớ chuyển đổi sang kích thước box2D
		    float x = ball.getX() * GameInfo.WORLD_TO_BOX;
		    // vị trí y là trên đỉnh...
		    float y = ball.getY() * GameInfo.WORLD_TO_BOX;
		        
		    bodyDef.position.set(x, y);
		    
		    Body body = world.createBody(bodyDef);
		    CircleShape dynamicCircle = new CircleShape();
		    dynamicCircle.setRadius(ball.getRadius());
		    
		    FixtureDef fixtureDef = new FixtureDef();
		    fixtureDef.shape = dynamicCircle;
		    fixtureDef.density = ball.getDenisty(); // độ đậm đặc 
		    fixtureDef.friction = ball.getFriction(); // độ ma sát
		    fixtureDef.restitution = ball.getRestitution(); // độ đàn hồi
		    
		    //Group
		    fixtureDef.filter.groupIndex=0;
		    body.createFixture(fixtureDef);
		    body.setLinearDamping(ball.getLinearDamping());
		    body.setAwake(true);
		    body.setAngularVelocity(ball.getAngularVelocity());
		    body.setActive(true);
		    body.setLinearVelocity(ball.getLinearVelocity_x(),ball.getLinearVelocity_y());
		    
		    // Song song với việc tạo dynamic body... đồng thời ta cũng tạo một 
		    // sprite tương ứng với nó để hiển thị trên màn hình...	    
		    // gán cái sprite này vào UserData của body.
//		    body.setUserData(ball.getSprite());
		    body.setUserData(ball);
		    // Đưa diễn viên mới này lên sân khấu thôi...
//		    batch.begin();
//		    ball.getSprite().draw(batch);
//		    batch.end();
		    dynamicCircle.dispose();
		    
	}
	
}
