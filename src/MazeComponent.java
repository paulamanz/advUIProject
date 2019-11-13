import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class MazeComponent extends JComponent{
	private MazeModel model; 			
	private MazeView view;
	
	private List<String> directions;
	
	public MazeComponent () {
		setModel (new MazeModel(2));
		setView (new MazeView(this) );
		
	}
	public MazeComponent (int level) {
		setModel (new MazeModel(level));
		setView (new MazeView(this) );
		
	}
	
	private void setModel(MazeModel model) {
		this.model = model;
		model.addChangeListener(event -> repaint());
	}
	
	private void setView(MazeView view) {
		this.view = view;
	}
	public boolean runDirections (String dir) {
		
		boolean success = model.changePosition(dir);
		return success;
		
		
	}
	public MazeModel getModel() {
		return this.model;
	}
	
	public boolean isSolved() {
		return model.isSolved();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return view.getPreferredSize();
		
	}
	
	@Override
	public void paintComponent (Graphics g) {
		System.out.println("Inside paint() >>>>>>>>>>>>>>>>>>>>>");
		view.paint(g, this);
		
	}
}
