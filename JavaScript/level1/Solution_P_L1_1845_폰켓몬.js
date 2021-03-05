// 출처 : https://programmers.co.kr/learn/courses/30/lessons/1845

function solution(nums) {
    var max = [...new Set(nums)].length;
    return nums.length/2 < max ? nums.length/2 : max;
}