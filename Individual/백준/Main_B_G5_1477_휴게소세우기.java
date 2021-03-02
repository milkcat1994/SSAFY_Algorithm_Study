import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -휴게소 세우기-
 * 1. 이분탐색을 이용한 매개 변수 탐색
 * 
 * 메모리 : 14524KB
 * 시간 : 128ms
 * 풀이 시간 : 2H 30M
 */

//출처 : https://www.acmicpc.net/problem/1477
public class Main_B_G5_1477_휴게소세우기 {
	static int N,M, L;
	
	static List<Place> places;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.println(solve());
	}
	
	static int solve() {
		int low, hi, mid;
		low = 1;
		hi = L-1;
		
		int m;
		while(low<=hi) {
			mid = (low+hi)/2;
			m=0;
			
			for(Place tp : places) {
				if(tp.dist <= mid) break;
				m += (tp.dist-1)/mid;
			}
			if(m<=M)
				hi=mid-1;
			else
				low=mid+1;
		}
		
		return low;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		List<Integer> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; ++i) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(list);
		
		int s,e;
		s = 0;
		places = new ArrayList<>();
		for(int i=0; i<N; ++i) {
			e = list.get(i);
			
			places.add(new Place(s,e));
			s = e;
		}
		places.add(new Place(s,L));
		
		Collections.sort(places, new Comparator<Place>() {
			@Override
			public int compare(Place o1, Place o2) {
				return o2.dist - o1.dist;
			}
		});
	}
	
	static class Place {
		int s,e;
		int dist;
		
		Place(int s, int e){
			this.s = s;
			this.e = e;
			this.dist = e-s;
		}
	}
}
