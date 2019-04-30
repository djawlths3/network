package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {
	
	private static final int PORT = 7000;
	
	public static void main(String[] args) {

		ServerSocket serverSocket = null;
		try {
			//1. 서버소켓생성
			serverSocket = new ServerSocket();
			
			//2. binding, socket = socketAddress(IPAddress +port)

			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			log("server starts...[port :"+PORT+"]");
			while(true) {
				//3.accept, connect wait
				Socket socket = serverSocket.accept();
				Thread thread = new EchoServerReceiveThread(socket);
				thread.start();				
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
	
	public static void log(String log) {
		System.out.println("[server#" +Thread.currentThread().getName() +"] "+ log);
	}

}
