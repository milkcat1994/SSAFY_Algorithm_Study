// 출처 : https://programmers.co.kr/learn/courses/30/lessons/72410

function solution(new_id) {
    var answer = new_id
    .toLowerCase() // 1
    .replace(/[^a-z0-9-_.]/g,'') // 2
    .replace(/\.+/g, '.') // 3
    .replace(/^\.|\.$/g, ''); // 4
    answer = answer.length == 0 ? "a" : answer; // 5
    answer = answer.substring(0,15).replace(/^\.|\.$/g, ''); // 6
    let len = answer.length;
    answer = len <= 2 ? answer + answer.charAt(len-1).repeat(3-len) : answer; // 7
    return answer;
}