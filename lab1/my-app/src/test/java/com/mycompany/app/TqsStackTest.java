package com.mycompany.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TqsStackTest 
{
   
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
        for(i=0;i<5;i++){ // tested for n = 5
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
        for(i=0;i<5;i++){
            stack.push(i);
        }
        for(k=0;k<stack.size();k++){
            stack.pop();
        }
        assertTrue(stack.isEmpty);
        assertEquals(0,stack.size());
    }
    

    @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    @Test

    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
    @Test

    @DisplayName("For bounded stacks only:pushing onto a full stack does throw an IllegalStateException")
    @Test

}
