package Mini_Assignments_A7;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class Controller extends KeyAdapter{
		private GameModel model;
		private GamePanel panel;
		//private Game2048 game;
		
		/*
		 * Constructor 
		 * Pars: GameModel m, GamePanel p
		 */
		public Controller(GameModel m, GamePanel p) {
			model = m;
			panel = p;
			//model.resetGame();
			
			//Update the panel with some variables.
			//Repaint the panel.
			panel.update(model.myLose,model.myScore,model.myWin,model.getTiles());
			panel.repaint();
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				model.resetGame();
			}
			if (!model.canMove()) {
				model.myLose = true;
			}
			
			if (!model.myWin && !model.myLose) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						model.left();
						break;
					case KeyEvent.VK_RIGHT:
						model.right();
						break;
					case KeyEvent.VK_DOWN:
						model.down();
						break;
					case KeyEvent.VK_UP:
						model.up();
						break;
				}
			}
			
			if (!model.myWin && !model.canMove()) {
				model.myLose = true;
			}
			/*
			 * Update panel
			 * Repaint
			 */
			panel.update(model.myLose, model.myScore,model.myWin, model.getTiles());
			panel.repaint();
		}
		public static void main(String[] args) {
			
			JFrame gameFrame = new JFrame();
			gameFrame.setTitle("2048 Game");
			gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			gameFrame.setSize(340, 400);
			gameFrame.setResizable(true);
			
			//GamePanel, GameModel. Initialize.
			GamePanel game = new GamePanel();
			GameModel model = new GameModel();
			
			//Add game
			gameFrame.add(game);
			//Add controller as Key Listener
			game.addKeyListener(new Controller(model,game));
			
			gameFrame.setLocationRelativeTo(null);
			gameFrame.setVisible(true);
		}
	
}
