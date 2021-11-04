package springbook.learningtest.template;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;

public class Calculator {
	// 3-31
	// 3-32
//	public Integer calcSum(String filePath) throws IOException {
		// 3-31
		// 3-32
//		BufferedReader br = null;
//		try {
//			br = new BufferedReader(new FileReader(filePath));
//			Integer sum = 0;
//			String line = null;
//			while((line = br.readLine()) != null) {
//				sum += Integer.valueOf(line);
//			}
//			
//			return sum;
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//			throw e;
//		} finally{
//			if ( br != null) {
//				try {br.close();} catch (IOException e) {e.printStackTrace();}
//			}
//		
//		}
//	}

	
	public Integer calcSum(String filePath) throws IOException {
		// 3-35
		BufferedReaderCallback sumCallback = new BufferedReaderCallback() {
			@Override
 			public Integer doSomethingWithReader(BufferedReader br) throws IOException {
				Integer sum = 0;
				String line = null;
				while ((line = br.readLine()) != null ) {
					sum += Integer.valueOf(line);
				}
				return sum;
			}
		};
		return fileReadTemplate(filePath, sumCallback);
	}

	public Integer fileReadTemplate(String filePath, BufferedReaderCallback callback) throws IOException {
		// 3-34
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			int ret = callback.doSomethingWithReader(br);
			return ret;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if ( br!= null ) {
				try {br.close();}
				catch (IOException e) { System.out.println(e.getMessage());}
			}
		}
	}

	public Integer calcMultiply(String filePath) throws IOException {
		BufferedReaderCallback multiplyCallback = new BufferedReaderCallback() {
			@Override
			public Integer doSomethingWithReader(BufferedReader br) throws IOException {
				Integer multiply = 1;
				String line = null;
				while ((line = br.readLine()) != null ) {
					multiply *= Integer.valueOf(line);
				}
				return multiply;
			}
		};
		return fileReadTemplate(filePath, multiplyCallback);
	}
}
