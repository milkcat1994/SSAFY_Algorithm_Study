// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12944

function solution(arr) {
    return arr.reduce((a, c) => a+c) / arr.length;
}