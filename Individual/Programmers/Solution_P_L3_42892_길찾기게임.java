import java.util.Arrays;

/*
 * -길 찾기 게임-
 * 1. 하위 Node의 left, right의 구간을 상속하는 방식
 * 
 * 테스트 1 〉	통과 (1.04ms, 51.7MB)
 * 테스트 2 〉	통과 (0.98ms, 52.4MB)
 * 테스트 3 〉	통과 (0.94ms, 51.9MB)
 * 테스트 4 〉	통과 (1.02ms, 52MB)
 * 테스트 5 〉	통과 (1.01ms, 52.2MB)
 * 테스트 6 〉	통과 (4.34ms, 54MB)
 * 테스트 7 〉	통과 (4.76ms, 54.5MB)
 * 테스트 8 〉	통과 (16.15ms, 62.2MB)
 * 테스트 9 〉	통과 (36.81ms, 66.5MB)
 * 테스트 10 〉	통과 (7.63ms, 54.4MB)
 * 테스트 11 〉	통과 (38.75ms, 64.6MB)
 * 테스트 12 〉	통과 (31.11ms, 63.4MB)
 * 테스트 13 〉	통과 (1.60ms, 54MB)
 * 테스트 14 〉	통과 (8.59ms, 54.6MB)
 * 테스트 15 〉	통과 (44.45ms, 62.6MB)
 * 테스트 16 〉	통과 (53.60ms, 62.7MB)
 * 테스트 17 〉	통과 (6.88ms, 53.5MB)
 * 테스트 18 〉	통과 (62.25ms, 63.2MB)
 * 테스트 19 〉	통과 (11.90ms, 54.3MB)
 * 테스트 20 〉	통과 (30.70ms, 61.9MB)
 * 테스트 21 〉	통과 (56.97ms, 63.5MB)
 * 테스트 22 〉	통과 (54.48ms, 63.6MB)
 * 테스트 23 〉	통과 (67.56ms, 65.1MB)
 * 테스트 24 〉	통과 (1.15ms, 51.9MB)
 * 테스트 25 〉	통과 (1.59ms, 52.5MB)
 * 테스트 26 〉	통과 (4.00ms, 58.5MB)
 * 테스트 27 〉	통과 (1.01ms, 53.9MB)
 * 테스트 28 〉	통과 (0.99ms, 53.3MB)
 * 테스트 29 〉	통과 (0.99ms, 52.8MB)
 * 풀이 시간 : 1H
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/42892
public class Solution_P_L3_42892_길찾기게임 {
	static int answerIdx;
	
	public int[][] solution(int[][] nodeinfo) {
		int nodeNum = nodeinfo.length;
		int[][] answer = new int[2][nodeNum];
		
		Node[] nodeArr = new Node[nodeNum];
		for (int row = 0; row < nodeNum; ++row) {
			nodeArr[row] = new Node(nodeinfo[row][0],nodeinfo[row][1], row+1, -1, 100001);
		}
		
		Arrays.sort(nodeArr, (o1,o2) -> {
			if(o1.y == o2.y)
				return o1.x-o2.x;
			return o2.y-o1.y;
		});
		
		int exY=nodeArr[0].y+1;
		int exStartIdx=0, startIdx=0;
		
		Node curNode, tempNode;
		for(int row = 1; row < nodeNum; ++row) {
			curNode = nodeArr[row];
			
			// 다음 row로 넘어갔다면 startIdx 갱신
			if(exY > curNode.y) {
				exStartIdx = startIdx;
				startIdx = row;
				exY = curNode.y;
			}
			// exStartIdx ~ startIdx 순회하며 leftMax, rightMax에 맞는 Node찾기
			// curNode가 tempNode의 leftMax, rightMax의 사이에 있는지 판단.
			for(int idx = exStartIdx; idx<startIdx; ++idx) {
				tempNode = nodeArr[idx];
				
				// add right
				if(tempNode.right == null && tempNode.x < curNode.x && curNode.x < tempNode.rightMax) {
					curNode.setLeftMax(tempNode.x);
					curNode.setRightMax(tempNode.rightMax);
					tempNode.setRight(curNode);
					break;
				}
				// add left
				else if(tempNode.left == null && tempNode.leftMax < curNode.x && curNode.x < tempNode.x) {
					curNode.setRightMax(tempNode.x);
					curNode.setLeftMax(tempNode.leftMax);
					tempNode.setLeft(curNode);
					break;
				}
			} // end for(int idx)
		} // end for(int row)
		
		Node topNode = nodeArr[0];
		
		answerIdx = 0;
		preOrder(topNode, answer);

		answerIdx = 0;
		postOrder(topNode, answer);
		
		return answer;
	}
	
	public void preOrder(Node curNode, int[][] answer) {
		if(curNode == null) return;
		
		answer[0][answerIdx++] = curNode.num;
		
		preOrder(curNode.left, answer);
		
		preOrder(curNode.right, answer);
	}
	public void postOrder(Node curNode, int[][] answer) {
		if(curNode == null) return;
		
		postOrder(curNode.left, answer);
		
		postOrder(curNode.right, answer);
		
		answer[1][answerIdx++] = curNode.num;
	}
	
	public class Node {
		int x,y, num;
		int leftMax, rightMax;
		Node left,right;
		
		Node(int x, int y, int num, int leftMax, int rightMax){
			this.x = x;
			this.y = y;
			this.num = num;
			this.leftMax = leftMax;
			this.rightMax = rightMax;

			this.left = null;
			this.right = null;
		}
		
		void setLeft(Node left){
			this.left = left;
		}
		
		void setRight(Node right) {
			this.right = right;
		}
		
		void setLeftMax(int leftMax) {
			this.leftMax = leftMax;
		}
		
		void setRightMax(int rightMax) {
			this.rightMax = rightMax;
		}
	}

	public static void main(String[] args) {
		Solution_P_L3_42892_길찾기게임 sol = new Solution_P_L3_42892_길찾기게임();
		int[][] arr = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
		int[][] answer = sol.solution(arr);
		for (int row = 0; row < answer.length; ++row) {
			for (int col = 0; col < answer[row].length; ++col) {
				System.out.print(answer[row][col]+" ");
			}
			System.out.println();
		}
	}
}