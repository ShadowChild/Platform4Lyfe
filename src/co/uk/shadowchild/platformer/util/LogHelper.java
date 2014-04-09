package co.uk.shadowchild.platformer.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LogHelper {

    private static final Logger LOGGER = LogManager.getLogger("Platformer");

    private static LogHelper instance = new LogHelper();

    public static LogHelper getInstance() {

        return instance;
    }

    public Logger getLogger() {

//        LOGGER.entry();
        return LOGGER;
    }

    public void info(Object message) {

        getLogger().info(message);
    }

    public void error(Object message, Throwable error) {

        getLogger().error(message, error);
    }
}
