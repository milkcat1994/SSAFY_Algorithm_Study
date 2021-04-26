import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * -행운의 문자열-
 * 
 * set으로 중복 확인시 메모리 초과
 * -> 중복되는 알파벳을 a!으로 나눠주면서 중복수 제거
 */

//출처 : https://www.acmicpc.net/problem/1342
public class Main_B_S1_1342_행운의문자열 {
	static String str;
	static int length;
	static StringBuilder sb;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		str = br.readLine();
		length = str.length();
		
		int[] alphabet = new int['z'-'a'+1];
		for(int i=0; i<length; ++i)
			alphabet[str.charAt(i)-'a']++;
		
		permu(0, 0);
		
		for(int i=0; i<alphabet.length; ++i) {
			if(alphabet[i]>1)
				answer /= factorial(alphabet[i]);
		}
		
		System.out.print(answer);
	}
	
	static int factorial(int n) {
		int num=1;
		for(int i=1; i<=n; ++i)
			num*=i;
		return num;
	}
	
	static void permu(int idx, int flag){
		if(idx >= length) {
			answer++;
			return;
		}
		
		for(int i = 0; i < length; ++i) {
			if((flag & (1<<i)) >= 1) continue;
			if(idx != 0 && sb.toString().charAt(idx-1) == str.charAt(i)) continue;
			
			sb.append(str.charAt(i));
			permu(idx+1, flag | (1<<i));
			sb.setLength(idx);
		}
	}
}
