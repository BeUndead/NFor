package com.com.nfor;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

public class NForByTest {

    @Test
    public void testByForByte() {
        NForBy<Byte> nForBy = new NForBy<Byte>((byte) 1, (byte) 2, (byte) 3);
        Byte[] retrieved = nForBy.get();
        Byte[] expected = new Byte[] {(byte) 1, (byte) 2, (byte) 3};
        Assert.assertEquals("Unexpected length", expected.length, nForBy.get().length);
        IntStream.range(0, expected.length).forEach(
                i -> Assert.assertEquals(String.format("Unexpected value for index %d", i),
                                         expected[i], retrieved[i]));
    }

    @Test
    public void testByForShort() {
        NForBy<Short> nForBy = new NForBy<Short>((short) 1, (short) 2, (short) 3);
        Short[] retrieved = nForBy.get();
        Short[] expected = new Short[] {(short) 1, (short) 2, (short) 3};
        Assert.assertEquals("Unexpected length", expected.length, nForBy.get().length);
        IntStream.range(0, expected.length).forEach(
                i -> Assert.assertEquals(String.format("Unexpected value for index %d", i),
                                         expected[i], retrieved[i]));
    }

    @Test
    public void testByForInteger() {
        NForBy<Integer> nForBy = new NForBy<Integer>(1, 2, 3);
        Integer[] retrieved = nForBy.get();
        Integer[] expected = new Integer[] {1, 2, 3};
        Assert.assertEquals("Unexpected length", expected.length, nForBy.get().length);
        IntStream.range(0, expected.length).forEach(
                i -> Assert.assertEquals(String.format("Unexpected value for index %d", i),
                                         expected[i], retrieved[i]));
    }

    @Test
    public void testByForLong() {
        NForBy<Long> nForBy = new NForBy<Long>(1L, 2L, 3L);
        Long[] retrieved = nForBy.get();
        Long[] expected = new Long[] {1L, 2L, 3L};
        Assert.assertEquals("Unexpected length", expected.length, nForBy.get().length);
        IntStream.range(0, expected.length).forEach(
                i -> Assert.assertEquals(String.format("Unexpected value for index %d", i),
                                         expected[i], retrieved[i]));
    }

    @Test
    public void testByForFloat() {
        NForBy<Float> nForBy = new NForBy<Float>(1F, 2F, 3F);
        Float[] retrieved = nForBy.get();
        Float[] expected = new Float[] {1F, 2F, 3F};
        Assert.assertEquals("Unexpected length", expected.length, nForBy.get().length);
        IntStream.range(0, expected.length).forEach(
                i -> Assert.assertEquals(String.format("Unexpected value for index %d", i),
                                         expected[i], retrieved[i]));
    }

    @Test
    public void testByForDouble() {
        NForBy<Double> nForBy = new NForBy<Double>(1D, 2D, 3D);
        Double[] retrieved = nForBy.get();
        Double[] expected = new Double[] {1D, 2D, 3D};
        Assert.assertEquals("Unexpected length", expected.length, nForBy.get().length);
        IntStream.range(0, expected.length).forEach(
                i -> Assert.assertEquals(String.format("Unexpected value for index %d", i),
                                         expected[i], retrieved[i]));
    }

    @Test
    public void testBySize() {
        NForBy<Integer> nForBy = new NForBy<Integer>(1, 2, 3);
        Assert.assertEquals("Unexpected length", 3, nForBy.size());
    }

}
