import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegiCode extends JFrame {
	
	private JToolBar toolBar;
	private ButtonGroup toolBarGroup;
	private JButton loop2, loop3, loop4, right, straight, left;
	private JPanel mainPanel, methodPanel;
	private JTextField mainTextField, starTextField;
	
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
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLACK);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
	}
	
	public void setUpMethodPanel() {
		methodPanel = new JPanel();
		methodPanel.setLayout(new GridLayout(0,1)); 
		methodPanel.setBackground(Color.GRAY);
		
		
		
		JLabel labelMain = new JLabel("Main Method:");
		JLabel labelStar = new JLabel("Star Method:");
		methodPanel.add(labelMain);
		mainTextField = new JTextField(30);
		
	
		
		mainTextField.setDragEnabled(true);
		methodPanel.add(mainTextField);
		
		methodPanel.add(labelStar);
		starTextField = new JTextField(30);
		methodPanel.add(starTextField);
		
		
		getContentPane().add(methodPanel, BorderLayout.EAST);
	}
	
	
	public void setUpToolBar() {
		
		toolBar = new JToolBar("My ToolBar");
		toolBarGroup = new ButtonGroup();
		
		
		loop2 = new JButton(" x2");
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
		
		
		loop3 = new JButton (" x3");
		toolBarGroup.add(loop3);
		toolBar.add(loop3);
		
		loop4 = new JButton(" x4");
		toolBarGroup.add(loop4);
		toolBar.add(loop4);
		
		toolBar.addSeparator(new Dimension(100,30));
		
		left = new JButton("Turn left");
		toolBarGroup.add(left);
		toolBar.add(left);
		
		straight = new JButton("Go straight");
		toolBarGroup.add(straight);
		toolBar.add(straight);
		
		right = new JButton("Turn right");
		toolBarGroup.add(right);
		toolBar.add(right);
		
		
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
	}

}
