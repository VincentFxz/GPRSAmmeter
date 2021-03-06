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

                    DataInputStream dataInputStream = new DataInputStream(socketReader);
                    DataOutputStream dataOutputStream = new DataOutputStream(socketWriter);

                    BufferedReader in = new BufferedReader(new InputStreamReader(socketReader));
                    PrintWriter out = new PrintWriter(socketWriter);

                    byte[] b = new byte[26];
//				    68 75 06 00 08 08 12
                    b[0] = (byte) 0xfe;
                    b[1] = (byte) 0xfe;
                    b[2] = (byte) 0xfe;
                    b[3] = (byte) 0x68;
                    b[4] = (byte) 0x75;
                    b[5] = (byte) 0x06;
                    b[6] = (byte) 0x00;
                    b[7] = (byte) 0x08;
                    b[8] = (byte) 0x08;
                    b[9] = (byte) 0x12;
                    b[10] = (byte) 0x68;
                    b[11] = (byte) 0x11;
                    b[12] = (byte) 0x04;
                    b[13] = (byte) 0x33;
                    b[14] = (byte) 0x33;
                    b[15] = (byte) 0x33;
                    b[16] = (byte) 0x33;
                    b[17] = (byte) 0x4E;
                    b[18] = (byte) 0x16;
                    b[19] = (byte) 0x11;
                    b[20] = (byte) 0x22;
                    b[21] = (byte) 0x33;
                    b[22] = (byte) 0x44;
                    b[23] = (byte) 0x55;
                    b[24] = (byte) 0x66;
                    b[25] = (byte) 0x01;
//                    b[0] = (byte) 0x68;
//                    b[1] = (byte) 0x75;
//                    b[2] = (byte) 0x06;
//                    b[3] = (byte) 0x00;
//                    b[4] = (byte) 0x08;
//                    b[5] = (byte) 0x08;
//                    b[6] = (byte) 0x12;
//                    b[7] = (byte) 0x68;
//                    b[8] = (byte) 0x11;
//                    b[9] = (byte) 0x04;
//                    b[10] = (byte) 0x33;
//                    b[11] = (byte) 0x33;
//                    b[12] = (byte) 0x33;
//                    b[13] = (byte) 0x33;
//                    b[14] = (byte) 0x4E;
//                    b[15] = (byte) 0x16;

                    System.out.println("request sent");

                    dataOutputStream.write(b);
                    dataOutputStream.flush();
//                    out.print(b);
//                    out.flush();

                    while (true){
                        byte[] received = new byte[1024];
                        dataOutputStream.write(b);
                        dataOutputStream.flush();
                        int count = dataInputStream.read(received);
                        byte[] output = AmmeterClient.trimByteArray(received, count);
//                        dataInputStream.readFully(received);
                        System.out.println(AmmeterClient.bytesToHexString(output));

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

    public static byte[] trimByteArray(byte[] input, int size){
        byte[] output = new byte[size];
        System.arraycopy(input, 0, output, 0, size);
        return output;
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
