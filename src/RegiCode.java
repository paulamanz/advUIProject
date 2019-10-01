import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegiCode extends JFrame {
	
	private JToolBar toolBar;
	private ButtonGroup toolBarGroup;
	private JButton loop2, loop3, loop4, right, straight, left, play, playstep, star;
	private JPanel mainPanel, methodPanel;
	private JTextField mainTextField, starTextField;
	private JTable table;
	private ImageIcon ball = new ImageIcon("src/ball.jpg");
	
	private String [] colunas = {"1", "2", "3", "4"};
	private Object [][] dados = {
		    {ball, "", "", ""},
		    {"", "", "", ""},
		    {"", "", "", ""},
		    {"", "", "", ""}
		};
	
	public RegiCode() {
		super("RegiCode");
		this.setPreferredSize(new Dimension(800,800));
		this.setMinimumSize(new Dimension(500, 500));
		
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
		table.setRowHeight(65);
		table.setRowSelectionAllowed(false);;
		mainPanel.add(table);
		//ImageIcon loop2icon = new ImageIcon("src/ball.jpg");
		//loop2 = new JButton(loop2icon);
		//mainPanel.add(loop2);
		
		//table.setValueAt(loop2icon, 0, 0);
		

		getContentPane().add(mainPanel, BorderLayout.CENTER);
	}
	
	public void setUpMethodPanel() {
		methodPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 10;
		c.weighty = 10;
		c.gridwidth = 2;   //2 columns width
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.HORIZONTAL;
		
		
		//methodPanel.setLayout(new GridLayout(0,1)); 
		methodPanel.setBackground(Color.GRAY);
		
		
		
		JLabel labelMain = new JLabel("Main Method:");
		c.gridx = 0;
		c.gridy = 0;
		labelMain.setVerticalTextPosition(JLabel.BOTTOM);
		methodPanel.add(labelMain, c);
		
		mainTextField = new JTextField(30);
		mainTextField.setDragEnabled(true); 
		c.gridx= 0;
		c.gridy = 1;
		c.ipady = 100;
		methodPanel.add(mainTextField,c);
		
		
		
		JLabel labelStar = new JLabel("Star Method:");
		c.gridx=0;
		c.gridy=2;
		c.ipady = 0;
		methodPanel.add(labelStar, c);
		
		
		starTextField = new JTextField(30);
		c.ipady = 100; 
		c.gridx= 0;
		c.gridy = 3;
		methodPanel.add(starTextField,c);
		
		Icon playicon = new ImageIcon("src/fast-forward.png");
		play = new JButton(playicon);
		c.gridwidth = 1;   //2 columns wide
		c.ipady = 0;
		c.gridx=1;
		c.gridy=4;
		methodPanel.add(play, c);
		
		Icon stepicon = new ImageIcon("src/play.png");
		playstep = new JButton (stepicon);
		c.gridx=0;
		c.gridy = 4;       
		methodPanel.add(playstep, c);
		
		
		getContentPane().add(methodPanel, BorderLayout.EAST);
	}
	
	
	public void setUpToolBar() {
		
		toolBar = new JToolBar("My ToolBar");
		toolBarGroup = new ButtonGroup();
		
		//Icon loop2icon = new ImageIcon("/Users/paulamanzano/Desktop/folder.png");
		Icon loop2icon = new ImageIcon("src/repeat.png");
		loop2 = new JButton(loop2icon);
		loop2.setTransferHandler(new TransferHandler("Loop twice"));
		toolBarGroup.add(loop2);
		toolBar.add(loop2);
		
//		loop2.addMouseMotionListener(new MouseAdapter() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                JButton button = (JButton) e.getSource();
//                TransferHandler handle = button.getTransferHandler();
//                handle.exportAsDrag(button, e, TransferHandler.COPY);
//            }
//        });
		
		Icon loop3icon = new ImageIcon("src/repeat.png");
		loop3 = new JButton (loop3icon);
		toolBarGroup.add(loop3);
		toolBar.add(loop3);
		
		Icon loop4icon = new ImageIcon("src/repeat.png");
		loop4 = new JButton(loop4icon);
		toolBarGroup.add(loop4);
		toolBar.add(loop4);
		
		toolBar.addSeparator(new Dimension(100,30));
		
		Icon lefticon = new ImageIcon("src/reply.png");
		left = new JButton(lefticon);
		toolBarGroup.add(left);
		toolBar.add(left);
		
		Icon straighticon = new ImageIcon("src/arrow-up.png");
		straight = new JButton(straighticon);
		toolBarGroup.add(straight);
		toolBar.add(straight);
		
		Icon righticon = new ImageIcon("src/share.png");
		right = new JButton(righticon);
		toolBarGroup.add(right);
		toolBar.add(right);
		
		toolBar.addSeparator(new Dimension(100,30));
		
		Icon staricon = new ImageIcon("src/star.png");
		star = new JButton(staricon);
		toolBarGroup.add(star);
		toolBar.add(star);
		
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
	}

}
