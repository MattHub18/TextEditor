package com.company.Utility;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class WordSequence extends ArrayList<WordSave> {

    @Override
    public boolean contains(Object o) {
        AtomicBoolean contains = new AtomicBoolean(false);
        forEach(v -> {
            if (v.getPos() == (((WordSave) o).getPos()))
                contains.set(true);
        });
        return (super.contains(o) && contains.get());
    }

    @Override
    public int indexOf(Object o) {
        AtomicInteger value = new AtomicInteger(-1);
        forEach(v -> {
            if (v.getPos() == (((WordSave) o).getPos()))
                value.set(super.indexOf(v));
        });
        return value.get();
    }
}
