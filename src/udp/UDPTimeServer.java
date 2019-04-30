package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UDPTimeServer {
	public static final int PORT = 7000;
	public static final int BUFFER_SIZE = 1024;
	
	public static void main(String[] args) {
		DatagramSocket socket = null;
		List portList =new ArrayList<Integer>();
		String msg = "\n(time을 치면 현재 시간을 보여줍니다)";
		try {
			//1. socket 생성
			socket = new DatagramSocket(PORT);
			while(true) {
				//2.data 수신
				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE],BUFFER_SIZE);
				
				socket.receive(receivePacket);
				int clientPort = receivePacket.getPort();
				byte[] data = receivePacket.getData();
				int length = receivePacket.getLength();
				String message = new String(data,0,length,"UTF-8");
				if( !(portList.contains(clientPort)) ) {
					portList.add(clientPort);
					message += msg;
				}
				
				System.out.println("[serverd] received : "+message);
				
				if("time".contentEquals(message)) {
					Calendar cal = Calendar.getInstance();
					int year = cal.get(Calendar.YEAR);
			        int mon = cal.get(Calendar.MONTH) + 1;
			        int day = cal.get(Calendar.DAY_OF_MONTH);
			        int hour = cal.get(Calendar.HOUR_OF_DAY);
			        int min = cal.get(Calendar.MINUTE);
			        
			        message = "현재 시간 "+year+"년"+mon+"월"+day+"일 " +hour+":"+min;
				}
				//3. data send
				byte[] sendData = message.getBytes("utf-8");
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), clientPort);
				socket.send(sendPacket);
				
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(socket != null && socket.isClosed() == false) {
				socket.close();				
			}
		}
		
	}

}
