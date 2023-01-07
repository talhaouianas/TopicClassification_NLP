package src;

import java.util.Comparator;

public class SortWords implements Comparator<Word>{

    public int compare(Word b, Word a) {
        return a.Occurrence<b.Occurrence ? -1 : a.Occurrence==b.Occurrence ? 0 : 1;
    }

}
