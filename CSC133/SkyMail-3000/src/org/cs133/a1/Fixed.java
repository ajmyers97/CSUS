package org.cs133.a1;

public abstract class Fixed extends GameObject{
        //constructor for objects with random locations
	public Fixed(int sz, int clr) {
            super(sz, clr);
        }

        //constructor for objects with specified locations
	public Fixed(int sz, int clr, float x, float y) {
            super(sz, clr, x, y);
        }

        @Override
        public void setLocation(float x, float y){
	    //fixed objects do not move
        }

    }
