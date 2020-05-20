package com.cse.ds;
import org.junit.*;
import org.junit.runners.MethodSorters;



@FixMethodOrder(MethodSorters.JVM)
public class TestMyPlayList {
    static MyPlayList obj = null;

    @BeforeClass
    public static void setUpBeforeClass() {
        obj = new MyPlayList();
    }  
    
    
    @Before
    public void populatePlayList(){
        obj.clear();
        for(int i=0;i<10;i++){
            obj.add(i);
        }      
    }
    
    
    @Test
    public void testNumberOfSongs() {
        Assert.assertEquals(obj.size(), 10);
    } 
      
    
    @Test
    public void testRemove()
    {
        obj.clear();
        obj.add(0);
        obj.add(1);
        obj.add(2);
        obj.remove(2);

        Assert.assertEquals(obj.size(), 2);

        //obj.remove(0);

        Assert.assertEquals(obj.get(0), 0);
        Assert.assertEquals(obj.get(1), 1);
        //Assert,assertNull(obj
    }

   
    @Test
    public void testAddSongs() {
        obj.clear();
        obj.add(0, 10);
        obj.add(1, 9);
        obj.add(2, 8);
        obj.add(2, 7);

        Assert.assertEquals(obj.get(0), 10);
        Assert.assertEquals(obj.get(1), 9);
        Assert.assertEquals(obj.get(2), 7);
        Assert.assertEquals(obj.get(3), 8);
        //Assert.assertEquals(obj.get(3), 3);
        //Assert.assertEquals(obj.get(3), 1);
        //Assert.assertEquals(obj.get(4), 2);
        //Assert.assertEquals(obj.get(5), 3);
        //Assert.assertEquals(obj.size(), 4);
        //Assert.assertEquals(obj.size(), 6);
    }
    
        
    @Test
    public void testSetSongs()
    {
        obj.clear();
        obj.add(0);
        obj.add(1);
        //obj.set(0, 4);
        Assert.assertEquals(obj.set(0, 4), 0);
        Assert.assertEquals(obj.get(0), 4);
    }

    @Test
    public void testShuffle()
    {
        obj.clear();
        obj.add(1);
        obj.add(2);
        obj.add(3);
        obj.add(4);
        obj.add(5);

        obj.shuffle();

        for (int i = 0; i < 5; i++)
        {
            System.out.println(obj.get(i));
        }


        Assert.assertEquals(obj.get(0), 5);
        Assert.assertEquals(obj.get(1), 2);
        Assert.assertEquals(obj.get(2), 3);
        Assert.assertEquals(obj.get(3), 4);
        Assert.assertEquals(obj.get(4), 1);
    }

    @Test
    public void testReverse()
    {
        obj.clear();
        obj.add(0, 1);
        obj.add(1, 2);
        obj.add(2, 3);
        obj.add(3, 4);
        obj.add(4, 5);
        obj.reverse();

        for (int i = 0; i < 5; i++)
        {
            System.out.println(obj.get(i));
        }
        
        Assert.assertEquals(obj.get(0), 5);
        Assert.assertEquals(obj.get(1), 4);
        Assert.assertEquals(obj.get(2), 3);
        Assert.assertEquals(obj.get(3), 2);
        Assert.assertEquals(obj.get(4), 1);
    }
    
    /*
    @Test
    public void testIterator() {
        MyListIterator<Song> iter = obj.myListIterator();
        for(int i=0;i<obj.size()/2;i++){
            iter.next();
        }
        Assert.assertEquals(iter.next(), 5);
    }
    */


    /**
    @Test(expected = IndexOutOfBoundsException.class)
    public void testException() {
        obj.set(100,1);
    }
    */
    

}


