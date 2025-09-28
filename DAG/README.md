# Longest Path in a DAG

This project is my solution to the **Longest Path problem in a Directed Acyclic Graph (DAG)**. The challenge was to take a starting vertex and figure out the longest possible directed path from that point.  

I decided not to stop at one idea, but to try **two different approaches**. Both get the right answer, but they solve the problem in very different ways.

---

## Approach 1: Topological Sort + Dynamic Programming

For the first approach, I used the more standard technique:  
- I run a **topological sort** of the graph to order the vertices.  
- Then I walk through them in that order, updating the “longest distance so far” for each vertex.  
- I keep track of parents along the way, which lets me rebuild the actual path at the end.  

This approach is very iterative and safe. It’s efficient, scales well, and is reliable even for very large graphs because it avoids recursion entirely.

---

## Approach 2: DFS + Memoization

The second approach is more recursive in nature:  
- For each vertex, I ask the question: *what’s the longest path starting from you?*  
- I answer this using a depth-first search (DFS), and I memoize the results so I don’t recompute things over and over.  
- I also record which “next neighbor” gave me the best path, so I can reconstruct the final sequence.  

This method is very clean and intuitive when you think recursively. The only drawback is that on extremely deep graphs, recursion depth could become a limitation, but otherwise it works just as well.

---

## How this holds up against the required questions

- **Does the solution work for larger graphs?**  
  Yes. Both solutions run in `O(V + E)` time, which is optimal. The topological sort version is safer for very large graphs since it doesn’t risk recursion depth issues.

- **Can you think of any optimizations?**  
  A couple:  
  - Only process the part of the graph that’s reachable from the starting vertex.  
  - If you need to solve this multiple times with different sources, cache the topological order so it doesn’t need to be recomputed.  

- **What’s the computational complexity?**  
  Both are `O(V + E)`. Every vertex and edge is looked at once, and memoization in the DFS ensures we don’t repeat work.  

- **Are there any unusual cases that aren’t handled?**  
  Cycles are the main issue:  
  - In the topological sort approach, the algorithm will explicitly throw an error if a cycle is detected.  
  - In the DFS approach, I assume the input is a DAG — if it’s not, the recursion would loop indefinitely unless extra cycle detection is added.  
  Other than that, single-node graphs, disconnected graphs, or nodes with no outgoing edges are all handled properly.

---


I liked working through both approaches. The **topological sort + DP** solution is the one I would trust in production because it’s stable and scalable. The **DFS + memoization** version was fun to implement and shows another way of thinking about the problem.  

Both gave me the chance to explore the problem from different angles, and I’m confident I could explain either in an interview setting.
