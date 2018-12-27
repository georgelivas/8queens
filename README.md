# The 8 queens puzzle
<p align="center">
    <a align="center"><img src="https://img.shields.io/packagist/l/doctrine/orm.svg"></a>
    <a align="center" href=""><img src="https://img.shields.io/badge/release%20date-December%2027-orange.svg"></a>
    <a align="center" href=""><img src="https://img.shields.io/badge/version-2.1-red.svg"></a>
    <a align="center" href="http://georgelivas.site"><img src="https://img.shields.io/badge/Visit%20my-Blog-brightgreen.svg"></a>
</p>

## Description

The eight queens puzzle is the problem of placing eight chess queens on an 8×8 chessboard so that no two queens threaten each other. Thus, a solution requires that no two queens share the same row, column, or diagonal. 

## Solution

The solution I used has 3 steps:

1. generate all (92) possible solutions.
2. receive input from user i.e. E4.
3. provide all solutions with a queen at cell E4.

---

1. To generate the 92 solutions I used a [backtracking](#Backtracking) recursive algorithm
    ```java
    private static void backtrack(int[] q, int pos) {
        if (pos == q.length) {
            saveBoard(q);
        } else {
            IntStream.range(0, q.length).boxed().forEach(i -> {
                q[pos] = i;
                if (isAccurate(q, pos)) {
                    backtrack(q, pos+1);
                }
            });
        }
    }
    ```
    
2. Using a ```Scanner``` I read input from the console in the form ```E4``` or ```e4``` then I translate the chess position to an array  ```[x, y]``` and checking if ```0,0 < x,y <  7,7 ```

    ```text
    i = index
    
    i        0   1   2   3   4   5   6   7
    
             A   B   C   D   E   F   G   H
           +---+---+---+---+---+---+---+---+
    0    1 |   |   |   |   |   |   |   |   |
           +---+---+---+---+---+---+---+---+
    1    2 |   |   |   |   |   |   |   |   |
           +---+---+---+---+---+---+---+---+
    2    3 |   |   |   |   |   |   |   |   |
           +---+---+---+---+---+---+---+---+
    3    4 |   |   |   |   | • |   |   |   |
           +---+---+---+---+---+---+---+---+
    4    5 |   |   |   |   |   |   |   |   |
           +---+---+---+---+---+---+---+---+
    5    6 |   |   |   |   |   |   |   |   |
           +---+---+---+---+---+---+---+---+
    6    7 |   |   |   |   |   |   |   |   |
           +---+---+---+---+---+---+---+---+
    7    8 |   |   |   |   |   |   |   |   |
           +---+---+---+---+---+---+---+---+
    ```
    
    > As shown above the position ```E4``` has coordinates ```[4, 3]```

3.  Since all the possible solutions are stored in an ```ArrayList``` the algorithm must filter all the ```Boards``` with a queen placed at the given location and print them.
     > The output would be similar to this:
    
    ```text
        ...
    
            A   B   C   D   E   F   G   H
          +---+---+---+---+---+---+---+---+
        1 |   |   |   | ♕ |   |   |   |   |
          +---+---+---+---+---+---+---+---+
        2 |   |   |   |   |   |   | ♕ |   |
          +---+---+---+---+---+---+---+---+
        3 |   |   |   |   | ♕ |   |   |   |
          +---+---+---+---+---+---+---+---+
        4 |   | ♕ |   |   |   |   |   |   |
          +---+---+---+---+---+---+---+---+
        5 |   |   |   |   |   | ♕ |   |   |
          +---+---+---+---+---+---+---+---+
        6 | ♕ |   |   |   |   |   |   |   |
          +---+---+---+---+---+---+---+---+
        7 |   |   | ♕ |   |   |   |   |   |
          +---+---+---+---+---+---+---+---+
        8 |   |   |   |   |   |   |   | ♕ |
          +---+---+---+---+---+---+---+---+
        
        
        There are 12 possible solutions with a queen in the position E3.
    
    ```

### Backtracking

#### Description of the algorithm

The backtracking algorithm enumerates a set of partial candidates that, in principle, could be completed in various ways to give all the possible solutions to the given problem. The completion is done incrementally, by a sequence of candidate extension steps.

Conceptually, the partial candidates are represented as the nodes of a tree structure, the potential search tree. Each partial candidate is the parent of the candidates that differ from it by a single extension step; the leaves of the tree are the partial candidates that cannot be extended any further.

The backtracking algorithm traverses this search tree recursively, from the root down, in depth-first order. At each node c, the algorithm checks whether c can be completed to a valid solution. If it cannot, the whole sub-tree rooted at c is skipped (pruned). Otherwise, the algorithm (1) checks whether c itself is a valid solution, and if so reports it to the user; and (2) recursively enumerates all sub-trees of c. The two tests and the children of each node are defined by user-given procedures.

Therefore, the actual search tree that is traversed by the algorithm is only a part of the potential tree. The total cost of the algorithm is the number of nodes of the actual tree times the cost of obtaining and processing each node. This fact should be considered when choosing the potential search tree and implementing the pruning test.

#### Pseudocode

```
boolean solve(Node n) {
    if n is a goal node, return true
    
    foreach option O possible from n {
        if solve(O) succeeds, return true
    }
    return false
}
```

## References

*  https://en.wikipedia.org/wiki/Eight_queens_puzzle
*  https://en.wikipedia.org/wiki/Backtracking









a program for the data structures and algorithms module.
