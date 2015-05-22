package com.floreantpos.main;

import com.floreantpos.config.AppConfig;
import com.floreantpos.swing.GlassPane;
import org.jdesktop.swingx.JXLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class PosWindow extends JFrame implements WindowListener {
	private static final String WLOCY = "wlocy"; //$NON-NLS-1$
	private static final String WLOCX = "wlocx"; //$NON-NLS-1$
	private static final String WHEIGHT = "wheight"; //$NON-NLS-1$
	private static final String WWIDTH = "wwidth"; //$NON-NLS-1$
	
	private GlassPane glassPane;
//	private JXStatusBar statusBar;
	private JXLabel statusBar;
//	private JLabel statusLabel;
	
	public PosWindow() {

        setUndecorated(false);
        setResizable(true);

		setIconImage(Application.getApplicationIcon().getImage());
		
		addWindowListener(this);
		
		glassPane = new GlassPane();
		glassPane.setOpacity(0.6f);
		setGlassPane(glassPane);

//        bannerLabel = new JXLabel("");
//        getContentPane().add(bannerLabel,BorderLayout.SOUTH);
//TODO
//      statusBar = new JXLabel(new javax.swing.ImageIcon(getClass().getResource("/images/bank_logo.png")), JTextField.LEFT);
      statusBar = new JXLabel("", JTextField.LEFT);
//		getContentPane().add(statusBar, BorderLayout.SOUTH);

//		statusLabel = new JLabel("");
//		statusBar.add(statusLabel, JXStatusBar.Constraint.ResizeBehavior.FILL);
	}
	
	public void setStatus(String status) {
		statusBar.setText(status);
	}

	public void setupSizeAndLocation() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(AppConfig.getInt(WWIDTH, (int) screenSize.getWidth()), AppConfig.getInt(WHEIGHT, (int) screenSize.getHeight()));
		
		setLocation(AppConfig.getInt(WLOCX, ((screenSize.width - getWidth()) >> 1)), AppConfig.getInt(WLOCY, ((screenSize.height - getHeight()) >> 1)));
		setMinimumSize(new Dimension(120, 90));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
//		GraphicsDevice window = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
//		setUndecorated(true);
//		window.setFullScreenWindow(this);
	}
	
	public void saveSizeAndLocation() {
		int width = getWidth();
		int height = getHeight();
		AppConfig.putInt(WWIDTH, width);
		AppConfig.putInt(WHEIGHT, height);

		Point locationOnScreen = getLocationOnScreen();
		AppConfig.putInt(WLOCX, locationOnScreen.x);
		AppConfig.putInt(WLOCY, locationOnScreen.y);
	}
	
	public void setGlassPaneVisible(boolean b) {
		glassPane.setVisible(b);
	}
	
	public void setGlassPaneMessage(String message) {
		glassPane.setMessage(message);
	}

	public void windowOpened(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		Application.getInstance().shutdownPOS();
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}
}
