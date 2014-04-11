package co.uk.shadowchild.platformer.event;

/**
 * @author ShadowChild
 */
public class KeyInputEvent extends Event {

    public int keyCode;

    public KeyInputEvent(int keyCode) {

        this.keyCode = keyCode;
    }
}
