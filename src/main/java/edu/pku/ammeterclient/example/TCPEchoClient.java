package edu.pku.ammeterclient.example;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPEchoClient {

	public void connect(String host, int port) throws UnknownHostException,
			IOException {
		if (null != host) {
			Socket socket = new Socket(host, port);
		}
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		TCPEchoClient tcpEchoClient = new TCPEchoClient();
		tcpEchoClient.connect("211.140.18.112", 5000);
	}

}
