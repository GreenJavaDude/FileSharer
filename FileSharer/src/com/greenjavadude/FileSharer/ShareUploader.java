package com.greenjavadude.FileSharer;

import java.io.*;
import java.net.*;

public class ShareUploader{
	public static final int PORT = 8787;
	
	private Socket socket;
	private String ip;
	private File file;
	
	public ShareUploader(String ip, File file){
		this.ip = ip;
		this.file = file;
	}
	
	public void upload(){
		try{
			BufferedInputStream bis = null;
			OutputStream os = null;
			try{
				socket = new Socket(InetAddress.getByName(ip), PORT);
				byte[] buffer = new byte[2048];
				bis = new BufferedInputStream(new FileInputStream(file));
				os = socket.getOutputStream();
				int bytesread = 0;
				
				while((bytesread = bis.read(buffer, 0, buffer.length)) != -1){
					os.write(buffer, 0, bytesread);
				}
			    
			    os.flush();
			}finally{
				bis.close();
			    os.close();
				socket.close();
			}
		}catch(IOException e){
			System.out.println("IO ERROR: Upload");
		}catch(NullPointerException e){
			System.out.println("Null ERROR: Upload");
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("ArrayIndexOutOfBoundsException ERROR: Upload");
		}
	}
}