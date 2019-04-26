package test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class Localhost {

	public static void main(String[] args) {
		try {
			//InetSocketAddress isa = InetSocketAddress.createUnresolved("192.168.1.23", 7500);
			InetAddress inetAddress = InetAddress.getLocalHost();
			String hostName = inetAddress.getHostName();
			String hostAddress = inetAddress.getHostAddress();
			System.out.println(hostName+": "+hostAddress);

			byte[] addresses = inetAddress.getAddress();
			for(byte address : addresses) {
				System.out.println(address & 0x000000ff);
			}
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
