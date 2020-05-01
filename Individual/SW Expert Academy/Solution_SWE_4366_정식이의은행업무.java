import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Solution_SWE_4366_정식이의은행업무 {
	static Set<Long> twoSet, threeSet;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; ++t) {
			char[] two = br.readLine().toCharArray();
			char[] three = br.readLine().toCharArray();
			
			twoSet = new HashSet<>();
			threeSet = new HashSet<>();
			long twoOrigin = getToTwo(two);
			long threeOrigin = getToThree(three);
			
			int x = two.length-1;
			for(char tc : two) {
				switch (tc) {
				case '0':
					twoSet.add(new Long((int) (twoOrigin+Math.pow(2, x))));
					break;
				case '1':
					twoSet.add(new Long((int) (twoOrigin-Math.pow(2, x))));
					break;
				}
				x--;
			}
			
			x = three.length-1;
			for(char tc : three) {
				switch (tc) {
				case '0':
					threeSet.add(new Long((int) (threeOrigin+Math.pow(3, x))));
					threeSet.add(new Long((int) (threeOrigin+Math.pow(3, x)*2)));
					break;
				case '1':
					threeSet.add(new Long((int) (threeOrigin-Math.pow(3, x))));
					threeSet.add(new Long((int) (threeOrigin+Math.pow(3, x))));
					break;
				case '2':
					threeSet.add(new Long((int) (threeOrigin-Math.pow(3, x))));
					threeSet.add(new Long((int) (threeOrigin-Math.pow(3, x)*2)));
					break;
				}
				x--;
			}
			
			for(Long tl : twoSet) {
				if(threeSet.contains(tl)) {
					System.out.println("#"+t+" "+tl);
					break;
				}
			}
			
		}
	}
	
	private static long getToTwo(char[] str) {
		int x = str.length-1;
		long res = 0;
		for(char tc : str) {
			if(tc == '1')
				res += Math.pow(2, x);
			x--;
		}
		return res;
	}
	
	private static long getToThree(char[] str) {
		int x = str.length-1;
		long res = 0;
		for(char tc : str) {
			if(tc >= '1')
				res += Math.pow(3, x) * (tc-'0');
			x--;
		}
		return res;
	}
	
}
