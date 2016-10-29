import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Cipher {
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?-=:"
			+ '\n' + '\r';
	private static final String SIMPLE_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	// Set this variable to the default alphabet you wish to use
	private static final String DEFAULT_ALPHABET = ALPHABET;
	private static Dictionary dictionary = Dictionary
			.buildDictionary("C:\\Users\\sasha\\Desktop\\JAVA\\Workspace\\CipherBlankTemplate\\words.txt");

	public static void main(String args[]) {

	}

	/**
	 * Returns plaintext encrypted by the rotation cipher with a shift of
	 * movement.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plainText
	 *            the plain text to be encrypted.
	 * @param shiftAmount
	 *            the number of characters in ALPHABET to shift by.
	 * @return returns the encrypted plainText.
	 */
	public static String rotationCipherEncrypt(String plainText, int shiftAmount, String alphabet) {
		String a = "";
		for (int i = 0; i < plainText.length(); i++) {
			int shift = alphabet.indexOf(plainText.substring(i, i + 1));
			shift = ShiftIndex(shift, shiftAmount, alphabet);
			a = a + alphabet.substring(shift, shift + 1);

		}
		return a;
	}

	/**
	 * @param index
	 *            start location
	 * @param shift
	 *            amount to move index by
	 * @param alphabet
	 *            alphabet used to shift
	 * @return new alphabet location
	 */

	public static int ShiftIndex(int index, int shift, String alphabet) {
		while (shift < 0)
			shift += alphabet.length();

		index += shift;
		index = index % alphabet.length();
		return index;

	}

	public static String rotationCipherEncrypt(String plainText, int shiftAmount) {
		return rotationCipherEncrypt(plainText, shiftAmount, DEFAULT_ALPHABET);
	}

	/**
	 * Returns a the result of decrypting cipherText by shiftAmount using the
	 * rotation cipher.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param cipherText
	 *            the encrypted text.
	 * @param shiftAmount
	 *            the key to decrypt the cipher.
	 * @return returns the decrypted cipherText.
	 */
	public static String rotationCipherDecrypt(String cipherText, int shiftAmount, String alphabet) {
		String a = "";
		for (int i = 0; i < cipherText.length(); i++) {
			int shift = alphabet.indexOf(cipherText.substring(i, i + 1));
			shift = ShiftIndex(shift, -shiftAmount, alphabet);
			a = a + alphabet.substring(shift, shift + 1);

		}
		return a;
	}

	public static String rotationCipherDecrypt(String cipherText, int shiftAmount) {
		return rotationCipherDecrypt(cipherText, shiftAmount, DEFAULT_ALPHABET);
	}

	/**
	 * Returns plaintext encrypted by the vigenere cipher encoded with the
	 * String code
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plainText
	 *            the plain text to be encrypted.
	 * @param code
	 *            the code to use as the encryption key.
	 * @return returns the encrypted plainText.
	 */
	public static String vigenereCipherEncrypt(String plainText, String code, String alphabet) {
		String r = "";
		int[] codeArray = codeIndexArray(code, alphabet);
		for (int i = 0; i < plainText.length(); i++) {
			int index = alphabet.indexOf(plainText.substring(i, i + 1));
			index = ShiftIndex(index, codeArray[i % codeArray.length], alphabet);
			r = r + alphabet.substring(index, index + 1);
		}
		return r;
	}

	/**
	 * Returns plaintext encrypted by the vigenere cipher encoded with the
	 * String code
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plainText
	 *            the plain text to be encrypted.
	 * @param code
	 *            the code to use as the encryption key.
	 * @return returns the encrypted plainText.
	 */
	public static String vigenereCipherEncrypt(String plainText, int[] code, String alphabet) {
		String r = "";
		for (int i = 0; i < plainText.length(); i++) {
			int index = alphabet.indexOf(plainText.substring(i, i + 1));
			index = ShiftIndex(index, code[i % code.length], alphabet);
			r = r + alphabet.substring(index, index + 1);
		}
		return r;
	}

	public static int[] codeIndexArray(String code, String alphabet) {
		int[] r = new int[code.length()];
		for (int i = 0; i < r.length; i++) {
			r[i] = alphabet.indexOf(code.substring(i, i + 1));
		}
		return r;
	}

	public static String vigenereCipherEncrypt(String plainText, String code) {
		return vigenereCipherEncrypt(plainText, code, DEFAULT_ALPHABET);
	}

	/**
	 * Returns the result of decrypting cipherText with the key code.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param cipherText
	 *            the encrypted text.
	 * @param code
	 *            the decryption key
	 * @return returns the decrypted cipherText.
	 */
	public static String vigenereCipherDecrypt(String cipherText, String code, String alphabet) {
		String r = "";
		int[] codeArray = codeIndexArray(code, alphabet);
		for (int i = 0; i < cipherText.length(); i++) {
			int index = alphabet.indexOf(cipherText.substring(i, i + 1));
			index = ShiftIndex(index, -1 * codeArray[i % codeArray.length], alphabet);
			r = r + alphabet.substring(index, index + 1);
		}
		return r;
	}

	public static String vigenereCipherDecrypt(String cipherText, String code) {
		return vigenereCipherDecrypt(cipherText, code, DEFAULT_ALPHABET);
	}

	/**
	 * returns a copy of the input plaintext String with invalid characters
	 * stripped out.
	 * 
	 * @param plaintext
	 *            The plaintext string you wish to remove illegal characters
	 *            from
	 * @param alphabet
	 *            A string of all legal characters.
	 * @return String A copy of plain with all characters not in alphabet
	 *         removed.
	 */
	private static String stripInvalidChars(String plaintext, String alphabet) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < plaintext.length(); i++) { // loop through plaintext
			if (alphabet.indexOf(plaintext.charAt(i)) >= 0) // get index of char
				b.append(plaintext.charAt(i)); // if it exists, keep it
			else
				// otherwise skip it &
				System.out.println("Stripping letter: \"" + plaintext.charAt(i) // display
																				// a
																				// message
						+ "\"");
		}
		return b.toString();
	}

	/**
	 * returns true if plaintext is valid English.
	 * 
	 * @param plaintext
	 *            the text you wish to test for whether it's valid English
	 * @return boolean returns true if plaintext is valid English.
	 */
	private static boolean isEnglish(String plainText) {
		String[] words = getWords(plainText);
		double count = 0;
		for (int i = 0; i < words.length; i++) {
			if (dictionary.isWord(words[i])) {
				count++;
			}
		}
		if (count / ((double) words.length) > .2)
			return true;

		return false;
	}

	public static String[] getWords(String text) {
		ArrayList<String> s = new ArrayList<String>();
		int c = 0;

		for (int i = 0; i < text.length(); i++) {
			String letter = text.substring(i, i + 1);
			if (!letter.equals(" ")) {
				try {
					s.set(c, s.get(c) + letter);
				} catch (Exception e) {
					s.add(letter);
				}
			} else {
				c++;
				while (text.substring(i, i + 1).equals(" ")) {
					i++;
				}
			}
		}

		String[] r = new String[s.size()];
		for (int i = 0; i < s.size(); i++) {
			r[i] = s.get(i);
		}
		return r;

	}

	/**
	 * Method that brute forces a rotation cipher decryption
	 * 
	 * @param cipherText
	 *            text to be decrypted via brute force
	 * @return decrypted string of empty string if no decode was found
	 */

	public static String rotationCipherCrack(String cipherText) {
		for (int i = 0; i <= 27; i++) {
			if (Cipher.isEnglish(rotationCipherDecrypt(cipherText, i))) {
				return rotationCipherDecrypt(cipherText, i);

			}
		}
		return "";
	}

	/**
	 * Returns a cipher with a custom character substitution.
	 * 
	 * @param plainText
	 *            the text that you want to encrypt
	 * @param permutation
	 *            an array representing how letters are replaced (A is replaced
	 *            with the letter in the position of permutation[0]...)
	 * @param alphabet
	 *            the alphabet used to encrypt
	 * @return returns encrypted cipher
	 */
	public String substitutionCipher(String plainText, int[] permutation, String alphabet) {
		String a = "";
		for (int i = 0; i < plainText.length(); i++) {
			String letter = plainText.substring(i, i + 1);
			a = a + alphabet.substring(permutation[alphabet.indexOf(letter)],
					permutation[alphabet.indexOf(letter)] + 1);
		}
		return a;
	}

	public static boolean isValidPermutation(int[] p) {
		for (int i = 0; i < p.length; i++) {
			for (int o = i; o < p.length; o++) {
				if (p[o] == p[i])
					return false;
			}
		}
		return true;
	}

	public static int[] randomPermutation(int length) {
		int[] r = new int[length];
		for (int i = 0; i < length; i++) {
			r[i] = i;
		}
		for (int a = 0; a < length; a++) {
			for (int b = 0; b < length; b++) {
				if (Math.random() > .5) {
					// swaps the two numbers
					r[a] = r[a] + r[b];
					r[b] = r[a] - r[b];
					r[a] = r[a] - r[b];
				}
			}
		}
		return r;
	}

	public static Bag placeInBag(int step, int start, String cipher) {
		Bag a = new Bag();
		for (int i = start; i < cipher.length(); i += step) {
			a.add(cipher.substring(i, i + 1));

		}
		return a;
	}

	public static int getRotation(String a, String alphabet) {
		return alphabet.indexOf(" ") - alphabet.indexOf(a) + 2;
	}

	public static String combineStrings(String[] s) {
		String r = "";
		for (int i = 0; i < s[0].length() - 1; i++) {
			for (int o = 0; o < s.length; o++) {
				r = r + s[o].substring(i, i + 1);
			}
		}
		return r;
	}

	public static String vigenereCipherCrackThreeLetter(String cipher, String alphabet) {

		Bag[] bags = new Bag[3];
		String[] parts = new String[3];
		for (int i = 0; i < 3; i++) {
			bags[i] = placeInBag(3, i, cipher);
			String space = bags[i].getMostFrequent();
			int rotation = getRotation(space, alphabet);
			System.out.println(bags[i].getString().length());
			parts[i] = rotationCipherDecrypt(bags[i].getString(), rotation, alphabet);
		}
		return combineStrings(parts);

	}

	public static String vigenereCipherCrack(String cipher, String alphabet) {
		String r = "";
		for (int length = 2; !isEnglish(r); length++) {

			Bag[] bags = new Bag[length];
			String[] parts = new String[length];
			for (int i = 0; i < length; i++) {
				bags[i] = placeInBag(length, i, cipher);
				String space = bags[i].getMostFrequent();
				int rotation = getRotation(space, alphabet);
				System.out.println(bags[i].getString().length());
				parts[i] = rotationCipherDecrypt(bags[i].getString(), rotation, alphabet);
			}
			r = combineStrings(parts);
		}
		return r;

	}

	public static String vigenereCipherCrackThreeLetter(String cipher) {
		return vigenereCipherCrackThreeLetter(cipher, DEFAULT_ALPHABET);

	}

}