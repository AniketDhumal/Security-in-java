import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class DESModes {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String inputText = sc.nextLine();

        String keyString = "12345678"; // DES key must be 8 bytes
        SecretKeySpec key = new SecretKeySpec(keyString.getBytes(), "DES");
        IvParameterSpec iv = new IvParameterSpec("abcdefgh".getBytes()); // 8-byte IV

        // ECB Mode
        Cipher desECB = Cipher.getInstance("DES/ECB/PKCS5Padding");
        desECB.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedECB = desECB.doFinal(inputText.getBytes());

        // CBC Mode
        Cipher desCBC = Cipher.getInstance("DES/CBC/PKCS5Padding");
        desCBC.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encryptedCBC = desCBC.doFinal(inputText.getBytes());

        System.out.println("\n--- Encrypted Results ---");
        System.out.println("ECB Encrypted (Base64): " + Base64.getEncoder().encodeToString(encryptedECB));
        System.out.println("CBC Encrypted (Base64): " + Base64.getEncoder().encodeToString(encryptedCBC));

        // Decrypt both to verify correctness
        desECB.init(Cipher.DECRYPT_MODE, key);
        desCBC.init(Cipher.DECRYPT_MODE, key, iv);
        System.out.println("\n--- Decrypted Results ---");
        System.out.println("ECB Decrypted: " + new String(desECB.doFinal(encryptedECB)));
        System.out.println("CBC Decrypted: " + new String(desCBC.doFinal(encryptedCBC)));

        sc.close();
    }
}
