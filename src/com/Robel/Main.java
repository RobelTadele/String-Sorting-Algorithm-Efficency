/*File or Class Name: Main
* Program author: Robel Tadele
* Course Number and Title: COSC2203, Data Structures Section 1
* Due Date: 10/21/2020
* Brief Description: The analysis of different Sorting Algorithm Runtimes
* */

package com.Robel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("undictionary.txt");
        Scanner reader = new Scanner(file);


        final int max = 5; //Max int array size make 45_000
        int increment = 1; //Can change to 5k late on //Can also come from command line



        String[] words = new String[max ];

        for (int i = 0; i < max; i++) {
            words[i] = reader.next();
            //System.out.println(words[i]);
        }

        //int[] index = new int[]{0,1,2,3,4,5,6,7,8,9};
        //Bubble Sort
        for (int size = 0;size < max ; size += increment) {
            int[] index = getNewNums(max);

            //Start Timer
            double startTime = System.nanoTime();


            /////Working Sorting Algorithms///////////////

            //int[] bubbleSorter = bubbleSort(words, index);
            //int[] selectionSorter = selectionSort(words,index);

            //int[] insertionSorter = insertionSort(words, index);
            //int[] heapSorter = heapSort(words, index);
            //int[] shellSorter = shellSort(words, index);


            //Main Printer
            //printArray(words, array, size);

            //System.out.println(words[index[size]]);

            QuickSort(words, index, 0, index.length-1);
            //mergeSort(words, index);

            //Printer for Merge and Quick Sort
            System.out.println(words[index[size]]);


            //Stop Timer
            double stopTime = System.nanoTime();
            double totalTime = (stopTime - startTime)/ 1_000_000_000;
            //System.out.println(totalTime);

            //Prints sorted words
            //printList(words, index);
            //System.out.println();
        }

        /*for (int i = 0; i < index.length; i++) {
            System.out.println(index[i]);
        }*/


    }//End of Main


    //Merge Sort
    public static void mergeSort(String[] words, int[] arr){

        if(arr.length > 1) {
            int halfSize = arr.length / 2;
            int[] left = new int[halfSize];
            int[] right = new int[arr.length - halfSize];

            System.arraycopy(arr, 0, left, 0, halfSize);
            System.arraycopy(arr, halfSize, right, 0, arr.length - halfSize);

            mergeSort(words, left);
            mergeSort(words, right);

            merge(words, arr, left, right);

        }
    }

    private static void merge(String[] words, int[] output, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while(i < left.length && j < right.length ){
            if(words[left[i]].compareTo(words[right[j]]) < 0){
                output[k++] = left[i++];
            }else{
                output[k++] = right[j++];
            }
        }

        while(i < left.length){
            output[k++] = left[i++];
        }

        while(j < right.length){
            output[k++] = right[j++];
        }
    }
    //Merge Sort End

    //Quick Sort Partition




















    public static void printArray(String[] words, int[] index, int size) {
        for (int i = 0; i < size; i++) {
            System.out.println(words[index[i]]);
        }
    }

    //Bubble Sort Algorithm
    public static int[] bubbleSort(String[] words, int[] index) {
        //Accessing through index
        for (int i = 0; i < index.length; i++) {
            for (int j = 0; j < index.length; j++)
                if (words[index[i]].compareTo(words[index[j]]) < 0) {
                    int temp = index[i];
                    index[i] = index[j];
                    index[j] = temp;
                }
        }
        return index;
    }//End of Bubble Sort

    //Selection Sort Algorithm
    public static int[] selectionSort(String[] words, int[] index){

        for (int i = 0; i < index.length-1; i++) {
            int posMin = i;
            for (int j = i + 1; j < index.length; j++) {
                if(words[index[j]].compareTo(words[index[posMin]])  <  0 ){
                    posMin = j;
                }

                int temp = index[posMin];
                index[posMin] = index[i];
                index[i] = temp;

                //d and c need to be switched
            }
        }
        return index;
    }//End of Selection Sort

    //Insertion Sort Algo
    public static int[] insertionSort(String[] words, int[] index){

        /*for (int i = 1; i < index.length; ++i) {
            String key = words[index[i]];
            int j = i -1;

            while(j >= 0 && words[index[j]].compareTo(key) > 0){
                words[index[j + 1]] = words[index[j]];
                j = j - 1;
            }
            words[index[j + 1]] = key;
        }*/

        for (int nextPos = 1; nextPos < index.length; nextPos++) {
            insertInsertion(words, index, nextPos);
        }

        return index;
    }//End of Insertion Sort

    private static void insertInsertion(String[] words, int[] index, int nextPos){

        String nextVal = words[index[nextPos]];
        while(nextPos > 0  && nextVal.compareTo(words[index[nextPos - 1]]) < 0){

            words[index[nextPos]] = words[index[nextPos - 1]];
            nextPos --;
        }
        words[index[nextPos]] = nextVal;
    }//End of Inserter

    //Shell Sort Algo
    public static int[] shellSort(String[] words, int[] index){

        int gap = index.length/2;
        while(gap > 0){
            for(int nextPos = gap; nextPos < index.length; nextPos++){
                insertShell(words,index, nextPos, gap);
            }//End of For loop

            if(gap == 2){
                gap = 1;
            }else{
                gap = (int)(gap / 2.2);
            }
        }//End of While Loop
        return index;
    }//End of Shell Sort

    //Inserts elements at the next Position
    private static void insertShell(String[] words, int[] index, int nextPos, int gap){

        String nextVal = words[index[nextPos]];
        while((nextPos > gap - 1) && (nextVal.compareTo(words[index[nextPos - gap]]) < 0)){

            words[index[nextPos]] = words[index[nextPos - gap]];
            nextPos -= gap;
        }
        words[index[nextPos]] = nextVal;
    }//End of Inserter

    public static int[] heapSort(String[] words, int[] index){
        int n = index.length;

        //Building Heap
        for (int i = n/2-1; i >= 0 ; i--)
            heaper(words, index, n, i);

        //Removes Element from heap
        for (int i = n-1; i > 0 ; i--) {
            //Moves current root
            String temp = words[index[0]];
            words[index[0]] = words[index[i]];
            words[index[i]] = temp;

            heaper(words, index, i, 0);
        }
        return index;
    }//End of Heap Sort Algo

    //Method to Heapify a subtree rooted at node i
    static private void heaper(String[] words, int[] index, int n, int i){
        int largest = i; // root
        int l = 2*i + 1; //Left CHILD
        int r = 2*i + 2; //Right CHILD

        //if left child is bigger than root
        if(l < n && words[index[l]].compareTo(words[index[largest]]) > 0 ){
            largest = l;
        }

        //if right child is bigger than root
        if(r < n && words[index[r]].compareTo(words[index[largest]]) > 0){
            largest = r;
        }

        //Largest != root
        if(largest != i){
            String swap = words[index[i]];
            words[index[i]] = words[index[largest]];
            words[index[largest]] = swap;

            //Recursive Call
            heaper(words, index, n, largest);
        }
    }//End of Heaper Method

    //Main merge Sorter

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Resets array in between the sorts
    public static int[] getNewNums(int size){

        int[] temp = new int[size];
        for (int i = 0; i < size ; i++) {
            temp[i] = i;
        }
        return temp;
    }

    //FOR TESTING PURPOSES ONLY -- Prints Words
    public static void printList(String[] words, int[] index){
        for (int j : index) {
            System.out.println(words[j]);
        }
    }
}