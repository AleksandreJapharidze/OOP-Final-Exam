package oop.finalexam.t1;

import java.util.*;

/**
 * The ListProcessor program demonstrates how to construct a third list (list3)
 * from two input lists (list1 and list2) based on a specific algorithm.
 *
 * <p><b>Algorithm Overview:</b></p>
 *
 * <ol>
 *     <li>For each element {@code val} in {@code list1}, calculate {@code val + 1}.</li>
 *     <li>If {@code val + 1} is a valid index in {@code list2}, take the string at that index from {@code list2}
 *         and append the original {@code val} to it (i.e., {@code list2[val + 1] + val}).</li>
 *     <li>Add this resulting string to {@code list3}.</li>
 *     <li>Once {@code list3} is fully built, go through {@code list1} again and treat each value as an index
 *         to remove from {@code list3}, <i>but only if</i> it is within valid bounds.</li>
 *     <li>To safely remove elements without affecting remaining indices, we collect all valid indices,
 *         sort them in descending order, and remove them from {@code list3}.</li>
 * </ol>
 *
 * <p><b>Error Handling Rules:</b></p>
 * <ul>
 *     <li>If {@code val + 1} is out of bounds for {@code list2}, the value is skipped and not used to build {@code list3}.</li>
 *     <li>If {@code val} is out of bounds for {@code list3}, it is ignored during the removal phase.</li>
 * </ul>
 *
 * <p>Final output is the resulting {@code list3} after all removals are applied.</p>
 *
 * <p><b>Example:</b></p>
 * <pre>
 * list1: [6, 1, 7, 3, 2, 5, 4, 10, 8, 9]
 * list2: ["hnn", "iyn", "gpe", "xnz", "aue", "hqk", "oln", "bmf", "oaf", "rqv", "kvg", "cgp"]
 *
 * Intermediate list3: [bmf6, gpe1, oaf7, aue3, xnz2, oln5, hqk4, cgp10, rqv8, kvg9]
 * Final list3 after removal: [bmf6]
 * </pre>
 *
 * @author Aleksandre Japharidze
 */
public class ListProcessor {
    /**
     * Entry point of the program.
     */
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(6, 1, 7, 3, 2, 5, 4, 10, 8, 9);
        List<String> list2 = Arrays.asList("hnn", "iyn", "gpe", "xnz", "aue", "hqk", "oln", "bmf", "oaf", "rqv", "kvg", "cgp");

        List<String> list3 = new ArrayList<>();
        for (int val : list1) {
            int indexInList2 = val + 1;
            if (indexInList2 < list2.size()) {
                list3.add(list2.get(indexInList2) + val);
            }
        }

        System.out.println("List3 before vanishing: " + list3);

        Set<Integer> indicesToRemove = new HashSet<>();
        for (int val : list1) {
            if (val >= 0 && val < list3.size()) {
                indicesToRemove.add(val);
            }
        }

        List<Integer> sortedIndices = new ArrayList<>(indicesToRemove);
        sortedIndices.sort(Comparator.reverseOrder());
        for (int index : sortedIndices) {
            list3.remove(index);
        }

        System.out.println("Final List3: " + list3);
    }
}