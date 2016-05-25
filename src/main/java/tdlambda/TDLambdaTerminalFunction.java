package tdlambda;


import burlap.oomdp.core.TerminalFunction;
import burlap.oomdp.core.states.State;

import static tdlambda.TDLambdaDomain.*;
public class TDLambdaTerminalFunction implements TerminalFunction {

	@Override
	public boolean isTerminal(State s) {
		return s.getFirstObjectOfClass(CLASS_AGENT).getIntValForAttribute(STATE_INDEX) == 6;
	}

}
