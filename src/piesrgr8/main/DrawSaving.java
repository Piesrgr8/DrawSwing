package piesrgr8.main;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DrawSaving extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static void SaveL() {
	      JFileChooser c = new JFileChooser();
	      FileNameExtensionFilter filter = new FileNameExtensionFilter(
	    	        "JPG & PNG Images", "jpg", "png");
	    	    c.setFileFilter(filter);

	      // Demonstrate "Save" dialog:
	      int rVal = c.showSaveDialog(DrawSwing.menuItem);
	      if (rVal == JFileChooser.APPROVE_OPTION) {
	    	  File file = c.getSelectedFile();
	    	  if (file == null) {
	    		  DrawSwing.setWarningMsg("The file is currently null.");
	    	        return;
	    	      }
	    	  
	    	      if (!file.getName().toLowerCase().endsWith(".png")) {
	    	        file = new File(file.getParentFile(), file.getName() + ".png");
	    	      }
	    	  DrawArea.saveCanvas(file);
	      }
	      if (rVal == JFileChooser.CANCEL_OPTION) {
	    	  
	      }
	  }

	public static void open() {
		run(new DrawSaving(), 250, 110);
	}

	private static void run(JFrame frame, int width, int height) {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(width, height);
		frame.setVisible(true);
	}
}
