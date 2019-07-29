package piesrgr8.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;

public class DrawArea extends JComponent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

    public static Integer nums;
	private Image image;
	private Graphics2D g2;
	private int currentX, currentY, oldX, oldY;
	
	public DrawArea() {
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {
				oldX = e.getX();
				oldY = e.getY();
			}
			
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			
			public void mouseDragged(MouseEvent e) {
				currentX = e.getX();
				currentY = e.getY();
				
				if (g2 != null) {
					g2.setStroke(new BasicStroke(nums));
					g2.drawLine(oldX, oldY, currentX, currentY);
					repaint();
					oldX = currentX;
					oldY = currentY;
				}
			}
		});
	}
	
	protected void paintComponent(Graphics g) {
		if (image == null) {
			image = createImage(getSize().width, getSize().height);
			g2 = (Graphics2D) image.getGraphics();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			clear();
		}
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(image, 0, 0, null);
	}
	
	public void clear() {
		g2.setPaint(Color.white);
		g2.fillRect(0, 0, getSize().width, getSize().height);
		g2.setPaint(Color.black);
		repaint();
	}
	
	public void red() {
		g2.setPaint(Color.red);
	}
	
	public void blue() {
		g2.setPaint(Color.blue);
	}
	
	public void green() {
		g2.setPaint(Color.green);
	}
	
	public void yellow() {
		g2.setPaint(Color.yellow);
	}
	
	public void black() {
		g2.setPaint(Color.black);
	}
	
	public void orange() {
		g2.setPaint(new Color(255, 132, 0));
	}
	
	public void magenta() {
		g2.setPaint(new Color(108, 0, 157));
	}
	
	public void pink() {
		g2.setPaint(Color.pink);
	}
	
	public void brown() {
		g2.setPaint(new Color(102, 60, 0));
	}
	
	public void updateTool(Object object) {
		if (object == "Pen") {
			black();
		}
		
		if (object == "Eraser") {
			g2.setPaint(Color.white);
		}
	}
	
	public void stroke(Integer nums2) {
		nums = nums2;
	}

}
