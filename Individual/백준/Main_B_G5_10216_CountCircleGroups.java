import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -Count Circle Groups-
 */

//출처 : https://www.acmicpc.net/problem/10216
public class Main_B_G5_10216_CountCircleGroups {
	static int TC,N;
	static Enemy[] enemis;
	static int[] parents;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while(--TC >= 0) {
			init();
			
			sb.append(find()+"\n");
		}
		
		System.out.print(sb.toString());
	}
	
	static int find() {
		int res=N;
		for (int left = 0; left < N-1; ++left) {
			for (int right = left+1; right < N; ++right) {
				if(enemis[left].isLinked(enemis[right])) {
					if(union(left, right)) {
						res--;
					}
				}
			}
		}
		return res;
	}
	
	static void init() throws Exception {
		N = Integer.parseInt(br.readLine());
		
		initParents(N);
		enemis = new Enemy[N];
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			enemis[i] = new Enemy(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
	}

	static void initParents(int n) {
		parents = new int[n];
		Arrays.fill(parents, -1);
	}
	
	static int getParent(int x) {
		if(parents[x] == -1) return x;
		return parents[x] = getParent(parents[x]);
	}
	
	static boolean union(int a, int b) {
		int pa = getParent(a);
		int pb = getParent(b);
		
		if(pa == pb) return false;
		
		parents[pa] = pb;
		
		return true;
	}
	
	static class Enemy {
		int r,c,d;
		
		public Enemy(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
		
		public boolean isLinked(Enemy e) {
			if(Math.pow(this.r - e.r, 2) + Math.pow(this.c - e.c, 2) <= Math.pow(this.d + e.d, 2))
				return true;
			return false;
		}
	}
}