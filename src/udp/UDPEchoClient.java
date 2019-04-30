package udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class UDPEchoClient {
	private static final String SERVER_IP = "192.168.1.23";
	private static final int SERVER_PORT = 700;
	
	public static void main(String[] args) {
		Scanner sanner = null;
		DatagramSocket socket = null;
		try {
			// 0.Scanner create
			sanner = new Scanner(System.in);
			
			// 1. socket create
			socket = new DatagramSocket();
			
			while(true) {
				//2. 키보드 입력 받기
				System.out.print(">>");
				String line = sanner.nextLine();
				if("quit".contentEquals(line)) {
					break;
				}
				
				//3.data write
				byte[] sendData = line.getBytes("utf-8");
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, new InetSocketAddress(SERVER_IP,UDPEchoServer.PORT));
				socket.send(sendPacket);
				
				//4. data read
				DatagramPacket receivePacket = new DatagramPacket(new byte[UDPEchoServer.BUFFER_SIZE],UDPEchoServer.BUFFER_SIZE);
				socket.receive(receivePacket);		
				String message = new String(receivePacket.getData(),0,receivePacket.getLength(),"utf-8");
				// 8. console print
				System.out.println("<< "+ message);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(sanner != null) {
				sanner.close();					
			}
			if(socket != null && socket.isClosed() == false) {
				socket.close();					
			}
		}
	}

}
