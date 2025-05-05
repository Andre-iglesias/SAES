import Saes.AES;

public class Main {
    public static void main(String[] args) {
        int plaintext = (0x6F << 8) | 0x69; // "oi"
        int[] roundKeys = { 0x5678, 0x9ABC };

        AES aes = new AES(roundKeys);

        System.out.printf("Texto plano (original): 0x%04X ('%c%c')%n", plaintext, plaintext >> 8, plaintext & 0xFF);

        // Cifra o texto
        int ciphertext = aes.encrypt(plaintext);
        System.out.printf("Texto cifrado: 0x%04X ('%c%c')%n", ciphertext, ciphertext >> 8, ciphertext & 0xFF);

        // Decifra o texto
        int decrypted = aes.decrypt(ciphertext);
        System.out.printf("Texto decifrado: 0x%04X ('%c%c')%n", decrypted, decrypted >> 8, decrypted & 0xFF);
    }
}
