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

            int width;
            int height;
            if(!Display.isFullscreen()) {

                width = 1920;
                height = 1080;
            } else {

                width = Launch.WIDTH * Launch.SCALE;
                height = Launch.HEIGHT * Launch.SCALE;
            }

            Launch.getInstance().getGame().setDisplayMode(width, height, !Display.isFullscreen());
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
