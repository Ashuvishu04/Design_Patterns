package org.akshay.designpatterns.StructuralPattern;

import java.util.Arrays;

public class CharacterCombination {

  public static void main(String[] args) {

    char a[] = {'a','b','c'};

    for(int i =0;i<a.length;i++){
      for(int j=i+1;j<a.length;j++){
        System.out.print(a[i]);
        System.out.print(a[j]);
        System.out.println();
      }
    }

    for(int i =a.length-1;i>=0;i--){
      for(int j=i-1;j>=0;j--){
        System.out.print(a[i]);
        System.out.print(a[j]);
        System.out.println();
      }
    }

    for(int i =0;i<a.length;i++){
      char c = a[i];

    }

  }
  }




