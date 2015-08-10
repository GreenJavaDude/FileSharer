package com.greenjavadude.FileSharer;

import java.io.File;

public class Testing {
	public static void main(String[] args){
		String path = "C://Programming//java_res//CV.odt";
		String path2 = "C://Programming//java_res//CV5.odt";
		Uploader uploader = new Uploader("localhost", new File(path));
		Receiver receiver = new Receiver(new File(path2));
		receiver.start();
		uploader.upload();
		
		while(!receiver.isFinished()){
			try{
				Thread.sleep(5);
			}catch(Exception e){
				
			}
		}
		System.exit(0);
	}
}