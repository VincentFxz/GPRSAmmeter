package edu.pku.ammeterclient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

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
                if(null != clientSocket){
                    System.out.println("ammeter gprs connected");
                    SocketAddress socketAddress = clientSocket.getRemoteSocketAddress();
                    System.out.println(socketAddress.toString());
                    InputStream socketReader = clientSocket.getInputStream();
                    OutputStream socketWriter = clientSocket.getOutputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socketReader));
                    PrintWriter out = new PrintWriter(socketWriter);

                    byte[] b = new byte[1024];
//				    68 75 06 00 08 08 12
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

                    System.out.println("request sent");

                    out.print(b);
                    out.flush();

                    while (true){
                        char[] recivedData = new char[1024];
                        in.read(recivedData);
                        System.out.println(recivedData);
                    }
                }

//                int totalBytesRcvd=0;
//                int bytesRcvd;
				
//				while(totalBytesRcvd<34){
//					if((bytesRcvd=socketReader.read(received, totalBytesRcvd, 34-totalBytesRcvd))==-1){
//						throw new SocketException("Connection closed prematurely");
//					}
//					totalBytesRcvd+=bytesRcvd;
//
//					System.out.println("Receved: "+AmmeterClient.bytesToHexString(received));
//					System.out.println(totalBytesRcvd);
//				}
////
//
//				socketWriter.write(b);
//				socketWriter.flush();
//				received = new byte[34];
//
//				while(totalBytesRcvd<34){
//					if((bytesRcvd=socketReader.read(received, totalBytesRcvd, 34-totalBytesRcvd))==-1){
//						throw new SocketException("Connection closed prematurely");
//					}
//					totalBytesRcvd+=bytesRcvd;
//
//					System.out.println("Receved: "+new String(AmmeterClient.bytesToHexString(received)));
//					System.out.println(totalBytesRcvd);
//				}
//				System.out.println("Receved: "+new String(AmmeterClient.bytesToHexString(received)));
//				clientSocket.close();
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	public static String bytesToHexString(byte[] src){  
	    StringBuilder stringBuilder = new StringBuilder("");  
	    if (src == null || src.length <= 0) {  
	        return null;  
	    }  
	    for (int i = 0; i < src.length; i++) {  
	        int v = src[i] & 0xFF;  
	        String hv = Integer.toHexString(v);  
	        if (hv.length() < 2) {  
	            stringBuilder.append(0);  
	        }  
	        stringBuilder.append(hv);  
	    }  
	    return stringBuilder.toString();  
	}  

	public static void main(String[] args) {
        System.out.println("helloworld");
        //try in idea

		new AmmeterClient().test();

	}

}
