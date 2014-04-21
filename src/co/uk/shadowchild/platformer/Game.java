package co.uk.shadowchild.platformer;

import co.uk.shadowchild.platformer.geom.Box;
import co.uk.shadowchild.platformer.handler.KeyInputHandler;
import co.uk.shadowchild.platformer.handler.MouseInputHandler;
import co.uk.shadowchild.platformer.render.RenderGlobal;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;


/**
 * @author ShadowChild
 */
public class Game {

    private static Game instance;

    public static final int WIDTH = 1920, HEIGHT = WIDTH / 16 * 9;

    public KeyInputHandler keyHandler = new KeyInputHandler();
    public MouseInputHandler mouseHandler = new MouseInputHandler();

    public Game() {

        instance = this;
    }

    public static Game getInstance() {

        return instance;
    }

    public void run() {

        try {

            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.setTitle("3D Game");
            Display.setResizable(false);
            Display.create();
        } catch(LWJGLException e) {

            e.printStackTrace();
        }

        initGL();

        while(!Display.isCloseRequested()) {

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            mouseHandler.handle();
            keyHandler.handle();

            for(Box box : RenderGlobal.boxes) {

                RenderGlobal.renderQuad(box);
            }

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }

    public void initGL() {

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
}
