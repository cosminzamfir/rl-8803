package tdlambda;

import burlap.oomdp.core.states.State;

import burlap.oomdp.singleagent.GroundedAction;
import burlap.oomdp.singleagent.RewardFunction;

import static tdlambda.TDLambdaDomain.*;
public class TDLambdaRewardFunction implements RewardFunction {

	private double[] rewards;

	public TDLambdaRewardFunction(double[] rewards) {
		this.rewards = rewards;
	}

	@Override
	public double reward(State s, GroundedAction a, State sprime) {
		int from = s.getFirstObjectOfClass(CLASS_AGENT).getIntValForAttribute(STATE_INDEX);
		int to = sprime.getFirstObjectOfClass(CLASS_AGENT).getIntValForAttribute(STATE_INDEX);
		if(from == 0 && to ==1) {
			return rewards[0];
		}
		if(from == 0 && to ==2) {
			return rewards[1];
		}
		if(from == 1 && to ==3) {
			return rewards[2];
		}
		if(from == 2 && to ==3) {
			return rewards[3];
		}
		return rewards[from +1];
	}

}
