import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -가르침-
 * 1. 비트마스킹 이용 조합으로 알파벳 뽑고 해당 알파벳 비교
 */

//출처 : https://www.acmicpc.net/problem/1062
public class Main_B_G4_1062_가르침_2 {
	//알파벳 26개
	static final int ALPH_SIZE = 26;
	static int isSelected = 0;	//bitMasking
	static int[] word;
	static int N,K;
	static int maxResult = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		word = new int[N];
		
		String tempStr;
		int strSize;
		for(int i = 0; i < N; ++i) {
			tempStr = br.readLine();
			strSize = tempStr.length();
			for(int j = 0; j < strSize; ++j) {
				word[i] |= (1 << (tempStr.charAt(j)-'a'));
			}
		}
		//a,c,t,i,n 을 배우지 못한다면 읽을 수 없다.
		if(K<5) {
			System.out.println(0);
			return;
		}
		else if(K == ALPH_SIZE) {
			System.out.println(N);
			return;
		}
		
		initUsed();
		//남은 K개수만큼 해당 알파벳 선택 하거나 하지 않을 예정
		getCombi(0, 0);
		
		System.out.println(maxResult);
	}
	
	public static void getCombi(int index, int count) {
		if(count == K) {
			//몇개 배울 수 있는지 확인하기
			int tempRes = canRead();
			maxResult = maxResult > tempRes ? maxResult : tempRes;
		}
		
		for(int i = index; i < ALPH_SIZE; ++i) {
			if((isSelected & (1<<i)) >= 1) continue;
			
			isSelected |= (1<<i);
			getCombi(i+1, count+1);
			isSelected ^= (1<<i);
		}
		
	}
	
	//비트연산 이용한 확인
	public static int canRead() {
		int res=0;
		for(int i = 0; i < N; ++i) {
			if((isSelected | word[i]) == isSelected)
				res++;
		}
		return res;
	}
	
	public static void initUsed() {
		//K가 5개 이상이라면 a,c,t,i,n을 배운다.
		isSelected |= (1 << ('a'-'a'));
		isSelected |= (1 << ('c'-'a'));
		isSelected |= (1 << ('t'-'a'));
		isSelected |= (1 << ('i'-'a'));
		isSelected |= (1 << ('n'-'a'));
		K-=5;
	}
}
