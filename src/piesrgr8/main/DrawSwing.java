package piesrgr8.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DrawSwing {

	Border bline;
	JButton clearB, blackB, blueB, greenB, redB, magB, yellowB, orangeB, pinkB, brownB, lockB;
	public static JMenuItem menuItem;
	JMenu lock;
	String[] jcbstuff = { "Pen", "Eraser" };
	DrawArea da;
	public static JComboBox<String> jcb;

	public static JSpinner spinner;
	
	public static int wid;
	public static int hei;

	ActionListener act = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == clearB) {
				da.clear();
				jcb.setSelectedItem("Pen");
			}
			
			if (e.getSource() == menuItem) {
				DrawSaving.SaveL();
			}
			
			if (e.getSource() == lockB) {
				if (!DrawArea.isLocked()) {
				    DrawArea.setLock(true);
				lockB.setBackground(Color.RED);
				} else {
					DrawArea.setLock(false);
					lockB.setBackground(Color.WHITE);
				}
			}
			
			if (jcb.getSelectedItem().equals("Pen")) {
				if (e.getSource() == blackB) {
					da.black();
				} else if (e.getSource() == blueB) {
					da.blue();
				} else if (e.getSource() == greenB) {
					da.green();
				} else if (e.getSource() == redB) {
					da.red();
				} else if (e.getSource() == magB) {
					da.magenta();
				} else if (e.getSource() == yellowB) {
					da.yellow();
				} else if (e.getSource() == orangeB) {
					da.orange();
				} else if (e.getSource() == pinkB) {
					da.pink();
				} else if (e.getSource() == brownB) {
					da.brown();
				}
			}
			
			if (e.getSource() == jcb) {
				da.updateTool(jcb.getSelectedItem());
			}
		}
	};

	ChangeListener listener = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			if (e.getSource() == spinner) {
				Integer sp2 = (Integer) spinner.getValue();
				da.stroke(sp2);
			}
		}
	};

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		new DrawSwing().show();
		DrawArea.nums = 2;
	}

	public void show() {
		JFrame f = new JFrame("For Mom");
		Container cont = f.getContentPane();

		cont.setLayout(new BorderLayout());

		da = new DrawArea();

		cont.add(da, BorderLayout.CENTER);

		JPanel pan = new JPanel();
		clearB = new JButton("Clear");
		clearB.addActionListener(act);

		blackB = new JButton("Black");
		blackB.addActionListener(act);
		blackB.setForeground(Color.white);
		blackB.setBackground(Color.black);

		redB = new JButton("Red");
		redB.addActionListener(act);
		redB.setForeground(new Color(60, 0, 0));
		redB.setBackground(Color.red);

		blueB = new JButton("Blue");
		blueB.addActionListener(act);
		blueB.setForeground(new Color(0, 0, 50));
		blueB.setBackground(Color.blue);

		greenB = new JButton("Green");
		greenB.addActionListener(act);
		greenB.setForeground(new Color(5, 130, 0));
		greenB.setBackground(Color.green);

		yellowB = new JButton("Yellow");
		yellowB.addActionListener(act);
		yellowB.setForeground(new Color(255, 185, 0));
		yellowB.setBackground(Color.yellow);

		orangeB = new JButton("Orange");
		orangeB.addActionListener(act);
		orangeB.setForeground(new Color(255, 55, 0));
		orangeB.setBackground(new Color(255, 132, 0));

		pinkB = new JButton("Pink");
		pinkB.addActionListener(act);
		pinkB.setForeground(new Color(255, 60, 134));
		pinkB.setBackground(Color.pink);

		brownB = new JButton("Brown");
		brownB.addActionListener(act);
		brownB.setForeground(new Color(186, 127, 0));
		brownB.setBackground(new Color(102, 60, 0));

		magB = new JButton("Purple");
		magB.addActionListener(act);
		magB.setForeground(new Color(255, 0, 255));
		magB.setBackground(new Color(108, 0, 157));
		
		lockB = new JButton("");
		lockB.addActionListener(act);
		ImageIcon imgc1 = new ImageIcon(new ImageIcon(getClass().getResource("lock.png")).getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
		lockB.setIcon(imgc1);
		lockB.setToolTipText("Locks the Screen");

		spinner = new JSpinner(new SpinnerNumberModel(2, 0, 40, 1));
		spinner.addChangeListener(listener);

		jcb = new JComboBox<String>(jcbstuff);
		jcb.setEditable(false);
		jcb.addActionListener(act);
		
		

		pan.add(clearB);
		pan.add(blackB);
		pan.add(redB);
		pan.add(blueB);
		pan.add(greenB);
		pan.add(yellowB);
		pan.add(orangeB);
		pan.add(magB);
		pan.add(pinkB);
		pan.add(brownB);
		pan.add(lockB);
		pan.add(spinner);
		pan.add(jcb);

		bline = BorderFactory.createLineBorder(Color.BLACK);
		pan.setBorder(bline);

		cont.add(pan, BorderLayout.SOUTH);

		Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("519dba9c90df32e.png"));

		f.setIconImage(img);
		f.setJMenuBar(menu());
		f.setSize(1280, 800);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(true);
		
		wid = f.getSize().width;
		hei = f.getSize().height;
	}
	
	public JMenuBar menu() {
		ImageIcon imgc = new ImageIcon(new ImageIcon(getClass().getResource("save.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		JMenuBar menuBar = new JMenuBar();
		
		//Build the first menu.
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		menuBar.add(menu);
		
		//a group of JMenuItems
		menuItem = new JMenuItem("Save",
				imgc);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "This doesn't really do anything");
		menuItem.addActionListener(act);
		menu.add(menuItem);
		return menuBar;
	}
	
	public static void setWarningMsg(String text){
	    Toolkit.getDefaultToolkit().beep();
	    JOptionPane optionPane = new JOptionPane(text,JOptionPane.WARNING_MESSAGE);
	    JDialog dialog = optionPane.createDialog("Warning!");
	    dialog.setAlwaysOnTop(true);
	    dialog.setVisible(true);
	}

}
