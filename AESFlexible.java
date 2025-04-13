import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class AESFlexible {
    public static String encrypt(String plainText, SecretKey key) throws Exception {
        Cipher aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        aesCipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = aesCipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String cipherText, SecretKey key) throws Exception {
        Cipher aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        aesCipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = aesCipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(decryptedBytes);
    }

    public static SecretKey getKeyFromInput(String hexKey) {
        byte[] keyBytes = hexStringToByteArray(hexKey);
        return new SecretKeySpec(keyBytes, "AES");
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2)
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                + Character.digit(s.charAt(i + 1), 16));
        return data;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter plaintext:");
            String plainText = sc.nextLine();

            System.out.println("Enter key size (128, 192, 256):");
            int keySize = sc.nextInt();
            sc.nextLine();  // Consume newline

            int keyLengthHex = keySize / 4; // 128 bits = 32 hex chars

            System.out.println("Enter " + keyLengthHex + "-character hexadecimal key:");
            String hexKey = sc.nextLine().trim();

            SecretKey key = getKeyFromInput(hexKey);

            String cipherText = encrypt(plainText, key);
            System.out.println("Encrypted: " + cipherText);

            String decryptedText = decrypt(cipherText, key);
            System.out.println("Decrypted: " + decryptedText);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
