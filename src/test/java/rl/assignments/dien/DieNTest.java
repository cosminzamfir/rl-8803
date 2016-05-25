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
	}
}
