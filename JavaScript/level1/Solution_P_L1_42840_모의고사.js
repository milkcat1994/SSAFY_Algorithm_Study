// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42840

function solution(answers) {
    var answer = [];
    let member1 = [1,2,3,4,5];
    let member2 = [2,1,2,3,2,4,2,5];
    let member3 = [3,3,1,1,2,2,4,4,5,5];
    var members = [[...member1], [...member2], [...member3]];
    var cnts = [];
    
    members.map((arr) => {
        cnts.push(check(answers, arr));
    });
    
    let max = Math.max(...cnts);
    cnts.map((cnt, idx) => {
        if(cnt == max) answer.push(idx+1);
    })
    
    return answer;
}

function check(answer, member){
    let size = member.length;
    let res = 0;
    
    answer.map((num, idx) => {
        if(num == member[idx%size])
            res++;
    })
    
    return res;
}