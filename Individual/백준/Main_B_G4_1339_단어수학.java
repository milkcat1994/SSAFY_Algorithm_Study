import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * -단어수학-
 */

//출처 : https://www.acmicpc.net/problem/1339
public class Main_B_G4_1339_단어수학 {
	static final int ALPH_LENGTH = 'Z'-'A'+1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		String str;
		int[] arr = new int[ALPH_LENGTH];
		int sl,si;
		for(int i=0; i<N; ++i) {
			str = br.readLine();
			sl = str.length();
			si=0;
			while(si<sl) {
				arr[str.charAt(si)-'A'] += pow(sl-si);
				si++;
			}
		}
		
		Arrays.sort(arr);
		int res=0, idx=9;
		for(int i=ALPH_LENGTH-1; i>=0; --i) {
			res+=idx*arr[i];
			idx--;
		}
		System.out.println(res);
	}
	
	// y^x
	public static int pow(int x) {
		int y = 1;
		while(--x>0) {
			y*=10;
		}
		return y;
	}
}
