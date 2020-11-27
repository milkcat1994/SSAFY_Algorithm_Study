import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 * -전화번호 목록-
 * Set을 이용하였으며, 각 문자열마다 slice하여 해당 문자열의 유무를 판단한다.
 * 
 * Trie로 풀어 볼 수 도 있다고 한다. -> 각 노드마다 0-9로 향하는 노드를 가지며 end인지 결과를 가진다.
 * (공간 복잡도가 터진다고 생각하여 시도하지 않았으나 안 터지는듯.)
 */

//출처 : https://www.acmicpc.net/problem/5052
public class Main_B_G4_5052_전화번호목록 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		String ts, resS;
		int tscnt, cnt;
		W:while(T-->0) {
			int N = Integer.parseInt(br.readLine());
			Set<String> set = new HashSet<>();
			
			for(int i=0; i<N; ++i)
				set.add(br.readLine());
			
			Iterator<String> iter = set.iterator();
			while(iter.hasNext()) {
				ts = iter.next();
				tscnt = ts.length();
				cnt = 0;
				sb.setLength(0);
				do {
					sb.append(ts.charAt(cnt));
					resS = sb.toString();
					if(!resS.equals(ts) && set.contains(resS)) {
						System.out.println("NO");
						continue W;
					}
					cnt++;
				}while(cnt < tscnt);
			}
			System.out.println("YES");
		}
	}
}
