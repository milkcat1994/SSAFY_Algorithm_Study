import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -트리-
 */

//출처 : https://www.acmicpc.net/problem/1068
public class Main_B_S1_1068_트리 {
	static int N, res, origin;
	static List<Integer>[] list;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		init();
		if(res == -1)
			System.out.print(0);
		else {
			find();
			System.out.print(res);
		}
	}
	
	static void find() {
		Queue<Integer> que = new LinkedList<>();
		
		que.offer(origin);
		
		int target;
		while(!que.isEmpty()){
			target = que.poll();
			
			if(list[target].size() == 0) {
				res++;
			}
			else {
				for(Integer ti : list[target]) {
					que.offer(ti);
				}
			}
		}
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N];
		arr = new int[N];
		for(int i=0; i<N; ++i)
			list[i] = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine(), " ");
		int ti;
		for(int i=0; i<N; ++i) {
			ti = Integer.parseInt(st.nextToken());
			arr[i] = ti;
			if(ti == -1) {
				origin = i;
				continue;
			}
			list[ti].add(i);
		}
		
		int target = Integer.parseInt(br.readLine());
		if(arr[target] == -1) {
			res = -1;
			list[target].clear();
		}
		else
			list[arr[target]].remove(new Integer(target));
	}
}
