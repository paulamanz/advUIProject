 	import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
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
import javax.swing.JTextArea;
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
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class RegiCode extends JFrame {

	private JToolBar toolBar, toolBar2;
	private ButtonGroup toolBarGroup;
	private JButton loop2, loop3, loop4, right, straight, left, play, playbutton, star, down;
	private JButton level1, level2, level3;
	private JPanel mainPanel, methodPanel;
	private JTextField mainTextField, starTextField;
	private MouseAdapter listener;
	private MazeComponent mazecomp;
	
	private ArrayList<String> draggedIconsInMain = new ArrayList<String>();
	private ArrayList<String> draggedIconsInStar = new ArrayList<String>();

	
	public RegiCode() {
		super("RegiCode");
		this.setPreferredSize(new Dimension(1800,900));
		this.setMinimumSize(new Dimension(900, 450));

		setUpUI();
		this.pack();
		this.setVisible(true);
	}

	public void setUpUI() {
		setUpListeners();
		setUpToolBar();
		setUpMainPanel();
		setUpMethodPanel();
	}
	
	public void setUpListeners(){

		listener = new MouseAdapter() {
			 
					//Point p = null;
					
					public void mouseDragged(MouseEvent e) {
						JButton button = (JButton) e.getSource();
						TransferHandler handle = button.getTransferHandler();
						handle.exportAsDrag(button, e, TransferHandler.COPY);
				 }
		    };
	}
	

	/*method to create the table*/
	public void setUpMainPanel() {


		mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLACK);
		mazecomp = new MazeComponent(1);
		mainPanel.add(mazecomp);

		getContentPane().add(mainPanel, BorderLayout.CENTER);
	}
	
	

	/*method to create the mainlabel, star, arrows*/
	public void setUpMethodPanel() {
		
		
		methodPanel = new JPanel(new GridBagLayout());
		methodPanel.setBorder(new EmptyBorder(6,6,6,6));
		GridBagConstraints c = new GridBagConstraints();
		methodPanel.setBackground(new Color(247,247,247));
		

		JLabel labelMain = new JLabel("Main");
		labelMain.setFont(new Font("MontSerrat", Font.BOLD, 20));
		labelMain.setForeground(new Color(38,185,154));
		c.gridx = 0;
		c.gridy = 0;
		labelMain.setVerticalTextPosition(JLabel.BOTTOM);
		methodPanel.add(labelMain, c);
        
        
		JPanel paneli = createEmptyJPanel();
		paneli.setBackground(new Color(166,240,225));
        new MyDropTargetListener(paneli, "mainPanel");
        c.gridx = 1;
		c.gridy = 0;
		//c.ipady = 1; 
		c.gridheight = 1; 
		c.gridwidth = 1;
		c.insets = new Insets(0,4,0,0);		
        methodPanel.add(paneli,c);

		Icon staricon = new ImageIcon("src/loop.png");
		JLabel labelStar = new JLabel(staricon);
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1; //posicaonavertical
		c.gridwidth = 1;
		methodPanel.add(labelStar, c);

		
		JPanel panelstar = createEmptyJPanel();
		panelstar.setBackground(new Color(195,197,242));
        new MyDropTargetListener(panelstar, "starPanel");
        c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1; 
		c.gridwidth = 1;
		c.insets = new Insets(3,3,0,0);
        methodPanel.add(panelstar,c);
        
        
        


		Icon stepicon = new ImageIcon("src/play.png");
		playbutton = new JButton (stepicon);
		playbutton.setBackground(new Color(240,203,19));
		c.gridx=1;
		c.gridy =4;
		c.gridwidth = 2; 
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		methodPanel.add(playbutton, c);

		playbutton.addActionListener(event -> runCommands());
		
		getContentPane().add(methodPanel, BorderLayout.EAST);

	}
	
	public boolean runSingleCommand(String dir) {
		boolean moved = false;
		
		moved = this.mazecomp.runDirections(dir);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return moved;
	}
	public void runCommands() {
		boolean success=false;
		boolean moved = false;
		
		 
		new Thread(){ 
				public boolean moved=false;
				public boolean success=false;
				 public void run() { 
					 int loop = 1;
					 if(draggedIconsInStar.size()>0) {
						 switch(draggedIconsInStar.get(0)) {
						 case "cicle2":
							 loop=2;
							 break;
						 case "cicle3":
							 loop=3;
							 break;
						 case "cicle4":
							 loop=4;
							 break;
						 }
					 }
					 
					 for (int i=0; i < loop; i++) {
						 for (String dir : draggedIconsInMain) {
							 try { 
								 moved = runSingleCommand(dir);
							 } catch (Exception e) { 
								 e.printStackTrace();
							 } 
						 } 
					 }
					 
					 success = mazecomp.isSolved();
						if(success) {
							System.out.println("THE GAME IS SOLVED!");
						}else {
							System.out.println("Try next time :(");
						}
					 
					} 
			}.start();
			
			
			System.out.println("!!!!!");
		
		
	}
	
	
	class MyDropTargetListener extends DropTargetAdapter {

	    private DropTarget dropTarget;
	    private JPanel p;
	    private String sourcePanel;

	    public MyDropTargetListener(JPanel panel, String sourcePanel) {
	        p = panel;
	        dropTarget = new DropTarget(panel, DnDConstants.ACTION_COPY, this, true, null);
	        this.sourcePanel = sourcePanel;

	    }

	    @Override
	    public void drop(DropTargetDropEvent event) {
	        try {
	            DropTarget test = (DropTarget) event.getSource();
	            Component ca = (Component) test.getComponent();
	            Point dropPoint = ca.getMousePosition();
	            Transferable tr = event.getTransferable();

	            if (event.isDataFlavorSupported(DataFlavor.imageFlavor)) {
	                Icon ico = (Icon) tr.getTransferData(DataFlavor.imageFlavor);

	                if (ico != null) {
	                	
	                	if (sourcePanel.equals("mainPanel")) {
	                		draggedIconsInMain.add(ico.toString().split("/")[1].split("\\.")[0]);
	                	}
	                	else if (sourcePanel.equals("starPanel")) {
	                		draggedIconsInStar.add(ico.toString().split("/")[1].split("\\.")[0]);
	                	}
	                	
	                	System.out.println("In Main: " + draggedIconsInMain);
	                	System.out.println("In Star: " + draggedIconsInStar);
	                	
	                    p.add(new JLabel(ico));
	                    p.revalidate();
	                    p.repaint();
	                    event.dropComplete(true);
	                }
	            } else {
	                event.rejectDrop();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            event.rejectDrop();
	        }
	    }
	}


	private static JPanel createEmptyJPanel() {
        final JPanel p = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(300, 300);
            }
        };

        TransferHandler dnd = new TransferHandler() {
            @Override
            public boolean canImport(TransferSupport support) {
                if (!support.isDrop()) {
                    return false;
                }
                //only Strings
                if (!support.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean importData(TransferSupport support) {
                if (!canImport(support)) {
                    return false;
                }

                Transferable tansferable = support.getTransferable();
                Icon ico;
                try {
                    ico = (Icon) tansferable.getTransferData(DataFlavor.imageFlavor);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                p.add(new JLabel(ico));
                return true;
            }
        };

        p.setTransferHandler(dnd);

        return p;
    }

	/*method to create the toolbar*/
	public void setUpToolBar() {

		toolBar = new JToolBar();
		toolBar2 = new JToolBar();
		
		JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		toolBarGroup = new ButtonGroup();

		Icon lefticon = new ImageIcon("src/left.png");
		left = new JButton(lefticon);
		MyDragGestureListener leftDraglistener = new MyDragGestureListener();
        DragSource leftDrag = new DragSource();
        leftDrag.createDefaultDragGestureRecognizer(left, DnDConstants.ACTION_COPY, leftDraglistener);
		toolBarGroup.add(left);
		toolBar.add(left);
		
		toolBar.addSeparator(new Dimension(10,30));
		
		
		Icon straighticon = new ImageIcon("src/straight.png");
		straight = new JButton(straighticon);
		MyDragGestureListener straightDraglistener = new MyDragGestureListener();
        DragSource straightDrag = new DragSource();
        straightDrag.createDefaultDragGestureRecognizer(straight, DnDConstants.ACTION_COPY, straightDraglistener);
		toolBarGroup.add(straight);
		toolBar.add(straight);
		
		toolBar.addSeparator(new Dimension(10,30));
		
		Icon downicon = new ImageIcon("src/down.png");
		down = new JButton(downicon);
		MyDragGestureListener downDraglistener = new MyDragGestureListener();
        DragSource downDrag = new DragSource();
        downDrag.createDefaultDragGestureRecognizer(down, DnDConstants.ACTION_COPY, downDraglistener);
		toolBarGroup.add(down);
		toolBar.add(down);
		
		toolBar.addSeparator(new Dimension(10,30));
				
		Icon righticon = new ImageIcon("src/right.png");
		right = new JButton(righticon);
		MyDragGestureListener rightDraglistener = new MyDragGestureListener();
        DragSource rightDrag = new DragSource();
        rightDrag.createDefaultDragGestureRecognizer(right, DnDConstants.ACTION_COPY, rightDraglistener);
		toolBarGroup.add(right);
		toolBar.add(right);
		
		toolBar.addSeparator(new Dimension(100,30));

		Icon loop2icon = new ImageIcon("src/cicle2.png");
		loop2 = new JButton(loop2icon);
		MyDragGestureListener dlistener = new MyDragGestureListener();
        DragSource ds1 = new DragSource();
        ds1.createDefaultDragGestureRecognizer(loop2, DnDConstants.ACTION_COPY, dlistener);
        toolBarGroup.add(loop2);
		toolBar.add(loop2);
		toolBar.addSeparator(new Dimension(10,30));

		Icon loop3icon = new ImageIcon("src/cicle3.png");
		loop3 = new JButton (loop3icon);
		MyDragGestureListener dlistener2 = new MyDragGestureListener();
        DragSource ds2 = new DragSource();
        ds2.createDefaultDragGestureRecognizer(loop3, DnDConstants.ACTION_COPY, dlistener2);
		toolBarGroup.add(loop3);
		toolBar.add(loop3);
		toolBar.addSeparator(new Dimension(10,30));

		Icon loop4icon = new ImageIcon("src/cicle4.png");
		loop4 = new JButton(loop4icon);
		MyDragGestureListener dlistener3 = new MyDragGestureListener();
        DragSource ds3 = new DragSource();
        ds3.createDefaultDragGestureRecognizer(loop4, DnDConstants.ACTION_COPY, dlistener3);
		toolBarGroup.add(loop4);
		toolBar.add(loop4);
		toolBar.addSeparator(new Dimension(10,30));
		
		

		toolBar.addSeparator(new Dimension(100,30));

		JPanel panelButton = new JPanel();
		level1 = new JButton("Level 1");
		level1.setPreferredSize(new Dimension(575,50));
		level1.setBackground(new Color(0,4,85));
		level1.setForeground(Color.white);
		level1.setFont(new Font("MontSerrat", Font.BOLD, 30));
		panelButton.add(level1);
		toolBar2.add(panelButton);
		
		level2 = new JButton("Level 2");
		level2.setPreferredSize(new Dimension(575,50));
		level2.setBackground(new Color(0,4,85));
		level2.setForeground(Color.white);
		level2.setEnabled(false);
		level2.setFont(new Font("MontSerrat", Font.BOLD, 30));
		panelButton.add(level2);
		toolBar2.add(panelButton);
		
		level3 = new JButton("Level 3");
		level3.setPreferredSize(new Dimension(575,50));
		level3.setBackground(new Color(0,4,85));
		level3.setForeground(Color.white);
		level3.setEnabled(false);
		level3.setFont(new Font("MontSerrat", Font.BOLD, 30));
		panelButton.add(level3);
		toolBar2.add(panelButton);
		
		
		
		toolBar.setFloatable( false);
		toolBar2.setFloatable( false);
		

		panel.add(toolBar2);
	    panel.add(toolBar);




		this.getContentPane().add(panel, BorderLayout.NORTH);
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
	 
	 class MyDragGestureListener implements DragGestureListener {

		    @Override
		    public void dragGestureRecognized(DragGestureEvent event) {
		        JButton label = (JButton) event.getComponent();
		        final Icon ico = label.getIcon();


		        Transferable transferable = new Transferable() {
		            @Override
		            public DataFlavor[] getTransferDataFlavors() {
		                return new DataFlavor[]{DataFlavor.imageFlavor};
		            }

		            @Override
		            public boolean isDataFlavorSupported(DataFlavor flavor) {
		                if (!isDataFlavorSupported(flavor)) {
		                    return false;
		                }
		                return true;
		            }

		            @Override
		            public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		                return ico;
		            }
		        };
		        event.startDrag(null, transferable);
		    }
		}



}