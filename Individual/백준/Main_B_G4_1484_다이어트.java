import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * -다이어트-
 * 투포인터 이용하여 조건 만족하는 left, right 찾기
 * 
 * 메모리 : 16384KB
 * 시간 : 156ms
 * 풀이시간 : 10M
 */

//출처 : https://www.acmicpc.net/problem/1484
public class Main_B_G4_1484_다이어트 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int G = Integer.parseInt(br.readLine());
		int l=1;
		int r=2;
		
		while(l<r) {
			if(G == r*r-l*l) {
				sb.append(r+"\n");
				l++;
				r++;
			}
			else if(G < r*r-l*l) {
				l++;
			}
			else {
				r++;
			}
		}
		
		if(sb.length()==0) {
			sb.append(-1);
		}
		System.out.print(sb.toString());
	}
}
