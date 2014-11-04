package edu.gatech.seclass.assignment7;

public class MyClass {

	// Add to the class a method called buggyMethod1 that contains a division by
	// zero fault that (1) may not be revealed by a test suite that achieves
	// 100% statement coverage and (2) would necessarily be revealed by a test
	// suite that achieves 100% branch coverage.
	public int buggyMethod1(int a, int b) {
		
		if(a==1 || a/b == 1){
			return 1;
		}
		else{
			return 2;
		}

	}

	// Add to the class a method called buggyMethod2 that contains a division by
	// zero fault that (1) may not be revealed by a test suite that achieves
	// 100% branch coverage and (2) may be revealed by a test suite that
	// achieves 100% statement coverage but not 100% branch coverage.
	public void buggyMethod2() {

		//branch coverage subsumes statement coverage
		System.out.println("not possible");
	}

}
