package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import model.Direction;
import model.MapModel;
import view.GraphicalView;
import view.TextView;

/**
 * Hunt the wumpus GUI
 * @author Ye Li
 *
 */

@SuppressWarnings("serial")
public class WumpusGUI extends JFrame {

	
	public static void main(String[] args) {
		new WumpusGUI().setVisible(true);
	}

	private JTabbedPane severalPanels;
	private MapModel map;

	private JPanel view1;
	private JPanel view2;

	private JLabel Move = new JLabel(" Move");
	private JLabel Shoot = new JLabel("Shoot");

	private JButton moveleft = new JButton("\u25C0");
	private JButton moveright = new JButton("\u25B6");
	private JButton moveup = new JButton("\u25B2");
	private JButton movedown = new JButton("\u25BC");
	private JButton shootleft = new JButton("\u25C0");
	private JButton shootright = new JButton("\u25B6");
	private JButton shootup = new JButton("\u25B2");
	private JButton shootdown = new JButton("\u25BC");

	
	
	public WumpusGUI() {
		map = new MapModel();
		
		layoutThisJFrame();
		registerListeners();
		setUpModelAndObservers();
	}
	
	public void layoutThisJFrame() {
		setTitle("Hunt the Wumpus");
		setBounds(0, 0, 720, 550);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		view1 = new TextView(map);
		view2= new GraphicalView(map);

	
		severalPanels = new JTabbedPane();
		severalPanels.setBounds(200, 30, 450, 480);
		severalPanels.add(view2, "Graphical view");

		severalPanels.add(view1, "Text view");

		add(severalPanels);
		
		Move.setBounds(80, 90, 100, 100);
		add(Move);
		moveleft.setBounds(30, 200, 50, 50);
		add(moveleft);
		moveright.setBounds(120, 200, 50, 50);
		add(moveright);
		moveup.setBounds(75, 156, 50, 50);
		add(moveup);
		movedown.setBounds(75, 200, 50, 50);
		add(movedown);
		Shoot.setBounds(80, 280, 100, 100);
		add(Shoot);
		shootleft.setBounds(30, 390, 50, 50);
		add(shootleft);
		shootright.setBounds(120, 390, 50, 50);
		add(shootright);
		shootup.setBounds(75, 346, 50, 50);
		add(shootup);
		shootdown.setBounds(75, 390, 50, 50);
		add(shootdown);
		

	}

	public void setUpModelAndObservers() {
		map.addObserver((Observer) view1);
		map.addObserver((Observer) view2);
		

	}

	public void registerListeners() {
		moveleft.addActionListener(new Moveleft());
		moveright.addActionListener(new MoveRight());
		moveup.addActionListener(new Moveup());
		movedown.addActionListener(new Movedown());
		shootleft.addActionListener(new Shootleft());
		shootright.addActionListener(new Shootright());
		shootup.addActionListener(new Shootup());
		shootdown.addActionListener(new Shootdown());

	}


	private class Moveleft implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			map.move(Direction.LEFT);
		}
	}

	private class MoveRight implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			map.move(Direction.RIGHT);
		}
	}

	private class Moveup implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			map.move(Direction.UP);

		}
	}

	private class Movedown implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			map.move(Direction.DOWN);
		}
	}

	private class Shootleft implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			map.shoot(Direction.LEFT);
		}
	}

	private class Shootright implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			map.shoot(Direction.RIGHT);
		}
	}

	private class Shootup implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			map.shoot(Direction.UP);
		}
	}

	private class Shootdown implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			map.shoot(Direction.DOWN);

		}
	}
}
