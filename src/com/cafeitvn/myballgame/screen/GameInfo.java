package com.cafeitvn.myballgame.screen;

public class GameInfo {

	
	public static final float VIEW_PORT_WIDTH = 800;
	public static final float VIEW_PORT_HEIGHT = 480;
	public static final float BOX_STEP = 1 / 45f;
	public static final int BOX_VELOCITY_ITERATIONS = 6;
	public static final int BOX_POSITION_ITERATIONS = 2;
	public static final float WORLD_TO_BOX = 0.01f; // ty le chuyen doi don vi tu game sang box2d
	public static final float BOX_TO_WORLD = 100f; // ty le chuyen doi don vi tu box2d sang game
	public static final int MAX_TOUCH =2;
	public static final float MAX_TIME =2f;
	
}
