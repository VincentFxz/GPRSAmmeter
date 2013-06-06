package edu.pku.ammeterclient.example;

import java.io.IOException;
import java.net.UnknownHostException;


public class EchoTest {
	
	public static void main (String [] args) throws Exception {
		
		Runnable serverRunnable = new Runnable() {
			
			public void run() {
				TCPEchoServer tcpEchoServer = new TCPEchoServer(5000);
				try {
					tcpEchoServer.run();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		
		Runnable clientRunnable = new Runnable() {
			
			public void run() {
				TCPEchoClient tcpEchoClient = new TCPEchoClient();
				try {
					tcpEchoClient.connect("127.0.0.1", 5000);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		Thread serverThread = new Thread(serverRunnable);
		Thread clientThread = new Thread(clientRunnable);
		
		serverThread.start();
		
		Thread.sleep(5000);
		
		clientThread.start();
		
	}

}
