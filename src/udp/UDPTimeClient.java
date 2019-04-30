package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class UDPTimeClient {
	private static final String SERVER_IP = "192.168.1.23";
	private static final int SERVER_PORT = 7000;
	
	public static void main(String[] args) {

		Scanner sanner = null;
		DatagramSocket socket = null;
		try {
			// 0.Scanner create
			sanner = new Scanner(System.in);
			byte[] sendData = null;
			DatagramPacket sendPacket = null;
			DatagramPacket receivePacket = null;
			String message = null;
			// 1. socket create
			socket = new DatagramSocket();
//			sendData = "join".getBytes("utf-8");
//			sendPacket = new DatagramPacket(sendData, sendData.length, new InetSocketAddress(SERVER_IP,UDPEchoServer.PORT));
//			socket.send(sendPacket);
//			
//			receivePacket = new DatagramPacket(new byte[UDPEchoServer.BUFFER_SIZE],UDPEchoServer.BUFFER_SIZE);
//			socket.receive(receivePacket);
//			message = new String(receivePacket.getData(),0,receivePacket.getLength(),"utf-8");
//			System.out.println("<< "+ message);
			while(true) {
				//2. 키보드 입력 받기
				System.out.print(">>");
				String line = sanner.nextLine();
				if("quit".contentEquals(line)) {
					socket.send(new DatagramPacket("quit".getBytes("utf-8"), sendData.length, new InetSocketAddress(SERVER_IP,UDPEchoServer.PORT)));
					break;
				}
				
				//3.data write
				sendData = line.getBytes("utf-8");
				sendPacket = new DatagramPacket(sendData, sendData.length, new InetSocketAddress(SERVER_IP,UDPEchoServer.PORT));
				socket.send(sendPacket);
				
				//4. data read
				receivePacket = new DatagramPacket(new byte[UDPEchoServer.BUFFER_SIZE],UDPEchoServer.BUFFER_SIZE);
				socket.receive(receivePacket);		
				message = new String(receivePacket.getData(),0,receivePacket.getLength(),"utf-8");
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
