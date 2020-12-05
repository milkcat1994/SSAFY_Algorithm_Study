import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * -생태학-
 */

//출처 : https://www.acmicpc.net/problem/4358
public class Main_B_G5_4358_생태학 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String input = "";
		int total = 0;
		Map<String, Integer> hm = new HashMap<>();
		while((input = br.readLine()) != null) {
			hm.put(input, hm.containsKey(input) ? hm.get(input)+1 : 1);
			total++;
		}
		
		List<String> list = new ArrayList<>();
		list.addAll(hm.keySet());
		list.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		
		for(String str : list) {
			sb.append(String.format("%s %.4f", str, (float)hm.get(str) / total * 100))
			.append('\n');
		}
		System.out.println(sb.toString());
	}
}
