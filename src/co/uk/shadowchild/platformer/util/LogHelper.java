package co.uk.shadowchild.platformer.util;

import co.uk.shadowchild.platformer.main.Launch;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author ShadowChild
 */
public class LogHelper {

    // create logger instance
    private static final Logger LOGGER = LogManager.getLogger("Platformer");

    // create LogHelper instance
    private static LogHelper instance = new LogHelper();

    /**
     * @return instance of this
     */
    public static LogHelper getInstance() {

        return instance;
    }

    /**
     * @return instance of logger
     */
    public Logger getLogger() {

        return LOGGER;
    }

    /**
     * Logs at INFO level
     *
     * @param message - the message to log
     */
    public void info(Object message) {

        Launch.getInstance().getConsole().textArea.append(message.toString() + "\n");
        getLogger().info(message);
    }

    /**
     * Logs at ERROR level
     *
     * @param message - the message to log
     * @param error   - the thrown error
     */
    public void error(Object message, Throwable error) {

        getLogger().error(message, error);
    }

    public void doStuuf() {

        System.setOut(getLogger().getStream(Level.INFO));
    }
}