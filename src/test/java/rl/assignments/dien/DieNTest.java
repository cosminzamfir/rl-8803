package rl.assignments.dien;

import static org.junit.Assert.*;

import org.junit.Test;

import dien.DieNApp;

public class DieNTest {

	@Test
	public void test() throws Exception {
				new DieNApp().run(4,0,1,1,1);
				new DieNApp().run(8,1,0,1,0,1,1,1,0);
				new DieNApp().run(6,1,0,1,1,0,1);
		
		System.out.println("======================================================================================");
		
		new DieNApp().run(15, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1);
		new DieNApp().run(28, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1);
		new DieNApp().run(17, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0);
		new DieNApp().run(25, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0);
		new DieNApp().run(22, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0);
		new DieNApp().run(12, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1);
		new DieNApp().run(11, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1);
		new DieNApp().run(27, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0);
		new DieNApp().run(16, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1);
		new DieNApp().run(26, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1);
	}
}
