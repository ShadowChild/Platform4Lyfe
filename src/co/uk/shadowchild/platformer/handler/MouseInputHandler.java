package co.uk.shadowchild.platformer.handler;

import co.uk.shadowchild.platformer.Game;
import co.uk.shadowchild.platformer.geom.Point;
import co.uk.shadowchild.platformer.render.RenderGlobal;
import org.lwjgl.input.Mouse;


/**
 * @author ShadowChild
 */
public class MouseInputHandler implements IInputHandler {

    private enum Type {

        LEFT(0),
        RIGHT(1),
        MIDDLE(2);

        private Type(int mouseID) {

            this.mouseID = mouseID;
        }

        public static Type getTypeFromInt(int type) {

            for(Type button : values()) {

                if(button.mouseID == type) {

                    return button;
                }
            }
            return null;
        }

        public int mouseID;
    }

    @Override
    public void handle() {

        while(Mouse.next()) {

            if(Mouse.getEventButtonState()) {

                Type type = Type.getTypeFromInt(Mouse.getEventButton());
                switch(type) {

                    case LEFT:
                        RenderGlobal.instance.spawnBox(new Point(Mouse.getX(), getCorrectedY()), 100, 100);
                        break;

                    default:
                        break;
                }
            }
        }
    }

    public static int getCorrectedY() {

        return Game.HEIGHT - Mouse.getY() - 1;
    }
}
