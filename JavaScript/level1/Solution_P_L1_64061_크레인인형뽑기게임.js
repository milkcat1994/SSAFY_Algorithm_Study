// 출처 : https://programmers.co.kr/learn/courses/30/lessons/64061

class Stack {
    constructor(){
        this.arr = [];
    }

    push(item){
        this.arr.push(item);
    }

    pop(){
        return this.arr.pop();
    }

    peek(){
        return this.arr[this.arr.length-1];
    }

    isEmpty(){
        return this.arr.length == 0 ? true : false
    }
}

function solution(board, moves) {
    var answer = 0;
    var boardSize = board.length;
    var boardStack = new Array(boardSize);
    for(let i=0; i<boardSize; i++){
        boardStack[i]= new Stack();
    }
    
    for(let row=boardSize-1; row>=0; row--){
        for(let col=0; col<boardSize; col++){
            if(board[row][col]!=0)
                boardStack[col].push(board[row][col]);
        }
    }

    var peekStack = new Stack();
    moves.map((curIdx) => {
        if(!boardStack[curIdx].isEmpty()){
            if(!peekStack.isEmpty()){
                if(boardStack[curIdx].peek() == peekStack.peek()){
                    answer++;
                    boardStack[curIdx].pop();
                    peekStack.pop();
                    return;
                }
            }
            peekStack.push(boardStack[curIdx].pop());
            return;
        }
    })
    return answer;
}