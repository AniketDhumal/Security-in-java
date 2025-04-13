import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class DESBruteForce {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        // Use a known key to encrypt the plaintext
        byte knownKeyByte = (byte) 123; // Secret 8-bit key
        byte[] fullKey = new byte[8]; // DES requires 8-byte keys
        for (int i = 0; i < 8; i++) fullKey[i] = knownKeyByte;

        SecretKeySpec knownKey = new SecretKeySpec(fullKey, "DES");
        Cipher des = Cipher.getInstance("DES/ECB/PKCS5Padding");
        des.init(Cipher.ENCRYPT_MODE, knownKey);
        byte[] cipherText = des.doFinal(plaintext.getBytes());
        String base64Cipher = Base64.getEncoder().encodeToString(cipherText);

        System.out.println("Encrypted Cipher (Base64): " + base64Cipher);

        // Start brute force attack
        System.out.println("\nStarting brute-force attack...");
        long startTime = System.nanoTime();

        boolean found = false;
        for (int k = 0; k <= 255; k++) {
            byte[] trialKey = new byte[8];
            for (int i = 0; i < 8; i++) trialKey[i] = (byte) k;

            SecretKeySpec testKey = new SecretKeySpec(trialKey, "DES");
            des.init(Cipher.DECRYPT_MODE, testKey);

            try {
                byte[] decrypted = des.doFinal(cipherText);
                String trialPlain = new String(decrypted);
                if (trialPlain.equals(plaintext)) {
                    long endTime = System.nanoTime();
                    double timeTaken = (endTime - startTime) / 1e6;
                    System.out.println("\nKey found: " + k);
                    System.out.println("Decrypted text: " + trialPlain);
                    System.out.println("Time taken: " + timeTaken + " ms");
                    found = true;
                    break;
                }
            } catch (Exception ignored) {}
        }

        if (!found) {
            System.out.println("Key not found.");
        }
        sc.close();
    }
}
