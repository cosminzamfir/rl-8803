package dien;

import burlap.oomdp.core.states.State;
import burlap.oomdp.singleagent.GroundedAction;
import burlap.oomdp.singleagent.RewardFunction;

import static dien.DieNDomain.*;
public class DieNReward implements RewardFunction {

	@Override
	public double reward(State s, GroundedAction a, State sprime) {
		if(!sprime.getFirstObjectOfClass(CLASS_AGENT).getBooleanValForAttribute(STATE_GAME_OVER)) {
			return 0;
		} else {
			return sprime.getFirstObjectOfClass(CLASS_AGENT).getIntValForAttribute(STATE_CURRENT_AMOUNT);
		}
		
	}

}
