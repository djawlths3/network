package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPserver {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			//1. 서버소켓생성
			serverSocket = new ServerSocket();
			//1-1Time-wait 시간에 소켓에 포트번호 할당을 가능하게 하기 위해서
			serverSocket.setReuseAddress(true);
			
			//1-2
			
			
			//2. binding, socket = socketAddress(IPAddress +port)
			//InetAddress inetAddress = InetAddress.getLocalHost();
			//String localhost = inetAddress.getHostAddress();
			String localhost = "0.0.0.0";
			int port = 6000;
			serverSocket.bind(new InetSocketAddress(localhost, port));

			//3.accept, connect wait
			Socket socket = serverSocket.accept();
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSocketAddress.getPort();
			System.out.println("connected by client ip: " + remoteHostAddress + ":"+remotePort);
			
			try {
				//4. IOStream catch 
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				
				while(true) {
					//5. data read
					byte[] buffer = new byte[256];
					
					int readByteCount = is.read(buffer);
					if(readByteCount == -1) {
						//정상종료, close() 메소드 사용
						System.out.println("close client");
						break;
					}
					String data = new String(buffer, 0,readByteCount, "UTF-8");
					System.out.println("[server] received: "+data);
					
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//6. data write
					os.write( data.getBytes("utf-8") );
					
				}			
			} catch (SocketException e) {
				System.out.println("[server] sudden closed by client");
			} 
			catch (IOException e) {
				e.printStackTrace();
			}finally {
				if(socket != null && socket.isClosed()) {
					socket.close();					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
