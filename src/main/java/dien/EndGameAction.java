package dien;

import java.util.ArrayList;
import java.util.List;

import burlap.oomdp.core.Domain;
import burlap.oomdp.core.TransitionProbability;
import burlap.oomdp.core.states.State;
import burlap.oomdp.singleagent.FullActionModel;
import burlap.oomdp.singleagent.GroundedAction;
import burlap.oomdp.singleagent.common.SimpleAction;
import static dien.DieNDomain.*;

/**
 * The agent decided to stop the game
 *
 */
public class EndGameAction extends SimpleAction implements FullActionModel {

	public EndGameAction(Domain domain, String name) {
		super(name, domain);
	}

	@Override
	public List<TransitionProbability> getTransitions(State s, GroundedAction groundedAction) {
		List<TransitionProbability> res = new ArrayList<TransitionProbability>();
		State sprime = s.copy();
		sprime.getFirstObjectOfClass(CLASS_AGENT).setValue(STATE_GAME_OVER, true);
		res.add(new TransitionProbability(sprime, 1));
		return res;
	}

	@Override
	protected State performActionHelper(State s, GroundedAction groundedAction) {
		s.getFirstObjectOfClass(CLASS_AGENT).setValue(STATE_GAME_OVER, true);
		return s;
	}

}
