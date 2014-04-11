package co.uk.shadowchild.platformer.impl;

import org.bytefire.libnbt.Tag;

import java.io.IOException;
import java.util.Map;


/**
 * @author ShadowChild
 */
public interface INbtUser {

    void writeNBT(Map<String, Tag> tags) throws IOException;

    Map<String, Tag> readNBT(Map<String, Tag> tags) throws IOException;
}
