import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -최솟값과 최댓값-
 * 세그먼트 트리 이용
 * Node라는 클래스 말고도 min, max Tree를 따로 구현하여도 된다.
 * 
 * 참고 : https://www.acmicpc.net/blog/view/9
 */

//출처 : https://www.acmicpc.net/problem/2357
public class Main_B_G1_2357_최솟값과최댓값 {
	static int N,M;
	static int[] arr;
	static Node[] nodes;
	
	static StringTokenizer st;
	static BufferedReader br;
	public static void main(String[] args) throws Exception {
		init();
		
		StringBuilder sb = new StringBuilder();
		int start, end;
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			start = Integer.parseInt(st.nextToken())-1;
			end = Integer.parseInt(st.nextToken())-1;
			sb.append(find(1, start, end, 0, N-1)+"\n");
		}
		System.out.print(sb.toString());
	}
	
	// start, end > 구해야 하는 범위
	// left, right > 보고 있는 배열의 범위
	static Node find(int idx, int start, int end, int left, int right) {
		// 겹치지 않는 경우
		// ex) l < r < s < e
		if(end < left || start > right) {
			return null;
		}

		// s < l < r < e
		if(start <= left && right <= end) {
			return nodes[idx];
		}
		
		// l < s < e < r
		// s < l < e < r || l < s < r < e
		Node an,bn;
		an = find(idx*2, start, end, left, (left+right)/2);
		bn = find(idx*2+1, start, end, (left+right)/2+1, right);
		if(an == null) {
			return bn;
		}
		else if(bn == null) {
			return an;
		}
		else {
			return new Node(Math.min(an.min, bn.min), Math.max(an.max, bn.max));
		}
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		for(int i=0; i<N; ++i)
			arr[i] = Integer.parseInt(br.readLine());
		
		// 트리의 크기 지정
		int nodeSize = (int)Math.pow(2, Math.ceil(baseLog(N,2))+1);
		nodes = new Node[nodeSize+1];
		
		initNodes(1, 0, N-1);
	}
	
	// 구간별 min, max 값을 저장하는 Node 객체를 지정한다.
	static Node initNodes(int idx, int start, int end) {
		if(start == end)
			return nodes[idx] = new Node(arr[start], arr[end]);

		Node an, bn;
		an = initNodes(idx*2, start, (start+end)/2);
		bn = initNodes(idx*2+1, (start+end)/2+1, end);
		
		return nodes[idx] = new Node(Math.min(an.min, bn.min), Math.max(an.max, bn.max));
	}
	
	// base를 밑으로 가지는 log 반환
	static double baseLog(double x, double base) {
		return Math.log10(x) / Math.log10(base);
	}
	
	static class Node {
		int min, max;
		
		Node(int min, int max){
			this.min = min;
			this.max = max;
		}
		
		@Override
		public String toString() {
			return min + " " + max;
		}
	}
}
