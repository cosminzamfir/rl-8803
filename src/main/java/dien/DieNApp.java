package dien;

import java.text.NumberFormat;
import java.util.List;

import burlap.behavior.singleagent.planning.stochastic.valueiteration.ValueIteration;
import burlap.behavior.valuefunction.QValue;
import burlap.oomdp.core.Domain;
import burlap.oomdp.core.states.State;
import burlap.oomdp.statehashing.SimpleHashableStateFactory;

public class DieNApp {

	public void run(int N, int... badSides) {
		System.out.println("Params: N = " + N + "; badSides = " + toString(badSides));
		if(badSides.length != N ) {
			throw new RuntimeException("Wrong command line params. The basSides array must have the same size as N");
		}
		DieNDomain generator = new DieNDomain(N, badSides);
		Domain domain = generator.generateDomain();
		DieNReward rf = new DieNReward();
		DieNTerminalState tf = new DieNTerminalState();
		State initialState = generator.createInitialState(domain);
		ValueIteration vi = new ValueIteration(domain, rf, tf, 1, new SimpleHashableStateFactory(), 1, 100);
		vi.toggleReachabiltiyTerminalStatePruning(true);
		vi.planFromState(initialState);
		List<QValue> l = vi.getQs(initialState);
		for (QValue qValue : l) {
			System.out.println("Q-Value for " + qValue.a + " = " + qValue.q);
		}
		System.out.println("=========================");

	}
	
	public String toString(int[] array) {
		StringBuilder sb = new StringBuilder("[");
		for (int d : array) {
			sb.append(NumberFormat.getInstance().format(d)).append(" ");
		}
		return sb.toString() + "]";
	}

}
