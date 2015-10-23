package com.com.nfor;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class NFor<T extends Number & Comparable<T>> extends NForPart<T> implements Iterable<T[]> {

    private NForWhile<T> to;

    NFor(NForPart<T> part, NForWhile<T> to) {
        this.clazz = part.clazz;
        this.n = part.n;
        this.from = part.from;
        this.by = part.by;

        this.to = to;
    }

    public static final <U extends Number & Comparable<U>> NForWhile.NForWhileTerm<U> noCondition(U value) {
        return new NForWhile.NForWhileTerm<U>(NForWhile.NForWhileCondition.NO_CONDITION, value);
    }

    public static final <U extends Number & Comparable<U>> NForWhile.NForWhileTerm<U> notEqualTo(U value) {
        return new NForWhile.NForWhileTerm<U>(NForWhile.NForWhileCondition.NOT_EQUAL_TO, value);
    }

    public static final <U extends Number & Comparable<U>> NForWhile.NForWhileTerm<U> lessThan(U value) {
        return new NForWhile.NForWhileTerm<U>(NForWhile.NForWhileCondition.LESS_THAN, value);
    }

    public static final <U extends Number & Comparable<U>> NForWhile.NForWhileTerm<U> lessThanOrEqualTo(U value) {
        return new NForWhile.NForWhileTerm<U>(NForWhile.NForWhileCondition.LESS_THAN_OR_EQUAL, value);
    }

    public static final <U extends Number & Comparable<U>> NForWhile.NForWhileTerm<U> equalTo(U value) {
        return new NForWhile.NForWhileTerm<U>(NForWhile.NForWhileCondition.EQUAL_TO, value);
    }

    public static final <U extends Number & Comparable<U>> NForWhile.NForWhileTerm<U> greaterThanOrEqualTo(U value) {
        return new NForWhile.NForWhileTerm<U>(NForWhile.NForWhileCondition.GREATER_THAN_OR_EQUAL, value);
    }

    public static final <U extends Number & Comparable<U>> NForWhile.NForWhileTerm<U> greaterThan(U value) {
        return new NForWhile.NForWhileTerm<U>(NForWhile.NForWhileCondition.GREATER_THAN, value);
    }

    @Override
    public Iterator<T[]> iterator() {
        return new NForIterator();
    }

    final class NForIterator implements Iterator<T[]> {

        private T[] indices;
        private T[] next;
        private boolean started = false;
        private int lastValidLoop = n;

        NForIterator() {
        }

        @Override
        public boolean hasNext() {
            try {
                this.next = doNext();
            } catch (NoSuchElementException nsee) {
                return false;
            }
            return next != null;
        }

        @Override
        public T[] next() {
            // Use next if it has already been calculated
            if (this.next != null) {
                this.indices = next.clone();
                // Set next as null to avoid confusion for next iteration
                this.next = null;
                return indices;
            } else {
                // Otherwise use doNext directly
                this.indices = doNext();
                if (this.indices == null) {
                    throw new NoSuchElementException();
                }
                // Set next as null to avoid confusion for next iteration
                this.next = null;
                return indices;
            }
        }

        private T[] doNext() {
            if (!started) {
                started = true;
                // Reduce n to first match
                for (int i = 0; i < n; i++) {
                    if (!to.get()[i].isMetFor(from.get()[i])) {
                        lastValidLoop = i;
                        break;
                    }
                }
                if (IntStream.range(0, n).allMatch(i -> !to.get()[i].isMetFor(from.get()[i]))) {
                    // Initial term is not valid, propogate exception
                    throw new NoSuchElementException();
                }
                // Set the indices using from.get()
                reset();
                return indices;
            }
            T[] indicesCopy = indices.clone();
            int whereToStart = n - 1;
            for (int i = lastValidLoop - 1; i >= 0; i--) {
                NForWhile.NForWhileTerm term = to.get()[i];
                if (!term.isMetFor(indicesCopy[i])) {
                    whereToStart = i;
                }
            }
            for (int i = whereToStart; i >= 0; i--) {
                NForWhile.NForWhileTerm term = to.get()[i];
                if (!term.isMetFor(indicesCopy[i])) {
                    continue;
                } else if (term.isMetFor(add(indicesCopy[i], by.get()[i]))) {
                    indicesCopy[i] = add(indicesCopy[i], by.get()[i]);
                    return indicesCopy;
                } else {
                    indicesCopy[i] = from.get()[i];
                }
            }
            return null;
        }

        private void reset() {
            this.indices = from.get();
        }

        private T add(T a, T b) {
            if (a instanceof Byte) {
                Byte sum = (byte) (a.byteValue() + b.byteValue());
                return (T) sum;
            } else if (a instanceof Short) {
                Short sum = (short) (a.shortValue() + b.shortValue());
                return (T) sum;
            } else if (a instanceof Integer) {
                Integer sum = (int) (a.intValue() + b.intValue());
                return (T) sum;
            } else if (a instanceof Long) {
                Long sum = (long) (a.longValue() + b.longValue());
                return (T) sum;
            } else if (a instanceof Float) {
                Float sum = (float) (a.floatValue() + b.floatValue());
                return (T) sum;
            } else if (a instanceof Double) {
                Double sum = (double) (a.doubleValue() + b.doubleValue());
                return (T) sum;
            } else { // Attempt to resolve as double; this is not always possible.
                Double sum = (double) (a.doubleValue() + b.doubleValue());
                return (T) sum;
            }
        }

    }
}