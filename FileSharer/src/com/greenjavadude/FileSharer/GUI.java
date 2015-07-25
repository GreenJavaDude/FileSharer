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
	
	private JFileChooser fileChooser;
	
	
	
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
					if(choose.isAncestorOf(selectFile)){
						choose.remove(selectFile);
						choose.remove(pathy);
						setVisible(true);
					}
				}else if(selected.equals("Send")){
					mode = Mode.SENDER;
					if(!choose.isAncestorOf(selectFile)){
						choose.add(selectFile);
						choose.add(pathy);
						setVisible(true);
					}
				}else{
					mode = null;
					if(choose.isAncestorOf(selectFile)){
						choose.remove(selectFile);
						choose.remove(pathy);
						setVisible(true);
					}
				}
			}
		});
		
		pathy = new JTextField();
		pathy.setEditable(false);
		
		selectFile = new JButton("Select File");
		selectFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				File file = null;
				
				int returnVal = fileChooser.showOpenDialog(GUI.this);
				
				if(returnVal == JFileChooser.APPROVE_OPTION){
					file = fileChooser.getSelectedFile();
					//dostuff
					pathy.setText(file.getAbsolutePath());
				}
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