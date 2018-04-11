package com.xter.slimcalendar;

import com.xter.slimcalendar.presentation.util.SolarCalendar;
import com.xter.slimcalendar.presentation.widget.Week;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author XTER
 * @date 2018/3/27.
 */

public class SolarTest {
	@Test
	public void testPrint() {
		System.out.print(System.currentTimeMillis());
	}


	@Test
	public void testSolar() {
		assertEquals(SolarCalendar.dayForTag(1901, 1, 1), Week.TUESDAY);
	}

}
