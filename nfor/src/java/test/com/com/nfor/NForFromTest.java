package com.com.nfor;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

public class NForFromTest {

    @Test
    public void testFromForByte() {
        NForFrom<Byte> nForFrom = new NForFrom<>((byte) 1, (byte) 2, (byte) 3);
        Byte[] retrieved = nForFrom.get();
        Byte[] expected = new Byte[] {(byte) 1, (byte) 2, (byte) 3};
        Assert.assertEquals("Unexpected length", expected.length, nForFrom.get().length);
        IntStream.range(0, expected.length).forEach(
                i -> Assert.assertEquals(String.format("Unexpected value for index %d", i),
                                         expected[i], retrieved[i]));
    }

    @Test
    public void testFromForShort() {
        NForFrom<Short> nForFrom = new NForFrom<>((short) 1, (short) 2, (short) 3);
        Short[] retrieved = nForFrom.get();
        Short[] expected = new Short[] {(short) 1, (short) 2, (short) 3};
        Assert.assertEquals("Unexpected length", expected.length, nForFrom.get().length);
        IntStream.range(0, expected.length).forEach(
                i -> Assert.assertEquals(String.format("Unexpected value for index %d", i),
                                         expected[i], retrieved[i]));
    }

    @Test
    public void testFromForInteger() {
        NForFrom<Integer> nForFrom = new NForFrom<>(1, 2, 3);
        Integer[] retrieved = nForFrom.get();
        Integer[] expected = new Integer[] {1, 2, 3};
        Assert.assertEquals("Unexpected length", expected.length, nForFrom.get().length);
        IntStream.range(0, expected.length).forEach(
                i -> Assert.assertEquals(String.format("Unexpected value for index %d", i),
                                         expected[i], retrieved[i]));
    }

    @Test
    public void testFromForLong() {
        NForFrom<Long> nForFrom = new NForFrom<>(1L, 2L, 3L);
        Long[] retrieved = nForFrom.get();
        Long[] expected = new Long[] {1L, 2L, 3L};
        Assert.assertEquals("Unexpected length", expected.length, nForFrom.get().length);
        IntStream.range(0, expected.length).forEach(
                i -> Assert.assertEquals(String.format("Unexpected value for index %d", i),
                                         expected[i], retrieved[i]));
    }

    @Test
    public void testFromForFloat() {
        NForFrom<Float> nForFrom = new NForFrom<>(1f, 2f, 3f);
        Float[] retrieved = nForFrom.get();
        Float[] expected = new Float[] {1f, 2f, 3f};
        Assert.assertEquals("Unexpected length", expected.length, nForFrom.get().length);
        IntStream.range(0, expected.length).forEach(
                i -> Assert.assertEquals(String.format("Unexpected value for index %d", i),
                                         expected[i], retrieved[i]));
    }

    @Test
    public void testFromForDouble() {
        NForFrom<Double> nForFrom = new NForFrom<>(1d, 2d, 3d);
        Double[] retrieved = nForFrom.get();
        Double[] expected = new Double[] {1d, 2d, 3d};
        Assert.assertEquals("Unexpected length", expected.length, nForFrom.get().length);
        IntStream.range(0, expected.length).forEach(
                i -> Assert.assertEquals(String.format("Unexpected value for index %d", i),
                                         expected[i], retrieved[i]));
    }

    @Test
    public void testFromSize() {
        NForFrom<Integer> nForFrom = new NForFrom<>(1, 2, 3);
        Assert.assertEquals("Unexpected length", 3, nForFrom.size());
    }

}
