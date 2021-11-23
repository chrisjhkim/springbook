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

//	public Integer calcSum(String filePath) throws IOException {
//		// 3-35
//		BufferedReaderCallback sumCallback = new BufferedReaderCallback() {
//			@Override
//			public Integer doSomethingWithReader(BufferedReader br) throws IOException {
//				Integer sum = 0;
//				String line = null;
//				while ((line = br.readLine()) != null ) {
//					sum += Integer.valueOf(line);
//				}
//				return sum;
//			}
//		};
//		return fileReadTemplate(filePath, sumCallback);
//	}
	public Integer calcSum(String filePath) throws IOException {
		// 3-40
		LineCallback<Integer> sumCallback = new LineCallback<Integer>() {
			@Override
			public Integer doSomethingWithLine(String line, Integer value) {
				return value + Integer.valueOf(line);
			}
		};
		return lineReadTemplate(filePath, sumCallback, 0);
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

//	public Integer calcMultiply(String filePath) throws IOException {
//		BufferedReaderCallback multiplyCallback = new BufferedReaderCallback() {
//			@Override
//			public Integer doSomethingWithReader(BufferedReader br) throws IOException {
//				Integer multiply = 1;
//				String line = null;
//				while ((line = br.readLine()) != null ) {
//					multiply *= Integer.valueOf(line);
//				}
//				return multiply;
//			}
//		};
//		return fileReadTemplate(filePath, multiplyCallback);
//	}
	public String concatenate(String filePath) throws IOException{
		LineCallback<String> concatenateCallback = new LineCallback<String>() {
			@Override
			public String doSomethingWithLine(String line, String value) {
				return value + line;
			}
		};
		return lineReadTemplate(filePath, concatenateCallback, "");
	}
	public Integer calcMultiply(String filePath) throws IOException {
		// 3-40
		LineCallback<Integer> multiplyCallback = new LineCallback<Integer>() {
			@Override
			public Integer doSomethingWithLine(String line, Integer value) {
				// TODO Auto-generated method stub
				return value * Integer.valueOf(line);
			}
		};
		return lineReadTemplate(filePath, multiplyCallback, 1);
	}
	
	public <T> T lineReadTemplate(String filePath, LineCallback<T> callback, T initVal) throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			T res = initVal;
			String line = null;
			while ((line = br.readLine()) != null) {
				res = callback.doSomethingWithLine(line, res);
			}
			return res;
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
}
	