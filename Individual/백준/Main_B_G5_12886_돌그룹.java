import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -돌그룹-
 */

//출처 : https://www.acmicpc.net/problem/12886
public class Main_B_G5_12886_돌그룹 {
	
	static class Game{
		int a,b,c, flag;
		Game(int a, int b, int c, int flag){
			this.a = a;
			this.b = b;
			this.c = c;
			this.flag = flag;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		boolean[][] memo = new boolean[1501][1501];
		int A,B,C,target;
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		target = (A+B+C)/3;
		if((A+B+C)%3 != 0) {
			System.out.println(0);
			return;
		}
		Game tg;
		int res=0, ca,cb,cc, nf,ne;
		
		Queue<Game> que = new LinkedList<>();
		memo[A][B] = true;
		que.offer(new Game(A, B, C, 1<<0));
		res += (A == target) ? 1 : 0;
		res += (B == target) ? 1 : 0;
		res += (C == target) ? 1 : 0;
		if(res > 1) {
			System.out.println(1);
			return;
		}
		
		while(!que.isEmpty()) {
			tg = que.poll();
			res = 0;
			ca = tg.a; cb = tg.b; cc = tg.c;
			res += (ca == target) ? 1 : 0;
			res += (cb == target) ? 1 : 0;
			res += (cc == target) ? 1 : 0;
			if(res > 1) {
				System.out.println(1);
				return;
			}
			
			if((tg.flag & 1<<0) ==0) {
				if(ca>cb) {
					nf=ca-cb; ne=cb+cb;
					if(!memo[nf][ne]) {
						que.offer(new Game(nf, ne, cc, 1<<0));
						memo[nf][ne] = true;
					}
				}
				else if(ca<cb) {
					nf=ca+ca; ne=cb-ca;
					if(!memo[nf][ne]) {
						que.offer(new Game(nf, ne, cc, 1<<0));
						memo[nf][ne] = true;
					}
				}
			}

			if((tg.flag & 1<<1) ==0) {
				if(ca>cc) {
					nf=ca-cc; ne=cc+cc;
					if(!memo[nf][ne]) {
						que.offer(new Game(nf, cb, ne, 1<<1));
						memo[nf][ne] = true;
					}
				}
				else if(ca<cc) {
					nf=ca+ca; ne=cc-ca;
					if(!memo[nf][ne]) {
						que.offer(new Game(nf, cb, ne, 1<<1));
						memo[nf][ne] = true;
					}
				}
			}

			if((tg.flag & 1<<2) ==0) {
				if(cb>cc) {
					nf=cb-cc; ne=cc+cc;
					if(!memo[nf][ne]) {
						que.offer(new Game(ca, nf, ne, 1<<2));
						memo[nf][ne] = true;
					}
				}
				else if(cb<cc) {
					nf=cb+cb; ne=cc-cb;
					if(!memo[nf][ne]) {
						que.offer(new Game(ca, nf, ne, 1<<2));
						memo[nf][ne] = true;
					}
				}
			}
		}
		System.out.println(0);
	}
}
