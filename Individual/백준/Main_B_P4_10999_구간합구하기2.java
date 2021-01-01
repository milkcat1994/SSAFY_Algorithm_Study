import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -구간 합 구하기 2-
 * 세그먼트 트리 + lazy propagation
 */

//출처 : https://www.acmicpc.net/problem/10999
public class Main_B_P4_10999_구간합구하기2 {
	static int N,M,K, nodeSize;
	static long[] arr;
	static Node[] nodes;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		init();
		
		find();
	}
	
	static void find() throws Exception {
		int a,b,c;
		long d;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M+K; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			switch (a) {
			// b,c번쨰 수에 d를 더하기
			case 1:
				b = Integer.parseInt(st.nextToken())-1;
				c = Integer.parseInt(st.nextToken())-1;
				d = Long.parseLong(st.nextToken());
				add(1, b, c, 0, N-1, d);
				break;
				// b,c번째 수의 합 출력
			case 2:
				b = Integer.parseInt(st.nextToken())-1;
				c = Integer.parseInt(st.nextToken())-1;
				sb.append(print(1, b, c, 0, N-1) + "\n");
				break;
			}
		}
		System.out.print(sb.toString());
	}
	
	static void propagate(int idx, int left, int right) {
		long lazy = nodes[idx].lazy;
		if(lazy != 0) {
			nodes[idx].v += lazy * (right-left+1);
			// 임시 저장해둔 차이값이 있다면 다음 노드로 넘길 것이다.
			// leaf 노드라면 전파 x
			if(left != right) {
				nodes[idx*2].lazy += lazy;
				nodes[idx*2+1].lazy += lazy;
			}
			
			nodes[idx].lazy = 0;
		}
	}
	
	static long add(int idx, int start, int end, int left, int right, long target) {
		// nodes[idx].lazy의 값이 있을수 있기때문에
		// 범위에 속하지 않더라도 지연 전파가 필요하다.
		propagate(idx, left, right);
		
		if(end < left || right < start)
			return nodes[idx].v;
		
		if(start <= left &&  right <= end) {
			nodes[idx].lazy += target;
			
			propagate(idx, left, right);
			
			return nodes[idx].v;
		}
		
		return nodes[idx].v = add(idx*2, start, end, left, (left+right)/2, target) + add(idx*2+1, start, end, (left+right)/2+1, right, target);
	}
	
	static long print(int idx, int start, int end, int left, int right) {
		propagate(idx, left, right);
		
		if(end < left || right < start)
			return 0;
		
		if(start <= left &&  right <= end) {
			return nodes[idx].v;
		}
		
		return print(idx*2, start, end, left, (left+right)/2) + print(idx*2+1, start, end, (left+right)/2+1, right);
	}
	
	static void init() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new long[N];
		for(int i=0; i<N; ++i)
			arr[i] = Long.parseLong(br.readLine());
		
		nodeSize = (int)(Math.pow(2, Math.ceil(baseLog(N,2)) + 1));
		
		nodes = new Node[nodeSize+1];
		initNodes(1, 0, N-1);
	}
	
	static double baseLog(double x, double base) {
		return Math.log10(x) / Math.log10(base);
	}
	
	static long initNodes(int idx, int left, int right) {
		if(left == right) {
			nodes[idx] = new Node(arr[left], 0);
			return nodes[idx].v;
		}
		
		nodes[idx] = new Node(initNodes(idx*2, left, (left+right)/2) + initNodes(idx*2+1, (left+right)/2+1, right), 0);
		return  nodes[idx].v;
	}
	
	static class Node {
		long v, lazy;
		
		Node(long v, long lazy){
			this.v = v;
			this.lazy = lazy;
		}
	}
}
