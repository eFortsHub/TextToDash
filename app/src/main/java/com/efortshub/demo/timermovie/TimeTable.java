package com.efortshub.demo.timermovie;

import java.io.Serializable;

public class TimeTable implements Serializable {
    private int index;
    private int secAtIndex;

    public TimeTable(int index, int secAtIndex) {
        this.index = index;
        this.secAtIndex = secAtIndex;
    }

    public int getIndex() {
        return index;
    }

    public int getSecAtIndex() {
        return secAtIndex;
    }
}
