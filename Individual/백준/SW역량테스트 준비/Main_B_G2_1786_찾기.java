import java.io.BufferedReader;
import java.io.InputStreamReader;

//출처 : https://www.acmicpc.net/problem/1786
public class Main_B_G2_1786_찾기 {
	static StringBuilder sb = new StringBuilder();
	static int res = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String origin = br.readLine();
		String pattern = br.readLine();
		
		KMP(origin, pattern);
		System.out.println(res);
		System.out.print(sb.toString());
	}
	
	static void KMP(String origin, String pattern) {
		int oLength = origin.length();
		int pLength = pattern.length();
		int[] pi = getPi(pattern);
		int j = 0;
		
		for(int i = 0; i < oLength; ++i) {
			while(j > 0 && origin.charAt(i) != pattern.charAt(j)) {
				j = pi[j-1];
			}
			
			if(origin.charAt(i) == pattern.charAt(j)) {
				if(j == pLength-1) {
					sb.append(i-j+1+" ");
					j = pi[j];
					res++;
				}
				else {
					j++;
				}
			}
		}
	}
	
	static int[] getPi(String pattern) {
		int pLength = pattern.length();
		int[] pi = new int[pLength];
		int j = 0;
		
		for(int i = 1; i < pLength; ++i) {
			while(j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = pi[j-1];
			}
			
			if(pattern.charAt(i) == pattern.charAt(j)) {
				pi[i] = ++j;
			}
		}
		return pi;
	}
}
