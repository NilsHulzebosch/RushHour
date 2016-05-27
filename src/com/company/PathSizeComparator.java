package com.company;
import java.util.Comparator;

public class PathSizeComparator implements Comparator<Grid> {

    public int compare(Grid a, Grid b) {
        // compare function for a-star using an admissible heuristic
        int a_total = a.getScore();
        int b_total = b.getScore();

        if (a_total < b_total) {
            return -1;
        } else if (a_total > b_total) {
            return 1;
        }
        return 0;
    }
}
