package com.example;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TqsStack<T> {
   private LinkedList<T> stack;
   private int limit;
  
   public TqsStack(){
     this.stack = new LinkedList<>();
   }
   
   public TqsStack(int i) {
     this.stack = new LinkedList<>();
     this.limit = i;
   }

   public T pop(){
     if(this.stack.size() == 0){
          throw new NoSuchElementException();
     } else return this.stack.removeLast();
   }

   public int size(){
        return this.stack.size();
   }

   public T peek(){
     if(this.stack.size() == 0){
          throw new NoSuchElementException();
     } else return this.stack.getLast();
   }

   public void push(T x){
     if(this.limit > 0) {   
          if (this.stack.size() < limit)  
              this.stack.add(x);
          else
              throw new IllegalStateException();
      }else {
          this.stack.add(x);
      }
   }

   public boolean isEmpty(){
     return this.stack.size() == 0;
   }
}