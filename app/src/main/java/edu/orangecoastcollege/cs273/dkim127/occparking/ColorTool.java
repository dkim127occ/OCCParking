package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.support.annotation.IntDef;

public class ColorTool
{
    @IntDef({ADAMS, A, B, C, D, E, G})
    public @interface ColorCode {}
    public static final int ADAMS = 0xf5ff00;
    public static final int A = 0xf100ff;
    public static final int B = 0x00ff59;
    public static final int C = 0xffaf60;
    public static final int D = 0x60b8ff;
    public static final int E = 0x00ffe9;
    public static final int G = 0x765bff;

    public static boolean closeMatch(@ColorCode int fixed, int color, long tolerance)
    {
        if ((int) Math.abs(fixed - color) > tolerance)
            return false;
        return true;
    }
}
