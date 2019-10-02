import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegiCode extends JFrame {

	private JToolBar toolBar;
	private ButtonGroup toolBarGroup;
	private JButton loop2, loop3, loop4, right, straight, left, play, playstep, star;
	private JPanel mainPanel, methodPanel;
	private JTextField mainTextField, starTextField;
	private JTable table;
	private ImageIcon regi = new ImageIcon("src/ghost.png");
	private ImageIcon planet = new ImageIcon("src/saturn.png");
	private ImageIcon meteorite = new ImageIcon("src/meteorite.png");

	private String [] colunas = {"1", "2", "3", "4", "5", "6", "7"};
	private Object [][] dados = {
		    {regi, "", "", "", "", "", meteorite},
		    {"", "", "", "", "", "", ""},
		    {"", "", meteorite, "", "", "", ""},
		    {"", "", "", "", "", "", ""},
		    {"", meteorite, "", "", "", "", ""},
		    {"", "", "", "", "", "", planet}
		};

	public RegiCode() {
		super("RegiCode");
		this.setPreferredSize(new Dimension(1800,900));
		this.setMinimumSize(new Dimension(900, 450));

		setUpUI();
		this.pack();
		this.setVisible(true);
	}

	public void setUpUI() {
		setUpToolBar();

		setUpMainPanel();
		setUpMethodPanel();

	}

	public void setUpMainPanel() {

		DefaultTableModel model = new DefaultTableModel(dados, colunas) {
		    @Override
		    public Class<?> getColumnClass(int column) {
		    		return ImageIcon.class;

		        }

		};

		mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLACK);
		table = new JTable(model);
		
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(100);
		columnModel.getColumn(2).setPreferredWidth(100);
		columnModel.getColumn(3).setPreferredWidth(100);
		columnModel.getColumn(4).setPreferredWidth(100);
		columnModel.getColumn(5).setPreferredWidth(100);
		columnModel.getColumn(6).setPreferredWidth(100);
		
		table.setRowHeight(100);
		table.setRowSelectionAllowed(false);;
		mainPanel.add(table);

		getContentPane().add(mainPanel, BorderLayout.CENTER);
	}

	public void setUpMethodPanel() {
		methodPanel = new JPanel(new GridBagLayout());
		methodPanel.setBorder(new EmptyBorder(6,6,6,6));
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 10;
		c.weighty = 10;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.HORIZONTAL;

		methodPanel.setBackground(new Color(247,247,247));



		JLabel labelMain = new JLabel("Main");
		labelMain.setFont(new Font("Rockwell", Font.PLAIN, 20));
		c.gridx = 0;
		c.gridy = 0;
		labelMain.setVerticalTextPosition(JLabel.BOTTOM);
		methodPanel.add(labelMain, c);

		mainTextField = new JTextField(30);
		mainTextField.setDragEnabled(true);
		c.gridwidth = 3;
		c.gridx= 1;
		c.gridy = 0;
		c.ipady = 150;
		methodPanel.add(mainTextField,c);


		Icon staricon = new ImageIcon("src/star.png");
		JLabel labelStar = new JLabel(staricon);
		//labelStar.setFont(new Font("Rockwell", Font.PLAIN, 20));
		c.gridwidth = 1;
		c.gridx=0;
		c.gridy=1;
		c.ipady = 0;
		methodPanel.add(labelStar, c);


		starTextField = new JTextField(30);
		c.gridwidth = 3;
		c.ipady = 100;
		c.gridx= 1;
		c.gridy = 1;
		methodPanel.add(starTextField,c);

		Icon playicon = new ImageIcon("src/fast-forward.png");
		play = new JButton(playicon);
		c.gridwidth = 2;   //2 columns wide
		c.ipady = 0;
		c.gridx=1;
		c.gridy=2;
		methodPanel.add(play, c);

		Icon stepicon = new ImageIcon("src/play.png");
		playstep = new JButton (stepicon);
		//c.gridwidth = 2;
		c.gridx=0;
		c.gridy = 2;
		methodPanel.add(playstep, c);


		getContentPane().add(methodPanel, BorderLayout.EAST);
	}


	public void setUpToolBar() {
		
//		MouseAdapter listener = new MouseAdapter() {
//			
//		}

		toolBar = new JToolBar("My ToolBar");
		toolBarGroup = new ButtonGroup();

		Icon loop2icon = new ImageIcon("src/2times.png");
		loop2 = new JButton(loop2icon);
		loop2.setTransferHandler(new TransferHandler("Loop twice"));
		toolBarGroup.add(loop2);
		toolBar.add(loop2);

		Icon loop3icon = new ImageIcon("src/3times.png");
		loop3 = new JButton (loop3icon);
		toolBarGroup.add(loop3);
		toolBar.add(loop3);

		Icon loop4icon = new ImageIcon("src/4times.png");
		loop4 = new JButton(loop4icon);
		toolBarGroup.add(loop4);
		toolBar.add(loop4);
//		loop4.addMouseListener(listener);
//	    loop4.addMouseMotionListener(listener);

		toolBar.addSeparator(new Dimension(100,30));

		Icon lefticon = new ImageIcon("src/left.png");
		left = new JButton(lefticon);
		toolBarGroup.add(left);
		toolBar.add(left);
		Icon righticon = new ImageIcon("src/right.png");
		right = new JButton(righticon);
		toolBarGroup.add(right);
		toolBar.add(right);
		
		toolBar.addSeparator(new Dimension(100,30));

		Icon straighticon = new ImageIcon("src/straight.png");
		straight = new JButton(straighticon);
		toolBarGroup.add(straight);
		toolBar.add(straight);

		

		toolBar.addSeparator(new Dimension(100,30));

		Icon staricon = new ImageIcon("src/star.png");
		star = new JButton(staricon);
		toolBarGroup.add(star);
		toolBar.add(star);

		this.getContentPane().add(toolBar, BorderLayout.NORTH);
	}
	
	public void makeUI() {
		 
	    MouseAdapter listener = new MouseAdapter() {
	 
	      Point p = null;
	 
	      @Override
	      public void mousePressed(MouseEvent e) {
	        p = e.getLocationOnScreen();
	      }
	 
	      @Override
	      public void mouseDragged(MouseEvent e) {
	        JComponent c = (JComponent) e.getSource();
	        Point l = c.getLocation();
	        Point here = e.getLocationOnScreen();
	        c.setLocation(l.x + here.x - p.x, l.y + here.y - p.y);
	        p = here;
	      }
	    };
	  }

}
