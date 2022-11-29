from graphviz import Digraph
import re

eq = []
A = ""
w = ""


def loader(input):
    global A, w
    with open(input) as f:
        lines = f.readlines()
    for l in lines:
        if l[0] == "(":
            eq.append(l[:-1])
        elif l[0] == "A":
            A = l[5:-2]
        elif l[0] == 'w':
            w = l[4:]
    A = re.sub('[, ]', '', A)
    w = re.sub('[, ]', '', w)


D = []
I = []


def dependence():
    for e1 in eq:
        for e2 in eq:
            left1 = e1[4]
            right1 = e1[4:]
            left2 = e2[4]
            right2 = e2[4:]
            if left1 in right2 or \
                    left2 in right1:
                D.append((e1[1], e2[1]))
            else:
                I.append((e1[1], e2[1]))


def drawGraph(word, alphabet, D):
    dot = Digraph()
    dict = {}
    new_word = []
    for a in alphabet:
        dict[a] = 0
    for w in word:
        dot.node(w + str(dict[w]), w + str(dict[w]))
        new_word.append(w + str(dict[w]))
        dict[w] = dict[w] + 1
    # dot.edges(['AB', 'AB', 'AB', 'BC', 'BA', 'CB'])
    edges = []

    for w1 in range(len(word)):
        for w2 in range(w1 + 1, len(word)):
            for d in D:
                if (word[w1], word[w2]) == d:
                    # dot.edge(str(new_word[w1]), str(new_word[w2]))
                    edges.append((str(new_word[w1]), str(new_word[w2])))
    print(edges)
    for e1 in edges:
        s = e1[0]
        e = e1[1]
        edges_excluding = [ed for ed in edges if ed != e1]
        print("     for", s, e)
        if can_simplify(s, e, edges_excluding):
            edges = edges_excluding

    for e in edges:
        dot.edge(e[0], e[1])

    # dot.edges(edges)
    # dot.edges(dot.edges()[:-1])

    print(dot.source)
    dot.render(view=True)


def can_simplify(start, end, edges):
    print(start, end, len(edges), edges)
    if start == end:
        return True
    for e in edges:
        print(e, len(edges))
        if e[0] == start:
            next_hop = e[1]
            if next_hop==end or can_simplify(next_hop, end, [ed for ed in edges if ed != e]):
                return True
    return False


def printT(D):
    print("D = {", end="")
    for d in range(len(D) - 1):
        print("(", D[d][0], ",", D[d][1], ")", sep="", end=",")
    print("(", D[len(D) - 1][0], ",", D[len(D) - 1][1], ")", sep="", end="}\n")


# input = 'input1.txt'
input = 'input2.txt'

loader(input)
dependence()
for e in eq:
    print(e)
print(A)
print(w)
printT(D)
printT(I)
drawGraph(w, A, D)
