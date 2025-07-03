# 📋 ListProcessor – Java Algorithmic List Transformer

## Overview

**ListProcessor** is a Java program that takes two predefined lists (`list1` and `list2`) and generates a third list (`list3`) based on a specific transformation and filtering algorithm.

The goal is to demonstrate indexed list manipulation, error handling, and safe element removal in Java using collections like `ArrayList` and `HashSet`.

---

## 🔍 How It Works

### Input Lists:

- `list1`: A list of integers
- `list2`: A list of strings

### Step-by-Step Algorithm:

1. **Transform Phase (Building `list3`):**
    - For each integer `val` in `list1`, calculate `val + 1`.
    - If `val + 1` is a valid index in `list2`, append `val` to `list2[val + 1]` to form a new string.
    - Add this new string to `list3`.

2. **Vanishing Phase (Filtering `list3`):**
    - Go through each `val` in `list1` again and treat it as an index.
    - If that index exists in the current version of `list3`, remove the element at that position.
    - To avoid index shifting issues, valid indices are collected in a set, sorted in descending order, and removed safely.

### Edge Case Handling:

- If `val + 1` exceeds the bounds of `list2`, it is ignored (no addition to `list3`).
- If `val` exceeds the size of `list3` during removal, it is ignored (no removal attempted).

---

## 💡 Example

### Input:

```java
list1: [6, 1, 7, 3, 2, 5, 4, 10, 8, 9]
list2: ["hnn", "iyn", "gpe", "xnz", "aue", "hqk", "oln", "bmf", "oaf", "rqv", "kvg", "cgp"]
```

### Output:

```java
List3 before vanishing: ["bmf6", "gpe1", "oaf7", "aue3", "xnz2", "oln5", "hqk4", "cgp10", "rqv8", "kvg9"]
Final List3: ["bmf6"]
```