package com.cse.ds;

/**
 * Author: Vicki Chen
 * CSE12 Login: cs12sp19af
 * Date: 4/16/19
 * File: MyPlayListIterator.java
 * Source of Help: PA2 write up, Piazza, CSE12 tutors
 *
 * This file contains the subclass MyPlayListIterator<E> that implements
 * MyListIterator<E>. 
 * It creates an iterator that can go through a song playlist
 * */

import java.util.*;

/** 
 * This class contains methods provides functionality of moving through the
 * playlist. It has a ListIterator implementation
 * */
public class MyPlayListIterator<E> implements MyListIterator<E> {

    Song left;
    Song right;
    private int index;
    //direction: 1 = next, -1 = previous
    private int direction;
    private boolean moved;
    
    /**
     * Intializes a MyPlayListIterator object with a playlist
     * @param list Playlist of songs
     * @return none
     * */
    public MyPlayListIterator(MyPlayList list)
    {
        left = list.dummy;
        right = left.getNext();
        index = 0;
        direction = 1;
        moved = false;
    }
    
    /**
     * Checks whether there are more elements other than dummy in order to go
     * forward
     * @param none
     * @return To go forward or not
     * */
    @Override
    public boolean hasNext() {
        //at the end
        if (right == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Return the next element in the list when going forward, and move the
     * iterator forward for one node
     * @param none
     * @return The next element
     * */
    @Override
    public E next() throws NoSuchElementException
    {
        if (this.hasNext() == false)
        {
            throw new NoSuchElementException();
        }
        else
        {
            //get next element
            E element = (E) right.getElement();

            //shift to right
            left = right;
            right = right.getNext();

            index++;
            
            //has moved
            moved = true;
            //move direction to forward
            direction = 1;
            return element;
        }
    }

    /**
     * Checks whether there are more elements other than dummy in order to go
     * in reverse direction
     * @param none
     * @return To go backwards or not
     * */
    @Override
    public boolean hasPrevious() {
        //at beginning
        if (left.getElement() == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Return the next element in the list when going backward, and move the
     * iterator backward for one node
     * @param none
     * @return previous element
     * */
    @Override
    public E previous() throws NoSuchElementException
    {
        if (this.hasPrevious() == false)
        {
            throw new NoSuchElementException();
        }
        else
        {

            //get the previous element
            E element = (E) left.getElement();

            //shift to left 
            left = left.getPrev();
            right = left;

            index--;
            //has moved
            moved = true;
            //move direction to backwards
            direction = -1;
            return element;
        }
    }

    /**
     * Return the index of the element returned by the call next()
     * @param none
     * @return the next index
     * */
    @Override
    public int nextIndex() {
        return index;
    }

    /**
     * Return the index of the element returned by the call previous()
     * @param none
     * @return the previous index
     * */
    @Override
    public int previousIndex() {
        return index-1;
    }

    /**
     * Change the value in the node returned by the most recent/previous call
     * with the new value
     * @param none
     * @return void
     * */
    @Override
    public void set(E o) throws NullPointerException, IllegalStateException
    {
        if (o == null)
        {
            throw new NullPointerException();
        }
        if (moved == false)
        {
            throw new IllegalStateException();
        }

        //moving forward
        if (direction == 1)
        {
            left.setElement(o);
        }
        //moving backwards
        else
        {
            right.setElement(o);
        }

    }
}
