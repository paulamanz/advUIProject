import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Point;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import programmingproject.maze.Cell;
import programmingproject.maze.CellObject;
import programmingproject.maze.Maze;

public class MazeView {
	
	private MazeComponent controller;
	private ImageIcon regi = new ImageIcon("src/ghost.png");
	private ImageIcon planet = new ImageIcon("src/saturn.png");
	private ImageIcon meteorite = new ImageIcon("src/meteorite.png");
	
	
	
	public MazeView (MazeComponent controller) {
		this.controller = controller;
		
	}
	

	public Dimension getPreferredSize() {

		return new Dimension(1000, 1000); 
	}
	
	public Point fromMazetoPixels(Point p) {
		
		int x = (int) p.getX();
		x = x*100 +100;
		int y = (int) p.getY();
		y = y*100+100;
		
		return new Point(y,x);
		
	}

	public void paint(Graphics g, MazeComponent mazeComponent) {
		System.out.println("Inside PAINT of VIEW");
		
		
		g.setColor(Color.WHITE);
		g.fillRect(100, 100, 600, 600);
		
		for (int i=100;i<=600;i+=100) {
			for (int j=100;j<=600;j+=100) {
				g.setColor(Color.GRAY);
				g.drawRect(i, j, 99, 99);
			}
		}	
		
		MazeModel model = controller.getModel();
		Maze m = model.getMaze();
		Cell[][] mymaze = m.getMaze();
		Point p;
		int x,y;
		for(int i = 0; i < m.getRows(); i++) {
			for(int j =0; j < m.getCols(); j++) {
				 CellObject state = mymaze[i][j].getState();
				 switch(state) {
				 
				 case OBSTACLE: 
					 p = fromMazetoPixels(new Point(i,j));
					 x = (int) p.getX();
					 y=(int) p.getY();
					 meteorite.paintIcon(mazeComponent, g,x, y);
					
					 break;
				 case REGI:
					 p = fromMazetoPixels(new Point(i,j));
					 x = (int) p.getX();
					 y=(int) p.getY();
					 regi.paintIcon(mazeComponent, g,x, y);
					 break;
				 case TREASURE:
					 p = fromMazetoPixels(new Point(i,j));
					 x = (int) p.getX();
					 y=(int) p.getY();
					 planet.paintIcon(mazeComponent, g,x, y);
					 
				 }
			}
		}
		
		
		
	}


}


