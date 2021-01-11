// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42862

function solution(n, lost, reserve) {
    var students = Array(n).fill(1);
    lost.map((num) => students[num-1]--);
    reserve.map((num) => students[num-1]++);
    
    for(var idx = 0; idx < n; idx++) {
        if(students[idx] === 0) {
            if(students[idx-1] === 2){
                students[idx-1]--;
                students[idx]++;
            }
            else if(students[idx+1] === 2) {
                students[idx+1]--;
                students[idx]++;
            }
        }
    }
    
    students = students.filter((num) => num > 0);
    return students.length;
}