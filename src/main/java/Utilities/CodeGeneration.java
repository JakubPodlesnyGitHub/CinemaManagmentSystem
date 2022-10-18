package Utilities;

import java.util.Random;

public class CodeGeneration {
    public static String generateCode(int codeLength){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(codeLength);
        for (int i = 0; i < codeLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            stringBuilder.append((char) randomLimitedInt);
        }
        return stringBuilder.toString();
    }
}
