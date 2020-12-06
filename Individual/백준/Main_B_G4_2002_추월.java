import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * -추월-
 */

//출처 : https://www.acmicpc.net/problem/2002
public class Main_B_G4_2002_추월 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str;
		
		boolean[] isOut = new boolean[N];
		List<String> list = new ArrayList<>();
		Map<String, Integer> hm = new HashMap<>();
		for(int i = 0; i < N; ++i) {
			str = br.readLine();
			list.add(str);
			hm.put(str, i);
		}
		
		int res = 0, idx = 0;
		for(int i = 0; i < N; ++i) {
			if(isOut[idx]) {
				idx++;
				i--;
				continue;
			}
			str = br.readLine();
			
			if(!list.get(idx).equals(str)) {
				res++;
				idx--;
			}
			isOut[hm.get(str)] = true;
			idx++;
		}
		
		System.out.print(res);
	}
}
