public class StateAndReward {

	/* State discretization function for the angle controller */
	public static String getStateAngle(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */
		String state1 = "state 1";
		String state2 = "state 2";
		String state3 = "state 3";
		String state4 = "state 4";
		int discAngle = discretize(angle, 4, -Math.PI, Math.PI);

		// System.out.println(discAngle);

		if (discAngle == 0) {
			return state1;
		} else if (discAngle == 1) {

			return state2;
		} else if (discAngle == 2) {

			return state3;
		} else if (discAngle == 3) {
			return state4;
		}
		return null;
	}

	/* Reward function for the angle controller */
	public static double getRewardAngle(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */

		double reward = 0;

		reward = (Math.PI + Math.PI / 3 - Math.abs(angle));

		return reward;
	}

	/* State discretization function for the full hover controller */
	public static String getStateHover(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */

		String state = "";
	

		int discVer = discretize(vy, 6, -5, 5);
		int discHor = discretize(vx, 6, -5, 5);

		// System.out.println("discVer: " + discVer);
		
		// System.out.println("DiscHor: " + discHor);
		


		state = getStateAngle(angle, vx, vy) +"#"+ discVer + "#"+ discHor;
		return state;
	}

	/* Reward function for the full hover controller */
	public static double getRewardHover(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */

		double reward = 0;
		reward = getRewardAngle(angle, vy, vx);
		double compReward = 1.1*reward - 2*Math.abs(vy) - Math.abs(vx);
		int finalReward = discretize(compReward, 100, -15,15);

		// System.out.println("compRew: " + compReward + ", reward: " + reward + ", vy
		// abs: " + 2 * Math.abs(vy));

		// System.out.println(yReward);
		return compReward;
	}

	// ///////////////////////////////////////////////////////////
	// discretize() performs a uniform discretization of the
	// value parameter.
	// It returns an integer between 0 and nrValues-1.
	// The min and max parameters are used to specify the interval
	// for the discretization.
	// If the value is lower than min, 0 is returned
	// If the value is higher than min, nrValues-1 is returned
	// otherwise a value between 1 and nrValues-2 is returned.
	//
	// Use discretize2() if you want a discretization method that does
	// not handle values lower than min and higher than max.
	// ///////////////////////////////////////////////////////////
	public static int discretize(double value, int nrValues, double min, double max) {
		if (nrValues < 2) {
			return 0;
		}

		double diff = max - min;

		if (value < min) {
			return 0;
		}
		if (value > max) {
			return nrValues - 1;
		}

		double tempValue = value - min;
		double ratio = tempValue / diff;

		return (int) (ratio * (nrValues - 2)) + 1;
	}

	// ///////////////////////////////////////////////////////////
	// discretize2() performs a uniform discretization of the
	// value parameter.
	// It returns an integer between 0 and nrValues-1.
	// The min and max parameters are used to specify the interval
	// for the discretization.
	// If the value is lower than min, 0 is returned
	// If the value is higher than min, nrValues-1 is returned
	// otherwise a value between 0 and nrValues-1 is returned.
	// ///////////////////////////////////////////////////////////
	public static int discretize2(double value, int nrValues, double min, double max) {
		double diff = max - min;

		if (value < min) {
			return 0;
		}
		if (value > max) {
			return nrValues - 1;			
		}

		double tempValue = value - min;
		double ratio = tempValue / diff;

		return (int) (ratio * nrValues);
	}

}
