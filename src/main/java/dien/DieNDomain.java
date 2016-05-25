package dien;

import burlap.oomdp.auxiliary.DomainGenerator;
import burlap.oomdp.core.Attribute;
import burlap.oomdp.core.Domain;
import burlap.oomdp.core.ObjectClass;
import burlap.oomdp.core.Attribute.AttributeType;
import burlap.oomdp.core.objects.MutableObjectInstance;
import burlap.oomdp.core.objects.ObjectInstance;
import burlap.oomdp.core.states.MutableState;
import burlap.oomdp.core.states.State;
import burlap.oomdp.singleagent.SADomain;

public class DieNDomain implements DomainGenerator {

	public static final String CLASS_AGENT = "agent";
	public static final String ACTION_ROLL = "roll";
	public static final String STATE_CURRENT_AMOUNT = "currentAmount";
	public static final String STATE_GAME_OVER = "gameOver";
	
	/** Number of die sides */
	private int N;
	private int[] badSides;
	State initialState;
	
	public DieNDomain(int N, int ...badSides) {
		super();
		this.N = N;
		this.badSides = badSides;
		if(badSides.length != N) {
			throw new RuntimeException("badSides[] array must be of size M");
		}
	}


	@Override
	public Domain generateDomain() {
		Domain domain = new SADomain();
		Attribute currentAmount = new Attribute(domain, STATE_CURRENT_AMOUNT, AttributeType.INT);
		currentAmount.setLims(0, Integer.MAX_VALUE);
		Attribute continueGame = new Attribute(domain, STATE_GAME_OVER, AttributeType.BOOLEAN);
		
		
		ObjectClass agent = new ObjectClass(domain, CLASS_AGENT);
		agent.addAttribute(currentAmount);
		agent.addAttribute(continueGame);
		
		new RollAction(domain,"roll", N,badSides);
		new EndGameAction(domain, "endGame");
		return domain;
	}
	
	public State createInitialState(Domain domain) {
		State s = new MutableState();
		ObjectInstance agent = new MutableObjectInstance(domain.getObjectClass(CLASS_AGENT), "agent0");
		agent.setValue(STATE_CURRENT_AMOUNT, 0);
		agent.setValue(STATE_GAME_OVER, false);
		s.addObject(agent);
		return s;
	}

}
