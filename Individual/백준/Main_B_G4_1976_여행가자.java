import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -여행가자-
 * unionFind이용하여 갈 수 있는 장소인지 파악
 */

//출처 : https://www.acmicpc.net/problem/1976
public class Main_B_G4_1976_여행가자 {
	static int[] parents;
	
	static void initParents(int M) {
		parents = new int[M];
		Arrays.fill(parents, -1);
	}
	
	static boolean union(int a, int b) {
		int pa = findParent(a);
		int pb = findParent(b);
		
		// 이미 속해있음
		if(pa == pb) return false;
		
		parents[pa] = pb;
		
		return true;
	}
	
	static int findParent(int x) {
		if(parents[x] == -1) return x;
		return parents[x] = findParent(parents[x]);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		initParents(N);
		
		String str;
		for (int row = 0; row < N; ++row) {
			str = br.readLine();
			for (int col = 0; col < N; ++col) {
				//연결 되었다면 union
				if(str.charAt(col*2) == '1')
					union(row,col);
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		
		//초기값
		int exi = -2, ci=0;
		while(st.hasMoreTokens()){
			ci = findParent(Integer.parseInt(st.nextToken())-1);
			if(exi != -2 && exi != ci) {
				System.out.println("NO");
				return;
			}
			exi = ci;
		}
		System.out.println("YES");
	}
}
