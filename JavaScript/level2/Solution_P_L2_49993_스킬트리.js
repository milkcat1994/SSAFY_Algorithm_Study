// 출처 : https://programmers.co.kr/learn/courses/30/lessons/64065

function solution(skill, skill_trees) {
    var answer = 0;

    let i=0;
    let map = new Map();
    for(let c of skill.split(''))
        map.set(c, i++);
    
    skill_trees.map((s) => {
        let curIdx=-1;
        let isRight = true;
        let ableArr = new Array(skill.length+1)
        ableArr[0] = true;
        s.split('').map((char) => {
            if(map.has(char)){
                if(curIdx > map.get(char) || !ableArr[map.get(char)])
                    isRight = false;
                curIdx = map.get(char);
                ableArr[map.get(char)+1] = true;
            }
        })
        if(isRight) answer++;
    })
    return answer;
}