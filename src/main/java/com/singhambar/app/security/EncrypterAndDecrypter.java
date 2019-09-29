package com.singhambar.app.security;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.singhambar.app.utilities.AppConstants;

public class EncrypterAndDecrypter {

	private Cipher aesEncryptCipher = null;
	private Cipher aesDecryptCipher = null;

	byte[] buf = new byte[AppConstants.BUFFER_SIZE];

	public EncrypterAndDecrypter() {
		Provider sunJce = new com.sun.crypto.provider.SunJCE();
		Security.insertProviderAt(sunJce, 1);
		// Create an 16-byte initialization vector
		byte[] ivKey = { 0x74, 0x68, 0x69, 0x73, 0x49, 0x73, 0x41, 0x53, 0x65, 0x63, 0x72, 0x65, 0x74, 0x4b, 0x65,
				0x79 };
		SecretKey secretKey = new SecretKeySpec(ivKey, "AES");
		AlgorithmParameterSpec paramSpec = new IvParameterSpec(ivKey);
		try {
			aesEncryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			aesEncryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
			aesDecryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			aesDecryptCipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
		} catch (NoSuchAlgorithmException e) {
		} catch (NoSuchPaddingException e) {
		} catch (InvalidKeyException e) {
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public EncrypterAndDecrypter(SecretKey key) {
		Provider sunJce = new com.sun.crypto.provider.SunJCE();
		Security.insertProviderAt(sunJce, 1);
		// Create an 16-byte initialization vector
		byte[] ivKey = { 0x74, 0x68, 0x69, 0x73, 0x49, 0x73, 0x41, 0x53, 0x65, 0x63, 0x72, 0x65, 0x74, 0x4b, 0x65,
				0x79 };
		SecretKey secretKey = new SecretKeySpec(ivKey, "AES");
		AlgorithmParameterSpec paramSpec = new IvParameterSpec(ivKey);
		try {
			aesEncryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			aesEncryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
			aesDecryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			aesDecryptCipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
		} catch (NoSuchAlgorithmException e) {
		} catch (NoSuchPaddingException e) {
		} catch (InvalidKeyException e) {
		} catch (InvalidAlgorithmParameterException e) {
		}
	}

	/**
	 * Encrypts the given InputStream
	 * 
	 * @param in
	 * @param out
	 * @throws Exception
	 */
	public void encrypt(InputStream in, OutputStream out) throws Exception {
		try {
			// Bytes written to out will be encrypted
			out = new CipherOutputStream(out, aesEncryptCipher);

			// Read in the cleartext bytes and write to out to encrypt
			int numRead = 0;
			while ((numRead = in.read(buf)) >= 0) {
				out.write(buf, 0, numRead);
			}

		} catch (Exception e) {
			throw new Exception("Error while encrypting " + e.getMessage(), e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * Decrypts the the given inputStream
	 * 
	 * @param in
	 * @param out
	 * @throws Exception
	 */
	public void decrypt(InputStream in, OutputStream out) throws Exception {
		try {
			// Bytes read from in will be decrypted
			in = new CipherInputStream(in, aesDecryptCipher);

			// Read in the decrypted bytes and write the cleartext to out
			int numRead = 0;
			while ((numRead = in.read(buf)) >= 0) {
				out.write(buf, 0, numRead);
			}
		} catch (Exception e) {
			throw new Exception("Error while decrypting " + e.getMessage(), e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * It encrypts the given string.
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public String encrypt(String src) throws Exception {
		try {
			return new sun.misc.BASE64Encoder().encode(aesEncryptCipher.doFinal(src.getBytes("UTF8")));
		} catch (Exception e) {
			throw new Exception("Error while encrypting " + e.getMessage(), e);
		}
	}

	/**
	 * It decrypts the given string
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public String decrypt(String src) throws Exception {
		try {
			// #AS-11588 [issue fix on solaris while editing and saving excel schema]
			// AS-11517 Error while decrypting the password from server-configure.properties
			// file on Solaris.
			// In case of solaris os, when we decrypt the space or blank value then it
			// throws exception whereas same works on other OS. We don't have idea why we
			// are initializing value as space in case value found null. To reduce the
			// testing scope we have added this check for solaris.

			String osName = System.getProperty("os.name");
			if (osName != null && osName.trim().toLowerCase().contains(AppConstants.SOLARIS_OS)
					&& (" ".equals(src) || src.isEmpty())) {
				src = src.trim();
			} else {
				src = new String(aesDecryptCipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(src)), "UTF-8");
			}
			return src;
		} catch (Exception e) {
			throw new Exception("Error while decrypting " + e.getMessage(), e);
		}
	}

}
