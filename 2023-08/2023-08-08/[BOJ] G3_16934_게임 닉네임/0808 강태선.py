import sys
from collections import defaultdict

class Node:
    def __init__(self):
        self.word = False
        self.children = defaultdict(Node)


class Trie:
    def __init__(self):
        self.head = Node()

    def insert(self, word):
        node = self.head

        for char in word:
            node = node.children[char]
        dic[word] += 1
        node.word = True

    def search(self, word):
        node = self.head
        return_char = ''

        for char in word:
            return_char += char
            if char not in node.children:
                return return_char
            node = node.children[char]

        if node.word:
            return_char += str(dic[return_char]+1)
        return return_char


if __name__ == "__main__":
    N = int(sys.stdin.readline())
    dic = defaultdict(int)
    tree = Trie()

    for _ in range(N):
        word = sys.stdin.readline().rstrip()
        print(tree.search(word))
        tree.insert(word)
