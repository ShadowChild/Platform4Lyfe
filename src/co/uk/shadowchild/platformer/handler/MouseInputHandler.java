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

        public int mouseID;
    }

    @Override
    public void handle() {

        while(Mouse.next()) {

            if(Mouse.getEventButtonState()) {

                switch(Mouse.getEventButton()) {

                    case 0: RenderGlobal.spawnBox(new Point(Mouse.getX(), getCorrectedY()), 100, 100); break;

                    default: break;
                }
            }
        }
    }

    public static int getCorrectedY() {

        return Game.HEIGHT - Mouse.getY() - 1;
    }
}
