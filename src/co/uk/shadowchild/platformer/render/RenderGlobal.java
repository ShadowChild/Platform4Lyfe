package co.uk.shadowchild.platformer.render;

import co.uk.shadowchild.platformer.geom.Box;
import co.uk.shadowchild.platformer.geom.Point;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import java.util.List;


/**
 * @author ShadowChild
 */
public class RenderGlobal {

    public static final RenderGlobal instance = new RenderGlobal();

    private RenderGlobal() {}

    private boolean isInitialized;

    public List<Box> boxes = new ArrayList<>();

    public void renderLine(Point left, Point right) {

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2i(left.x, left.y);
        GL11.glVertex2i(right.x, right.y);
        GL11.glEnd();
    }

    public void renderQuad(Box box) {

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor3f(box.red, box.green, box.blue);
        GL11.glVertex2i(box.topLeft.x, box.topLeft.y);
        GL11.glVertex2i(box.topLeft.x + box.width, box.topLeft.y);
        GL11.glVertex2i(box.topLeft.x + box.width, box.topLeft.y + box.height);
        GL11.glVertex2i(box.topLeft.x, box.topLeft.y + box.height);
        GL11.glEnd();
    }

    public void renderTexturedQuad(Texture texture, Box box) {

        texture.bind();
        renderQuad(box);
    }

    public void spawnBox(Point topLeft, int width, int height) {

        Box box = new Box(topLeft, width, height);
        boxes.add(box);
    }

    public void initGL(int width, int height) {

        if(isInitialized) return;

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, width, height, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        isInitialized = true;
    }

    public void bindTexture(Texture texture) {

        texture.bind();
    }
}
