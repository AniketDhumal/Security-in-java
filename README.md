# 🔐 Cryptography Algorithms in Java

This repository demonstrates multiple cryptographic algorithms implemented in **Java**. These include both classical and modern ciphers like **AES**, **DES**, **Affine Cipher**, **Hill Cipher**, **Playfair Cipher**, and more. The purpose of this repository is to provide a hands-on experience with different encryption/decryption methods and showcase their use cases.

---

## 📘 Projects Overview

1. **AES vs DES Performance**  
   A performance comparison of **AES** and **DES** encryption for various data sizes (1KB, 10KB, 100KB).
   
2. **DES Brute Force**  
   Demonstrates a brute-force attack on **DES** encryption by trying all possible 8-bit keys to decrypt a ciphertext.

3. **DES Example**  
   Shows how to perform encryption and decryption using the **DES** algorithm in **ECB** mode with user-provided key and plaintext in hexadecimal format.

4. **DES Modes**  
   Demonstrates **DES** encryption in **ECB** (Electronic Codebook) and **CBC** (Cipher Block Chaining) modes, including both encryption and decryption processes with hardcoded keys and IVs.

5. **Affine Cipher**  
   Implements the **Affine Cipher**, a simple substitution cipher based on linear algebra, used for encryption and decryption with a pair of keys.

6. **Hill Cipher**  
   Implements the **Hill Cipher**, a polygraphic substitution cipher based on linear algebra, which encrypts a block of letters (usually 2x2 or 3x3 matrices).

7. **Playfair Cipher**  
   Implements the **Playfair Cipher**, which encrypts digraphs (pairs of letters) and is based on a 5x5 matrix of letters.

8. **AES Modes**  
   Demonstrates AES encryption in different modes like **ECB**, **CBC**, **CTR**, and **GCM**, showing both encryption and decryption processes.

9. **AES Flexible**  
   Implements a flexible AES encryption system where the user can select the mode (ECB, CBC, etc.) and set a custom key length (128, 192, or 256 bits) for encryption/decryption.

---

## 🛠 Features

- **AES vs DES Performance**:  
  - Compares encryption speeds for AES and DES across different data sizes.
  
- **DES Brute Force**:  
  - Implements a brute-force approach to decrypt a **DES** ciphertext with a known key.
  
- **DES Example**:  
  - Provides an example of how to encrypt and decrypt data with **DES** in **ECB** mode using hexadecimal inputs for plaintext and key.
  
- **DES Modes**:  
  - Shows how **DES** encryption behaves in **ECB** and **CBC** modes.

- **Affine Cipher**:  
  - Implements **Affine Cipher** encryption and decryption, which is based on mathematical functions.

- **Hill Cipher**:  
  - Implements **Hill Cipher** encryption/decryption with 2x2 or 3x3 matrices.

- **Playfair Cipher**:  
  - Implements **Playfair Cipher** encryption/decryption with digraphs (pairs of letters) using a 5x5 matrix.

- **AES-Modes**:  
  - Implements AES encryption with different modes of operation: **ECB**, **CBC**, **CTR**, and **GCM**.

- **AES Flexible**:  
  - A flexible implementation of AES encryption that allows the user to choose encryption modes and key sizes (128, 192, 256 bits).

---

## 📷 Sample Output

