package co.uk.shadowchild.platformer.util;

import co.uk.shadowchild.platformer.Launch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author ShadowChild
 */
public class LogHelper {

    private static final Logger LOGGER = LogManager.getLogger("P4Lyfe");

    public static Logger getLogger() {

        return LOGGER;
    }

    public static void debug(String message) {

        if(Launch.getIsDebug()) {

            getLogger().debug(message);
        }
    }
}
