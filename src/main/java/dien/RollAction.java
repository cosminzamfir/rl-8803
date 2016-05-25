package dien;

import static dien.DieNDomain.CLASS_AGENT;
import static dien.DieNDomain.STATE_CURRENT_AMOUNT;
import static dien.DieNDomain.STATE_GAME_OVER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import burlap.oomdp.core.Domain;
import burlap.oomdp.core.TransitionProbability;
import burlap.oomdp.core.objects.ObjectInstance;
import burlap.oomdp.core.states.State;
import burlap.oomdp.singleagent.FullActionModel;
import burlap.oomdp.singleagent.GroundedAction;
import burlap.oomdp.singleagent.common.SimpleAction;

/**
 * Agent decide to roll the die
 *
 */
public class RollAction extends SimpleAction implements FullActionModel {

	private static Random random = new Random();
	
	private int N;
	private int[] badSides;

	public RollAction(Domain domain, String name, int n, int[] badSides) {
		super(name, domain);
		this.domain = domain;
		N = n;
		this.badSides = badSides;
	}

	@Override
	public List<TransitionProbability> getTransitions(State s, GroundedAction groundedAction) {
		int currentAmount = s.getFirstObjectOfClass(CLASS_AGENT).getIntValForAttribute(STATE_CURRENT_AMOUNT);
		List<TransitionProbability> res = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			State newState = s.copy();
			if (badSides[i-1] == 1) {
				newState.getFirstObjectOfClass(CLASS_AGENT).setValue(STATE_CURRENT_AMOUNT, 0);
				newState.getFirstObjectOfClass(CLASS_AGENT).setValue(STATE_GAME_OVER, true);
			} else {
				newState.getFirstObjectOfClass(CLASS_AGENT).setValue(STATE_CURRENT_AMOUNT, currentAmount + i);
				newState.getFirstObjectOfClass(CLASS_AGENT).setValue(STATE_GAME_OVER, false);
			}
			res.add(new TransitionProbability(newState, 1.0 / N));
		}
		return res;
	}

	@Override
	protected State performActionHelper(State s, GroundedAction groundedAction) {
		ObjectInstance agent = s.getFirstObjectOfClass(CLASS_AGENT);
		int currentAmount = agent.getIntValForAttribute(STATE_CURRENT_AMOUNT);
		assert currentAmount > 0 : "Cannot trigger any action if currentAmount is 0";
		int n = random.nextInt(N + 1);
		if (badSides[n] == 1) {
			agent.setValue(STATE_CURRENT_AMOUNT, 0);
			agent.setValue(STATE_GAME_OVER, true);
		} else {
			agent.setValue(STATE_CURRENT_AMOUNT, currentAmount + n);
			agent.setValue(STATE_GAME_OVER, false);
		}
		return s;
	}

}
