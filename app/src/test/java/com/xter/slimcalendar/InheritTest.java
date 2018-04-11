package com.xter.slimcalendar;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by XTER on 2018/3/30.
 */

public class InheritTest {


	@Test
	public void testIn(){
		Parent p = new Parent();
		Sun s = new Sun();
		p.setData(5);
	}

	class Parent{
		int[] data;
		Parent(){
		}

		private void setData(int max){
			data = new int[max];
			for(int i=0;i<max;i++){
				data[i] = i;
			}
			change(data);
		}

		protected void change(int[] data){

		}
	}

	class Sun extends Parent{
		@Override
		protected void change(int[] data) {
			System.out.print(Arrays.toString(data));
		}

		@Override
		public String toString() {
			return super.data.toString();
		}
	}
}
