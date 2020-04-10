import java.io.BufferedReader;
import java.io.InputStreamReader;

//출처 : https://www.acmicpc.net/problem/16916
public class Main_B_G3_16916_부분문자열 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String origin = br.readLine();
		String pattern = br.readLine();
		
		System.out.print(KMP(origin, pattern) ? 1 : 0);
		
	}
	
	static boolean KMP(String origin, String pattern) {
		int oLength = origin.length();
		int pLength = pattern.length();
		int j = 0;
		int[] pi = getPi(pattern);
		
		for(int i = 0; i < oLength; ++i) {
			while(j > 0 && origin.charAt(i) != pattern.charAt(j)) {
				j = pi[j-1];
			}
			
			if(origin.charAt(i) == pattern.charAt(j)) {
				if(j == pLength-1) {
					return true;
				}
				else
					j++;
			}
		}
		return false;
	}
	
	static int[] getPi(String pattern) {
		int length = pattern.length();
		int[] pi = new int[pattern.length()];
		int j = 0;
		
		for(int i = 1; i < length; ++i) {
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
