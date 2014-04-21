package co.uk.shadowchild.platformer;

import co.uk.shadowchild.platformer.util.LogHelper;
import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.Level;


/**
 * @author ShadowChild
 */
public class Launch {

    private static boolean isDebug;

    private static Game game;

    public static void main(String... args) {

        LogHelper.getLogger().info("Starting launch sequence");

        for(String arg : args) {

            isDebug = arg.equals("--debug");
        }

        if(isDebug) {

            System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/lib/lwjgl/" + "native/" + getSystemOS());
        } else {

            System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/native/" + getSystemOS());
        }

        System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");

        // Pre Init
        System.setOut(LogHelper.getLogger().getStream(Level.INFO));
        System.setErr(LogHelper.getLogger().getStream(Level.ERROR));

        // Init
        game = new Game();

        // Post Init
        game.run();
    }

    private static String getSystemOS() {

        if(SystemUtils.IS_OS_UNIX) {

            return "linux";
        }
        if(SystemUtils.IS_OS_MAC) {

            return "macosx";
        }
        if(SystemUtils.IS_OS_WINDOWS) {

            return "windows";
        }
        if(SystemUtils.IS_OS_SUN_OS) {

            return "solaris";
        }

        return "";
    }

    public static Game getGameInstance() {

        return game;
    }
}
