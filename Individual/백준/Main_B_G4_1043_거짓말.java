import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -거짓말-
 * 진실을 듣는 사람과 같은 파티에 참석하는 사람의 경우 같은 집합으로 취급한다.
 * 파티에 참여하는 각각 사람들이 진실을 들어야 하는 살마과 같은 집합인 사람이 있다면 진실을 말해야한다.
 * 
 * 이를 Union-Find를 이용하여 구분하였다.
 */

//출처 : https://www.acmicpc.net/problem/1043
public class Main_B_G4_1043_거짓말 {
	static int[] parents;
	
	static void initSet(int N) {
		parents = new int[N+1];
		Arrays.fill(parents, -1);
	}
	
	static int findSet(int x) {
		if(parents[x] == -1) return x;
		return parents[x] = findSet(parents[x]);
	}
	
	static boolean union(int a, int b) {
		int pa = findSet(a);
		int pb = findSet(b);
		
		if(pa == pb) return false;
		
		parents[pa] = pb;
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[][] party = new boolean[M][N+1];
		boolean[] trust = new boolean[N+1];
		initSet(N);
		
		st = new StringTokenizer(br.readLine(), " ");
		int T = Integer.parseInt(st.nextToken());
		for(int i=1; i<=T; ++i) {
			trust[Integer.parseInt(st.nextToken())] = true;
		}
		
		int ti=0, ci=0, K;
		for(int i = 0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			//오는 사람의 수
			K = Integer.parseInt(st.nextToken());
			if(K>0) {
				ti = Integer.parseInt(st.nextToken());
				party[i][ti]=true;
				//2명 이상이라면 union
				for(int k = 1; k < K; ++k) {
					ci = Integer.parseInt(st.nextToken());
					union(ti, ci);
					party[i][ci]=true;
				}
			}
		}
		
		// 진실을 들어야 하는 사람의 부모의 진실여부를 바꾸어준다.
		for(int i = 1; i<=N; ++i) {
			if(trust[i]) {
				trust[findSet(i)] = true;
			}
		}
		
		int res = M;
		for (int row = 0; row < M; ++row) {
			for (int col = 1; col <= N; ++col) {
				//해당 파티에 있는 사람이라면 진실을 아는 사람과 같은 무리인지 확인
				if(party[row][col]) {
					//진실을 아는 무리라면 파티 갯수 -1
					if(trust[findSet(col)]) {
						res--;
						break;
					}
				}
			}
		}
		
		System.out.println(res);
	}
}
