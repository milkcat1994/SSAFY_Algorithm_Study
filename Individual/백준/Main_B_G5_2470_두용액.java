import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -두용액-
 * 1. two point로 좌,우 줄여가는 방식
 */

//출처 : https://www.acmicpc.net/problem/2470
public class Main_B_G5_2470_두용액 {
	
	static final long MAX=2000000000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		List<Long> list = new ArrayList<Long>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			list.add(new Long(Long.parseLong(st.nextToken())));
		}
		
		Collections.sort(list);
		
		int l=0,r=N-1;
		long diff=MAX, tempL=0;
		//result l,r 위치
		int rl=0,rr=N-1;
		
		while(l<r) {
			tempL = list.get(l) + list.get(r);
			
			//diff보다 작다면 갱신하고 0보다 작은지 비교할것.
			if(abs(tempL) < diff) {
				rl = l;	rr = r;
				diff=abs(tempL);
				//0보다 크다면 우측을 당길것.
				if(tempL > 0) {
					r--;
				}
				// 0보다 작다면 좌측을 당길것.
				else {
					l++;
				}
			}
			// diff보다 작지 않다면
			else {
				//0보다 크다면 우측을 당길것.
				if(tempL > 0) {
					r--;
				}
				// 0보다 작다면 좌측을 당길것.
				else {
					l++;
				}
			}
		} // end while(l<r)
		
		System.out.println(list.get(rl)+" "+list.get(rr));
	}
//	5
//	-100000000 -2222222 -1111 -12 -1
	public static long abs(long a) {
		return a>0 ? a : -a;
	}
}