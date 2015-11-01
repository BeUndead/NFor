package com.com.nfor;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

public class NForByTest {

    @Test
    public void testByForByte() {
        NForBy<Byte> nForBy = new NForBy<>((byte) 1, (byte) 2, (byte) 3);
        Byte[] retrieved = nForBy.get();
        Byte[] expected = new Byte[] {(byte) 1, (byte) 2, (byte) 3};
        Assert.assertEquals("Unexpected length", expected.length, nForBy.get().length);
        IntStream.range(0, expected.length).forEach(
                i -> Assert.assertEquals(String.format("Unexpected value for index %d", i),
                                         expected[i], retrieved[i]));
    }

    @Test
    public void testByForShort() {
        NForBy<Short> nForBy = new NForBy<>((short) 1, (short) 2, (short) 3);
        Short[] retrieved = nForBy.get();
        Short[] expected = new Short[] {(short) 1, (short) 2, (short) 3};
        Assert.assertEquals("Unexpected length", expected.length, nForBy.get().length);
        IntStream.range(0, expected.length).forEach(
                i -> Assert.assertEquals(String.format("Unexpected value for index %d", i),
                                         expected[i], retrieved[i]));
    }

    @Test
    public void testByForInteger() {
        NForBy<Integer> nForBy = new NForBy<>(1, 2, 3);
        Integer[] retrieved = nForBy.get();
        Integer[] expected = new Integer[] {1, 2, 3};
        Assert.assertEquals("Unexpected length", expected.length, nForBy.get().length);
        IntStream.range(0, expected.length).forEach(
                i -> Assert.assertEquals(String.format("Unexpected value for index %d", i),
                                         expected[i], retrieved[i]));
    }

    @Test
    public void testByForLong() {
        NForBy<Long> nForBy = new NForBy<>(1l, 2l, 3l);
        Long[] retrieved = nForBy.get();
        Long[] expected = new Long[] {1l, 2l, 3l};
        Assert.assertEquals("Unexpected length", expected.length, nForBy.get().length);
        IntStream.range(0, expected.length).forEach(
                i -> Assert.assertEquals(String.format("Unexpected value for index %d", i),
                                         expected[i], retrieved[i]));
    }

    @Test
    public void testByForFloat() {
        NForBy<Float> nForBy = new NForBy<>(1f, 2f, 3f);
        Float[] retrieved = nForBy.get();
        Float[] expected = new Float[] {1f, 2f, 3f};
        Assert.assertEquals("Unexpected length", expected.length, nForBy.get().length);
        IntStream.range(0, expected.length).forEach(
                i -> Assert.assertEquals(String.format("Unexpected value for index %d", i),
                                         expected[i], retrieved[i]));
    }

    @Test
    public void testByForDouble() {
        NForBy<Double> nForBy = new NForBy<>(1d, 2d, 3d);
        Double[] retrieved = nForBy.get();
        Double[] expected = new Double[] {1d, 2d, 3d};
        Assert.assertEquals("Unexpected length", expected.length, nForBy.get().length);
        IntStream.range(0, expected.length).forEach(
                i -> Assert.assertEquals(String.format("Unexpected value for index %d", i),
                                         expected[i], retrieved[i]));
    }

    @Test
    public void testBySize() {
        NForBy<Integer> nForBy = new NForBy<>(1, 2, 3);
        Assert.assertEquals("Unexpected length", 3, nForBy.size());
    }

}
