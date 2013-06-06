package edu.pku.ammeterclient.example;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class InetAddressExample {

	public static void main(String[] args) throws Exception {
		Enumeration<NetworkInterface> networkInterfaces = NetworkInterface
				.getNetworkInterfaces();
		if (null == networkInterfaces) {
			System.out.println("no network interface");
		} else {
			while (networkInterfaces.hasMoreElements()){
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				System.out.println("network interface : " + networkInterface.getName());
				Enumeration<InetAddress> networkAddresses = networkInterface.getInetAddresses();
				if(null == networkAddresses){
					System.out.println("no address");
				} else {
					while (networkAddresses.hasMoreElements()){
						InetAddress inetAddress = networkAddresses.nextElement();
						System.out.println("inet address : " + inetAddress.getHostAddress());
					}
				}
			}
		}
		
		

	}

}
