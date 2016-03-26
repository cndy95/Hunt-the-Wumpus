package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import model.MapModel;

/**
 * Text View
 * @author Ye Li
 *
 */

@SuppressWarnings("serial")
public class TextView extends JPanel implements Observer {
	private MapModel map;
	private String returnString;
	private JTextArea message;
	private JTextArea returnText;
	// Constructor
	public TextView(MapModel map) {
		returnString = map.toString();
		returnText = new JTextArea(returnString);
		

		returnText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		add(returnText,BorderLayout.WEST);

		message = new JTextArea();
		add(message);
	}

	@Override
	public void update(Observable observed, Object unused) {
		map = (MapModel) observed;
		returnText.updateUI();
		returnText.setText(map.toString());

		message.setText(map.getMessage());
	}

}