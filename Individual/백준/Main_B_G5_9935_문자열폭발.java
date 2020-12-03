import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * -문자열 폭발-
 * 1. StringBuilder를 이용하여 원본 문자열을 하나씩 더해나간다.
 * 2. pattern의 끝자리와 같은 문자가 나오면 pattern의 길이만큼 이전 문자열들을 본떠 확인해본다.
 * 3. 패턴일치 한다면 StringBuilder의 길이를 조절한다.
 * 
 * + pattern의 길이만큼 확인했을때 다르다면 그 길이만큼 건너뛰어도 된다.
 * └> pattern에는 중복된 문자가 없기때문
 */

//출처 : https://www.acmicpc.net/problem/9935
public class Main_B_G5_9935_문자열폭발 {
	static StringBuilder sb = new StringBuilder();
	static String pattern;
	static int ol, pl;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String origin = br.readLine();
		pattern = br.readLine();
		
		ol = origin.length();
		pl = pattern.length();
		for(int i=0; i<ol; ++i) {
			sb.append(origin.charAt(i));
			if(sb.length() >= pl && sb.charAt(sb.length()-1) == pattern.charAt(pl-1)) {
				if(find()) {
					sb.setLength(sb.length()-pl);
				}
			}
		}
		
		if(sb.length() == 0)
			System.out.println("FRULA");
		else {
			System.out.print(sb.toString());
		}
	}
	
	static boolean find() {
		StringBuilder sb2 = new StringBuilder();
		for(int i = sb.length()-pl; i<sb.length(); ++i) {
			sb2.append(sb.charAt(i));
		}
		if(sb2.toString().equals(pattern))
			return true;
		else
			return false;
	}
}
