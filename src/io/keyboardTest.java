package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class keyboardTest {

	public static void main(String[] args) {
		//기반 스트림(표준입력, 키보드, Syete.in)
		BufferedReader br = null;
		
		try {
			//보조 스트림1, byte -> char
			InputStreamReader isr = new InputStreamReader(System.in,"UTF-8");	
			// 보조 스트림2, char -> String
			br =new BufferedReader(isr);
			String line = null;
			while((line = br.readLine()) != null) {
				if(line.equals("exit")) {
					System.out.println("종료~");
					break;
				}
				System.out.println("> " +line);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(br != null) {
					br.close();					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
