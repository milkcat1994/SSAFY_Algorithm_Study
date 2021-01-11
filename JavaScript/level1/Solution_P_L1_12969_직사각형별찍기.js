// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12969

process.stdin.setEncoding('utf8');
process.stdin.on('data', data => {
    const n = data.split(" ");
    const c = Number(n[0]), r = Number(n[1]);
    console.log(`${"*".repeat(c)}\n`.repeat(r));
});