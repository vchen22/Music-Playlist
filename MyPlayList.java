package com.cse.ds;

/**
 * Author: Vicki Chen
 * CSE12 Login: cs12sp19af
 * Date: 4/16/19
 * File: MyPlayList.java
 * Source of Help: PA2 write up, Piazza, CSE12 tutors
 *
 * This file contains the subclass MyPlayList<E> that extends AbstractList<E>.
 * Creates a doubly linked list that stores songs.
 */

import java.util.*;

/**
 * This class contains methods that manipulates the songs in a doubly linked 
 * list. It can get the list size, get certain songs in the list, add and
 * remove songs, set values in songs, clear, shuffle, and reverse the list.
 * */
public class MyPlayList<E> extends AbstractList<E> {

    private int nsongs;
    Song dummy;

    private static final int SEED_NUM = 1234;

    //  Implementation of the MyPlayList Class

    /** Only 0-argument constructor is define */
    public MyPlayList()
    {
        nsongs = 0;
        dummy = new Song(null);
    }

    /**
     * Accessor to get the size of the list 
     * @param none
     * @return the number of songs in the list
     * */
    @Override
    public int size()
    {
        return nsongs;
    }

    /**
     * Get music data within the node at a certain index 
     * @param index index of element in list
     * @return The song at the index
     * */
    @Override
    public E get(int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index >= this.size())
        {
            throw new IndexOutOfBoundsException();
        }
       
        //call helper method to get the song at the index
        Song getSong = this.getNth(index);

        return (E) getSong.getElement();

    }

    /**
     * Adds a song into the list at a certain index
     * @param index Index to insert element
     * @param element Element to add
     * @return void
     * */
    @Override
    public void add(int index, E element) throws NullPointerException, 
           IndexOutOfBoundsException
    {        
        if (index < 0 || index > this.size())
        {
            throw new IndexOutOfBoundsException();
        }

        if (element == null)
        {
            throw new NullPointerException();
        }       

        Song newSong = new Song(element);
        
        //if index is at the end or nothing in list, call the other add method
        //to add song at the end of list
        if (index == this.size() || this.size() == 0 && index == 0)
        {
            this.add(element);
            return;
        }       
        //adding in the first index when size is greater than 0
        else if (this.size() >= 1 && index == 0)
        {
            //only need to get the song after because the song before will
            //be a dummy
            Song nextSong = this.getNth(index);

            dummy.setNext(newSong);
            newSong.setPrev(dummy);

            newSong.setNext(nextSong);
            nextSong.setPrev(newSong);
        }
        else
        {
            //get the songs that will be before and after the song being
            //inserted
            Song nextSong = this.getNth(index);
            Song prevSong = this.getNth(index-1);

            newSong.setNext(nextSong);
            nextSong.setPrev(newSong);

            newSong.setPrev(prevSong);
            prevSong.setNext(newSong);
        }
       
        //increment number of songs
        nsongs++;
    }

    /**
     * Add a song at the end of the list
     * @param element Element to add
     * @return true
     * */
    @Override
    public boolean add(E element) throws NullPointerException
    {
        if (element == null)
        {
            throw new NullPointerException();
        }   

        Song newSong = new Song(element);

        //get song at the end of the list
        Song prevSong = this.getNth(this.size()-1);

        //relocate links
        newSong.setPrev(prevSong);
        newSong.setNext(null);
        prevSong.setNext(newSong);
      
        //increment number of songs in list
        nsongs++;
        return true;   
    }

    /**
     * Helper method to get the song at a certain index
     * @param index Index to extract song
     * @return currSong Song at the wanted index
     * */
    private Song getNth(int index)
    { 
        Song currSong = dummy;

        //iterate through the list to get songs at a certain index
        for (int i = 0; i < index+1; i++)
        {
            currSong = currSong.getNext();
        }

        return currSong;
    }

    /**
     * Set value of a song to another value
     * @param index Index of song getting set to
     * @param element Element to be set to
     * @return prevData The previous data at the index
     * */
    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException,
           NullPointerException
    {
        
        if (index < 0 || index > this.size())
        {
            throw new IndexOutOfBoundsException();
        }

        if (element == null)
        {
            throw new NullPointerException();
        }

        //call helper method to get index of song that needs to be set
        Song setSong = this.getNth(index);
        //store the previous data at the index
        E prevData = (E) setSong.getElement();
        //set new element of that song
        setSong.setElement(element);
        return prevData;
    }

    /**
     * Remove a song at a certain index from the list
     * @param index Index of song getting removed
     * @return element of the song being removed
     * */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index >= this.size())
        {
            throw new IndexOutOfBoundsException();
        }

        Song removedSong = this.getNth(index);

        //removing last song
        if (index == this.size()-1)
        {
            //establish the song before the last song now becomes the last one
            Song last = removedSong.getPrev();
            last.setNext(null);
        }
        else
        {
            //Song class' remove method
            removedSong.remove();
        }

        //decrement number of songs
        nsongs--;

        return (E) removedSong.getElement();
    }       

    /**
     * Empty out all content in the list
     * @param none
     * @return void
     * */
    @Override
    public void clear()
    {   
        //clear when there are songs in the list
        while (isEmpty() == false)
        {
            this.remove(0);
        }      
    }

    /**
     * Checks if list is empty
     * @param none
     * @return if size is 0 or not
     * */
    @Override
    public boolean isEmpty()
    {
        if (this.size() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Shuffles two songs in playlist
     * @param none
     * @return void
     **/
    public void shuffle()
    {
        Random rand = new Random();
        rand.setSeed(SEED_NUM);

        int randIndex1 = rand.nextInt(this.size());
        int randIndex2 = rand.nextInt(this.size());

        //get the two songs at the indices to swap
        Song firstSong = this.getNth(randIndex1);
        Song secondSong = this.getNth(randIndex2);

        //take out song of first index
        this.remove(randIndex1);

        //add the second song to first song's original place
        this.add(randIndex1, (E) secondSong.getElement());

        //repeat steps but with second song and second index
        this.remove(randIndex2);

        this.add(randIndex2, (E) firstSong.getElement());

                
    }

    /**
     * Reverses playlist
     * @param none
     * @return void
     * */
    public void reverse()
    {
        int listSize = this.size();

        //iterate half of the list 
        for (int i = 0; i < listSize/2; i++)
        {
            Song firstSong = this.getNth(i);
            Song secondSong = this.getNth(listSize-1-i);

            //swap places with its corresponding indicies
            this.remove(i);

            this.add(i, (E) secondSong.getElement());

            this.remove(listSize-1-i);

            this.add(listSize-1-i, (E) firstSong.getElement());
        }
    }

    /**
     * Create a myPlayListIterator
     * @param none
     * @return MyPlayListIterator object
     * */
    public MyListIterator myListIterator() {
        return new MyPlayListIterator(this);
    }

}


