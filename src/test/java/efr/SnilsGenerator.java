package efr;

import java.util.Random;

public class SnilsGenerator {
    public static String generateSnils() {
        Random random = new Random();
        int[] snilsNumber = new int[9];

        // Генерируем первые 9 цифр
        for (int i = 0; i < 9; i++) {
            snilsNumber[i] = random.nextInt(10);
        }

        // Вычисляем контрольное число
        int controlSum = 0;
        for (int i = 0; i < 9; i++) {
            controlSum += snilsNumber[i] * (9 - i);
        }

        int controlNumber;
        if (controlSum < 100) {
            controlNumber = controlSum;
        } else if (controlSum == 100 || controlSum == 101) {
            controlNumber = 0;
        } else {
            controlNumber = controlSum % 101;
            if (controlNumber == 100 || controlNumber == 101) {
                controlNumber = 0;
            }
        }

        // Форматируем СНИЛС в строку: XXX-XXX-XXX YY
        StringBuilder snilsBuilder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                snilsBuilder.append("-");
            }
            snilsBuilder.append(snilsNumber[i]);
        }
        snilsBuilder.append(" ").append(String.format("%02d", controlNumber));

        return snilsBuilder.toString();
    }
}
