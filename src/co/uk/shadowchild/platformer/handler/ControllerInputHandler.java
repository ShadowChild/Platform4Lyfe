package co.uk.shadowchild.platformer.handler;

import co.uk.shadowchild.platformer.geom.Point;
import co.uk.shadowchild.platformer.render.RenderGlobal;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;


/**
 * @author ShadowChild
 */
public class ControllerInputHandler implements IInputHandler {

    public static Controller controller;

    @Override
    public void handle() {

        controller.poll();
        while(Controllers.next()) {

            if(Controllers.getEventButtonState()) {

                Button button = Button.getButtonFromInt(Controllers.getEventControlIndex());
                switch(button) {

                    case A:
                        RenderGlobal.instance.spawnBox(new Point(15, 15), 100, 100);
                        break;

                    default:
                        break;
                }
            }
        }
    }

    public static enum Button {

        A(0),
        B(1),
        X(2),
        Y(3),
        LB(4),
        RB(5),
        SELECT(6),
        START(7),
        L3(8),
        R3(9);

        private Button(int buttonID) {

            this.buttonID = buttonID;
        }

        public int buttonID;

        public static Button getButtonFromInt(int buttonID) {

            for(Button button : values()) {

                if(button.buttonID == buttonID) {

                    return button;
                }
            }
            return null;
        }
    }

    public static enum Axis {

        L3Y(0),
        L3X(1),
        R3Y(2),
        R3X(3),
        TRIGGER(4);

        private Axis(int axisID) {

            this.axisID = axisID;
        }

        public int axisID;

        public Axis getAxisFromInt(int axisID) {

            for(Axis axis : values()) {

                if(axis.axisID == axisID) {

                    return axis;
                }
            }
            return null;
        }
    }
}
