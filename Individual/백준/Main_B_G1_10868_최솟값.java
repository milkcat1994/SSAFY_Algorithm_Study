import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -최솟값-
 */

//출처 : https://www.acmicpc.net/problem/10868
public class Main_B_G1_10868_최솟값 {
	static int N, M;
	static int[] nodes, arr;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		init();
		
		find();
	}
	
	static void find() throws Exception {
		int s, e;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			s = Integer.parseInt(st.nextToken())-1;
			e = Integer.parseInt(st.nextToken())-1;
			
			sb.append(findMin(1, s, e, 0, N-1) + "\n");
		}
		System.out.println(sb.toString());
	}
	
	static int findMin(int idx, int start, int end, int left, int right) {
		if(end < left || right < start)
			return Integer.MAX_VALUE;
		
		if(start <= left && right <= end)
			return nodes[idx];
		
		return Math.min(findMin(idx*2, start, end, left, (left+right)/2), findMin(idx*2+1, start, end, (left+right)/2+1, right));
	}
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		int nodeSize = (int)Math.pow(2, Math.ceil(baseLog(N, 2))+1);
		nodes = new int[nodeSize];
		
		for(int i=0; i<N; ++i)
			arr[i] = Integer.parseInt(br.readLine());
		
		initNodes(1, 0, N-1);
	}
	
	static int initNodes(int idx, int start, int end) {
		if(start == end)
			return nodes[idx] = arr[start];
		
		return nodes[idx] = Math.min(initNodes(idx*2, start, (start+end)/2), initNodes(idx*2+1, (start+end)/2+1, end));
	}
	
	static double baseLog(double x, double base) {
		return Math.log10(x) / Math.log10(base);
	}
}
