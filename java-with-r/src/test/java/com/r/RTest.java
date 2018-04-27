package com.r;


import org.junit.Test;
import org.rosuda.JRI.REXP;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class RTest
{

    @Test
    public void EvalCmdPythagorasTest() {
        // R command to calculate a pythagoras
        final String command = "c <- sqrt(2^2 + 2^2);";
        // Result for the pythagoras calculation
        final double expected = 2.8284271247461903;
        // R expression containing the result
        final REXP rexp = RUtil.evaluate(command);
        assertEquals(expected , rexp.asDouble(), 0);
    }

    @Test
    public void EvalCommandTest(){
        final double expected = 3.5;
        REXP rexp = RUtil.evaluate("mean(1:6)");
        assertEquals(expected , rexp.asDouble(), 0);
    }

    @Test
    public void EvalResourceCommandTest() throws IOException {
        final String expected = "Hello R World";
        InputStream inputStream = RUtil.class.getResourceAsStream("/helloWorld.R");
        List<REXP> rexps = RUtil.evaluate(inputStream);
        rexps.forEach( rexp -> assertEquals(expected , rexp.asString()) );
    }

    @Test
    public void EvalVectorTest(){
        REXP rexp = RUtil.evaluate("iris");
        assertTrue(rexp.asVector().getNames().size() > 0);
    }

    @Test
    public void EvalRListTest(){
        String [] expected = {"a","b","c"};
        REXP rexp = RUtil.evaluate("pairlist(a=1,b='foo',c=1:5)");
        assertArrayEquals(expected, rexp.asList().keys());
    }

    @Test
    public void EvalSQRTTest(){
        Double expected = 6.0;
        REXP rexp = RUtil.evaluate("sqrt(36)");
        assertEquals(expected, rexp.asDouble(), 0);
    }

}
