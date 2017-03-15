package com.blueware.domain;

import com.blueware.utils.ByteUtils;

/**
 * Created by zhangyan on 2017/3/14.
 */
public class Head {
    public static final int TYPE_EOF = 1;
    public static final int TYPE_RECV = 2;


    int margic;
    int type;
    int version;
    int namelength;
    long dataLength;
    String names;

    @Override
    public String toString() {
        return String.format("{margic:%d, type:%d, version:%d, namelength:%d, dataLength:%d, names:%s}", margic, type, version, namelength, dataLength, names);
    }

    public static Head parse(byte[] buff) throws IllegalArgumentException {
        Head head = null;
        if (buff != null && buff.length >= 24){
            head = new Head();
            head.margic = ByteUtils.toInteger(buff, 0);
            head.type = ByteUtils.toInteger(buff, 4);
            head.version = ByteUtils.toInteger(buff, 8);
            head.namelength = ByteUtils.toInteger(buff, 12);
            head.dataLength = ByteUtils.toLong(buff, 16);
        }else{
            throw new IllegalArgumentException("parase Head error!");
        }
        return head;
    }

    public int getMargic() {
        return margic;
    }

    public void setMargic(int margic) {
        this.margic = margic;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getNamelength() {
        return namelength;
    }

    public void setNamelength(int namelength) {
        this.namelength = namelength;
    }

    public long getDataLength() {
        return dataLength;
    }

    public void setDataLength(long dataLength) {
        this.dataLength = dataLength;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
