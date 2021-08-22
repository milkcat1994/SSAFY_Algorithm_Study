import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -행성터널-
 * 1. 행성간의 거리가 x,y,z 각각 좌표차이의 최솟값이므로 좌표별로 정렬하여 인접한 edge만을 이용하여 답을 도출한다.
 * 2. MST중 크루스칼을 이용하여 구하였다.
 * 
 */

//출처 : https://www.acmicpc.net/problem/2887
public class Main_B_G1_2887_행성터널 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static Atmos[] atmosArr;
	static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
	
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		init();
		
		Arrays.sort(atmosArr, (o1, o2) -> o1.x - o2.x);
		getEdges();
		Arrays.sort(atmosArr, (o1, o2) -> o1.y - o2.y);
		getEdges();
		Arrays.sort(atmosArr, (o1, o2) -> o1.z - o2.z);
		getEdges();
		
		System.out.println(solve());
	}
	
	static int solve() {
		int cnt = 0;
		int minCost = 0;
		Edge te;
		while(cnt+1 < N) {
			te = pq.poll();
			if(!union(te.s, te.e))
				continue;
			cnt++;
			minCost += te.w;
		}
		
		return minCost;
	}
	
	static void getEdges() {
		for(int i=0; i<N-1; ++i) {
			pq.add(new Edge(atmosArr[i].idx, atmosArr[i+1].idx, atmosArr[i].getDistance(atmosArr[i+1])));
		}
	}
	
	static class Edge {
		int s,e,w;
		Edge(int s, int e, int w){
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
	
	static class Atmos {
		int idx,x,y,z;
		
		Atmos(int idx, int x, int y, int z){
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		int getDistance(Atmos atmos) {
			return min(Math.abs(this.x - atmos.x), Math.abs(this.y - atmos.y), Math.abs(this.z - atmos.z));
		}
	}
	
	static int min(int x, int y, int z) {
		return Math.min(x, Math.min(y, z));
	}
	
	static void init() throws Exception {
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		atmosArr = new Atmos[N];
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			atmosArr[i] = new Atmos(
					i,
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())
					);
		}
		
		parents = new int[N];
		Arrays.fill(parents, -1);
	}
	
	static int getParents(int x) {
		if(parents[x] == -1) return x;
		return parents[x] = getParents(parents[x]);
	}
	
	static boolean union(int a, int b) {
		int pa = getParents(a);
		int pb = getParents(b);
		
		if(pa == pb) return false;
		
		parents[pa] = pb;
		return true;
	}
}
