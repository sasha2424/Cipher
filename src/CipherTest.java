import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CipherTest {

	@Test
	public void rotationCipherEncryptBy3() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "wkh!txlfn!eurzq!irA!mxpshg!ryhu!wkh!odCB!grjv";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 3);
		assertEquals(testCipherText, correctCipherText);
	}
	
	@Test
	public void rotationCipherDecryptBy3() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "wkh!txlfn!eurzq!irA!mxpshg!ryhu!wkh!odCB!grjv";
		String testPlainText = Cipher.rotationCipherDecrypt(correctCipherText, 3);
		assertEquals(testPlainText, plaintext);
	}
	
	@Test
	public void rotationCipherDecryptBy100() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "KyvaHLztBasIFNEawFOaALDGvuaFMvIaKyvaCrQPauFxJ";
		String testPlainText = Cipher.rotationCipherDecrypt(correctCipherText, 100);
		assertEquals(testPlainText, plaintext);
	}
	
	@Test
	public void rotationCipherEncryptBy100() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "KyvaHLztBasIFNEawFOaALDGvuaFMvIaKyvaCrQPauFxJ";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 100);
		assertEquals(testCipherText, correctCipherText);
	}
	
	@Test
	public void rotationCipherEncryptBy3CapsWithPunctuation() {
		String plaintext = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "]WKHbcTXLFNbEURZQ!IR0 !MXPSHG!RYHU!WKH!OD21!GRJV/]";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 3);
		assertEquals(testCipherText, correctCipherText);
	}
	
	@Test
	public void rotationCipherDecryptBy3CapsWithPunctuation() {
		String plaintext = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "]WKHbcTXLFNbEURZQ!IR0 !MXPSHG!RYHU!WKH!OD21!GRJV/]";
		String testPlainText = Cipher.rotationCipherDecrypt(correctCipherText, 3);
		assertEquals(testPlainText, plaintext);
	}
	
	@Test
	public void rotationCipherDecryptBy100CapsWithPunctuation() {
		String plaintext = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "c,YVpq7.ZT1pS85)4aW5 :a0.36VUa5(V8a,YVa2R\"'aU5X9dc";
		String testPlainText = Cipher.rotationCipherDecrypt(correctCipherText, 100);
		assertEquals(testPlainText, plaintext);
	}
	
	@Test
	public void rotationCipherEncryptBy100CapsWithPunctuation() {
		String plaintext = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "c,YVpq7.ZT1pS85)4aW5 :a0.36VUa5(V8a,YVa2R\"'aU5X9dc";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 100);
		assertEquals(testCipherText, correctCipherText);
	}
	
	@Test
	public void vigenereCipherBoth() {
		String plainText = "hello, is it me you are looking for";
		String code = "qwertyuiop";
		assertEquals(Cipher.vigenereCipherDecrypt(Cipher.vigenereCipherEncrypt(plainText,code),code), plainText);
	}
	
	@Test
	public void vigenereCipherEncrypt() {
		String plainText = "a b c d ef g";
		String code = "b";
		assertEquals("b'c'd'e'fg'h", Cipher.vigenereCipherEncrypt(plainText,code));
	}
	
	@Test
	public void vigenereCipherDecrypt() {
		String plainText = "a b c d ef g";
		String code = "b";
		assertEquals(plainText, Cipher.vigenereCipherDecrypt(Cipher.vigenereCipherEncrypt(plainText,code),code));
	}
	
	@Test
	public void vigenereCipherBruteForce() {
		String plainText = "hello is it me that                                                                                                                                                                                                      you are looking for because things are cool that the things do.";
		String code = "bbb";
		String encrypted = Cipher.vigenereCipherEncrypt(plainText,code);
		assertEquals(plainText , Cipher.decryptVernier3(encrypted));
	}
	
	
	@Test
	public void bruteForceRotationCypher(){
		String plainText = "hello is it me you are looking for";
		int shift = 5;
		assertEquals(Cipher.bruteForceRotationCypher(Cipher.rotationCipherEncrypt(plainText,shift)), plainText);
	}
}
