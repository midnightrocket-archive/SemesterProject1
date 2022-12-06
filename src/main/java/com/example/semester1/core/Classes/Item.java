package com.example.semester1.core.Classes;

public class Item extends AliasableImplementation {
    private int x = 0;
    private int y = 0;


    public Item(String id, String displayName) {
        super(id, displayName);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}