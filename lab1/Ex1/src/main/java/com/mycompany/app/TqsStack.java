package com.mycompany.app;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class TqsStack<T>{

    private ArrayList<T> stack;

    public TqsStack(){
        this.stack = new ArrayList<T>();
    }

    public void push(T x) {
        this.stack.add(x);
    }

    public T pop(){
        if(this.stack.size() == 0){
            throw new NoSuchElementException();
        }
        else return this.stack.remove(this.stack.size()-1);    
    }

    public T peek(){
        if(this.stack.size() == 0){
            throw new NoSuchElementException();
        }
        else return this.stack.get(this.stack.size()-1);
    }

    public int size(){
        return this.stack.size();
    }

    public boolean isEmpty(){
        if(this.stack.size() == 0){
            return true;
        }else return false;
    }
}
