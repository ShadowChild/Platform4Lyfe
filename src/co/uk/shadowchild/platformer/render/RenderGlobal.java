package co.uk.shadowchild.platformer.render;

import co.uk.shadowchild.platformer.geom.Box;
import co.uk.shadowchild.platformer.geom.Point;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;


/**
 * @author ShadowChild
 */
public class RenderGlobal {

    public static List<Box> boxes = new ArrayList<>();

    public static void renderLine(Point left, Point right) {

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2i(left.x, left.y);
        GL11.glVertex2i(right.x, right.y);
        GL11.glEnd();
    }

    public static void renderQuad(Box box) {

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor3f(box.red, box.green, box.blue);
        GL11.glVertex2i(box.topLeft.x, box.topLeft.y);
        GL11.glVertex2i(box.topLeft.x + box.width, box.topLeft.y);
        GL11.glVertex2i(box.topLeft.x + box.width, box.topLeft.y + box.height);
        GL11.glVertex2i(box.topLeft.x, box.topLeft.y + box.height);
        GL11.glEnd();
    }

    public static void spawnBox(Point topLeft, int width, int height) {

        Box box = new Box(topLeft, width, height);
        boxes.add(box);
    }
}
