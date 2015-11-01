package com.com.nfor;

import com.com.nfor.NForWhile.NForWhileCondition;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NForWhileTermTest {

    private static final Integer BASE = 0;
    private static final Integer LOWER = -1;
    private static final Integer SAME = 0;
    private static final Integer HIGHER = 1;

    @Test
    public void testConditionNoCondition() throws Exception {

        NForWhileCondition condition = NForWhileCondition.NO_CONDITION;
        NForWhile.NForWhileTerm<Integer> term = new NForWhile.NForWhileTerm(condition, null);
        assertTrue("Condition was incorrect for a lower value", term.isMetFor(LOWER));
        assertTrue("Condition was incorrect for the same value", term.isMetFor(SAME));
        assertTrue("Condition was incorrect for a higher value", term.isMetFor(HIGHER));
    }

    @Test
    public void testConditionNotEqual() throws Exception {

        NForWhileCondition condition = NForWhileCondition.NOT_EQUAL_TO;
        NForWhile.NForWhileTerm<Integer> term = new NForWhile.NForWhileTerm(condition, BASE);
        assertTrue("Condition was incorrect for a lower value", term.isMetFor(LOWER));
        assertFalse("Condition was incorrect for the same value", term.isMetFor(SAME));
        assertTrue("Condition was incorrect for a higher value", term.isMetFor(HIGHER));
    }

    @Test
    public void testConditionLessThan() throws Exception {

        NForWhileCondition condition = NForWhileCondition.LESS_THAN;
        NForWhile.NForWhileTerm<Integer> term = new NForWhile.NForWhileTerm(condition, BASE);
        assertTrue("Condition was incorrect for a lower value", term.isMetFor(LOWER));
        assertFalse("Condition was incorrect for the same value", term.isMetFor(SAME));
        assertFalse("Condition was incorrect for a higher value", term.isMetFor(HIGHER));
    }

    @Test
    public void testConditionLessThanOrEqual() throws Exception {

        NForWhileCondition condition = NForWhileCondition.LESS_THAN_OR_EQUAL_TO;
        NForWhile.NForWhileTerm<Integer> term = new NForWhile.NForWhileTerm(condition, BASE);
        assertTrue("Condition was incorrect for a lower value", term.isMetFor(LOWER));
        assertTrue("Condition was incorrect for the same value", term.isMetFor(SAME));
        assertFalse("Condition was incorrect for a higher value", term.isMetFor(HIGHER));
    }

    @Test
    public void testConditionEqual() throws Exception {

        NForWhileCondition condition = NForWhileCondition.EQUAL_TO;
        NForWhile.NForWhileTerm<Integer> term = new NForWhile.NForWhileTerm(condition, BASE);
        assertFalse("Condition was incorrect for a lower value", term.isMetFor(LOWER));
        assertTrue("Condition was incorrect for the same value", term.isMetFor(SAME));
        assertFalse("Condition was incorrect for a higher value", term.isMetFor(HIGHER));
    }

    @Test
    public void testConditionGreaterThanOrEqual() throws Exception {

        NForWhileCondition condition = NForWhileCondition.GREATER_THAN_OR_EQUAL_TO;
        NForWhile.NForWhileTerm<Integer> term = new NForWhile.NForWhileTerm(condition, BASE);
        assertFalse("Condition was incorrect for a lower value", term.isMetFor(LOWER));
        assertTrue("Condition was incorrect for the same value", term.isMetFor(SAME));
        assertTrue("Condition was incorrect for a higher value", term.isMetFor(HIGHER));
    }

    @Test
    public void testConditionGreaterThan() throws Exception {

        NForWhileCondition condition = NForWhileCondition.GREATER_THAN;
        NForWhile.NForWhileTerm<Integer> term = new NForWhile.NForWhileTerm(condition, BASE);
        assertFalse("Condition was incorrect for a lower value", term.isMetFor(LOWER));
        assertFalse("Condition was incorrect for the same value", term.isMetFor(SAME));
        assertTrue("Condition was incorrect for a higher value", term.isMetFor(HIGHER));
    }

}
