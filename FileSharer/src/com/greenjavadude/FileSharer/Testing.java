package com.greenjavadude.FileSharer;

import java.io.File;

public class Testing {
	public static void main(String[] args){
		String path = "C://Programming//java_res//CV.odt";
		String path2 = "C://Programming//java_res//CV3.odt";
		ShareUploader uploader = new ShareUploader("localhost", new File(path));
		Receiver receiver = new Receiver(new File(path2));
		receiver.start();
		uploader.upload();
		System.out.println("Success!");
	}
}