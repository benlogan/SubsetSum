import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://en.wikipedia.org/wiki/Subset_sum_problem
 *
 * http://stackoverflow.com/questions/4632322/finding-all-possible-combinations-of-numbers-to-reach-a-given-sum/4633515#4633515
 *
 */
public class SubsetSum {

	private static int count = 0;

	public static void main(String args[]) {
		//Integer[] numbers = {3,9,8,4,5,7,10};
		Integer[] numbers = {3,9,8,4,5,5,7,10};
		int target = 15;
		//sum_up(new ArrayList<Integer>(Arrays.asList(numbers)), target);
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(Integer n : numbers) {
			map.put("n:" + Math.random() + ":" + n, n);
		}
		sumUp(map, target);
	}

	static void sum_up_recursive(ArrayList<Integer> numbers, int target, ArrayList<Integer> partial) {
		int s = 0;
		for (int x: partial) s += x;
		if (s == target) {
			count++;
			System.out.println("sum(" + Arrays.toString(partial.toArray()) + ")=" + target);
		}
		if (s >= target) {
			return;
		}
		for(int i = 0; i < numbers.size(); i++) {
			ArrayList<Integer> remaining = new ArrayList<Integer>();
			int n = numbers.get(i);
			for (int j = i + 1; j < numbers.size(); j++) {
				remaining.add(numbers.get(j));
			}
			ArrayList<Integer> partial_rec = new ArrayList<Integer>(partial);
			partial_rec.add(n);
			sum_up_recursive(remaining, target, partial_rec);
		}
	}

	static void sumUpRecursive(Map<String, Integer> numbersMap, int target, Map<String, Integer> partial) {
		int s = 0;
		for (int x: partial.values()) {
			s += x;
		}
		if (s == target) {
			count++;
			//System.out.println("sum(" + Arrays.toString(partial.keySet().toArray()) + ")=" + target);
			System.out.println("sum(" + Arrays.toString(partial.values().toArray()) + ")=" + target);
		}
		if (s >= target) {
			return;
		}
		
		int i = 0;
		
		Map<String, Integer> remaining = new HashMap<String, Integer>();
		remaining.putAll(numbersMap);
		
		for(String key : numbersMap.keySet()) {
			int n = numbersMap.get(key);
			for (int j = i + 1; j < numbersMap.size(); j++) {
				remaining.remove(key);
			}
			Map<String, Integer> partial_rec = new HashMap<String, Integer>(partial);
			partial_rec.put(key, n);
			sumUpRecursive(remaining, target, partial_rec);
		}
	}

	static void sum_up(ArrayList<Integer> numbers, int target) {
		sum_up_recursive(numbers, target, new ArrayList<Integer>());
		System.out.println("Found the target " + count + " times!");
	}

	static void sumUp(Map<String, Integer> numbers, int target) {
		sumUpRecursive(numbers, target, new HashMap<String, Integer>());
		System.out.println("Found the target " + count + " times!");
	}

}