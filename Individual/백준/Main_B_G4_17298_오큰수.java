import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -오큰수-
 * 숫자가 들어오는대로 가장 작은, idx가 가장 작은 것이 앞에 오도록 우선순위 큐에 넣는다.
 * 뒤에 들어오는 수가 먼저 들어온 수보다 크다면 먼저 들어온 수를 제거하며, 해당 자리의 오큰수를 지정해준다.
 */

//출처 : https://www.acmicpc.net/problem/17298
public class Main_B_G4_17298_오큰수 {
	static int N;
	static PriorityQueue<Number> pq;
	static int[] arr;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		init();
		
		find();
		
		print();
	}
	
	static void print() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; ++i) {
			sb.append(arr[i]+" ");
		}
		System.out.print(sb.toString());
	}
	
	static void find() {
		int ti;
		Number tn;
		for(int i=0; i<N; ++i) {
			ti = Integer.parseInt(st.nextToken());
			
			while(!pq.isEmpty() && pq.peek().n < ti) {
				tn = pq.poll();
				arr[tn.idx] = ti;
			}
			
			pq.offer(new Number(ti, i));
		}
	}
	
	static void init() throws Exception {
		pq = new PriorityQueue<>();
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		Arrays.fill(arr, -1);
		
		st = new StringTokenizer(br.readLine(), " ");
	}
	
	static class Number implements Comparable<Number>{
		int n, idx;
		
		Number(int n, int idx){
			this.n = n;
			this.idx = idx;
		}

		@Override
		public int compareTo(Number o) {
			if(this.n == o.n)
				return this.idx - o.idx;
			return this.n - o.n;
		}
	}
}
