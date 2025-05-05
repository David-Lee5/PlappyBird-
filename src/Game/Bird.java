package Game;

import java.awt.*;

public class Bird {
	private int dx, dy, width, height;
	private Image img;
	private int velocityY = 0;
	private int gravity = 1;
	
	public Bird(int dx, int dy, int width, int height, Image img) {
		this.dx = dx;
		this.dy = dy;
		this.width = width;
		this.height = height;
		this.img = img;
	}

	public void draw(Graphics g) {
		g.drawImage(img, dx, dy, width, height, null);
	}

	public void update() {
		velocityY += gravity;
		dy += velocityY;
		dy = Math.max(dy, 0);
	}

	public int getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
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

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public void jump() {
		velocityY -= 12;
	}
}
