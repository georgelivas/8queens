package com.company;

import java.util.Objects;

public class Cell {
    private String content;
    private boolean isAvailable = true;

    public Cell(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
        setAvailable(false);
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return getContent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        Cell cell = (Cell) o;
        return isAvailable == cell.isAvailable &&
                Objects.equals(content, cell.content);
    }
}
