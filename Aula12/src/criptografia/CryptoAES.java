package criptografia;


import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.*;
import javax.crypto.spec.*;
 

public class CryptoAES { 
	   static String IV = null;
	   
	   public CryptoAES() { 
	      IV = "AAAAAAAAAAAAAAAA";
	   } 
	      
	   public String encrypt(String textopuro, String chaveencriptacao) throws Exception { 
	      Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
	      SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("ISO-8859-1"), "AES"); 
	      encripta.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("ISO-8859-1"))); 
	      return new String(encripta.doFinal(textopuro.getBytes("ISO-8859-1")));
	   }
	   
	   public String geraDecifra(String texto , String chaveencriptacao) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException{			 
			Cipher aescf = Cipher.getInstance("AES/CBC/PKCS5Padding");
			SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("ISO-8859-1"), "AES"); 
			aescf.init(Cipher.DECRYPT_MODE, key , new IvParameterSpec(IV.getBytes("ISO-8859-1")));
			return new String(aescf.doFinal(texto.getBytes("ISO-8859-1")));
			 
		}
	}