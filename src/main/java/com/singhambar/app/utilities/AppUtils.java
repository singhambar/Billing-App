/**
 * 
 */
package com.singhambar.app.utilities;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.singhambar.app.security.EncrypterAndDecrypter;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
public final class AppUtils {
	
	private static EncrypterAndDecrypter enc = null;

	/**
	 * @return the mapper
	 */
	public static synchronized ObjectMapper getMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		mapper.setSerializationInclusion(Include.NON_NULL);
		return mapper;
	}

	public static synchronized String encrypt(String str) throws Exception {
		if (enc == null)
			enc = new EncrypterAndDecrypter();
		return enc.encrypt(str);
	}

	public static synchronized String decrypt(String str) throws Exception {
		if (enc == null)
			enc = new EncrypterAndDecrypter();
		return enc.decrypt(str);
	}

}
