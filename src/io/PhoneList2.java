package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PhoneList2 {

	public static void main(String[] args) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("phone.txt"));
			while(scanner.hasNextLine()) {
				String name = scanner.next();
				String phone01 = scanner.next();
				String phone02 = scanner.next();
				String phone03 = scanner.next();
				System.out.println("이름 : "+name+ " // 번호 : "+ phone01 +"-"+phone02+"-"+phone03);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}

}
