package co.uk.shadowchild.platformer;

import co.uk.shadowchild.platformer.util.Utils;


/**
 * @author ShadowChild
 */
public class GameThread extends Thread {

    public GameThread() {

        super();
        this.setName("GameThread");
    }

    @Override
    public void run() {

        long lastTime = System.nanoTime();
        final double ticks = 60;
        double ns = 1000000000 / ticks;
        double delta = 0;

        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(Main.game.gameRunning) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if(delta >= 1) {

                Main.game.tick();
                updates++;
                delta--;
            }
            frames++;
            Main.game.render();

            if(System.currentTimeMillis() - timer > 1000) {

                timer += 1000;
                Utils.getInstance().frames = frames;
                Utils.getInstance().ticks = updates;
//                LogHelper.getInstance().info("Ticks = " + updates + ", Frames = " + frames);
                updates = 0;
                frames = 0;
            }
        }
        Main.game.stop();
    }
}
