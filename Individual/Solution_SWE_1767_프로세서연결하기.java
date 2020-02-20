import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -프로세서 연결하기-
 * 1. 벽에 붙은 코어를 제외하고 list에 넣어준다.
 * 2. 각 코어가 연결할 수 있는 방향으로 전선을 설치하며 확인한다.
 * 3. 전선 설치 전에 설치 가능한지 먼저 확인 후 설치 한다.
 * 4. 4방향에 대해 모두 설치 해본다.
 * 5. 코어 갯수가 많다면 전선 길이 바로 갱신
 * └──코어 갯수가 같다면 전선 길이 짧은 것으로 갱신
 */

//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4suNtaXFEDFAUf
public class Solution_SWE_1767_프로세서연결하기 {
	static int N;
	static char[][] board;
	static boolean[][] isVisited;
	 
	//상하좌우
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	 
	public static class Core{
	    int row,col;
	     
	    Core(int row, int col){
	        this.row = row;
	        this.col = col;
	    }
	}
	 
	static List<Core> list = new ArrayList<Core>();
	static int coreSize;
	static int maxCore;
	static int minBridge;
	 
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int T = Integer.parseInt(st.nextToken());
	    StringBuilder sb = new StringBuilder();
	     
	    String tempStr;
	    for(int t = 1; t <= T; ++t) {
	        maxCore = 0;
	        minBridge = Integer.MAX_VALUE;
	        N = Integer.parseInt(br.readLine());
	        board = new char[N][N];
	        isVisited = new boolean[N][N];
	         
	        for (int row = 0; row < N; ++row) {
	            tempStr = br.readLine();
	            for (int col = 0; col < N; ++col) {
	                board[row][col] = tempStr.charAt(col*2);
	                switch (board[row][col]) {
	                case '1':
	                	//벽에 붙은 코어 제외하고 설치 할것이다.
	                    if(row!=0 && row!=N-1 && col!=0 && col!=N-1)
	                        list.add(new Core(row, col));
	                    break;
	                }
	            }
	        }
	        coreSize = list.size();
	         
	        selectCore(0,0,0);
	        sb.append("#").append(t).append(" ").append(minBridge).append('\n');
	        list.clear();
	    } //end TestCase
	     
	    System.out.print(sb.toString());
	}
	
	//index의 코어를 4방향으로 전선 연결해볼것이다. 또는 연결하지 않을것이다.
    public static void selectCore(int index, int count, int result) {
	     
	    if(maxCore < count) {
	        minBridge = result;
	        maxCore = count;
	    }
	    else if(maxCore == count) {
	        minBridge = minBridge < result ? minBridge : result;
	    }
	     
	    if(index >= coreSize)
	        return;
	     
	    int cr = list.get(index).row;
	    int cc = list.get(index).col;
	    int tempRes=0;
	    for(int i = 0; i < 4; ++i) {
	        if(!connectable(cr,cc,i))
	            continue;
	        tempRes = makeLine(cr,cc,i,true);
	        selectCore(index+1, count+1, result+tempRes);
	        makeLine(cr,cc,i,false);
	    }
	     
	    selectCore(index+1,count, result);
	}
	
    // 설치가 가능하다면 해당 방향으로 설치를 해나간다._'2'
    // flag가 true라면 설치, false라면 '0'으로 덮어 회수한다.
	public static int makeLine(int cr, int cc, int dir, boolean flag) {
	    int tr = 0;
	    char c = flag ? '2' : '0';
	    int nr,nc,dr=drow[dir],dc=dcol[dir];
	    nr = cr+dr; nc = cc+dc;
	    while(!isOut(nr, nc)) {
	        board[nr][nc] = c;
	        nr+=dr; nc+=dc;
	        tr++;
	    }
	    return tr;
	}
	 
	//해당 방향으로 설치가 가능한지 확인
	public static boolean connectable(int row, int col, int dir) {
	    int cr = row;   int cc = col;   int dr = drow[dir]; int dc = dcol[dir];
	    int nr = cr,nc = cc;
	     
	    while(true) {
	        nr+=dr; nc+=dc;
	        if(isOut(nr,nc))
	            return true;
	         
	        if(dontGo(nr,nc))
	            return false;
	    }
	}
	
	//해당 좌표에 코어나 전선이 있다면 전선을 설치 할 수 없다.
	public static boolean dontGo(int row, int col) {
	    if(board[row][col] == '1'|| board[row][col] == '2')
	        return true;
	    return false;
	}
	
	//경계선 밖으로 나갔다면 전선은 설치 될 수 있다.
	public static boolean isOut(int row, int col) {
	    if(row<0 || col<0 || row>=N || col>=N)
	        return true;
	    return false;
	}
}
