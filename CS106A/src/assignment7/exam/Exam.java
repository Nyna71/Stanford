package assignment7.exam;

import java.util.HashMap;
import java.util.Map;

import acm.program.*;

public class Exam extends ConsoleProgram {
	
	public void run() {

		Map<String, String> hm1 = new HashMap<String,String>();
		Map<String, String> hm2 = new HashMap<String,String>();
		
		hm1.put("Alice", "Healthy");
		hm1.put("Mary", "Ecstatic");
		hm1.put("Bob", "Happy");
		hm1.put("Chuck", "Fine");
		hm1.put("Felix", "Sick");
		
		hm2.put("Mary", "Ecstatic");
		hm2.put("Felix", "Healthy");
		hm2.put("Ricardo", "Superb");
		hm2.put("Tam", "Fine");
		hm2.put("Bob", "Happy");
		
		System.out.println(commonKeyValuePairs(hm1, hm2));
		
	}

	private int commonKeyValuePairs(Map<String, String> hm1,
			Map<String, String> hm2) {

		int result = 0;
		
		for(String key : hm1.keySet()) {
			if(hm2.containsKey(key))
					if(hm2.get(key).equals(hm1.get(key))) result++;
		}
		
		return result;
	}
	
}
