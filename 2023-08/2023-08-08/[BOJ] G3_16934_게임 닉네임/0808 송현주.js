/*
[BOJ] 16234_게임 닉네임_골드3
https://www.acmicpc.net/problem/16234
*/

// Node 클래스
class Node {
  constructor(value = "") {
    this.value = value;
    this.isEnd = false;
    this.cnt = 0;
    this.children = [];
  }
}

// Trie 클래스
class Trie {
  constructor() {
    this.root = new Node();
    this.output = "";
  }

  // 닉네임 등록
  insert(nickname) {
    let currentNode = this.root;

    for (let char of nickname) {
      if (currentNode.children[char] === undefined) {
        currentNode.children[char] = new Node(currentNode.value + char);
      }
      currentNode = currentNode.children[char];
    }
    // 해당 char가 마지막 문자였다면, 단어 수 세기
    currentNode.isEnd = true;
    currentNode.cnt += 1;
  }

  // 단어 검색
  search(nickname) {
    let currentNode = this.root;
    for (let char of nickname) {
      // 기존에 등록된 닉네임이 아니라면
      if (!currentNode.children[char]) {
        return currentNode.value + char;
      }
      currentNode = currentNode.children[char];
    }

    // 기존에 등록된 닉네임이 존재한다면
    if (currentNode.isEnd) {
      return `${currentNode.value}${currentNode.cnt + 1}`;
    }
    return currentNode.value;
  }
}

const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const answer = [];
const N = +input[0];
const trie = new Trie();
for (let i = 1; i <= N; i++) {
  answer.push(trie.search(input[i]));
  trie.insert(input[i]);
}

console.log(answer.join("\n"));
