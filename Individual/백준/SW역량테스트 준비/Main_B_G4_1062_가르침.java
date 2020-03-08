import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * -가르침-
 * 1. 조합으로 알파벳 뽑고 해당 알파벳 비교
 * 
 * => 비트마스킹으로 수정 가능할듯
 */

//출처 : https://www.acmicpc.net/problem/1062
public class Main_B_G4_1062_가르침 {
	//알파벳 26개
	static final int ALPH_SIZE = 26;
	static int N,K;
	static Set<Char>[] set;
	static Char[] alph;
	static int maxResult = Integer.MIN_VALUE;
	
	static class Char {
		char c;
		boolean used;
		Char(char c) {
			this.c = c;
			this.used = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		set = new HashSet[N];
		alph = new Char[ALPH_SIZE];
		for(int i = 0; i < ALPH_SIZE; ++i) {
			alph[i] = new Char((char)('a'+i));
		}
		
		String tempStr;
		int strSize;
		for(int i = 0; i < N; ++i) {
			set[i] = new HashSet<>();
			tempStr = br.readLine();
			strSize = tempStr.length();
			for(int j = 0; j < strSize; ++j) {
				set[i].add(alph[tempStr.charAt(j)-'a']);
			}
		}
		
		//a,c,t,i,n 을 배우지 못한다면 읽을 수 없다.
		if(K<5) {
			System.out.println(0);
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
			//각 set다 돌면서 used가 false인것 있으면 pass
			int tempRes = canRead();
			maxResult = maxResult > tempRes ? maxResult : tempRes;
			return;
		}
		
		for(int i = index; i < ALPH_SIZE; ++i) {
			if(alph[i].used) continue;
			
			alph[i].used = true;
			getCombi(i+1, count+1);
			alph[i].used = false;
		}
		
	}
	
	public static int canRead() {
		int res=0;
		Iterator<Char> iter;
		N:for(int i = 0; i < N; ++i) {
			iter = set[i].iterator();
			while(iter.hasNext()) {
				//used가 false인것이 있다면 해당 글자 읽지 못한다.
				if(!iter.next().used)
					continue N;
			}
			res++;
		}
		return res;
	}
	
	public static void initUsed() {
		//K가 5개 이상이라면 a,c,t,i,n을 배운다.
		alph['a'-'a'].used = true;
		alph['c'-'a'].used = true;
		alph['t'-'a'].used = true;
		alph['i'-'a'].used = true;
		alph['n'-'a'].used = true;
		K-=5;
	}
}
