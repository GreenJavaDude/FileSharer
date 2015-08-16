package com.greenjavadude.FileSharer;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class GUI extends JFrame{
	private static final long serialVersionUID = -4746261571872333721L;
	private static final String[] list = {"Choose to receive or send", "Receive", "Send"};
	
	private Receiver receiver;
	private Uploader uploader;
	
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
					add(BorderLayout.CENTER, start);
					if(choose.isAncestorOf(selectFile)){
						choose.remove(selectFile);
						choose.remove(pathy);
					}
				}else if(selected.equals("Send")){
					if(isAncestorOf(start)){
						remove(start);
					}
					if(!choose.isAncestorOf(selectFile)){
						choose.add(selectFile);
						choose.add(pathy);
					}
				}else if(selected.equals("Choose to receive or send")){
					if(isAncestorOf(start)){
						remove(start);
					}
					if(choose.isAncestorOf(selectFile)){
						choose.remove(selectFile);
						choose.remove(pathy);
					}
				}
				repaint();
				setVisible(true);
			}
		});
		
		start = new JButton("Start");
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(choose.isAncestorOf(selectFile)){
					//send mode
					String ip = JOptionPane.showInputDialog(null, "Enter the ipv4 of the receiver", "Question", JOptionPane.QUESTION_MESSAGE);
					uploader = new Uploader(ip, file);
					uploader.upload();
					
					while(!uploader.isFinished()){
						try{
							Thread.sleep(10);
						}catch(Exception ex){
							
						}
					}
										
					JOptionPane.showMessageDialog(null, "The file has been uploaded");
				}else{
					//receive mode
					int returnVal = fileChooser.showOpenDialog(GUI.this);
					
					if(returnVal == JFileChooser.APPROVE_OPTION){
						receiver = new Receiver(fileChooser.getSelectedFile());
						receiver.start();
						
						while(!uploader.isFinished()){
							try{
								Thread.sleep(10);
							}catch(Exception ex){
								
							}
						}
						
						JOptionPane.showMessageDialog(null, "The file has been downloaded");
					}
				}
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
				
				repaint();
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