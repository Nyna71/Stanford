package assignments.assignment3;

import java.util.ArrayList;
import java.util.List;

public class Weights {

	public static void main(String[] args) {
		List<Integer> weights = new ArrayList<Integer>();

		weights.add(1);
		weights.add(2);
		weights.add(6);

		System.out.println(isMeasurable(9, weights));
	}

	private static boolean isMeasurable(int amount, List<Integer> weights) {
		// Check if last single weight in pool matches the amount
		if(weights.size() == 1) return amount == weights.get(0);

		// Check if amount equals to first weight of the list
		if(isMeasurable(amount, weights.subList(0, 1))) return true;

		// Check if amount can be matched with remaining weights, without using first weight
		if(isMeasurable(amount, weights.subList(1, weights.size()))) return true;

		// Check if keep weights on right balance, adding new weights would match the amount
		if(isMeasurable(amount - weights.get(0), weights.subList(1, weights.size()))) return true;

		// Check if amount can be matched with first weight on left balance, and remaining weights
		if(isMeasurable(amount + weights.get(0), weights.subList(1, weights.size()))) return true;

		return false;

	}
}
