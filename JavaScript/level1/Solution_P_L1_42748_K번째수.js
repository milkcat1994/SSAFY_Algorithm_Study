// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42748

function solution(array, commands) {
    var answer = [];
    commands.map(([from, to, k]) => {
        answer.push(array.slice(from-1, to).sort((cur, next) => cur - next)[k-1]);
    })
    return answer;
}