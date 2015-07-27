package com.greenjavadude.FileSharer;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import javax.swing.*;

public class GUI extends JFrame{
	private static final long serialVersionUID = -4746261571872333721L;
	private static final String[] list = {"Choose to receive or send", "Receive", "Send"};
	
	private Mode mode;
	private Receiver receiver;
	private ShareUploader uploader;
	
	private JPanel choose;
	private JComboBox<String> box;
	private JButton selectFile;
	private JTextField pathy;
	private JButton start;
	
	private JFileChooser fileChooser;
	
	private File file;
	
	public GUI(){
		setTitle("File Sharer");
		setSize(500, 300);
		setLocationRelativeTo(null);
		
		
		fileChooser = new JFileChooser();
		
		box = new JComboBox<String>(list);
		box.setSelectedIndex(0);
		box.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String selected = (String) box.getSelectedItem();
				if(selected.equals("Receive")){
					mode = Mode.RECEIVER;
					add(BorderLayout.CENTER, start);
					if(choose.isAncestorOf(selectFile)){
						choose.remove(selectFile);
						choose.remove(pathy);
					}
					setVisible(true);
				}else if(selected.equals("Send")){
					mode = Mode.SENDER;
					if(isAncestorOf(start) && file.equals(null)){
						remove(start);
					}
					if(!choose.isAncestorOf(selectFile)){
						choose.add(selectFile);
						choose.add(pathy);
					}
					setVisible(true);
				}else if(selected.equals("Choose to receive or send")){
					mode = null;
					if(isAncestorOf(start)){
						remove(start);
						System.out.println("Removed start");
					}
					if(choose.isAncestorOf(selectFile)){
						choose.remove(selectFile);
						choose.remove(pathy);
					}
					setVisible(true);
				}
			}
		});
		
		start = new JButton("Start");
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//start
			}
		});
		
		pathy = new JTextField("");
		pathy.setEditable(false);
		
		selectFile = new JButton("Select File");
		selectFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int returnVal = fileChooser.showOpenDialog(GUI.this);
				
				if(returnVal == JFileChooser.APPROVE_OPTION){
					file = fileChooser.getSelectedFile();
					//dostuff
					System.out.println(file.getAbsolutePath());
					pathy.setText(file.getAbsolutePath());
					add(BorderLayout.CENTER, start);
				}
				setVisible(true);
			}
		});
		
		choose = new JPanel();
		choose.add(box);
		
		add(BorderLayout.NORTH, choose);
		
		setVisible(true);
	}
	
	public static void main(String[] args){
		GUI gui = new GUI();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}