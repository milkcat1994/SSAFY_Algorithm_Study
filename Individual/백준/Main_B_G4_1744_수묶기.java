import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -수묶기-
 * 1을 중심으로 좌/우로 나누어 오름차순 정렬을 한다.
 * 우측은 모두 양수로 2개씩 짝지어 곱해준다.
 * 좌측도 0을 포함해서 2개씩 짝지어 곱해준다.
 * 1의경우 더해주는것이 더 큰 값을 내므로 더해준다.
 */

//출처 : https://www.acmicpc.net/problem/1744
public class Main_B_G4_1744_수묶기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int res=0,a,b, ridx=N-1, lidx=0;
		for(int i=N-1; i>0; i-=2) {
			a = arr[i];
			b = arr[i-1];
			if(a>1 && b>1) {
				res+=a*b;
				ridx=i-2;
			}
			else {
				ridx=i;
				break;
			}
		}
		
		for(int i=0; i<ridx; i+=2) {
			a = arr[i];
			b = arr[i+1];
			if(a<=0 && b<=0) {
				res+=a*b;
				lidx=i+2;
			}
			else {
				lidx=i;
				break;
			}
		}
		
		for(int i=lidx; i<=ridx; ++i) {
			res+=arr[i];
		}
		
		System.out.println(res);
	}
}
