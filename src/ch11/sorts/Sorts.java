package ch11.sorts;

import java.text.DecimalFormat;
import java.util.Random;

public class Sorts {
    private static final int SIZE = 50;
    private static int[] values = new int[SIZE];

    private static void initValues() {
        Random rand = new Random();
        for (int index = 0; index < SIZE; index++) {
            values[index] = Math.abs(rand.nextInt()) % 100;
        }
    }

    private static void printValues() {
        int value;
        DecimalFormat fmt = new DecimalFormat("00");
        System.out.println("数组中的元素是：");
        for (int index = 0; index < SIZE; index++) {
            value = values[index];
            if (((index + 1) % 10) == 0) {
                System.out.println(fmt.format(value));
            } else {
                System.out.print(fmt.format(value) + " ");
            }
        }
        System.out.println();
    }

    private static boolean isSorted() {
        for (int index = 0; index < (SIZE - 1); index++) {
            if (values[index] > values[index + 1]) {
                return false;
            }
        }
        return true;
    }

    private static void swap(int index1, int index2) {
        int temp = values[index1];
        values[index1] = values[index2];
        values[index2] = temp;
    }

    //选择排序
    private static int minIndex(int startIndex, int endIndex) {
        int indexOfMin = startIndex;
        for (int index = startIndex + 1; index <= endIndex; index++) {
            if (values[index] < values[indexOfMin]) {
                indexOfMin = index;
            }
        }
        return indexOfMin;
    }

    private static void selectionSort() {
        int endIndex = SIZE - 1;
        for (int current = 0; current < endIndex; current++) {
            swap(current, minIndex(current, endIndex));
        }
    }

    //冒泡排序
    private static void bubbleUp(int startIndex, int endIndex) {
        for (int index = endIndex; index > startIndex; index--) {
            if (values[index] < values[index - 1]) {
                swap(index, index - 1);
            }
        }
    }

    private static void bubbleSort() {
        int current = 0;
        while (current < (SIZE - 1)) {
            bubbleUp(current, SIZE - 1);
            current++;
        }
    }

    //冒泡排序
    private static boolean bubbleUp2(int startIndex, int endIndex) {
        boolean sorted = true;
        for (int index = endIndex; index > startIndex; index--) {
            if (values[index] < values[index - 1]) {
                swap(index, index - 1);
                sorted = false;
            }
        }
        return sorted;
    }

    private static void shortBubble() {
        int current = 0;
        boolean sorted = false;
        while ((current < (SIZE - 1)) && !sorted) {
            sorted = bubbleUp2(current, SIZE - 1);
            current++;
        }
    }

    //插入排序
    private static void insertItem(int startIndex, int endIndex) {
        boolean finished = false;
        int current = endIndex;
        boolean moreToSearch = true;
        while (moreToSearch && !finished) {
            if (values[current] < values[current - 1]) {
                swap(current, current - 1);
                current--;
                moreToSearch = (current != startIndex);
            } else {
                finished = true;
            }
        }
    }

    private static void insertionSort() {
        for (int count = 1; count < SIZE; count++) {
            insertItem(0, count);
        }
    }

    //合并排序
    private static void merge(int leftFirst, int leftLast, int rightFirst, int rightLast) {
        int[] tempArray = new int[SIZE];
        int index = leftFirst;
        int saveFirst = leftFirst;

        while ((leftFirst <= leftLast) && (rightFirst <= rightLast)) {
            if (values[leftFirst] < values[rightFirst]) {
                tempArray[index] = values[leftFirst];
                leftFirst++;
            } else {
                tempArray[index] = values[rightFirst];
                rightFirst++;
            }
            index++;
        }

        while (leftFirst <= leftLast) {
            tempArray[index] = values[leftFirst];
            leftFirst++;
            index++;
        }

        while (rightFirst <= rightLast) {
            tempArray[index] = values[rightFirst];
            rightFirst++;
            index++;
        }

        for (index = saveFirst; index <= rightLast; index++) {
            values[index] = tempArray[index];
        }
    }

    private static void mergeSort(int first, int last) {
        if (first < last) {
            int middle = (first + last) / 2;
            mergeSort(first, middle);
            mergeSort(middle + 1, last);
            merge(first, middle, middle + 1, last);
        }
    }

    //快速排序
    private static int split(int first, int last) {
        int splitVal = values[first];
        int saveF = first;
        boolean onCorrectSide;
        first++;

        do {
            onCorrectSide = true;
            while (onCorrectSide) {
                if (values[first] > splitVal) {
                    onCorrectSide = false;
                } else {
                    first++;
                    onCorrectSide = (first <= last);
                }
            }
            onCorrectSide = (first <= last);
            while (onCorrectSide) {
                if (values[last] <= splitVal) {
                    onCorrectSide = false;
                } else {
                    last--;
                    onCorrectSide = (first <= last);
                }
            }
            if (first < last) {
                swap(first, last);
                first++;
                last--;
            }
        } while (first <= last);
        swap(saveF, last);
        return last;
    }

    public static void quickSort(int first, int last) {
        if (first < last) {
            int splitPoint;
            splitPoint = split(first, last);
            quickSort(first, splitPoint - 1);
            quickSort(splitPoint + 1, last);
        }
    }

    //堆排序
    private static int newHole(int hole, int lastIndex, int item) {
        int left = (hole * 2) + 1;
        int right = (hole * 2) + 2;
        if (left > lastIndex) {
            return hole;
        } else if (left == lastIndex) {
            if (item < values[left]) {
                return left;
            } else {
                return hole;
            }
        } else if (values[left] < values[right]) {
            if (values[right] <= item) {
                return hole;
            } else {
                return right;
            }
        } else if (values[left] <= item) {
            return hole;
        } else {
            return left;
        }
    }

    private static void reheapDown(int item, int root, int lastIndex) {
        int hole = root;
        int newhole;
        newhole = newHole(hole, lastIndex, item);
        while (newhole != hole) {
            values[hole] = values[newhole];
            hole = newhole;
            newhole = newHole(hole, lastIndex, item);
        }
        values[hole] = item;
    }

    private static void heapSort() {
        int index;
        for (index = (SIZE / 2 - 1); index >= 0; index--) {
            reheapDown(values[index], index, SIZE - 1);
        }
        for (index = SIZE - 1; index >= 1; index--) {
            swap(0, index);
            reheapDown(values[0], 0, index - 1);
        }
    }

    public static void main(String[] args) {
        initValues();
        printValues();
        System.out.println("是否已经排序？" + (isSorted()?"是的":"没有"));
        System.out.println();

        //selectionSort();
        //bubbleSort();
        //shortBubble();
        //insertionSort();
        //mergeSort(0, SIZE - 1);
        //quickSort(0, SIZE - 1);
        heapSort();

        printValues();
        System.out.println("是否已经排序？" + (isSorted()?"是的":"没有"));
        System.out.println();
    }
}
