package tdlambda;

import burlap.behavior.singleagent.learning.actorcritic.Actor;
import burlap.behavior.singleagent.learning.actorcritic.ActorCritic;
import burlap.behavior.singleagent.learning.actorcritic.actor.BoltzmannActor;
import burlap.behavior.singleagent.learning.actorcritic.critics.TDLambda;
import burlap.oomdp.core.Domain;
import burlap.oomdp.core.TerminalFunction;
import burlap.oomdp.core.states.State;
import burlap.oomdp.singleagent.RewardFunction;
import burlap.oomdp.singleagent.environment.Environment;
import burlap.oomdp.singleagent.environment.SimulatedEnvironment;
import burlap.oomdp.statehashing.SimpleHashableStateFactory;

public class TDLambdaApp {

	private static double gamma = 1.0;
	private static int numEpisodes = 100000;
	private static double lambda = 0.623;
	
	public void run(double probToState1, double lambda, double[] rewards) {
		TDLambdaDomain domainGenerator = new TDLambdaDomain(probToState1);
		Domain domain = domainGenerator.generateDomain();
		State initialState = domainGenerator.createInitialState(domain);
		
		RewardFunction rf = new TDLambdaRewardFunction(rewards);
		TerminalFunction tf = new TDLambdaTerminalFunction();

		TDLambda tdLambda = new TDLambda(rf, tf, 1, new SimpleHashableStateFactory(), 1, 0, lambda);
		Actor actor = new BoltzmannActor(domain, new SimpleHashableStateFactory(), 1);
		ActorCritic actorCritic = new ActorCritic(domain, gamma, actor, tdLambda);
		Environment env = new SimulatedEnvironment(domain, rf, tf, initialState);
		for (int i = 0; i < numEpisodes; i++) {
			if(i>0) {
				tdLambda.resetData();
			}
			actorCritic.runLearningEpisode(env);
			System.out.println(tdLambda.value(initialState));
		}
		
	}
	
	public static void main(String[] args) {
		new TDLambdaApp().run(0.81, lambda, new double[]{7.9,-5.1,2.5,-7.2,9.0,0.0,1.6});
	}

}
