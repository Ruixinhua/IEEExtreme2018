package troll;

//Don't place your source in a package
import java.util.*;
import java.lang.*;
import java.io.*;

//Please name your class Main
class Main {
	static void print_number(char []c) {
		for (int i = 0; i < c.length-1; i++) {
			System.out.print(c[i]+" ");
		}
		System.out.println(c[c.length-1]);
	}
	public static void main (String[] args) throws java.lang.Exception {
	    Scanner in = new Scanner(System.in);
	    int t = in.nextInt();
	    for(int k = 0;k<t;k++) {
	    	int n = in.nextInt();
	    	char []correct = new char[n];
	    	for (int i = 0; i < correct.length; i++) {
	    		correct[i] = '0';
	    	}
	    	System.out.print("Q ");
	    	print_number(correct);
	    	int now = in.nextInt();
	    	for (int j = 0; j < correct.length+1; j++) {
	    		int last = now;
	    		if(last == correct.length) {
	    			System.out.print("A ");
	    			print_number(correct);
	    			break;
	    		}
	    		correct[j] = '1';
	    		System.out.print("Q ");
	    		print_number(correct);
	    		now = in.nextInt();
	    		if(now > last) correct[j] = '1';
	    		else {
	    			correct[j] = '0';
	    			now++;
	    		}
	    	} 
	    }
		in.close();
	}
}