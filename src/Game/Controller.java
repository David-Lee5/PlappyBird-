package Game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Controller extends JPanel implements ActionListener, KeyListener {
	private Timer timer = new Timer(20, this);
	private Timer placePipesTimer;
	
	private Image backgroundImg;
	private Image birdImg;
	private Image topPipeImg;
	private Image bottomPipeImg;
	
	private Bird bird;
	private ArrayList<Pipe> pipes;
	private Random random = new Random();
	
	private boolean gameOver= false;
	private double score = 0;
	
	private Sound sound;
	
	public Controller() {
		
		this.addKeyListener(this);
		this.setFocusable(true);
		try {
			backgroundImg = new ImageIcon(getClass().getResource("/img/flappybirdbg.png")).getImage();
			birdImg = new ImageIcon(getClass().getResource("/img/flappybird.png")).getImage();
			topPipeImg = new ImageIcon(getClass().getResource("/img/toppipe.png")).getImage();
			bottomPipeImg = new ImageIcon(getClass().getResource("/img/bottompipe.png")).getImage();
		} catch (Exception e) {
			System.err.println("Error loading images: " + e.getMessage());
            return;
		}
		
		
		bird = new Bird(100, 200, Consts.bird_Width, Consts.bird_Height, birdImg);
		pipes = new ArrayList<Pipe>();
		placePipesTimer = new Timer(1500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				placePipes();
			}
		});
		placePipesTimer.start();
		timer.start();
	}
	@Override
	public void paintComponent(Graphics g) { // draw
		super.paintComponent(g);
		draw(g);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { // loop
		// TODO Auto-generated method stub
		update();
		repaint();
		if(gameOver) {
			timer.stop();
			placePipesTimer.stop();
		}
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			bird.setVelocityY(-9);
			sound.playSound("/Sound/plappy_sound.wav");
			if (gameOver) {
				restart();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void draw(Graphics g) {
		g.drawImage(backgroundImg, 0, 0, Consts.WIDTH, Consts.HEIGHT, null);
		bird.draw(g);
		// pipes
		for (Pipe pipe : pipes) {
			pipe.draw(g);
		}
		// score
		g.setColor(Color.white);
		g.setFont(new Font("Courier New", Font.BOLD, 40));
		if(gameOver) g.drawString("Game Over : " + String.valueOf((int) score), Consts.WIDTH/2-150, Consts.HEIGHT/2);
		else g.drawString(String.valueOf((int) score), Consts.WIDTH/2 -30, 35);
	}
	public void placePipes() {
		
		int centerY= Consts.pipe_MinY + random.nextInt(Consts.pipe_MaxY - Consts.pipe_MinY); // [150;450]
		
		int topPipeY= centerY - Consts.pipe_Gap/2 - Consts.pipe_Height;
		Pipe topPipe= new Pipe(Consts.WIDTH, topPipeY, Consts.pipe_Width, Consts.pipe_Height,topPipeImg);
		pipes.add(topPipe);
		
		int botPipeY= centerY + Consts.pipe_Gap/2;	
		Pipe bottomPipe= new Pipe(Consts.WIDTH, botPipeY, Consts.pipe_Width, Consts.pipe_Height,bottomPipeImg);
		pipes.add(bottomPipe);
//		int randomPipeY = (int) (-Consts.pipe_Width / 4 - Math.random() * (Consts.pipe_Height / 2));
//		int gap = Consts.pipe_Gap;
//		Pipe topPipe= new Pipe(Consts.WIDTH, randomPipeY, Consts.pipe_Width, Consts.pipe_Height,topPipeImg);
//		pipes.add(topPipe);
//		Pipe bottomPipe= new Pipe(Consts.WIDTH, randomPipeY+ Consts.pipe_Height + gap, Consts.pipe_Width, Consts.pipe_Height,bottomPipeImg);
//		pipes.add(bottomPipe);
	}
	public void update() {
		bird.update();
		
		for(int i =0 ; i< pipes.size(); i++) {
			Pipe pipe = pipes.get(i);
			pipe.update();
			
			if(!pipe.getPassed() && bird.getDx() >pipe.getX() + Consts.pipe_Width ) { 
				pipe.setPassed(true);
				sound.playSound("/Sound/sound_passed.wav");
				score+=0.5;
				//if(score %10 == 0) pipe.setVelocityX(pipe.getVelocityX()-1);
			}
			
			 if(collision(bird, pipe)) gameOver = true;
			 if(pipe.getX()+ pipe.getWidth() <0) {// remove pipe 
				 pipes.remove(i);
				 i--;
			 }
			

		}
		if(bird.getDy() > Consts.HEIGHT || bird.getDy() < 0) gameOver = true;
	}
	public boolean collision(Bird bird, Pipe pipe ) {
		return bird.getDx() < pipe.getX()+ pipe.getWidth() && 
				bird.getDx() + bird.getWidth() > pipe.getX() && 
				bird.getDy() < pipe.getY() + pipe.getHeight() &&
				bird.getDy() + bird.getHeight() > pipe.getY()
				;
	}
	public void restart() {
		bird.setDy(Consts.HEIGHT / 2);
		bird.setVelocityY(0);
		pipes.clear();
		score = 0;
		gameOver = false;
		placePipesTimer.start();
		timer.start();
	}
}
