// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42748

function solution(array, commands) {
    var answer = [];
    commands.map((arr) => {
        answer.push(array.slice(arr[0]-1, arr[1]).sort((a,b) => a-b)[arr[2]-1]);
    })
    return answer;
}