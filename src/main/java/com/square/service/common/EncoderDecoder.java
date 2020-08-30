package com.square.service.common;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class EncoderDecoder implements IEncoderDecoder{

	@Override
	public String encodeString(String actualString, String salt) {
		String encodableString = salt+actualString;
		String str = Base64.getEncoder().encodeToString(encodableString.getBytes());
		return str;
	}

	@Override
	public String decodeString(String encodedString, String salt) {
		String decodeFormat = "";
		if (encodedString.length() > salt.length()) {
			byte[] decodedByte = Base64.getDecoder().decode(encodedString);
			String detailString = new String(decodedByte);
			decodeFormat = detailString.substring(salt.length());
		}
		return decodeFormat;
	}

	@Override
	public String hashingString(String str) {
		MessageDigest digest;
		byte[] encodedhash;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			encodedhash = digest.digest(
					str.getBytes(StandardCharsets.UTF_8));
			 StringBuffer hexString = new StringBuffer();
			    for (int i = 0; i < encodedhash.length; i++) {
			    String hex = Integer.toHexString(0xff & encodedhash[i]);
			    if(hex.length() == 1) hexString.append('0');
			        hexString.append(hex);
			    }
			    return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

}

