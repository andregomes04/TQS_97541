package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TqsStackTest {

    TqsStack<String> wordStack;

    @BeforeEach
    void setUp() {
        wordStack = new TqsStack<String>();
    }

    @DisplayName("A stack is empty on contrusction")
    @Test
    void isEmpty() {
        assertTrue(wordStack.isEmpty());
    }

    @DisplayName("A stack has size 0 on construction")
    @Test
    void sizeZero(){
        assertEquals(0,wordStack.size());
    }

    @DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    @Test
    void nPushTest(){
        // n = 3
        wordStack.push("a");
        wordStack.push("b");
        wordStack.push("c");
        assertEquals(3, wordStack.size());
    }

    @DisplayName("If one pushes x then pops, the value popped is x")
    @Test
    void pushPopTest(){
        wordStack.push("54");
        assertEquals("54",wordStack.pop());
    }

    @DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
    @Test 
    void pushPeekTest(){
        wordStack.push("54");
        int n = wordStack.size();
        assertEquals("54", wordStack.peek());
        assertEquals(n, wordStack.size());
    }

    @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
    @Test 
    void popUntilEmplty(){
        wordStack.push("12");
        wordStack.push("45");
        while(!wordStack.isEmpty()){
            wordStack.pop();
        }
        assertEquals(0, wordStack.size());
        assertTrue(wordStack.isEmpty());
    }
    
    @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    @Test
    void popEmpty(){
        
        assertTrue(wordStack.isEmpty());
        assertThrows(NoSuchElementException.class, () -> {
            wordStack.pop();
        });
    }

    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
    @Test
    void peekEmpty(){
        assertTrue(wordStack.isEmpty());
        assertThrows(NoSuchElementException.class, () -> {
            wordStack.peek();
        });
    }


    @DisplayName("For bounded stacks only: pushing onto a full stack does throw an IllegalStateException")
    @Test
    void pushFull(){
        wordStack = new TqsStack<String>(3);
        wordStack.push("gg");
        wordStack.push("55");
        wordStack.push("yy");

        assertThrows(IllegalStateException.class, () -> {
            wordStack.push("full");
        });
    }
}
