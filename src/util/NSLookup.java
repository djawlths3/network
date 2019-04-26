package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class NSLookup {

	public static void main(String[] args) {
		NSLookup NSL = new NSLookup();
		NSL.domainToIp();
	}

	
	public void domainToIp() {

		BufferedReader br = null;
		System.out.print("> ");
		try {
			InputStreamReader isr = new InputStreamReader(System.in,"UTF-8");
			br =new BufferedReader(isr);
			String line = null;
			while((line = br.readLine()) != null) {				
				if(line.equals("exit")) {
					System.out.println("종료~");
					break;
				}
				System.out.println("loading...");
				InetAddress[] inetAddress = InetAddress.getAllByName(line);
				for(InetAddress ia : inetAddress) {
					System.out.println( line + ": " + ia.getHostAddress());
				}
				System.out.print("> ");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			System.out.println(e.getLocalizedMessage() +" 존재하지 않는 도메인 입니다");
			domainToIp();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null) {
					br.close();					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
