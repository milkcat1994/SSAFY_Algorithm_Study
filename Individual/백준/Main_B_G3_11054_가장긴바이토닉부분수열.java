import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -가장 긴 바이토닉 부분 수열-
 */

//출처 : https://www.acmicpc.net/problem/11054
public class Main_B_G3_11054_가장긴바이토닉부분수열 {
	static int N;
	static int[] arr;
	static List<Integer> list;
	
	public static void main(String[] args) throws Exception {
		init();
		int max=0, ti;
		for(int i=0; i<N; ++i) {
			ti = find(i);
			max = max > ti ? max : ti;
		}
		System.out.print(max+1);
	}
	
	// 선택할 idx를 넘겨준다.
	static int find(int ci) {
		list = new ArrayList<>();
		int size, idx, left=0, right=0;
		int max = arr[ci];
		
		// 해당 idx까지 증가하는 LIS 찾기
		for(int i=0; i<ci; ++i) {
			if(max <= arr[i]) continue;
			
			size = list.size();
			idx = getPoint(0, size-1, arr[i]);
			if(idx > size-1)
				list.add(arr[i]);
			else
				list.set(idx, arr[i]);
		}
		left = list.size();
		
		list.clear();
		for(int i=N-1; i>ci; --i) {
			if(max <= arr[i]) continue;
			
			size = list.size();
			idx = getPoint(0, size-1, arr[i]);
			if(idx > size-1)
				list.add(arr[i]);
			else
				list.set(idx, arr[i]);
		}
		right = list.size();
		
		return left+right;
	}
	
	// 이분법으로 들어오는 숫자가 들어갈 자리를 찾는다. -> 작은것중 큰 것
	static int getPoint(int s, int e, int target) {
		int start=s, end=e;
		
		if(e == -1 || list.get(e) < target) return e+1;
		
		while(start <= end) {
			int m = (start+end)/2;

			if(list.get(m) < target) {
				start = m+1;
			}
			else {
				end = m-1;
			}
		}
		return end+1;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		arr = new int[N];
		for(int i=0; i<N; ++i)
			arr[i] = Integer.parseInt(st.nextToken());
	}
}
