package com.com.nfor;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.com.nfor.NFor.greaterThan;
import static com.com.nfor.NFor.greaterThanOrEqualTo;
import static com.com.nfor.NFor.lessThan;
import static com.com.nfor.NFor.notEqualTo;

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

}
