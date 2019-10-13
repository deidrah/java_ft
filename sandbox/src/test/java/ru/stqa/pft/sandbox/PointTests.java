package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testAreaOne() {
        Point p1 = new Point(10, 12);
        Point p2 = new Point(14, 2);
        Assert.assertEquals(p1.distance(p2), 10.770329614269007);
    }
    @Test
    public void testAreaTwo() {
        Point p1 = new Point(8, 14);
        Point p2 = new Point(16, 1);
        Assert.assertEquals(p1.distance(p2), 15.264337522473747);
    }
    @Test
    public void testAreaThree() {
        Point p1 = new Point(15, 11);
        Point p2 = new Point(12, 52);
        Assert.assertEquals(p1.distance(p2), 41.10960958218893);
    }
}
