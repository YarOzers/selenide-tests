package efr;

import java.util.Random;

public class CadastralNumberGenerator {
    public static String generateCadastralNumber() {
        Random random = new Random();

        // Первая группа: регион (2 цифры)
        String region = String.format("%02d", random.nextInt(100));

        // Вторая группа: район (2 цифры)
        String district = String.format("%02d", random.nextInt(100));

        // Третья группа: номер квартала (6 цифр)
        String quarter = String.format("%06d", random.nextInt(1000000));

        // Четвёртая группа: номер участка (4 цифры)
        String plot = String.format("%04d", random.nextInt(10000));

        // Формируем кадастровый номер
        return region + ":" + district + ":" + quarter + ":" + plot;
    }
}
