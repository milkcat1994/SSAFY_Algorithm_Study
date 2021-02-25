import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -친구비-
 * 1. 각 그래프 중 최소값을 추출하면 되는 문제 -> Union-Find 이용
 * 2. 각 그래프의 부모 idx에 해당 그래프중 가장 최소값을 저장한다. (Line 30 함수)
 * 3. 위에서 구한 최소값들을 모두 합하고, K와의 초과 유무 판단 이후 출력. (Line 40 함수)
 * 
 * 메모리 : 19208KB
 * 시간 : 232ms
 * 풀이 시간 : 35M
 */

//출처 : https://www.acmicpc.net/problem/16562
public class Main_B_G3_16562_친구비 {
	static int N,M,K;
	static int[] paies, minPay;
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		init();
		
		solve();
		
		System.out.print(print());
	}
	
	static void solve() {
		int pa;
		for(int i=0; i<N; ++i) {
			pa = getParent(i);
			
			minPay[pa] = Integer.min(minPay[pa], paies[i]);
		}
	}
	
	static String print() {
		int sum=0;
		for(int i=0; i<N; ++i) {
			if(minPay[i]!=Integer.MAX_VALUE)
				sum+=minPay[i];
		}
		return sum > K ? "Oh no" : Integer.toString(sum);
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 친구비
		st = new StringTokenizer(br.readLine(), " ");
		paies = new int[N];
		for(int i=0; i<N; ++i) {
			paies[i] = Integer.parseInt(st.nextToken());
		}
		
		initParent(N);
		
		int a,b;
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			union(a,b);
		}
		
		minPay = new int[N];
		Arrays.fill(minPay, Integer.MAX_VALUE);
	}
	
	static void initParent(int n) {
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
		
		if(pa==pb) return false;
		
		parents[pa] = pb;
		
		return true;
	}
}
