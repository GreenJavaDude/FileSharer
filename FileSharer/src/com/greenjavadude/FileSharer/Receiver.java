package com.greenjavadude.FileSharer;

import java.io.*;
import java.net.*;

public class Receiver implements Runnable{
	public static final int PORT = 8787;
	
	private ServerSocket server;
	private Socket socket;
	
	private File file;
	
	public Receiver(File file){
		this.file = file;
		try{
			server = new ServerSocket(PORT, 10);
			new Thread(this).start();
		}catch(IOException e){
			
		}
	}
	
	public void start(){
		new Thread(this).start();
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
			}
		}catch(IOException e){
			System.out.println("IO ERROR: Receiver");
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("ArrayOutOfBoundsException ERROR: Receiver");
		}
	}
}