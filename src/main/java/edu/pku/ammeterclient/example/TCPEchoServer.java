package edu.pku.ammeterclient.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;


public class TCPEchoServer {
	
	private int port;
	
	public TCPEchoServer(int port) {
		this.port = port;
	}
	
	public void run () throws IOException {
		
		ServerSocket serverSocket = new ServerSocket(port); 
		
		while (true) {
			Socket clientSocket = serverSocket.accept();
			SocketAddress socketAddress = clientSocket.getRemoteSocketAddress();
			System.out.println(socketAddress.toString());
		}
		
	}
	
	

}
