package com.example.quizzapp.models;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomizingIterator<T> implements Iterator<T> {
    private Iterator<Integer> positions;
    private List<T> list;
    public RandomizingIterator(List<T> iteratedList) {
        Random r = new Random();
        this.list = iteratedList;
        Set<Integer> indexSet = new LinkedHashSet<>();
        while(indexSet.size() != iteratedList.size())
            indexSet.add(r.nextInt(iteratedList.size()));

        positions = indexSet.iterator();
    }

    @Override
    public boolean hasNext() {
        return positions.hasNext();
    }

    @Override
    public T next() {
        return list.get(positions.next());
    }
}
