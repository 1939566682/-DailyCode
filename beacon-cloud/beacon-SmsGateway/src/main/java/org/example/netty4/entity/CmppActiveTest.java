package org.example.netty4.entity;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.example.netty4.utils.Command;

public class CmppActiveTest extends CmppMessageHeader {

    public CmppActiveTest() {
        super(Command.CMPP_ACTIVE_TEST, Command.CMPP2_VERSION);
    }

    /**
     * 实现类必须自定义对象序列化
     *
     * @return
     */
    @Override
    public byte[] toByteArray() {
        ByteBuf buf = Unpooled.buffer(12);
        buf.writeInt(12);
        buf.writeInt(Command.CMPP_ACTIVE_TEST);
        buf.writeInt(0);
        return buf.array();
    }
}
