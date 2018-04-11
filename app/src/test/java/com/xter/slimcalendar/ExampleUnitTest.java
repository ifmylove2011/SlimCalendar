package com.xter.slimcalendar;

import android.graphics.Color;

import com.xter.slimcalendar.presentation.util.ColorUtil;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
	@Test
	public void addition_isCorrect() throws Exception {
		assertEquals(4, 2 + 2);
	}

	@Test
	public void testSum(){
		int j =0;
		for(int i=0; i<100; i++){
			j=++j;
		}
		System.out.println(j);
	}

	@Test
	public void testFind(){
		int[] ar = {1,6,7,62,18,20};
		assertEquals(true, Arrays.binarySearch(ar,65)<0);
		assertEquals(0, Arrays.binarySearch(ar,1));
	}

	@Test
	public void testColorTrans(){
		String oldColor = "ffffff";
		System.out.println(ColorUtil.reserve(oldColor));

		int white = Integer.parseInt("ffffff",16);

		System.out.println(white);
		System.out.println(ColorUtil.reserve(white));
	}



}