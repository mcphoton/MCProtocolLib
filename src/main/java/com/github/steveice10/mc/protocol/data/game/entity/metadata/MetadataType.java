package com.github.steveice10.mc.protocol.data.game.entity.metadata;

import com.github.steveice10.mc.protocol.data.message.Message;
import com.github.steveice10.mc.protocol.data.message.TextMessage;
import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import java.io.IOException;
import java.util.UUID;

public enum MetadataType {
    BYTE {
        @Override
        public void write(Object value, NetOutput out) throws IOException {
            out.writeByte((byte)value);
        }

        @Override
        public Byte read(NetInput in) throws IOException {
            return in.readByte();
        }
    }, VARINT {
        @Override
        public void write(Object value, NetOutput out) throws IOException {
            out.writeVarInt((int)value);
        }

        @Override
        public Integer read(NetInput in) throws IOException {
            return in.readVarInt();
        }
    }, FLOAT {
        @Override
        public void write(Object value, NetOutput out) throws IOException {
            out.writeFloat((float)value);
        }

        @Override
        public Float read(NetInput in) throws IOException {
            return in.readFloat();
        }
    }, STRING {
        @Override
        public void write(Object value, NetOutput out) throws IOException {
            out.writeString((String)value);
        }

        @Override
        public String read(NetInput in) throws IOException {
            return in.readString();
        }
    }, CHAT {
        @Override
        public void write(Object value, NetOutput out) throws IOException {
            out.writeString(((Message)value).toJsonString());
        }

        @Override
        public Message read(NetInput in) throws IOException {
            return TextMessage.fromString(in.readString());
        }
    }, ITEM_STACK {
        @Override
        public void write(Object value, NetOutput out) throws IOException {
            NetUtil.writeItem(out, (ItemStack)value);
        }

        @Override
        public ItemStack read(NetInput in) throws IOException {
            return NetUtil.readItem(in);
        }
    }, BOOLEAN {
        @Override
        public void write(Object value, NetOutput out) throws IOException {
            out.writeBoolean((boolean)value);
        }

        @Override
        public Boolean read(NetInput in) throws IOException {
            return in.readBoolean();
        }
    }, ROTATION {
        @Override
        public void write(Object value, NetOutput out) throws IOException {
            NetUtil.writeRotation(out, (Rotation)value);
        }

        @Override
        public Rotation read(NetInput in) throws IOException {
            return NetUtil.readRotation(in);
        }
    }, POSITION {
        @Override
        public void write(Object value, NetOutput out) throws IOException {
            NetUtil.writePosition(out, (IntPosition)value);
        }

        @Override
        public IntPosition read(NetInput in) throws IOException {
            return NetUtil.readPosition(in);
        }
    }, OPTIONAL_POSITION {
        @Override
        public void write(Object value, NetOutput out) throws IOException {
            if (value == null) {
                out.writeBoolean(false);
            } else {
                out.writeBoolean(true);
                NetUtil.writePosition(out, (IntPosition)value);
            }
        }

        @Override
        public IntPosition read(NetInput in) throws IOException {
            return in.readBoolean() ? NetUtil.readPosition(in) : null;
        }
    }, BLOCK_FACE {
        @Override
        public void write(Object value, NetOutput out) throws IOException {
            //TODO
        }

        @Override
        public Object read(NetInput in) throws IOException {
            return null;//TODO
        }
    }, OPTIONAL_UUID {
        @Override
        public void write(Object value, NetOutput out) throws IOException {
            if (value == null) {
                out.writeBoolean(false);
            } else {
                out.writeBoolean(true);
                out.writeUUID((UUID)value);
            }
        }

        @Override
        public UUID read(NetInput in) throws IOException {
            return in.readBoolean() ? in.readUUID() : null;
        }
    }, OPTIONAL_BLOCK_ID {
        @Override
        public void write(Object value, NetOutput out) throws IOException {
            if (value == null) {
                out.writeBoolean(false);
            } else {
                out.writeBoolean(true);
                out.writeVarInt((int)value);
            }
        }

        @Override
        public Integer read(NetInput in) throws IOException {
            return in.readBoolean() ? in.readVarInt() : null;
        }
    };

    public int getId() {
        return ordinal();
    }

    public abstract void write(Object value, NetOutput out) throws IOException;

    public abstract Object read(NetInput in) throws IOException;

    public static MetadataType getType(int id) {
        return values()[id];
    }
}