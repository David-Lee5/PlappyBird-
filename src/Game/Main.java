package Game;

import javax.swing.JFrame;

public class Main extends JFrame{
	public Main() {
		this.add(new Controller());
		this.setTitle("FlappyBird");
		this.setSize(Consts.WIDTH,Consts.HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}
	public static void main(String[] args) {
		new Main();
	}
}
