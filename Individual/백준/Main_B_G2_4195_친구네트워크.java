import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * -친구 네트워크-
 * <String, String>이 아닌 각 String을 idx로 구분지어서 표시하면
 * int[] 2개와 HashMap<String, Integer> 1개를 통해 관리 할 수 있다.
 */

//출처 : https://www.acmicpc.net/problem/4195
public class Main_B_G2_4195_친구네트워크 {
	static int TC, N;
	static BufferedReader br;
	static StringTokenizer st;
	static Map<String, String> parents;
	static Map<String, Integer> hm;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		TC = Integer.parseInt(br.readLine());
		
		while(--TC >= 0) {
			init();
			
			find();
		}
	}
	
	static void find() throws Exception {
		parents = new HashMap<String, String>();
		hm = new HashMap<String, Integer>();
		
		StringBuilder sb = new StringBuilder();
		String str1, str2;
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			str1 = st.nextToken();
			str2 = st.nextToken();
			
			// 해당 아이디 없다면 등록시키기
			if(!parents.containsKey(str1)) {
				parents.put(str1, "");
				hm.put(str1, 1);
			}
			
			if(!parents.containsKey(str2)) {
				parents.put(str2, "");
				hm.put(str2, 1);
			}
			
			// 두 아이디 합쳐주기
			union(str1, str2);
			
			sb.append(hm.get(findParent(str1))+"\n");
		}
		System.out.print(sb.toString());
	}
	
	// 부모 아이디를 구해온다.
	static String findParent(String x) {
		if(parents.get(x).equals("")) return x;
		parents.put(x, findParent(parents.get(x)));
		return parents.get(x);
	}
	
	static boolean union(String a, String b) {
		String pa = findParent(a);
		String pb = findParent(b);
		
		// 이미 속해있다면 아래의 갱신은 필요없다.
		if(pa.equals(pb)) return false;
		
		// 부모 아이디를 갱신해주며,
		// pb에 현재까지 확인한 아이디의 개수를 추가해준다.
		parents.put(pa, pb);
		hm.put(pb, hm.get(pa) + hm.get(pb));
		
		return true;
	}
	
	static void init() throws Exception {
		N = Integer.parseInt(br.readLine());
	}
}
