import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
 * -개미굴-
 */

//출처 : https://www.acmicpc.net/problem/14725
public class Main_B_G2_14725_개미굴 {
	static class Feed{
		Map<String, Feed> hm;
		String name;
		Feed(String name){
			hm = new TreeMap<>();
			this.name = name;
		}
	}
	
	static Map<String, Feed> hm;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		hm = new TreeMap<>();
		int N = Integer.parseInt(br.readLine());
		
		Map<String, Feed> thm;
		String str;
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int t = Integer.parseInt(st.nextToken());
			thm = hm;
			for(int j = 0; j < t; ++j) {
				str = st.nextToken();
				if(thm.containsKey(str)) {
					thm = thm.get(str).hm;
				}
				else {
					thm.put(str, new Feed(str));
					thm = thm.get(str).hm;
				}
			}
		}
		
		dfs(hm, 0);
		System.out.print(sb.toString());
	}
	
	static void dfs(Map<String, Feed> map, int idx) {
		Set<String> set = map.keySet();
		Iterator<String> iter = set.iterator();
		String str;
		while(iter.hasNext()) {
			str = iter.next();
			for(int i = 0; i<idx; ++i) {
				sb.append("--");
			}
			sb.append(str).append('\n');
			dfs(map.get(str).hm, idx+1);
		}
	}
}
