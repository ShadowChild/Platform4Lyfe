package co.uk.shadowchild.platformer;

import co.uk.shadowchild.platformer.util.LogHelper;
import co.uk.shadowchild.platformer.util.ResourceBank;
import org.apache.logging.log4j.Level;
import org.lwjgl.LWJGLUtil;


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

            System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/lib/lwjgl/" + "native/" + LWJGLUtil.getPlatformName());
        } else {

            System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/native/" + LWJGLUtil.getPlatformName());
        }
        System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));

        System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");

        // Pre Init
        System.setOut(LogHelper.getLogger().getStream(Level.INFO));
        System.setErr(LogHelper.getLogger().getStream(Level.ERROR));

        // Init
        game = new Game();
        ResourceBank.load();

        // Post Init
        game.run();
    }

    public static Game getGameInstance() {

        return game;
    }

    public static boolean getIsDebug() {

        return isDebug;
    }
}
