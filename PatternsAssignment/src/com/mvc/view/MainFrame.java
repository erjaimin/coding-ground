package com.mvc.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import com.main.Constants;
import com.main.PatternObjectFactroy;

public class MainFrame {
	
	private JFrame frame;
	private JSplitPane mainPane;
	private JSplitPane perspectivePlane;
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem load;
	private JMenuItem quit;
	private JMenuItem undo;
	private JMenuItem redo;
	private JFileChooser fileChooser;

	public MainFrame() {
		super();
		addComponents();
	}

	private void addComponents() {
		frame = new JFrame();
		fileChooser = new JFileChooser();
		
		mainPane = new JSplitPane();
		mainPane.setLeftComponent(PatternObjectFactroy.getJpanel(Constants.IMAGE_VIEW));
		
	    perspectivePlane = new JSplitPane();
		perspectivePlane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		perspectivePlane.setResizeWeight(0.5);
		perspectivePlane.setLeftComponent(PatternObjectFactroy.getJpanel(Constants.TOP_VIEW));
		perspectivePlane.setRightComponent(PatternObjectFactroy.getJpanel(Constants.BOTTOM_VIEW));
		
		mainPane.setRightComponent(perspectivePlane);
		mainPane.setResizeWeight(0.25);
		frame.add(mainPane, BorderLayout.CENTER);
		
	    JMenuBar menuBar = new JMenuBar();
	    JMenu fileMenu = new JMenu("File");
	    JMenu editMenu = new JMenu("Edit");
		
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		load = new JMenuItem("Load");
		quit = new JMenuItem("Quit");
		undo = new JMenuItem("Undo");
		redo = new JMenuItem("Redo");
		
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(load);
		fileMenu.add(quit);
		
		editMenu.add(undo);
		editMenu.add(redo);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		frame.add(menuBar, BorderLayout.NORTH);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
		
	}

	/**
	 * @return the mainPane
	 */
	public JSplitPane getMainPane() {
		return mainPane;
	}

	/**
	 * @param mainPane the mainPane to set
	 */
	public void setMainPane(JSplitPane mainPane) {
		this.mainPane = mainPane;
	}

	/**
	 * @return the perspectivePlane
	 */
	public JSplitPane getPerspectivePlane() {
		return perspectivePlane;
	}

	/**
	 * @param perspectivePlane the perspectivePlane to set
	 */
	public void setPerspectivePlane(JSplitPane perspectivePlane) {
		this.perspectivePlane = perspectivePlane;
	}

	/**
	 * @return the open
	 */
	public JMenuItem getOpen() {
		return open;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(JMenuItem open) {
		this.open = open;
	}

	/**
	 * @return the save
	 */
	public JMenuItem getSave() {
		return save;
	}

	/**
	 * @param save the save to set
	 */
	public void setSave(JMenuItem save) {
		this.save = save;
	}

	/**
	 * @return the load
	 */
	public JMenuItem getLoad() {
		return load;
	}

	/**
	 * @param load the load to set
	 */
	public void setLoad(JMenuItem load) {
		this.load = load;
	}

	/**
	 * @return the quit
	 */
	public JMenuItem getQuit() {
		return quit;
	}

	/**
	 * @param quit the quit to set
	 */
	public void setQuit(JMenuItem quit) {
		this.quit = quit;
	}

	/**
	 * @return the undo
	 */
	public JMenuItem getUndo() {
		return undo;
	}

	/**
	 * @param undo the undo to set
	 */
	public void setUndo(JMenuItem undo) {
		this.undo = undo;
	}

	/**
	 * @return the redo
	 */
	public JMenuItem getRedo() {
		return redo;
	}

	/**
	 * @param redo the redo to set
	 */
	public void setRedo(JMenuItem redo) {
		this.redo = redo;
	}

	/**
	 * @return the fileChooser
	 */
	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	/**
	 * @param fileChooser the fileChooser to set
	 */
	public void setFileChooser(JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}
	
	public void openFileDialog(){
		this.fileChooser.showOpenDialog(frame);
	}
	
	public void openSaveDialog(){
		this.fileChooser.showSaveDialog(frame);
	}
	
	public void showErrorDialog(String message){
		JOptionPane.showMessageDialog(frame, message);
	}
}
