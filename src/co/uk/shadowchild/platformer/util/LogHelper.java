package co.uk.shadowchild.platformer.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author ShadowChild
 */
public class LogHelper {

    private static final Logger LOGGER = LogManager.getLogger("3DGame");

    public static Logger getLogger() {

        return LOGGER;
    }
}
