package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.MapModel;

/**
 * Graphical view
 * 
 * @author Ye Li
 *
 */

@SuppressWarnings("serial")
public class GraphicalView extends JPanel implements Observer {
	private BufferedImage ground;
	private BufferedImage slime;
	private BufferedImage pit;
	private BufferedImage blood;
	private BufferedImage goop;
	private BufferedImage wumpus;
	private BufferedImage hunter;
	private JLabel over;
	private MapModel map;
	private boolean[][] passed;

	public GraphicalView(MapModel map) {
		super();
		this.map = map;
		passed = new boolean[10][10];
		over = new JLabel();
		over.setLocation(500,100);

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				passed[i][j] = false;
			}
		}
		try {
			ground = ImageIO.read(new File("Ground.png"));
			slime = ImageIO.read(new File("Slime.png"));
			pit = ImageIO.read(new File("SlimePit.png"));
			blood = ImageIO.read(new File("Blood.png"));
			goop = ImageIO.read(new File("Goop.png"));
			wumpus = ImageIO.read(new File("Wumpus.png"));
			hunter = ImageIO.read(new File("TheHunter.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update(Observable observed, Object unused) {
		map = (MapModel) observed;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map.getStatus()) {
					Object state = map.getCellState(i, j);

					if (state.equals("[S]") || state.equals("[P]") || state.equals("[B]") || state.equals("[W]")
							|| state.equals("[G]") || passed[i][j] == true || map.isHunter(i, j)) {
						
						passed[i][j] = true;
						g2.drawImage(ground, (j * 43), i * 43, 43, 43, null);

						if (state.equals("[S]")) {
							g2.drawImage(ground, (j * 43), i * 43, 43, 43, null);
							g2.drawImage(slime, (j * 43), i * 43, 43, 43, null);

						}
						if (state.equals("[P]")) {
							g2.drawImage(ground, (j * 43), i * 43, 43, 43, null);
							g2.drawImage(pit, (j * 43), i * 43, 43, 43, null);

						}
						if (state.equals("[B]")) {
							g2.drawImage(ground, (j * 43), i * 43, 43, 43, null);
							g2.drawImage(blood, (j * 43), i * 43, 43, 43, null);

						}
						if (state.equals("[G]")) {
							g2.drawImage(ground, (j * 43), i * 43, 43, 43, null);
							g2.drawImage(goop, (j * 43), i * 43, 43, 43, null);

						}
						if (state.equals("[W]")) {
							g2.drawImage(ground, (j * 43), i * 43, 43, 43, null);
							g2.drawImage(wumpus, (j * 43), i * 43, 43, 43, null);

						}
						if (state.equals("[X]")) 
							g2.drawImage(ground, (j * 43), i * 43, 43, 43, null);

						if (map.isHunter(i, j)) 
							g2.drawImage(hunter, (j * 43), i * 43, 43, 43, null);
						
					} else {
						g2.setColor(Color.BLACK);
						g2.fillRect((j * 43), i * 43, 43, 43);
					}
				} else {
					Object state = map.getCellStateDone(i, j);
					g2.setColor(Color.BLACK);
					g2.fillRect((j * 43), i * 43, 43, 43);
					int hunterx = map.getHunterX();
					int huntery = map.getHunterY();

					if (map.CheckWin()) {
						over.setText("Shoot It! YOU WIN!");
					} else {
						if(map.isPit(hunterx, huntery))
							over.setText("Fall in the pit! Game Over");
						
						else if(map.isWumpus(hunterx, huntery))
							over.setText("Eaten by wumpus! Game Over");						
						else
							over.setText("Shoot YourSelf! Game Over");
						
					}
					over.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 25));
					over.setForeground(Color.WHITE);
					add(over);
				

					if (state.equals("[S]")) 
						g2.drawImage(slime, (j * 43), i * 43, 43, 43, null);

					if (state.equals("[P]")) 
						g2.drawImage(pit, (j * 43), i * 43, 43, 43, null);

					if (state.equals("[B]"))
						g2.drawImage(blood, (j * 43), i * 43, 43, 43, null);

					if (state.equals("[G]"))
						g2.drawImage(goop, (j * 43), i * 43, 43, 43, null);

					if (state.equals("[W]"))
						g2.drawImage(wumpus, (j * 43), i * 43, 43, 43, null);

					if (map.isHunter(i, j))
						g2.drawImage(hunter, (j * 43), i * 43, 43, 43, null);
				}
			}
		}
	
	}
}
