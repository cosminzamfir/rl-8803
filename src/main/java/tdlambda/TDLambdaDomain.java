package tdlambda;

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

public class TDLambdaDomain implements DomainGenerator {

	public static final String STATE_INDEX = "stateIndex";
	public static final String CLASS_AGENT = "agent";
	
	private double probToState1;
	

	public TDLambdaDomain(double probToState1) {
		super();
		this.probToState1 = probToState1;
	}

	@Override
	public Domain generateDomain() {
		Domain domain = new SADomain();
		Attribute stateIndexAttr = new Attribute(domain, STATE_INDEX, AttributeType.INT);
		
		ObjectClass agent = new ObjectClass(domain, CLASS_AGENT);
		agent.addAttribute(stateIndexAttr);
		
		new TDLambdaAction(domain, probToState1);
		return domain;
	}
	
	public State createInitialState(Domain domain) {
		State s = new MutableState();
		ObjectInstance agent = new MutableObjectInstance(domain.getObjectClass(CLASS_AGENT), "agent0");
		agent.setValue(STATE_INDEX, 0);
		s.addObject(agent);
		return s;
	}

	
	
}
