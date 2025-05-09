# SpecialType-PriorityQueue

## Overview

This project implements a custom **Advanced Priority Queue (APQ)** in Java that supports both **min-heap** and **max-heap** modes using a single resizable array-based structure.

No built-in Java data structures (e.g., `ArrayList`, `Heap`, etc.) are allowed. The implementation supports dynamic switching between heap modes and advanced operations beyond traditional priority queues.

---

## ✨ Key Features

- `toggle()` – Switch between Min and Max mode
- `insert(k, v)` – Add a new entry
- `removeTop()` / `top()` – Access/remove top element
- `remove(e)` – Remove a specific entry
- `replaceKey(e, k)` / `replaceValue(e, v)`
- `state()` / `isEmpty()` / `size()`
- `peekAt(n)` – Access n-th ranked entry
- `merge(otherAPQ)` – Combine two APQs

---
