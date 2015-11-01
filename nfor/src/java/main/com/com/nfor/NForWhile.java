package com.com.nfor;

final class NForWhile<T extends Number & Comparable<T>> {

    private final NForWhileTerm[] terms;

    NForWhile(final NForWhileTerm... terms) {
        this.terms = terms;
    }

    public int size() {
        return terms.length;
    }

    final NForWhileTerm[] get() {
        return terms;
    }

    protected enum NForWhileCondition {
        NO_CONDITION,
        NOT_EQUAL_TO,
        LESS_THAN,
        LESS_THAN_OR_EQUAL_TO,
        EQUAL_TO,
        GREATER_THAN_OR_EQUAL_TO,
        GREATER_THAN
    }

    protected static final class NForWhileTerm<U extends Number & Comparable<U>> {

        private final NForWhileCondition condition;
        private final U at;

        NForWhileTerm(NForWhileCondition condition, U at) {
            this.condition = condition;
            this.at = at;
        }

        final boolean isMetFor(final U value) {
            switch (condition) {
                case NO_CONDITION:
                    return true;
                case NOT_EQUAL_TO:
                    return value.compareTo(at) != 0;
                case LESS_THAN:
                    return value.compareTo(at) < 0;
                case LESS_THAN_OR_EQUAL_TO:
                    return value.compareTo(at) <= 0;
                case EQUAL_TO:
                    return value.compareTo(at) == 0;
                case GREATER_THAN_OR_EQUAL_TO:
                    return value.compareTo(at) >= 0;
                case GREATER_THAN:
                    return value.compareTo(at) > 0;
                default:
                    return false;
            }
        }

    }

}
