import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -단어섞기-
 */

//출처 : https://www.acmicpc.net/problem/9177
public class Main_B_G5_9177_단어섞기 {
	static String str1, str2, str3;
	static int len1, len2, len3;
	static boolean[][] isSelected;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			str1 = st.nextToken();	len1=str1.length();
			str2 = st.nextToken();	len2=str2.length();
			str3 = st.nextToken();	len3=str3.length();
			isSelected = new boolean[len1+1][len2+1];
			
			sb.append("Data set ").append(tc).append(": ");
			if(dfs(0,0,0))
				sb.append("yes");
			else
				sb.append("no");
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
	
	static boolean dfs(int l, int r, int cnt) {
		if(isSelected[l][r]) return false;
		if(cnt>= len3) return true;
		isSelected[l][r] = true;
		
		if(l<len1 && str1.charAt(l) == str3.charAt(cnt)) {
			if(dfs(l+1, r, cnt+1)) return true;
		}
		
		if(r<len2 && str2.charAt(r) == str3.charAt(cnt)) {
			if(dfs(l, r+1, cnt+1)) return true;
		}
		
		return false;
	}
}
