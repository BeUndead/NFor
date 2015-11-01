package com.com.nfor;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides a simple {@link Iterable} {@link Object} to simplify the construction of {@code n}-nested {@code for}-loops.
 * The usage is as follows:
 * <pre>
 *     {@code
 *     NFor<Integer> nfor = NFor.of(Integer.class)
 *             .from(0, 0, 0)
 *             .by(1, 1, 1)
 *             .to(3, 3, 3);
 *
 *     for (Integer[] indices : nfor) {
 *         // Do stuff
 *     }}
 * </pre>
 * This would then provide the same iteration as:
 * <pre>
 *     {@code
 *     for (int i = 0; i < 3; i++) {
 *         for (int j = 0; j < 3; j++) {
 *             for (int k = 0; k < 3; k++) {
 *                 // Do stuff
 *             }
 *         }
 *     }}
 * </pre>
 * <p>
 * For more complex looping, additional methods are provided.  For example, in order to perform an equivalent loop to
 * the following:
 * <pre>
 *     {@code
 *     for (int i = 1; i >= -5; i -= 2) {
 *         for (int j = 5; j != 10; j++) {
 *             for (int k = 10; ; k++) {
 *                 // Do stuff
 *             }
 *         }
 *     }}
 * </pre>
 * the following can be used (with {@code import static NFor.*} for utility methods):
 * <pre>
 *     {@code
 *     NFor<Integer> nfor = NFor.of(Integer.class)
 *             .from(1, 5, 10)
 *             .by(-2, 1, 0)
 *             .to(greaterThanOrEqualTo(-5), notEqualTo(10), noCondition());
 *
 *     for (Integer[] indices : nfor) {
 *         // Do stuff
 *     }}
 * </pre>
 *
 * @param <T> The {@link java.lang.reflect.Array}-{@link java.lang.reflect.Type} which the instanced {@link NFor} will
 *            be iterating over.
 */
public final class NFor<T extends Number & Comparable<T>> extends NForPart<T> implements Iterable<T[]> {

    private final NForWhile<T> to;
    private final NForIterator iterator;

    NFor(NForPart<T> part, NForWhile<T> to) {
        this.clazz = part.clazz;
        this.n = part.n;
        this.from = part.from;
        this.by = part.by;
        this.to = to;
        verify();
        this.iterator = new NForIterator();
    }

    private void verify() {
        if (n < 1 || from.size() != n || by.size() != n || to.size() != n) {
            throw new IllegalArgumentException();
        }
    }

    public static final <U extends Number & Comparable<U>> NForWhile.NForWhileTerm<U> noCondition() {
        return new NForWhile.NForWhileTerm<>(NForWhile.NForWhileCondition.NO_CONDITION, null);
    }

    public static final <U extends Number & Comparable<U>> NForWhile.NForWhileTerm<U> notEqualTo(U value) {
        return new NForWhile.NForWhileTerm<>(NForWhile.NForWhileCondition.NOT_EQUAL_TO, value);
    }

    public static final <U extends Number & Comparable<U>> NForWhile.NForWhileTerm<U> lessThan(U value) {
        return new NForWhile.NForWhileTerm<>(NForWhile.NForWhileCondition.LESS_THAN, value);
    }

    public static final <U extends Number & Comparable<U>> NForWhile.NForWhileTerm<U> lessThanOrEqualTo(U value) {
        return new NForWhile.NForWhileTerm<>(NForWhile.NForWhileCondition.LESS_THAN_OR_EQUAL_TO, value);
    }

    public static final <U extends Number & Comparable<U>> NForWhile.NForWhileTerm<U> equalTo(U value) {
        return new NForWhile.NForWhileTerm<>(NForWhile.NForWhileCondition.EQUAL_TO, value);
    }

    public static final <U extends Number & Comparable<U>> NForWhile.NForWhileTerm<U> greaterThanOrEqualTo(U value) {
        return new NForWhile.NForWhileTerm<>(NForWhile.NForWhileCondition.GREATER_THAN_OR_EQUAL_TO, value);
    }

    public static final <U extends Number & Comparable<U>> NForWhile.NForWhileTerm<U> greaterThan(U value) {
        return new NForWhile.NForWhileTerm<>(NForWhile.NForWhileCondition.GREATER_THAN, value);
    }

    @Override
    public Iterator<T[]> iterator() {
        return iterator;
    }

    final class NForIterator implements Iterator<T[]> {

        private T[] indices;
        private T[] next;
        private boolean started = false;
        private int firstEmptyLoop = n;

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
            // If next has already been set; use, reset it and return
            if (next != null) {
                indices = next.clone();
                next = null;
                return indices;
            } else {
                // Otherwise, call doNext directly
                indices = doNext();
                return indices;
            }
        }

        private T[] doNext() {
            // If this is the first call, we need to do some initial checks
            if (!started) {
                started = true;
                // Check for the first empty loop
                for (int i = 0; i < n; i++) {
                    NForWhile.NForWhileTerm<T> term = to.get()[i];
                    if (!term.isMetFor(from.get()[i])) {
                        firstEmptyLoop = i;
                        break;
                    }
                }
                // If the first loop is empty, immediately break
                if (firstEmptyLoop == 0) {
                    throw new NoSuchElementException();
                }

                // At this point, first loop is non-empty, and firstEmptyLoop is set
                // Set indices to their initial values
                reset();
                return indices;
            }

            // If it is not the first iteration, then compute the next values using a copy
            T[] indicesCopy = indices.clone();
            for (int i = firstEmptyLoop - 1; i >= 0; i--) {
                NForWhile.NForWhileTerm<T> term = to.get()[i];
                // If we can advance this term, do so and return
                if (term.isMetFor(add(indicesCopy[i], by.get()[i]))) {
                    indicesCopy[i] = add(indicesCopy[i], by.get()[i]);
                    return indicesCopy;
                } else {
                    // Otherwise, we must reset this index and continue
                    indicesCopy[i] = from.get()[i];
                }
            }
            // If we reach here, we have reset all indices, without finding one we can advance
            // This means we are done looping
            throw new NoSuchElementException();
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
                Integer sum = a.intValue() + b.intValue();
                return (T) sum;
            } else if (a instanceof Long) {
                Long sum = a.longValue() + b.longValue();
                return (T) sum;
            } else if (a instanceof Float) {
                Float sum = a.floatValue() + b.floatValue();
                return (T) sum;
            } else if (a instanceof Double) {
                Double sum = a.doubleValue() + b.doubleValue();
                return (T) sum;
            } else { // Attempt to resolve as double; this is not always possible.
                Double sum = a.doubleValue() + b.doubleValue();
                return (T) sum;
            }
        }

    }

}
