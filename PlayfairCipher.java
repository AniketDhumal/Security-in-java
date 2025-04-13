import java.util.*;

public class PlayfairCipher {

    static char[][] matrix = new char[5][5];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter keyword (e.g. MONARCHY): ");
        String keyword = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");

        buildMatrix(keyword);

        System.out.println("Generated 5x5 Matrix:");
        printMatrix();

        System.out.print("Enter plaintext (e.g. BALLOON): ");
        String plaintext = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");

        List<String> digraphs = formDigraphs(plaintext);

        String encrypted = encrypt(digraphs);
        System.out.println("Encrypted text: " + encrypted);

        String decrypted = decrypt(encrypted);
        System.out.println("Decrypted text: " + decrypted);
    }

    // 1. Build 5x5 matrix
    static void buildMatrix(String keyword) {
        Set<Character> used = new LinkedHashSet<>();

        for (char ch : keyword.toCharArray()) {
            used.add(ch);
        }

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (ch == 'J') continue;
            used.add(ch);
        }

        Iterator<Character> it = used.iterator();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = it.next();
            }
        }
    }

    // 2. Print matrix
    static void printMatrix() {
        for (char[] row : matrix) {
            for (char ch : row) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }

    // 3. Form digraphs
    static List<String> formDigraphs(String text) {
        List<String> pairs = new ArrayList<>();
        int i = 0;

        while (i < text.length()) {
            char first = text.charAt(i);
            char second = (i + 1 < text.length()) ? text.charAt(i + 1) : 'X';

            if (first == second) {
                pairs.add("" + first + 'X');
                i++;
            } else {
                pairs.add("" + first + second);
                i += 2;
            }
        }

        // Ensure even length
        if (pairs.get(pairs.size() - 1).length() == 1)
            pairs.set(pairs.size() - 1, pairs.get(pairs.size() - 1) + "X");

        return pairs;
    }

    // 4. Find row and column in matrix
    static int[] findPosition(char ch) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (matrix[i][j] == ch)
                    return new int[]{i, j};
        return null;
    }

    // 5. Encryption
    static String encrypt(List<String> digraphs) {
        StringBuilder result = new StringBuilder();

        for (String pair : digraphs) {
            int[] pos1 = findPosition(pair.charAt(0));
            int[] pos2 = findPosition(pair.charAt(1));

            if (pos1[0] == pos2[0]) {
                // Same row
                result.append(matrix[pos1[0]][(pos1[1] + 1) % 5]);
                result.append(matrix[pos2[0]][(pos2[1] + 1) % 5]);
            } else if (pos1[1] == pos2[1]) {
                // Same column
                result.append(matrix[(pos1[0] + 1) % 5][pos1[1]]);
                result.append(matrix[(pos2[0] + 1) % 5][pos2[1]]);
            } else {
                // Rectangle
                result.append(matrix[pos1[0]][pos2[1]]);
                result.append(matrix[pos2[0]][pos1[1]]);
            }
        }

        return result.toString();
    }

    // 6. Decryption
    static String decrypt(String ciphertext) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            char ch1 = ciphertext.charAt(i);
            char ch2 = ciphertext.charAt(i + 1);

            int[] pos1 = findPosition(ch1);
            int[] pos2 = findPosition(ch2);

            if (pos1[0] == pos2[0]) {
                // Same row
                result.append(matrix[pos1[0]][(pos1[1] + 4) % 5]);
                result.append(matrix[pos2[0]][(pos2[1] + 4) % 5]);
            } else if (pos1[1] == pos2[1]) {
                // Same column
                result.append(matrix[(pos1[0] + 4) % 5][pos1[1]]);
                result.append(matrix[(pos2[0] + 4) % 5][pos2[1]]);
            } else {
                // Rectangle
                result.append(matrix[pos1[0]][pos2[1]]);
                result.append(matrix[pos2[0]][pos1[1]]);
            }
        }

        return result.toString();
    }
}
