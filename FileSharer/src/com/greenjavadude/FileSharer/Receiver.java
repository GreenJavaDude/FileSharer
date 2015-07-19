package com.greenjavadude.FileSharer;

import java.io.*;
import java.net.*;

public class Receiver {
	public static final int PORT = 8787;
	
	private Socket socket;
	private ObjectInputStream input;
	private ServerSocket server;
	
	public Receiver(){
		try{
			server = new ServerSocket(PORT, 1);
		}catch(IOException e){
			
		}
	}
	
	public File getObject(){
		File file = null;
		try{
			socket = server.accept();
			input = (ObjectInputStream) socket.getInputStream();
			file = (File) input.readObject();
		}catch(IOException e){
			
		}catch(ClassNotFoundException e){
			
		}
		return file;
	}
	
	public boolean saveFile(File f){
		boolean worked = false;
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/user/home/Desktop/Download/"+f.getName()));
			try{
				oos.writeObject(f);
				worked = true;
			}finally{
				oos.close();
			}
		}catch(Exception e){
			
		}
		return worked;
	}
}