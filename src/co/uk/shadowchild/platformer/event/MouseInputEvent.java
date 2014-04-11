package co.uk.shadowchild.platformer.event;

/**
 * @author ShadowChild
 */
public class MouseInputEvent extends Event {

    public int button, x, y;

    public MouseInputEvent(int button, int x, int y) {

        this.button = button;
        this.x = x;
        this.y = y;
    }
}
