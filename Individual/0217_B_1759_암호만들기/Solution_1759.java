import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 *-암호만들기- 
 *1. 조합코드 이용한다.
 *2. 그러나 최소 하나의 모음, 두개의 자음을 포함해야 한다.
 *3. 선택한 문자가 자모음인지 확인하고 갯수를 aflag,bflag로 저장한다.
 *4. 정당한 개수라면 해당 암호를 출력한다.
 */

//출처 : https://www.acmicpc.net/problem/1759
public class Solution_1759 {
	static int L,C;
	static List<Character> list = new ArrayList<Character>();
	static Stack<Character> stack = new Stack<Character>();
	static StringBuilder sb = new StringBuilder();
	
	//모음, 자음
	//모음 : a e i o u
	static int aflag=0, bflag=0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		String tempString = br.readLine();
		for(int i = 0; i < C; ++i) {
			list.add(tempString.charAt(i*2));
		}
		
		//입력 받은 알파벳 오름차순 정렬
		list.sort(null);
		
		getPwd(0,L,0);
		
		System.out.println(sb.toString());
	}
	
	public static void getPwd(int index, int limit, int count) {
		if(count >= limit) {
			//자모음이 원하는 개수로 들어왔는지 확인
			if(aflag >=1 && bflag >=2) {
				//들어왔다면 선택한 문자 출력
				for(Character c : stack) {
					sb.append(c);
				}
				sb.append('\n');
			}
			return;
		}
		
		//이전에 선택한것이 자음인지 모음인지 기억하기 위한 변수
		boolean tflag;
		for(int idx = index; idx < C; ++idx) {
			tflag = containsA(idx);
			if(tflag) aflag++;
			else bflag++;
			
			stack.push(list.get(idx));
			getPwd(idx+1, limit, count+1);
			stack.pop();
			
			if(tflag) aflag--;
			else bflag--;
		}
	}
	
	//모음이라면 true 반환
	public static boolean containsA(int index) {
		Character c = list.get(index);
		if(c.equals('a') || c.equals('e') || c.equals('i') || c.equals('o') || c.equals('u'))
			return true;
		return false;
	}
}