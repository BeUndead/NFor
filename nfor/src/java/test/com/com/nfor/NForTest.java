package com.com.nfor;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static com.com.nfor.NFor.*;

public class NForTest {

    @Test
    public void testNForIndicesMatchThoseOfActualForLoop() throws Exception {
        List<Integer[]> fromActualLoopList = new ArrayList<>();
        for (Integer i = -5; i < 3; i++) {
            for (Integer j = 0; j >= -6; j--) {
                for (Integer k = 4; k != 10; k += 2) {
                    for (Integer l = -1; l > -9; l -= 3) {
                        fromActualLoopList.add(new Integer[] {i, j, k, l});
                    }
                }
            }
        }

        List<Integer[]> fromNForLoopList = new ArrayList<>();
        NFor<Integer> nfor = NFor.of(Integer.class).from(-5, 0, 4, -1).by(1, -1, 2, -3)
                .to(lessThan(3), greaterThanOrEqualTo(-6), notEqualTo(10), greaterThan(-9));
        for (Integer[] indices : nfor) {
            fromNForLoopList.add(indices);
        }

        Assert.assertEquals("Lengths did not match", fromActualLoopList.size(), fromNForLoopList.size());
        Assert.assertArrayEquals("Values did not match",
                                 fromActualLoopList.toArray(), fromNForLoopList.toArray());
    }

    @Test
    public void testNForWithOtherClass() throws Exception {
        NFor<Double> nfor = NFor.of(Double.class).from(0d, 0d, 0d).by(.5d, .5d, .5d)
                .to(lessThanOrEqualTo(3d), lessThanOrEqualTo(3d), lessThanOrEqualTo(3d));
        int loops = 0;
        for (Double[] indices : nfor) {
            loops++;
        }
        Assert.assertEquals("Unexpected number of loops", 7 * 7 * 7, loops);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNForWithIllegalOfArgument() throws Exception {
        NFor.of(BigInteger.class);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNForWithIllegalFirstArgument() throws Exception {
        NFor.of(Integer.class).from(0, 0).by(1, 1, 1).to(3, 3, 3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNForWithIllegalSecondArgument() throws Exception {
        NFor.of(Integer.class).from(0, 0, 0).by(1, 1).to(3, 3, 3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNForWithIllegalThirdArgument() throws Exception {
        NFor.of(Integer.class).from(0, 0, 0).by(1, 1, 1).to(3, 3);
    }

    @Test
    public void testInfiniteLoop() throws Exception {
        int BREAK_POINT = 10000;
        NFor<Integer> nfor = NFor.of(Integer.class).from(0, 0).by(1, 1).to(noCondition(), lessThanOrEqualTo(1));
        int counter = 0;
        for (Integer[] indices : nfor) {
            if (++counter == BREAK_POINT) {
                break;
            }
        }
        Assert.assertEquals("Looped for less than expected amount", BREAK_POINT, counter);
    }

    @Test
    public void testEmptyLoop() throws Exception {
        NFor<Integer> nfor = NFor.of(Integer.class).from(0, 0).by(1, 1).to(lessThan(-1), lessThan(-1));
        int counter = 0;
        for (Integer[] indices : nfor) {
            counter++;
        }
        Assert.assertEquals("Looped in empty loop", 0, counter);
    }

    @Test
    public void testEmptyLoopWithNonEmptyInnerLoop() throws Exception {
        NFor<Integer> nfor = NFor.of(Integer.class).from(0, 0).by(1, 1).to(lessThan(-1), lessThan(2));
        int counter = 0;
        for (Integer[] indices : nfor) {
            counter++;
        }
        Assert.assertEquals("Looped in empty loop", 0, counter);
    }

}
