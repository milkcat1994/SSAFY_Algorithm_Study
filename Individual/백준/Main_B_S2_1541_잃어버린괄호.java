import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -잃어버린 괄호-
 * 한번 음수가 나온다면 그 뒤는 모두 음수이다.
 */

//출처 : https://www.acmicpc.net/problem/1541
public class Main_B_S2_1541_잃어버린괄호 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String str = br.readLine();
		st = new StringTokenizer(str, "+-");
		
		List<Character> operations = new ArrayList<>();
		List<Integer> nums = new ArrayList<>();
		int res = Integer.parseInt(st.nextToken());
		while(st.hasMoreTokens())
			nums.add(Integer.parseInt(st.nextToken()));
		
		for(int i=0; i<str.length(); ++i) {
			switch (str.charAt(i)) {
			case '+':
			case '-':
				operations.add(str.charAt(i));
				break;
			}
		}
		
		int size = nums.size();
		boolean isMinus = false;
		for(int i=0; i<size; ++i) {
			if(operations.get(i) == '-')
				isMinus = true;
			
			if(isMinus)
				res -= nums.get(i);
			else
				res += nums.get(i);
		}
		
		System.out.println(res);
	}
}
