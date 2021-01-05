// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12906

function solution(arr) {
    var answer = arr.filter((n, idx) => n !== arr[idx+1]);

    return answer;
}