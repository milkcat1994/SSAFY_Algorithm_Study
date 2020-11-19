import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -피자굽기-
 * 순차적으로 화덕의 지름을 입력받되 나중에 들어오는 길이는 이전에 나온 길이보다 짧거나 같아야한다.
 * 그럴경우 내림차순으로 화덕의 지름이 구해지며, 뒤쪽부터 순차적으로 확인하며 피자를 쌓아올리면된다.
 */

//출처 : https://www.acmicpc.net/problem/1756
public class Main_B_G5_1756_피자굽기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int D = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[D];
		int ex=Integer.MAX_VALUE, cur;
		st = new StringTokenizer(br.readLine(), " ");
		
		for(int i=0; i<D; ++i) {
			cur = Integer.parseInt(st.nextToken());
			if(ex < cur) {
				cur = ex;
			}
			arr[i] = cur;
			ex = cur;
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int si=D-1;
		while(st.hasMoreTokens()) {
			cur = Integer.parseInt(st.nextToken());
			for(int i=si; i>=0; --i) {
				si=i-1;
				if(cur<=arr[i]) {
					break;
				}
			}
			if(si==-1) {
				System.out.println(0);
				return;
			}
		}
		System.out.println(si+2);
	}
}
