import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AESvsDESPerformance {

    public static byte[] generateData(int sizeInKB) {
        byte[] data = new byte[sizeInKB * 1024];
        new SecureRandom().nextBytes(data);
        return data;
    }

    public static SecretKey generateAESKey() throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128);
        return generator.generateKey();
    }

    public static SecretKey generateDESKey() throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance("DES");
        generator.init(56);
        return generator.generateKey();
    }

    public static long encryptData(byte[] data, String algorithm, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        long start = System.nanoTime();
        cipher.doFinal(data);
        long end = System.nanoTime();
        return (end - start) / 1_000_000; // return milliseconds
    }

    public static void main(String[] args) throws Exception {
        int[] sizesKB = {1, 10, 100};

        System.out.printf("%-10s %-15s %-15s\n", "Size(KB)", "AES Time(ms)", "DES Time(ms)");
        System.out.println("------------------------------------------");

        for (int size : sizesKB) {
            byte[] data = generateData(size);
            SecretKey aesKey = generateAESKey();
            SecretKey desKey = generateDESKey();

            long aesTime = encryptData(data, "AES", aesKey);
            long desTime = encryptData(data, "DES", desKey);

            System.out.printf("%-10d %-15d %-15d\n", size, aesTime, desTime);
        }
    }
}
