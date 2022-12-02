from graphviz import Digraph
from graphviz import Graph
import os
os.environ["PATH"] += os.pathsep + r"C:\Users\Loloxon\AppData\Local\Programs\Python\Python310\Graphviz\bin"

eq = []
A = ""
w = ""


def loader(idx):
    global A, w
    with open("input" + idx + ".txt", encoding="utf8") as f:
        lines = f.readlines()
    for l in lines:
        if l[0] == "(":
            eq.append(l[:-1])
        elif l[0] == "A":
            A = l[5:-2]
        elif l[0] == 'w':
            w = l[4:]
    for c in A:
        if c in '[, ]':
            c = ""
    for c in w:
        if c in '[, ]':
            c = ""


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


def drawGraph(word, alphabet, D, idx):
    global output

    dot = Digraph()
    # dot.format(format='png')
    dict = {}
    new_word = []
    for a in alphabet:
        dict[a] = 0
    for w in word:
        dot.node(w + str(dict[w]), w + str(dict[w]))
        new_word.append(w + str(dict[w]))
        dict[w] = dict[w] + 1
    edges = []

    for w1 in range(len(word)):
        for w2 in range(w1 + 1, len(word)):
            for d in D:
                if (word[w1], word[w2]) == d:
                    edges.append((str(new_word[w1]), str(new_word[w2])))
    for e1 in edges:
        s = e1[0]
        e = e1[1]
        edges_excluding = [ed for ed in edges if ed != e1]
        if can_simplify(s, e, edges_excluding):
            edges = edges_excluding

    for e in edges:
        dot.edge(e[0], e[1])

    printFNF(edges)

    this_print = dot.source
    output += this_print + "\n"

    dot.render(format="png", filename="graph" + idx, view=True)

def can_simplify(start, end, edges):
    if start == end:
        return True
    for e in edges:
        if e[0] == start:
            next_hop = e[1]
            if next_hop == end or can_simplify(next_hop, end, [ed for ed in edges if ed != e]):
                return True
    return False


def printT(D, name):
    global output

    this_print = name + " = {"
    for d in range(len(D) - 1):
        this_print += "(" + D[d][0] + "," + D[d][1] + "),"
    this_print += "(" + D[len(D) - 1][0] + "," + D[len(D) - 1][1] + ")}"
    output += this_print + "\n\n"

def printFNF(edges):
    global output

    this_print = "FNF([w]) = "

    dist = {}
    for e in edges:
        dist[e[0]] = 0
        dist[e[1]] = 0

    Q = []
    for e in edges:
        Q.append(e[0])
    while len(Q)>0:
        v = Q[0]
        Q.pop(0)
        for e in edges:
            if e[0] == v:
                dist[e[1]] = max(dist[e[0]]+1, dist[e[1]])
                Q.append(e[1])

    for idx in range(len(edges)):
        this_print += "["
        for d in dist:
            if dist[d] == idx:
                this_print += d + ", "
        if this_print[-1] == "[":
            this_print = this_print[:-1]
        else:
            this_print = this_print[:-2]
            this_print += "]"
    output += this_print + "\n\n"

def saver(idx, output):
    f = open("output" + idx + ".txt", "w")
    f.write(output)
    f.close()

# idx = '1'
idx = '2'

loader(idx)
dependence()

output = ""
printT(D, "D")
printT(I, "I")
drawGraph(w, A, D, idx)

saver(idx, output)

print(output)
