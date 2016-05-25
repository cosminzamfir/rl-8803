package dien;

import burlap.oomdp.core.TerminalFunction;

import static dien.DieNDomain.*;

import burlap.oomdp.core.states.State;

public class DieNTerminalState implements TerminalFunction {

	@Override
	public boolean isTerminal(State s) {
		return s.getFirstObjectOfClass(CLASS_AGENT).getBooleanValForAttribute(STATE_GAME_OVER)
				|| s.getFirstObjectOfClass(CLASS_AGENT).getIntValForAttribute(STATE_CURRENT_AMOUNT) >= MAX_VALUE;
	}

}
