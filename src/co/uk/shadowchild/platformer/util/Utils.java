package co.uk.shadowchild.platformer.util;

/**
 * @author ShadowChild
 */
public class Utils {

    public int frames;
    public int ticks;

    private static Utils instance = new Utils();

    public static Utils getInstance() {
        return instance;
    }
}
