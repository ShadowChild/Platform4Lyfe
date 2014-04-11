package co.uk.shadowchild.platformer.handler;

import co.uk.shadowchild.platformer.event.MouseInputEvent;
import co.uk.shadowchild.platformer.util.LogHelper;
import com.google.common.eventbus.Subscribe;
import org.lwjgl.input.Mouse;


/**
 * @author ShadowChild
 */
public class MouseHandler {

    @Subscribe
    public void handle(MouseInputEvent evt) {

        LogHelper.getInstance().info("Button: " + Mouse.getButtonName(evt.button) + " x = " + evt.x + " y = " + evt.y);
    }
}
