package com.cse.ds;

/**
 * Author: Vicki Chen
 * CSE12 Login: cs12sp19af
 * Date: 4/16/19
 * File: Song.java
 * Source of Help: PA2 write up, Piazza
 *
 * This file contains the class Song<E>.
 * It can create song objects and manipulate its properties and its
 * surrouding song objects.
*/

/**
 * This class contains getter and setter methods to manipulate song object's
 * attributes
 * */
public class Song<E> {

    E data;
    Song next;
    Song prev;

    /** Constructor to create singleton Song */
    public Song(E element)
    {
        data = element;
    }

    /** Constructor to create singleton link it between previous and next
     *   @param element Element to add, can be null
     *   @param prevSong predecessor Song, can be null
     *   @param nextSong successor Song, can be null
     */
    public Song(E element, Song prevSong, Song nextSong)
    {
        data = element;
        next = nextSong;
        prev = prevSong;
    }

    /** Remove this Song from the list. Update previous and next Songs */
    public void remove()
    {
        this.next.prev = this.prev;
        this.prev.next = this.next;
    }
    
    /** Set the previous Song in the list
     *  @param p new previous Song
     */
    public void setPrev(Song p)
    {
        //To be implemented
        prev = p;
    }

    /** Set the next Song in the list
     *  @param n new next Song
     */
    public void setNext(Song n)
    {
        //To be implemented
        next = n;
    }

    /** Set the element
     *  @param e new element
     */
    public void setElement(E e)
    {
        //To be implemented
        data = e;
    }
    /** Accessor to get the next Song in the list */
    public Song getNext()
    {
        return next;
    }
    /** Accessor to get the prev Song in the list */
    public Song getPrev()
    {
        return prev;

    }
    /** Accessor to get the Songs Element */
    public E getElement()
    {
        return data;
    }
}


