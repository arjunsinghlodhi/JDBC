package com.nt.jdbc;

public class Demo12 {

	public static void main(String[] args) {
	int a=5;
	{
		int b=10;
		++b;
		++a;

		{
		    a=20;
			++a;
			a=++b;
		}
		++a;
		++b;
		System.out.println(a+" " + b);
	}
	System.out.println(a);

	}

}
