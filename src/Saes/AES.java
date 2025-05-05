package Saes;

public class AES {
    private final int[] roundKeys;

    public AES(int[] roundKeys) {
        this.roundKeys = roundKeys.clone(); // Copia defensiva
    }

    public int encrypt(int block) {
        for (int round = 0; round < 2; round++) {
            block = substitute(block, S_BOX);
            block = permute(block);
            block ^= roundKeys[round];
        }
        return block;
    }

    public int decrypt(int block) {
        for (int round = 1; round >= 0; round--) {
            block ^= roundKeys[round];
            block = invPermute(block);
            block = substitute(block, INV_S_BOX);
        }
        return block;
    }

    // As constantes e funções auxiliares permanecem privadas
    private static final int[] S_BOX = {
            0x6, 0x4, 0xC, 0x5, 0x0, 0x7, 0x2, 0xE,
            0x1, 0xF, 0x3, 0xD, 0x8, 0xA, 0x9, 0xB
    };

    private static final int[] INV_S_BOX = new int[16];
    static {
        for (int i = 0; i < S_BOX.length; i++) {
            INV_S_BOX[S_BOX[i]] = i;
        }
    }

    private static int permute(int value) {
        return (value << 4 | value >>> 12) & 0xFFFF;
    }

    private static int invPermute(int value) {
        return (value >>> 4 | value << 12) & 0xFFFF;
    }

    private static int substitute(int value, int[] sBox) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            int nibble = (value >>> (i * 4)) & 0xF;
            result |= sBox[nibble] << (i * 4);
        }
        return result;
    }
}
