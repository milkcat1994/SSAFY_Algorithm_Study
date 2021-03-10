// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42746

function solution(numbers) {
    var answer = numbers.map((num) => num+"")
        .sort((a,b) => (b+a)*1 - (a+b)*1)
        .join("");
    return answer.replace(/^0+/,'0');
}