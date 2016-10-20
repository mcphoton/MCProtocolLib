package org.spacehq.mc.protocol.util;

import org.junit.Test;
import org.mcphoton.mc.protocol.data.game.entity.metadata.Position;

import java.io.IOException;

import static org.mcphoton.mc.protocol.util.NetUtil.readPosition;
import static org.mcphoton.mc.protocol.util.NetUtil.writePosition;
import static org.spacehq.mc.protocol.ByteBufHelper.*;

public class NetUtilTest {

    @Test
    public void testPosition() throws IOException {
        writePosition(out, new Position(1, 61, -1));
        assertPosition(readPosition(in), 1, 61, -1);
    }

}
