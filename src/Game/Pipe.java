package Game;

import java.awt.*;
public class Pipe {
	private int x, y, width, height;
	private Image img;
	private boolean passed;
	private int velocityX = -4;
	public Pipe(int x, int y, int width, int height, Image img){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.img = img;
		this.passed = false;
	}
	public int getX() {
		return x;
	}
	public boolean getPassed() {
		return passed;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setPassed(boolean passed) {
		this.passed = passed;
	}
	public void draw(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}
	public void update() {
		x+=velocityX;
	}
}
