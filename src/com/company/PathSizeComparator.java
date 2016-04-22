package com.company;
import java.util.Comparator;

public class PathSizeComparator implements Comparator<Grid> {

    public int compare(Grid a, Grid b) {
        int a_total = a.getPathSize() + a.getPathEstimate();
        int b_total = b.getPathSize() + b.getPathEstimate();

        if (a_total < b_total) {
            return -1;
        } else if (a_total > b_total) {
            return 1;
        }
        return 0;
    }
}
