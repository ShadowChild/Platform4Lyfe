package co.uk.shadowchild.platformer.main;

import co.uk.shadowchild.platformer.Game;
import co.uk.shadowchild.platformer.console.Console;
import co.uk.shadowchild.platformer.handler.KeyHandler;
import co.uk.shadowchild.platformer.handler.MouseHandler;
import co.uk.shadowchild.platformer.util.LogHelper;
import com.google.common.eventbus.EventBus;
import org.apache.commons.lang3.SystemUtils;
import org.lwjgl.opengl.Display;


/**
 * Main class for the game
 *
 * @author ShadowChild
 */
public class Launch {

    /**
     * game instance
     */
    private static Game game;

    /**
     * Launch instance
     */
    private static Launch instance;

    /**
     * Console instance
     */
    private static Console console;

    // width, height and scale
    public static final int WIDTH = 800;

    // create aspect ratio of 16:9
    public static final int HEIGHT = WIDTH / 16 * 9;
    public static final int SCALE = 2;

    // event buses
    public static final EventBus KEY_EVENT_BUS = new EventBus("KeyHandler");
    public static final EventBus MOUSE_EVENT_BUS = new EventBus("MouseHandler");

    private static boolean isDebug;

    // main method for every java program
    public static void main(String... args) {

        for(String string : args) {

            if(string.equals("--nowindow")) System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
            if(string.equals("--debug")) {

                isDebug = true;
            } else {

                isDebug = false;
            }
        }

        if(isDebug) {

            System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/lib/lwjgl/" + "native/" + getSystemOS());
        } else {

            System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/native/" + getSystemOS());
        }

        instance = new Launch();
//        console = new Console();

        LogHelper.getInstance().info("Starting Game");
//        System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");

        // register event handlers
        KEY_EVENT_BUS.register(new KeyHandler());
        MOUSE_EVENT_BUS.register(new MouseHandler());

        // initialise instances
        game = new Game();
        game.start();
    }

    public Game getGame() {

        return game;
    }

    public static Launch getInstance() {

        return instance;
    }

    public Console getConsole() {

        return console;
    }

    public static void kill() {

        LogHelper.getInstance().info("Shutting down");
        Display.destroy();
        System.exit(1);
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
}
