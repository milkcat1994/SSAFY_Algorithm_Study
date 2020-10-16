import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * -숫자고르기-
 * 1. cycle 판단
 */

//출처 : https://www.acmicpc.net/problem/
public class Main_B_G5_2668_숫자고르기 {
	static int N, maxResult;
	static int[] arr;
	static boolean[] isVisited;
	static List<Integer> resList;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		
		for(int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(br.readLine()) -1;
		}
		
		int res = 0;
		isVisited = new boolean[N];
		resList = new ArrayList<>();
		
		for(int i = 0; i < N; ++i) {
			res += cycle(i);
		}
		
		System.out.println(res);
		Collections.sort(resList);
		for(int var : resList)
			System.out.println(var+1);
	}
	
	public static int cycle(int idx){
		int cur = idx;
		int next = arr[idx];
		int cnt = 0;
		List<Integer> tempList = new ArrayList<>();
		boolean[] tempVisited = Arrays.copyOf(isVisited, N);
		
		while(!tempVisited[cur]) {
			tempVisited[cur] = true;
			tempList.add(cur);
			cnt++;
			cur = next;
			next = arr[cur];
		}
		//시작점 도착시
		if(cur == idx) {
			// tempVisited 값 isVisited에 씌우기
			resList.addAll(tempList);
			isVisited = Arrays.copyOf(tempVisited, N);
			return cnt;
		}
		return 0;
	}
}
