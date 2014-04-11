package co.uk.shadowchild.platformer;

import co.uk.shadowchild.platformer.event.Event;
import co.uk.shadowchild.platformer.event.EventFactory;
import co.uk.shadowchild.platformer.main.Launch;
import co.uk.shadowchild.platformer.player.Player;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;


/**
 * Game instance
 * This controls all game elements
 *
 * @author ShadowChild
 */
public class Game {

    // player instance
    public Player thePlayer;

    /**
     * time at last frame
     */
    long lastFrame;

    /**
     * frames per second
     */
    int fps;
    /**
     * last fps time
     */
    long lastFPS;


    public Game() {

        //        this.setPreferredSize(new Dimension(width * scale, height * scale));
        //        this.setMaximumSize(new Dimension(width * scale, height * scale));
        //        this.setMinimumSize(new Dimension(width * scale, height * scale));

        // initialise play instance
        thePlayer = new Player();
    }

    // do tick stuff
    public void tick() {

        //        LogHelper.getInstance().info("Stuuf");

        while(Mouse.next()) {

            if(Mouse.getEventButtonState()) {

                Event evt = EventFactory.createMouseEvent(Mouse.getEventButton(), Mouse.getEventX(), Mouse.getEventY());
                Launch.MOUSE_EVENT_BUS.post(evt);
            }
        }

        while(Keyboard.next()) {

            if(Keyboard.getEventKeyState()) {

                Event evt = EventFactory.createKeyEvent(Keyboard.getEventKey());
                Launch.KEY_EVENT_BUS.post(evt);
            }
        }
    }

    public void start() {

        try {

            // initialise the display
            setDisplayMode(Launch.WIDTH * Launch.SCALE, Launch.HEIGHT * Launch.SCALE, false);
            Display.setTitle("Platformer");
            //            setDisplayMode(Launch.WIDTH * Launch.SCALE, Launch.HEIGHT * Launch.SCALE, false);
            Display.create();
        } catch(LWJGLException e) {

            e.printStackTrace();
            System.exit(0);
        }

        initGL(); // init OpenGL
        getDelta(); // call once before loop to initialise lastFrame
        lastFPS = getTime(); // call before loop to initialise fps timer

        while(!Display.isCloseRequested()) {
            int delta = getDelta();

            update(delta);
            renderGL();

            Display.update();
            Display.sync(60); // cap fps to 60fps
        }

        Launch.kill();
    }

    public void update(int delta) {

        tick();
        updateFPS(); // update FPS Counter
    }

    /**
     * Calculate how many milliseconds have passed
     * since last frame.
     *
     * @return milliseconds passed since last frame
     */
    public int getDelta() {
        long time = getTime();
        int delta = (int)(time - lastFrame);
        lastFrame = time;

        return delta;
    }

    /**
     * Get the accurate system time
     *
     * @return The system time in milliseconds
     */
    public long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    /**
     * Calculate the FPS and set it in the title bar
     */
    public void updateFPS() {
        if(getTime() - lastFPS > 1000) {
            Display.setTitle("Platformer - FPS: " + fps);
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
    }

    public void initGL() {

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 0, 600, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    public void renderGL() {
        // Clear The Screen And The Depth Buffer
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        // set the color of the quad (R,G,B,A)
        GL11.glColor3f(0.5f, 0.5f, 1.0f);

        // draw quad
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(100, 100);
        GL11.glVertex2f(100 + 200, 100);
        GL11.glVertex2f(100 + 200, 100 + 200);
        GL11.glVertex2f(100, 100 + 200);
        GL11.glEnd();
        //        Display.update();
    }

    public void setDisplayMode(int width, int height, boolean fullscreen) {

        // return if requested DisplayMode is already set
        if((Display.getDisplayMode().getWidth() == width) &&
                (Display.getDisplayMode().getHeight() == height) &&
                (Display.isFullscreen() == fullscreen)) {
            return;
        }

        try {
            DisplayMode targetDisplayMode = null;

            if(fullscreen) {
                DisplayMode[] modes = Display.getAvailableDisplayModes();
                int freq = 0;

                for(DisplayMode current : modes) {
                    if((current.getWidth() == width) && (current.getHeight() == height)) {
                        if((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
                            if((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
                                targetDisplayMode = current;
                                freq = targetDisplayMode.getFrequency();
                            }
                        }

                        // if we've found a match for bpp and frequence against the
                        // original display mode then it's probably best to go for this one
                        // since it's most likely compatible with the monitor
                        if((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) && (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
                            targetDisplayMode = current;
                            break;
                        }
                    }
                }
            } else {
                targetDisplayMode = new DisplayMode(width, height);
            }

            if(targetDisplayMode == null) {
                System.out.println("Failed to find value mode: " + width + "x" + height + " fs=" + fullscreen);
                return;
            }

            Display.setDisplayMode(targetDisplayMode);
            Display.setFullscreen(fullscreen);

        } catch(LWJGLException e) {
            System.out.println("Unable to setup mode " + width + "x" + height + " fullscreen=" + fullscreen + e);
        }
    }
}
