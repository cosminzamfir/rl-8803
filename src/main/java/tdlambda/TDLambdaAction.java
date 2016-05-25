package tdlambda;

import java.util.ArrayList;
import java.util.List;

import burlap.oomdp.core.Domain;
import burlap.oomdp.core.TransitionProbability;
import burlap.oomdp.core.states.State;
import burlap.oomdp.singleagent.FullActionModel;
import burlap.oomdp.singleagent.GroundedAction;
import burlap.oomdp.singleagent.common.SimpleAction;
import static tdlambda.TDLambdaDomain.*;

public class TDLambdaAction extends SimpleAction implements FullActionModel {

	
	
	private double probToState1;

	public TDLambdaAction(Domain domain, double probToState1) {
		super("default", domain);
		this.probToState1 = probToState1;
	}

	@Override
	public List<TransitionProbability> getTransitions(State s, GroundedAction groundedAction) {
		List<TransitionProbability> res = new ArrayList<TransitionProbability>();
		int stateIndex = s.getFirstObjectOfClass(CLASS_AGENT).getIntValForAttribute(STATE_INDEX);
		if(stateIndex == 0) {
			State newS = s.copy();
			newS.getFirstObjectOfClass(CLASS_AGENT).setValue(STATE_INDEX, stateIndex+1);
			res.add(new TransitionProbability(newS, probToState1));
			
			newS = s.copy();
			newS.getFirstObjectOfClass(CLASS_AGENT).setValue(STATE_INDEX, stateIndex+2);
			res.add(new TransitionProbability(newS, probToState1));
		} else {
			State newS = s.copy();
			newS.getFirstObjectOfClass(CLASS_AGENT).setValue(STATE_INDEX, stateIndex+1);
			res.add(new TransitionProbability(newS, 1));
		}
		return res;
	}

	@Override
	protected State performActionHelper(State s, GroundedAction groundedAction) {
		int stateIndex = s.getFirstObjectOfClass(CLASS_AGENT).getIntValForAttribute(STATE_INDEX);
		State newS = s.copy();
		if(stateIndex == 0) {
			if(Math.random() < probToState1) {
				stateIndex++;
			} else {
				stateIndex += 2;
			}
		} else if(stateIndex == 1 || stateIndex == 2) {
			stateIndex = 3;
		} else {
			stateIndex ++;
		}
		newS.getFirstObjectOfClass(CLASS_AGENT).setValue(STATE_INDEX, stateIndex);
		return newS;
	}

}
