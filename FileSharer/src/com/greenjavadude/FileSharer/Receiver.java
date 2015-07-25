package com.greenjavadude.FileSharer;

import java.io.*;
import java.net.*;

public class Receiver implements Runnable{
	public static final int PORT = 8787;
	
	private ServerSocket server;
	private Socket socket;
	
	private boolean success;
	private File file;
	
	public Receiver(File file){
		success = false;
		this.file = file;
		try{
			server = new ServerSocket(PORT, 10);
			new Thread(this).start();
		}catch(IOException e){
			
		}
	}
	
	public void run(){
		try{
			socket = server.accept();
			byte[] bytes = new byte[1024];
		    InputStream is = socket.getInputStream();
		    FileOutputStream fos = new FileOutputStream(file);
		    BufferedOutputStream bos = new BufferedOutputStream(fos);
		    int bytesread = 0;
			try{
				while((bytesread = is.read(bytes, 0, bytes.length)) != 0){
					bos.write(bytes, 0, bytesread);
				}
				success = true;
			}finally{
				bos.close();
				is.close();
				socket.close();
			}
		}catch(IOException e){
			System.out.println("ERROR: Receiver");
		}
	}
	
	public boolean isFinished(){
		return success;
	}
}