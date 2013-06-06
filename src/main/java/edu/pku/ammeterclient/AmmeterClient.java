package edu.pku.ammeterclient;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

public class AmmeterClient {

	PrintWriter out;

	private int i = 0;

	public void test() {
//		String serverHost = "211.140.18.108";
		int port = 5000;
		try{
			
			ServerSocket serverSocket = new ServerSocket(port); 
			
			while (true) {
				Socket clientSocket = serverSocket.accept();
				SocketAddress socketAddress = clientSocket.getRemoteSocketAddress();
				System.out.println(socketAddress.toString());
				Socket testSocket = new Socket(socketAddress.toString(), port);
				InputStream socketReader = testSocket.getInputStream();
				OutputStream socketWriter = testSocket.getOutputStream();
				byte[] b = new byte[1024];
//				6875 06 00 08 08 12
				b[0] = (byte) 0x68;
				b[1] = (byte) 0x75;
				b[2] = (byte) 0x06;
				b[3] = (byte) 0x00;
				b[4] = (byte) 0x08;
				b[5] = (byte) 0x08;
				b[6] = (byte) 0x12;
				b[7] = (byte) 0x68;
				b[8] = (byte) 0x11;
				b[9] = (byte) 0x04;
				b[10] = (byte) 0x33;
				b[11] = (byte) 0x33;
				b[12] = (byte) 0x33;
				b[13] = (byte) 0x33;
				b[14] = (byte) 0x4E;
				b[15] = (byte) 0x16;
				socketWriter.write(b);
				socketWriter.flush();
				System.out.println("request sent");
				
				int totalBytesRcvd=0;
				int bytesRcvd;
				byte[] received = new byte[34];
				
				while(totalBytesRcvd<34){
					if((bytesRcvd=socketReader.read(received, totalBytesRcvd, 34-totalBytesRcvd))==-1){
						throw new SocketException("Connection closed prematurely");
					}
					totalBytesRcvd+=bytesRcvd;
				}
				System.out.println("Receved: "+new String(received));
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {

		new AmmeterClient().test();

	}

}
