package com.greenjavadude.FileSharer;

import java.io.*;
import java.net.*;

public class ShareUploader{
	public static final int PORT = 8787;
	
	private File saveObj;
	private Socket socket;
	private ObjectOutputStream output;
	
	private boolean startup;
	
	public ShareUploader(File file, String ip){
		saveObj = file;
		try{
			socket = new Socket(InetAddress.getByName(ip), PORT);
			output = (ObjectOutputStream) socket.getOutputStream();
			startup = true;
		}catch(Exception e){
			startup = false;
		}
	}
	
	public boolean upload(){
		if(startup){
			try{
				output.writeObject(saveObj);
				return true;
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return false;
	}
}










