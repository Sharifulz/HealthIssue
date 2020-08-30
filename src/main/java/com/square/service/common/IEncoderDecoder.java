package com.square.service.common;

public interface IEncoderDecoder {

	public String encodeString(String actualString,String id);
	public String decodeString(String encodedString, String id);
	
	public String hashingString(String str);
	
}
