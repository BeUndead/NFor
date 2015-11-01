package com.com.nfor;

final class NForFrom<T extends Number & Comparable<T>> {

    private final T[] from;

    @SafeVarargs
    NForFrom(final T... from) {
        this.from = from;
    }

    final T[] get() {
        return from;
    }

    final int size() {
        return from.length;
    }

}
