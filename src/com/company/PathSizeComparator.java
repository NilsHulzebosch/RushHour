package com.company;
import java.util.Comparator;

public class PathSizeComparator implements Comparator<Grid> {

    public int compare(Grid a, Grid b) {
        if (a.getPathSize() < b.getPathSize()) {
            return -1;
        } else if (a.getPathSize() > b.getPathSize()) {
            return 1;
        }
        return 0;
    }
}
