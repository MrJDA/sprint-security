package com.oocl.springsecuritydemo;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class PracticeTest {

    @Test
    void arrayFlip90(){
        int[][] array = new int[5][5];
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++){
                array[i][j] = (i+1)*j;
            }
        int[][] flipArray = leftFlipArray(array, array[0].length);
        System.out.println("翻转前:");
        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("后:");
        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(flipArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    int[][] leftFlipArray(int[][] array, int n){
        if(array == null || array.length != n || array[0].length != n)return null;
        int[][] flipArray = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++){
                flipArray[n - 1 - j][i] = array[i][j];
            }
        return flipArray;
    }


    @Test
    void baseConversion(){
        int number = 11011;
        int baseNumber = 8;
        int[] convertedNumber = new int[100];
        int count  = 0;
        int result  = number;
        while(result >= baseNumber){
            int remainder = result%baseNumber;
            convertedNumber[count++] = remainder;
            result = result/baseNumber;
        }
        convertedNumber[count] =  result;
       for(int i = count; i >= 0; i--){
           System.out.print(convertedNumber[i]);
       }
    }

    @Test
    void baseConversionTo10(){
        int num = 1010;
        int baseNumber = 2;
        int count = ("" + num).length();
        int convertNumber = 0;
        for(int i = 1; i <= count; i++){
            int j = i;
            int mNum = 1;
            int baseNumberNum = 1;
            while(j > 0){
                mNum *= 10;
                baseNumberNum *= baseNumber;
                j--;
            }
            convertNumber += (((num%mNum)/(mNum/10))*(baseNumberNum/baseNumber));

        }
        System.out.println(convertNumber);
    }

    @Test
    void testQuickSort(){
        int[] array = {1, 10, 5, 6, 8, 2, 4, 20, 56, 34, 29, 45, 33};
        quickSort(0, array.length -1, array);
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
    }

    void quickSort(int start, int end, int[] array){
        if(start >= end)return;
        int i = start;
        int j = end;
        int temp = array[i];
        while(i < j){
            while( i < j && array[j] > temp){
                j--;
            }
            if(i < j) array[i++] = array[j];
            while(i < j && array[i] < temp){
                i++;
            }
            if(i < j) array[j--] = array[i];
        }
        array[i] = temp;
        quickSort(start, i-1, array);
        quickSort(i + 1 , end, array);
    }

    @Test
    void testBubbleSort(){
        int[] array = {1, 10, 5, 6, 8, 2, 4, 20, 56, 34, 29, 45, 33};
//        bubbleSort(array);
//        insertSort(array);
//           selectSort(array);
//        quickSort2(0, array.length-1, array);
//        mergeSort(0, array.length-1, array);
        shellSort(array);
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
    }

    void bubbleSort(int[] array){
        int length = array.length;
        for(int i = 0; i < length; i++)
            for(int j = 0; j < length - i - 1; j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
    }

    void insertSort(int[] array){
        int length = array.length;
        for(int i = 1; i < length; i++){
            int temp  = array[i];
            int j = i;
           while(j > 0 && temp < array[j-1]){
                array[j] = array[j - 1];
                j--;
            }
            if(i != j){
                array[j] = temp;
            }
        }
    }

    void selectSort(int[] array){
        int length = array.length;
        for(int i = 0; i < length; i ++){
            int index = i;
            int j  = i+1;
            while(j < length){
                if(array[j] < array[index]) index = j;
                j++;
            }
            if(index != i){
               int temp = array[index];
               array[index] = array[i];
               array[i] = temp;
            }
        }
    }

    void quickSort2(int start, int end , int[] array){
        if( start >= end) return;
        int  i = start;
        int j = end;
        int temp = array[i];
        while( i < j){
            while( i < j && array[j] >= temp){
                j--;
            }
            if( i < j)array[i++] = array[j];
           while(i < j && array[i] <= temp){
               i++;
           }
           if(i < j){
               array[j--] = array[i];
           }
        }
        array[i] = temp;
        quickSort2(start, i -1, array);
        quickSort2(i +1, end, array);
    }
    void shellSort(int[] array){
        int length = array.length;
        int increment= length;
        do{
            increment = increment/3+1;
            for(int i = 0; i <= increment; i++){
                for(int j = i+increment; j < length; j+=increment){
                    int temp = array[j];
                    int k = j-increment;
                    while(k > 0 && temp < array[k]){
                        array[k+increment] = array[k];
                        k-=increment;
                    }
                    array[k+increment] = temp;
                }
            }
        }while(increment>1);
    }

    void mergeSort(int start, int end, int[] array){
        if(start >= end)return;
        int middle = start + (end - start)/2;
        mergeSort(start, middle, array);
        mergeSort(middle+1, end, array);
        merge(start, middle, end, array);
    }

    void merge(int start, int middle, int end, int[] array){
        int[] temp = new int[array.length];
        int length = 0;
        int i_start = start;
        int j_start = middle+1;
        while(i_start <= middle && j_start <= end){
            if(array[i_start] < array[j_start]){
                temp[length++] = array[i_start++];
            }else{
                temp[length++] = array[j_start++];
            }
        }
        while(i_start <= middle)temp[length++] = array[i_start++];
        while(j_start <= end)temp[length++] = array[j_start++];
        for(int i = 0; i < length; i++){
            array[start + i] = temp[i];
        }
    }
}
