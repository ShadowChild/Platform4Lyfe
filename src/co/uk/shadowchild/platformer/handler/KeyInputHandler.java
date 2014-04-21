package co.uk.shadowchild.platformer.handler;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;


/**
 * @author ShadowChild
 */
public class KeyInputHandler implements IInputHandler {

    @Override
    public void handle() {

        while(Keyboard.next()) {

            if(Keyboard.getEventKeyState()) {

                switch(Keyboard.getEventKey()) {

                    case Keyboard.KEY_ESCAPE: Display.destroy(); System.exit(1);

                    default: break;
                }
            }
        }
    }
}