### **AES vs DES Performance**
```text
Size(KB)  AES Time(ms)  DES Time(ms)
-----------------------------------
1         5              3
10        50             35
100       450            400

# Cryptography Examples

## DES Brute Force

**Enter plaintext**: HelloWorld  
**Encrypted Cipher (Base64)**: t+7Y/Jgx9VMy5n6V9ZQp5g==  
**Starting brute-force attack...**  
**Key found**: 123  
**Decrypted text**: HelloWorld  
**Time taken**: 14.85 ms  

---

## DES Example

**Enter plaintext (16 hex digits)**: 68656C6C6F205245544C42  
**Enter key (16 hex digits)**: 1234567890ABCDEF  
**Encrypted Text (Hex)**: A56D8AB1FF1A2C87  
**Decrypted Text (Hex)**: 68656C6C6F205245544C42  

---

## DES Modes

**Enter plaintext**: HelloDESWorld

### Encrypted Results
- **ECB Encrypted (Base64)**: t+7Y/Jgx9VMy5n6V9ZQp5g==  
- **CBC Encrypted (Base64)**: eBOipzMtoM5n9W5a0dcfEw==  

### Decrypted Results
- **ECB Decrypted**: HelloDESWorld  
- **CBC Decrypted**: HelloDESWorld  

---

## Affine Cipher

**Enter plaintext**: HELLO  
**Enter key (a,b)**: 5 8  
**Encrypted Text**: ZEBBW  
**Decrypted Text**: HELLO  

---

## Hill Cipher

**Enter plaintext (2x2 matrix)**: HELLO  
**Enter key (2x2 matrix)**: 6 24, 1 1  
**Encrypted Text**: XFPX  
**Decrypted Text**: HELLO  

---

## Playfair Cipher

**Enter plaintext**: HELLO  
**Enter key**: PLAYFIREXM  
**Encrypted Text**: BIPUL  
**Decrypted Text**: HELLO  

---

## AES Modes

**Enter plaintext**: HelloAESWorld

### Encrypted Results
- **ECB Encrypted (Base64)**: d8fKljF6+2QOxLMxgfsljw==  
- **CBC Encrypted (Base64)**: ZGJLDLpp+7dbSZh8UzIcIg==  

### Decrypted Results
- **ECB Decrypted**: HelloAESWorld  
- **CBC Decrypted**: HelloAESWorld  

---

## AES Flexible

**Enter plaintext**: HelloFlexibleAES  
**Enter encryption mode (ECB, CBC, CTR, GCM)**: CBC  
**Enter key length (128, 192, 256)**: 128  

**Encrypted (Base64)**: RzAZf6dbKd6c5Jwm7LNRzQ==  
**Decrypted**: HelloFlexibleAES  


## 🧑‍💻 How to Run
Clone the repository or download the individual Java files.

For each file, compile and run using the following commands:

javac <filename>.java
java <filename>

For example, to run the AES Modes program:

javac AESModes.java
java AESModes

Follow the instructions printed in the console for user inputs.

## 🧰 Requirements
Java JDK 8 or higher

No external libraries required (uses Java's built-in javax.crypto package for DES/AES, and custom algorithms for Affine, Hill, and Playfair ciphers)

⚠️ Notes
DES ECB Mode is not recommended for secure cryptographic applications because it reveals patterns in the plaintext. Use CBC or GCM modes for better security.

Brute-force Attack: The DES brute-force example uses a very simplified 8-bit key space for educational purposes. Real-world attacks would require much more computational power.

IV Management: In CBC mode, an IV (Initialization Vector) is required, which is hardcoded in the example. Use secure, random IVs in production code.

Affine Cipher: The Affine Cipher works with two keys, and only certain values are valid for the key pair (a, b) to ensure invertibility.

Hill Cipher: The Hill Cipher requires a key matrix to be invertible. It is a polygraphic substitution cipher and works on blocks of text.

Playfair Cipher: The Playfair Cipher works on digraphs (pairs of letters). A key matrix of 5x5 letters is used for encryption/decryption.

AES Modes: AES is demonstrated in several modes of operation, including ECB, CBC, CTR, and GCM. GCM mode also provides authentication, adding integrity checks.

📋 Code Structure
AESvsDESPerformance.java:
Compares AES and DES performance for different data sizes.

DESBruteForce.java:
Demonstrates a brute-force attack on DES encryption.

DESExample.java:
Shows how to encrypt and decrypt data with DES in ECB mode using hexadecimal input.

DESModes.java:
Demonstrates DES encryption in ECB and CBC modes.

AffineCipher.java:
Implements the Affine Cipher for encryption and decryption.

HillCipher.java:
Implements the Hill Cipher for encryption and decryption using 2x2 or 3x3 matrices.

PlayfairCipher.java:
Implements the Playfair Cipher for encryption and decryption using a 5x5 matrix.

AESModes.java:
Implements AES encryption in various modes like ECB, CBC, CTR, and GCM.

AESFlexible.java:
Implements flexible AES encryption that allows users to select the mode and key length.

👨‍💻 Author & Educational Use
These programs are ideal for learning and teaching cryptography and Java encryption. The examples are meant for educational purposes and to provide hands-on experience with various cipher algorithms.

🔗 References
Java Cryptography Documentation

Wikipedia - DES

Wikipedia - AES

Affine Cipher

Hill Cipher

Playfair Cipher

AES Modes
