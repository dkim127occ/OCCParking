package edu.orangecoastcollege.cs273.dkim127.occparking;

import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class ColorToolUnitTest
{
    public static final int ADAMS = 0xf5ff00;
    public static final int A = 0xf100f0;
    public static final int B = 0x00ff50;
    public static final int C = 0xffaf60;
    public static final int D = 0x60b8f0;
    public static final int E = 0x00ffe0;
    public static final int G = 0x765bf0;

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {

    }

    @Test
    public void testCloseMatchAdams()
    {
        int color = ADAMS + (int) (Math.random() * 100) % 16;
        assertTrue(ColorTool.closeMatch(ColorTool.ADAMS, color, 0xf));
    }

    @Test
    public void testCloseMatchA()
    {
        int color = A + (int) (Math.random() * 100) % 16;
        assertTrue(ColorTool.closeMatch(ColorTool.A, color, 0xf));
    }

    @Test
    public void testCloseMatchB()
    {
        int color = B + (int) (Math.random() * 100) % 16;
        assertTrue(ColorTool.closeMatch(ColorTool.B, color, 0xf));
    }

    @Test
    public void testCloseMatchC()
    {
        int color = C + (int) (Math.random() * 100) % 16;
        assertTrue(ColorTool.closeMatch(ColorTool.C, color, 0xf));
    }

    @Test
    public void testCloseMatchD()
    {
        int color = D + (int) (Math.random() * 100) % 16;
        assertTrue(ColorTool.closeMatch(ColorTool.D, color, 0xf));
    }

    @Test
    public void testCloseMatchE()
    {
        int color = E + (int) (Math.random() * 100) % 16;
        assertTrue(ColorTool.closeMatch(ColorTool.E, color, 0xf));
    }

    @Test
    public void testCloseMatchG()
    {
        int color = G + (int) (Math.random() * 100) % 16;
        assertTrue(ColorTool.closeMatch(ColorTool.G, color, 0xf));
    }
}
