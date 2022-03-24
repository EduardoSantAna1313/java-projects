package br.com.system.constant;

import java.awt.Color;

/**
 * @author Eduardo
 */
public abstract class Constants {

	/*
	 * Screen Dimension
	 */
	public static final int SCREEN_WIDTH = 640;

	public static final int SCREEN_HEIGHT = 640;

	public static final float UNITS_WIDE = 10.0f;

	/*
	 * Colors
	 */
	public static final float[] BACKGROUND_COLOR = new float[] {
		0.0f, 0.0f, 0.0f, 0.0f
	};

	public static final float[] DIVISION_LINES_COLOR = new float[] {
		180.0f / 255.0f, 180.0f / 255.0f, 180.0f / 255.0f, 1.0f
	};

	public static final float[] TICK_LINES_COLOR = new float[] {
		1.0f, 1.0f, 1.0f, 1.0f
	};

	public static final float[] OFFSET_LINE_COLOR = new float[] {
		0.0f, 0.0f, 1.0f
	};

	public static final Color TEXT_COLOR = Color.YELLOW;

	/**
	 * int - MAX.
	 */
	public static final int MAX = 10;

	/**
	 * int - MIN.
	 */
	public static final int MIN = -10;

	private Constants() {
		// NA
	}

}
