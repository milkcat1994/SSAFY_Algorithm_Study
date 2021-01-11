// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12950

function solution(arr1, arr2) {
    return arr1.map((row, idx1) => row.map((col, idx2) => col+arr2[idx1][idx2]));
}