package co.uk.shadowchild.platformer.player;

import co.uk.shadowchild.platformer.impl.INbtUser;
import co.uk.shadowchild.platformer.render.GlobalRenderer;
import org.bytefire.libnbt.Tag;
import org.bytefire.libnbt.TagInt;

import java.awt.*;
import java.io.IOException;
import java.util.Map;


/**
 * @author ShadowChild
 */
public class Player implements INbtUser {

    private int x = 200, y = 200;

    @Override
    public void writeNBT(Map<String, Tag> tags) throws IOException {

        tags.put("playerX", new TagInt("playerX", x));
        tags.put("playerY", new TagInt("playerY", y));
    }

    @Override
    public Map<String, Tag> readNBT(Map<String, Tag> tags) throws IOException {

        TagInt x = (TagInt)tags.get("playerX");
        TagInt y = (TagInt)tags.get("playerY");

        this.x = x.getPayload();
        this.y = y.getPayload();
        return tags;
    }

    public Image getPlayerImage() {

        return GlobalRenderer.player;
    }

    public int getXCoord() {

        return x;
    }

    public int getYCoord() {

        return y;
    }
}
