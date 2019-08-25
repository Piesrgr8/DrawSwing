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
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class DrawArea extends JComponent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	static JComponent jcp;
    public static Integer nums;
	public static Image Canvasimage;
	private Graphics2D g2;
	private int currentX, currentY, oldX, oldY;
	private static int lock;
	
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
				
				if (!isLocked() && g2 != null) {
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
		if (Canvasimage == null) {
			Canvasimage = createImage(getSize().width, getSize().height);
			g2 = (Graphics2D) Canvasimage.getGraphics();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			clear();
		}
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(Canvasimage, 0, 0, null);
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
	
	public static boolean isLocked() {
		if (lock == 1) {
			return true;
		}
		return false;
	}
	
	public static void setLock(Boolean b) {
		if (b == true) {
			lock = 1;
			System.out.println("Set Lock to 1");
		} else {
			lock = 0;
			System.out.println("Set Lock to 0");
		}
	}
	
	public static void saveCanvas(File path) {
		BufferedImage image = new BufferedImage(DrawSwing.wid,
				DrawSwing.hei, BufferedImage.TYPE_INT_RGB);

		Graphics2D g2 = (Graphics2D) image.getGraphics();

		jcp.paint(g2);
		try {
			System.out.println(path.toString());
			ImageIO.write(image, "png", path);
		} catch (Exception e) {
			System.out.println("Error on saving!");
			DrawSwing.setWarningMsg("The file could not be saved at this time." + "Please try again later.");
			e.printStackTrace();
		}
	}

}
