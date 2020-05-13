import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//출처 : https://www.acmicpc.net/problem/2252
public class Main_B_G2_2252_줄세우기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] list = new ArrayList[N+1];
		for(int i = 0; i < N+1; ++i)
			list[i] = new ArrayList<>();
		int[] exNeed= new int[N+1];
		//번호는 1~N
		int a,b;
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			exNeed[b]++;
			list[a].add(b);
		}
		
		Queue<Integer> que = new LinkedList<>();
		for(int i = 1; i <= N; ++i) {
			if(exNeed[i]==0)
				que.offer(i);
		}
		
		int ci, ni;
		while(!que.isEmpty()) {
			ci = que.poll();
			sb.append(ci).append(' ');
			
			int size = list[ci].size();
			for(int i = 0; i < size; ++i) {
				ni = list[ci].get(i);
				exNeed[ni]--;
				if(exNeed[ni] == 0) {
					que.offer(ni);
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}
