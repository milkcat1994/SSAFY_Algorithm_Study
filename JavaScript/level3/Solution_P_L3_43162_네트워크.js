// 출처 : https://programmers.co.kr/learn/courses/30/lessons/43162

function solution(n, computers) {
    var answer = 0;
    let isVisited = new Array(n);
    
    const dfs = (start) => {
        isVisited[start]=true;
        for(let i=0; i<n; i++){
            if(!isVisited[i] && computers[start][i]==1){
                dfs(i);
            }
        }
    }
    
    for(let i=0; i<n; i++){
        if(!isVisited[i]){
            answer++;
            dfs(i);
        }
    }
    
    return answer;
}