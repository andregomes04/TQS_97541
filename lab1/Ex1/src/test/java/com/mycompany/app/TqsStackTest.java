package com.mycompany.app;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TqsStackTest {
    private TqsStack<Integer> stack;


    @DisplayName("A stack is empty on construction")
    @Test
    void EmptyStack() {
        assertTrue(stack.isEmpty());
    }

    @DisplayName("A stack has size 0 on construction")
    @Test
    void StackSizeZero() {
        assertEquals(0,stack.size());
    }

    @DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    @Test
    void StackSizeN() {
        for(int i=0;i<5;i++){ // tested for n = 5
            stack.push(i);
        }
        assertEquals(5,stack.size());
    }

    @DisplayName("If one pushes x then pops, the value popped is x")
    @Test
    void StackPushPop() {
        stack.push(15);
        int i = stack.pop();
        assertEquals(i,15);
    }

    @DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
    @Test
    void StackPushPeek() {
        stack.push(15);
        int SizePrePeek = stack.size();
        int i = stack.peek();
        int SizePostPeek = stack.size();
        assertEquals(i,15);
        assertEquals(SizePrePeek,SizePostPeek);
    }

    @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
    @Test
    void StackNPops() {
        for(int i=0;i<5;i++){
            stack.push(i);
        }
        for(int k=0;k<stack.size();k++){
            stack.pop();
        }
        assertTrue(stack.isEmpty());
        assertEquals(0,stack.size());
    }
    

    @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    @Test
    void PopEmpty(){
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            stack.pop();
        });
    }

    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
    @Test
    void PeekEmpty() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            stack.peek();
        });
    }
}
