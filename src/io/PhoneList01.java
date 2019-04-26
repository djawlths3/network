package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

public class PhoneList01 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = null;
		try {			
			//보조스트림1(byte -> char)
			InputStreamReader isr = new InputStreamReader(new FileInputStream("phone.txt"),"UTF-8");
			br = new BufferedReader(isr);
			String line = null;
			while((line=br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line,"\t ");
				int index = 0;
				while(st.hasMoreElements()) {
					String token = st.nextToken();
					System.out.print(token);
					
					if(index ==0) {
						System.out.print(":");
					} else if(index == 1) {
						System.out.print("-");
					}else if(index ==2) {
						System.out.print("-");
					}
					index++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(br !=null) {
				br.close();				
			}
		}
		
	}

}
