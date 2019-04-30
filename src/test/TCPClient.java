package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TCPClient {

	private static final String SERVER_IP = "192.168.1.23";
	private static final int SERVER_PORT = 6000;
	
	public static void main(String[] args) {
		Socket socket = null;
		try {
			// 1. socket create
			socket = new Socket();
			
			//1-1 소캣 버퍼 사이즈 확인
			int receiveBufferSize = socket.getReceiveBufferSize();
			int sendBufferSize = socket.getSendBufferSize();
			System.out.println(receiveBufferSize+" : "+sendBufferSize);
			//1-2 소켓버퍼사이즈 변경
			socket.setReceiveBufferSize(1024*10);
			socket.setSendBufferSize(1024*10);
			System.out.println(receiveBufferSize+" : "+sendBufferSize);
			//1-3 SO_NODELAY(Nagle Algorithm off
			socket.setTcpNoDelay(true);
			socket.setSoTimeout(1000);
			// 2. server connect			
			socket.connect(new InetSocketAddress(SERVER_IP,SERVER_PORT));
			System.out.println("[client] connected");
			// 3. IOStream 받아오기
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			// 4. write
			String data = "Hello World\n";
			os.write(data.getBytes("utf-8"));
			
			// 5.read
			byte[] buffer = new byte[256];
			int readByteCount = is.read(buffer); //blocking
			if (readByteCount == -1) {
				System.out.println("[client] close by server");
			}
			
			data = new String(buffer,0,readByteCount,"utf-8");
			System.out.println("[client] received: "+ data);
		} catch (SocketTimeoutException e) {
			System.out.println("[client] time out");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(socket != null && socket.isClosed() == false) {
					socket.close();					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
