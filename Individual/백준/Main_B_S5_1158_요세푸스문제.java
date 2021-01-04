import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * -요세푸스 문제-
 */

//출처 : https://www.acmicpc.net/problem/1158
public class Main_B_S5_1158_요세푸스문제 {
	static int N, K;
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		init();
		
		find();
		
		System.out.print(sb.toString());
	}
	
	static void find() {
		sb.append('<');
		
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=1; i<=N; ++i)
			list.add(i);
		
		int size = N, idx=-1;
		
		while(!list.isEmpty()) {
			idx = (idx+K)%size;
			sb.append(list.get(idx)).append(", ");
			list.remove(idx);
			
			idx--;
			size--;
			if(size != 0)
				idx = (idx+size)%size;
		}
		
		sb.setLength(sb.length()-2);
		sb.append('>');
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	}
}
