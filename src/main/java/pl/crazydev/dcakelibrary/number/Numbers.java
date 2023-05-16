package pl.crazydev.dcakelibrary.number;

import java.util.Random;

public abstract class Numbers {
    public static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public static boolean isDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public static boolean tryPercent(double percent) {
        return randomDouble(100d) <= percent;
    }

    public static int randomInt(int range) {
        Random random = new Random(new Random().nextInt());

        return random.nextInt(range + 1);
    }

    public static double randomDouble(double range) {
        return Math.round((range * 2d) * Math.random()) / 2d;
    }
}
