package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class EchoServerReceiveThread extends Thread {
	private Socket socket;
	
	public EchoServerReceiveThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSocketAddress.getPort();
			EchoServer.log("connected by client ip: " + remoteHostAddress + ":"+remotePort);
			
			//4. IOStream catch 
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			BufferedReader br = new BufferedReader( new InputStreamReader(socket.getInputStream(),"utf-8") );
			PrintWriter pr = new PrintWriter( new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true ); // true 값은 자동으로 flush 해주는 기능
			
			while(true) {
				//5. data read
				String data = br.readLine();
				if(data == null) {
					EchoServer.log("close by client");
					break;
				}
				EchoServer.log("received : " +data);
				pr.println(data);
			}			
		} catch (SocketException e) {
			System.out.println("[server] sudden closed by client");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(socket != null && socket.isClosed()) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}					
			}
		}
	}
	
	
	
}
