package com.greenjavadude.FileSharer;

import java.io.*;
import java.net.*;

public class Receiver implements Runnable{
	public static final int PORT = 8787;
	
	private ServerSocket server;
	private Socket socket;
	
	private File file;
	
	private boolean finished;
	
	public Receiver(File file){
		this.file = file;
		finished = false;
	}
	
	public void start(){
		try {
			server = new ServerSocket(PORT, 10);
			new Thread(this).start();
		}catch(IOException e){
			
		}
	}
	
	public boolean isFinished(){
		return finished;
	}
	
	public void run(){
		try{
			socket = server.accept();
			byte[] buffer = new byte[2048];
		    InputStream is = socket.getInputStream();
		    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			try{
				int bytesread = 0;
				
				while((bytesread = is.read(buffer, 0, buffer.length)) != 0){
					bos.write(buffer, 0, bytesread);
				}
				System.out.println("Receiver finished!");
				bos.flush();
			}finally{
				bos.close();
				is.close();
				socket.close();
				finished = true;
			}
		}catch(IOException e){
			System.out.println("IO ERROR: Receiver");
		}catch(ArrayIndexOutOfBoundsException e){
			//finished
		}
	}
}