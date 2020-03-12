import java.io.BufferedReader;
import java.io.InputStreamReader;


/*
 * -책 페이지-
 * 참조 : https://www.slideshare.net/Baekjoon/baekjoon-online-judge-1019
 */

//출처 : https://www.acmicpc.net/problem/1019
public class Main_B_G1_1019_책페이지 {
	static int[] arr;
	static int start,end;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		start = 1;
		end = Integer.parseInt(str);
		arr=new int[10];
		int x = 0;
		
		while(true) {
			setNum(x);
			if(start==end) break;
			
			for(int i = 0; i<=9; ++i) {
				arr[i]+=((end-start)/10+1)*(int)Math.pow(10, x);
			}
			x++;
			start/=10;
			end/=10;
		}
		
		for(int i = 0; i<10; ++i)
			System.out.print(arr[i]+" "); 
	}

	static void setNum(int x) {
		while(start%10!=0) {
			calc(start, x);
			if(start==end) return;
			++start;
		}
		
		while(end%10!=9) {
			calc(end, x);
			if(start==end) return;
			--end;
		}
	}
	
	static void calc(int n, int x) {
		while(n>0) {
			arr[n%10] += (int)Math.pow(10, x);
			n/=10;
		}
	}
}
