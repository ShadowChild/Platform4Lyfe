package co.uk.shadowchild.platformer.handler;

import co.uk.shadowchild.platformer.event.KeyInputEvent;
import co.uk.shadowchild.platformer.main.Launch;
import co.uk.shadowchild.platformer.util.LogHelper;
import co.uk.shadowchild.platformer.util.ScreenshotHelper;
import com.google.common.eventbus.Subscribe;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import java.io.IOException;


/**
 * @author ShadowChild
 */
public class KeyHandler {

    @Subscribe
    public void handle(KeyInputEvent evt) {

        if(evt.keyCode == Keyboard.KEY_ESCAPE) {

            Launch.kill();
        }

        if(evt.keyCode == Keyboard.KEY_F) {

            Launch.getInstance().getGame().setDisplayMode(Launch.WIDTH * Launch.SCALE, Launch.HEIGHT * Launch.SCALE, !Display.isFullscreen());
        }

        if(evt.keyCode == Keyboard.KEY_F2) {

            try {

                ScreenshotHelper.screenCap();
            } catch(IOException e) {

                e.printStackTrace();
            }
        }

        LogHelper.getInstance().info("Key = " + Keyboard.getKeyName(evt.keyCode));
    }
}
