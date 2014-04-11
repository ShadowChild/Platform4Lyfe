package co.uk.shadowchild.platformer.event;

/**
 * @author ShadowChild
 */
public class EventFactory {

    public static KeyInputEvent createKeyEvent(int eventKey) {

        return new KeyInputEvent(eventKey);
    }

    public static MouseInputEvent createMouseEvent(int button, int x, int y) {

        return new MouseInputEvent(button, x, y);
    }
}
