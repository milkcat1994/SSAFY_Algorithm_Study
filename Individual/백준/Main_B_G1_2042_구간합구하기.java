import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -구간 합 구하기-
 * 세그먼트 트리 이용한 구간 합 구하기
 */

//출처 : https://www.acmicpc.net/problem/2042
public class Main_B_G1_2042_구간합구하기 {
	static int N, M, K;
	static long[] arr, nodes;
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		init();
		
		StringBuilder sb = new StringBuilder();
		int a,b;
		long c;
		for(int i=0; i<M+K; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Long.parseLong(st.nextToken());
			switch (a) {
			// b번쨰 숫자 c로 변경 -> 차이만큼 더해줄 예정
			case 1:
				change(1, 0, N-1, b-1, c-arr[b-1]);
				arr[b-1] = c;
				break;
				
			// b~c까지의 합 출력
			case 2:
				sb.append(find(1, b-1, (int)c-1, 0, N-1) + "\n");
				break;
			}
		}
		System.out.print(sb.toString());
	}
	
	// start, end > 구해야 하는 구간
	// left, right > 전체 구간
	static long find(int idx, int start, int end, int left, int right) {
		// s < e < l < r
		if(end < left || right < start)
			return 0;
		
		// s < l < r < e
		if(start <= left && right <= end)
			return nodes[idx];
		
		return find(idx*2, start, end, left, (left + right)/2) + find(idx*2+1, start, end, (left + right)/2+1, right);
	}
	
	// b의 값을 c만큼 변동준다.
	static void change(int idx, int start, int end, int b, long c) {
		// 리프 노드라면 변경 뒤 return
		if(start == end) {
			nodes[idx] += c;
			return;
		}
		
		// b가 속해있다면 해당 노드 값 변경
		if(start<= b && b <= end)
			nodes[idx] += c;
		
		
		// b가 속해있는 하위노드로 진행
		if(start <= b && b <= (start+end)/2)
			change(idx*2, start, (start+end)/2, b, c);
		
		if((start+end)/2+1 <= b && b <= end)
			change(idx*2+1, (start+end)/2+1, end, b, c);
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new long[N];
		for(int i=0; i<N; ++i)
			arr[i] = Long.parseLong(br.readLine());
		
		int nodeSize = (int)Math.pow(2, Math.ceil(baseLog(N, 2))+1);
		nodes = new long[nodeSize+1];
		
		initNodes(1, 0, N-1);
	}
	
	// 미리 구간합을 저장해두기 위한 nodes 배열 초기화
	static long initNodes(int idx, int start, int end) {
		if(start == end) {
			return nodes[idx] = arr[start];
		}
		
		return nodes[idx] = initNodes(idx*2, start, (start+end)/2) + initNodes(idx*2+1, (start+end)/2+1, end);
	}
	
	// 밑이 2인 log함수 결과값 반환
	static double baseLog(double x, double base) {
		return Math.log10(x) / Math.log10(base);
	}
}
