import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class AESModes {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String input = sc.nextLine();

        SecretKey key = generateKey(128);
        byte[] iv = generateIV();

        System.out.println("\nAES Encryption Modes:");
        System.out.println("=====================");

        System.out.println("\n1. ECB Mode");
        String encryptedECB = encrypt(input, key, null, "AES/ECB/PKCS5Padding");
        String decryptedECB = decrypt(encryptedECB, key, null, "AES/ECB/PKCS5Padding");
        System.out.println("Encrypted (ECB): " + encryptedECB);
        System.out.println("Decrypted (ECB): " + decryptedECB);

        System.out.println("\n2. CBC Mode");
        String encryptedCBC = encrypt(input, key, iv, "AES/CBC/PKCS5Padding");
        String decryptedCBC = decrypt(encryptedCBC, key, iv, "AES/CBC/PKCS5Padding");
        System.out.println("Encrypted (CBC): " + encryptedCBC);
        System.out.println("Decrypted (CBC): " + decryptedCBC);

        System.out.println("\n3. CTR Mode (with NoPadding)");
        byte[] paddedInput = padToBlockSize(input);
        String encryptedCTR = encrypt(new String(paddedInput), key, iv, "AES/CTR/NoPadding");
        String decryptedCTR = decrypt(encryptedCTR, key, iv, "AES/CTR/NoPadding");
        System.out.println("Encrypted (CTR): " + encryptedCTR);
        System.out.println("Decrypted (CTR): " + decryptedCTR.trim());

        // ECB mode vulnerability
        if (input.equals("HELLOHELLOHELLOHELLO")) {
            System.out.println("\n⚠️ ECB Mode produces repeating patterns in ciphertext for repeating plaintext blocks!");
        }
    }

    public static SecretKey generateKey(int keySize) throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(keySize);
        return generator.generateKey();
    }

    public static byte[] generateIV() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return iv;
    }

    public static String encrypt(String input, SecretKey key, byte[] iv, String transformation) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        if (transformation.contains("ECB")) {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        }
        byte[] encryptedBytes = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String cipherText, SecretKey key, byte[] iv, String transformation) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        if (transformation.contains("ECB")) {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        }
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(decryptedBytes);
    }

    public static byte[] padToBlockSize(String input) {
        byte[] data = input.getBytes();
        int blockSize = 16;
        int paddedLength = ((data.length + blockSize - 1) / blockSize) * blockSize;
        byte[] padded = new byte[paddedLength];
        Arrays.fill(padded, (byte) 0);
        System.arraycopy(data, 0, padded, 0, data.length);
        return padded;
    }
}
