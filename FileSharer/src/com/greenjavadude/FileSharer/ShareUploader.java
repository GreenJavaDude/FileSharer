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
				byte[] size = new byte[1024];
				bis = new BufferedInputStream(new FileInputStream(file));
				os = socket.getOutputStream();
				
				while(true){
					if(bis.read(size, 0, size.length) != -1){
						os.write(size, 0, size.length);
					}else{
						break;
					}
				}
			    
			    os.flush();
			}finally{
				bis.close();
			    os.close();
				socket.close();
			}
		}catch(IOException e){
			
		}
	}
}