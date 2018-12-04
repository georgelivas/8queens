package com.company;

import java.util.Arrays;
import java.util.Objects;

public class Cell {
    private String content;
    private boolean isAvailable = true;
    private int[] coordinates;

    public Cell(String content, int[] coordinates) {
        this.content = content;
        this.coordinates = coordinates;
    }

    public String getContent() {
        return this.content;
    }

    public int[] getCoordinates() {
        return coordinates;
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
                Objects.equals(content, cell.content) &&
                Arrays.equals(coordinates, cell.coordinates);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(content, isAvailable);
        result = 31 * result + Arrays.hashCode(coordinates);
        return result;
    }
}
