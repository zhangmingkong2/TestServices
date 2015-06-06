package com.marlinl.tsser.util;

public class ByteArray {
	
	private byte[] array;
	
	public ByteArray(byte[] byteArray){
		this.array = byteArray;
	}
	
	public ByteArray(int size){
		array = new byte[size];
	}
	
	public byte[] getByte(){
		return this.array;
	}
	
	public void add(byte[] b){
		byte[] temp = new byte[array.length+b.length];
		System.arraycopy(array, 0, temp, 0, array.length);
		System.arraycopy(b, 0, temp, array.length, b.length);
		array = temp;
	}
	
	
	public int size(){
		return array.length;
	}

}
