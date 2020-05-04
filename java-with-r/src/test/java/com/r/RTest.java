package com.r;

import org.junit.Test;
import org.rosuda.JRI.REXP;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class RTest {

	@Test
	public void evalCmdPythagorasTest() {
		// R command to calculate a pythagoras
		final String command = "c <- sqrt(2^2 + 2^2);";
		// Result for the pythagoras calculation
		final double expected = 2.8284271247461903;
		// R expression containing the result
		final REXP rexp = RUtil.evaluate(command);
		assertEquals(expected, rexp.asDouble(), 0);
	}

	@Test
	public void evalCommandTest() {
		final double expected = 3.5;
		REXP rexp = RUtil.evaluate("mean(1:6)");
		assertEquals(expected, rexp.asDouble(), 0);
	}

	@Test
	public void evalResourceCommandTest() throws IOException {
		final String expected = "Hello R World";
		InputStream inputStream = RUtil.class.getResourceAsStream("/helloWorld.R");
		List<REXP> results = RUtil.evaluate(inputStream);
		results.forEach(rexp -> assertEquals(expected, rexp.asString()));
	}

	@Test
	public void evalVectorTest() {
		REXP rexp = RUtil.evaluate("iris");
		assertTrue(rexp.asVector().getNames().size() > 0);
	}

	@Test
	public void evalRListTest() {
		String[] expected = { "a", "b", "c" };
		REXP result = RUtil.evaluate("pairlist(a=1,b='foo',c=1:5)");
		assertArrayEquals(expected, result.asList().keys());
	}

	@Test
	public void evalSQRTTest() {
		Double expected = 6.0;
		REXP result = RUtil.evaluate("sqrt(36)");
		assertEquals(expected, result.asDouble(), 0);
	}

	@Test
	public void evalResourceGraphPNGTest() throws IOException {
		InputStream inputStream = RUtil.class.getResourceAsStream("/cars_trucks.R");
		List<REXP> evaluation = RUtil.evaluate(inputStream);

		Optional<REXP> firstEval = evaluation.stream().findFirst();
		String currentDir = firstEval.get().asString();

		File pngFile = new File(currentDir, "cars_trucks.png");
		assertTrue(pngFile.exists() && pngFile.length() > 0);

		pngFile.deleteOnExit();
	}

	@Test
	public void evalSQLiteTest() throws IOException {
		String expected = "[STRING* (\"Hello\", \"World\")]";
		InputStream inputStream = RUtil.class.getResourceAsStream("/sqlite.R");
		List<REXP> results = RUtil.evaluate(inputStream);
		REXP lastExp = results.get(results.size() - 1);
		assertEquals(expected, lastExp.asVector().get(0).toString());
	}

	@Test
	public void evalH2Test() throws IOException {
		String expected = "[STRING* (\"Hello\", \"World\")]";
		InputStream inputStream = RUtil.class.getResourceAsStream("/h2.R");
		List<REXP> results = RUtil.evaluate(inputStream);
		REXP lastExp = results.get(results.size() - 1);
		assertEquals(expected, lastExp.asVector().get(0).toString());
	}

}
