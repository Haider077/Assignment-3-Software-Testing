//Challenges Faced : 
//* Uncertainty of whether sufficient testing has been completed for each method.
//* Finding specific ways of testing a particular method.
//* Analytically analyzing each method for possible errors given mathematical uncertainty. For example whether something will be divided by 0.
//* Trying to consider all possible values that are possible given a range.
//
//
//
//
//
package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import java.security.InvalidParameterException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class RangeTest {
	private Range exampleRange;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	
	@BeforeEach
	void setUp() throws Exception {
	}
	
	//Tests whether combine function can return null
	@Test
	void combineTest1(){
		Range range1 = null;
		Range range2 = null;
		
		assertTrue(exampleRange.combine(range1, range2)== null);
		
	}
	//Tests whether other range is returned if one range is null
	@Test
	void combineTest2(){
		Range range1 = null;
		Range range2 = new Range(199.9, 200.0);
		
		assertTrue(exampleRange.combine(range1, range2) == range2);
		
	}

	//Tests whether subset ranges are combined correctly
	@Test
	void combineTest3(){
		Range range1 = new Range(80.0, 90.0);
		Range range2 = new Range(50.0, 200.0);
		Range rangeComb = new Range(65.0,145.0);
		
		assertEquals(rangeComb,exampleRange.combine(range1, range2));
	}
	//Tests whether same ranges are combined correctly
	void combineTest4() {
		Range range1 = new Range(80.0, 90.0);
		Range range2 = new Range(80.0, 90.0);
		Range rangeComb = new Range(80.0, 90.0);
		
		assertEquals(rangeComb,exampleRange.combine(range1, range2));
		
		
	}
	//Tests whether overlapping ranges are combined correctly
	@Test
	void combineTest5(){
		Range range1 = new Range(80.0, 90.0);
		Range range2 = new Range(50.0, 60.0);
		Range rangeComb = new Range(80.0,90.0);
		
		assertEquals(rangeComb,exampleRange.combine(range1, range2));
	}
	
	//Tests low range returns are equal to the lowest possible value in the range
	@Test
	void constrainTest1() {
		Range range1 = new Range(80.0, 90.0);
		double lowRangeValue = 5.0;
		
		assertEquals(range1.constrain(lowRangeValue),80.0);
	}
	
	//Tests high range returns are equal to the highest possible value in the range
	@Test
	void constrainTest2() {
		Range range1 = new Range(80.0, 90.0);
		double highRangeValue = 105.0;
		
		assertEquals(range1.constrain(highRangeValue),90.0);
	}
	//Tests internal range returns are equal to the closests possible value in the range
	@Test
	void constrainTest3() {
		Range range1 = new Range(80.0, 90.0);
		double internalRangeValue = 84.0;
		
		assertEquals(range1.constrain(internalRangeValue),84.0);
	}
	//Tests if contains value is true
	@Test
	void containTest1() {
		Range range1 = new Range(80.0, 90.0);
		double internalValue = 84.0;
		assertTrue(range1.contains(internalValue) == true);
	}
	
	//Tests if contains external value is false
	@Test
	void containTest2() {
		Range range1 = new Range(80.0, 90.0);
		double externalValue = 55.0;
		assertTrue(range1.contains(externalValue) == false);
	}
	//Tests if this method works for any objects as suggested by its documentation
	@Test
	void equalTest1() {
		Range test = new Range(0,100);
		Range test1 = new Range(0,50);
		
		assertTrue(test.equals(test1) == false);
		
	}
	//Tests if this method correctly equates objects
	@Test
	void equalTest2() {
		Range test = new Range(0,100);
		Range test1 = new Range(0,100);
		
		assertTrue(test.equals(test1) == true);
		
	}
	//Tests if this method correctly equates objects
	@Test
	void equalTest3() {
		Range test = null;
		Range test1 = new Range(0,100);
		
		assertTrue(test1.equals(test) == false);
		
	}
	//Tests whether expand can handle null values.
	@Test
	void expandTest1() {
		
		Range test = new Range(0,100);
		
		assertThrows(InvalidParameterException.class,()->{test.expand(null,0,1);});
		
		
	
	}
	@Test
	void expandTest2() {
		Range test = null;
		exampleRange = new Range(0,100);
		assertThrows(InvalidParameterException.class,()->{test.expand(exampleRange,0,1);});
		
	}
	//test negative lower bounds
	@Test
	void expandTest3() {
		Range test =  new Range(0,100);
		exampleRange = new Range(0,100);
		
		int ngtive = -1;
		try {
			test.expand(exampleRange,ngtive,1);
			assertEquals(5,5); // return true
		}
		catch(Exception e) {
			
			assertEquals(5,7); //return false
		}
	}
	//test negative lower bounds
	@Test
	void expandTest4() {
		Range test =  new Range(0,100);
		exampleRange = new Range(0,100);
		
		int ngtive = -1;
		try {
			test.expand(exampleRange,0,ngtive);
			assertEquals(5,5); // return true
		}
		catch(Exception e) {
			
			assertEquals(5,7); //return false
		}
	}
	//test char numbers
	@Test
	void expandTest5() {
		Range test =  new Range('0','9');
		exampleRange = new Range('0','5');
		

		try {
			test.expand(exampleRange,'0','5');
			assertEquals(5,5); // return true
		}
		catch(Exception e) {
			
			assertEquals(5,7); //return false
		}
	}
	//Tests if range is expanded correctly
	@Test
	void expandTest6() {
		Range test =  new Range(2,6);

		Range expectedRange = new Range(1,8);
		assertTrue(test.expand(test,0.25,0.5).equals(expectedRange) == true);
	}
	//Tests null value in the range
	@Test
    void expandToIncludeTest1() {
		
		//exercise
		Range range1 = null;

		double specifiedValue = 3;
		
		Range rangeTest = range1.expandToInclude(range1, specifiedValue);
		
		Range expectedRange = new Range(3,3);

				
		//verify
		assertEquals(range1.expandToInclude(range1, specifiedValue), expectedRange);
			
	}
	
	//Tests the same numbers for both the range and the specified value
	@Test
    void expandToIncludeTest2() {
		
		//exercise
		Range range1 = new Range(0,0);


		double specifiedValue = 0;
		
		Range rangeTest = range1.expandToInclude(range1, specifiedValue);
		
		Range expectedRange = new Range(0,0);

				
		//verify
		assertEquals(range1.expandToInclude(range1, specifiedValue), expectedRange);
			
	}
	
	
	//Tests the range using char values
	@Test
    void expandToIncludeTest3() {
		
		//exercise
		Range range1 = new Range('1', '5');


		double specifiedValue = 0;
		
		Range rangeTest = range1.expandToInclude(range1, specifiedValue);
		
		Range expectedRange = new Range(0,5);

				
		//verify
		assertEquals(range1.expandToInclude(range1, specifiedValue), expectedRange);
			
	}
	
	//Tests a negative number in the specified value
	@Test
    void expandToIncludeTest4() {
		
		//exercise
		Range range1 = new Range(1,5);


		double specifiedValue = -1;
		
		Range rangeTest = range1.expandToInclude(range1, specifiedValue);
		
		Range expectedRange = new Range(-1,5);

				
		//verify
		assertEquals(range1.expandToInclude(range1, specifiedValue), expectedRange);
			
		//it expects 1.0,1.0 even though the specified value of -1 was supposed to be included
	}
	
	//Tests a specified value that is already within the range
	@Test
    void expandToIncludeTest5() {
		
		//exercise
		Range range1 = new Range(0,5);


		double specifiedValue = 4;
		
		Range rangeTest = range1.expandToInclude(range1, specifiedValue);
		
		Range expectedRange = new Range(0,5);

				
		//verify
		assertEquals(range1.expandToInclude(range1, specifiedValue), expectedRange);
			
	}
	
	//Tests integer values for range to see if the central value is double
	@Test
    void getCentralValueTest1() {
		
		//exercise
		Range range1 = new Range(3,7);
		double centralValue = 5.0;
				
		//verify
		assertEquals(range1.getCentralValue(), centralValue);
			
	}
	
	//Tests range value that is higher in the first value and lower in the second to see if it work	
	@Test
    void getCentralValueTest2() {
		try {
		//exercise
		Range range1 = new Range(5,1);
		double centralValue = 3.0;
			
		//verify
		assertEquals(range1.getCentralValue(), centralValue);
		}
		catch(Exception e) {
			assertEquals(5,7);//return false
		}
	}
	
	//Tests range values that are equal 
	@Test
    void getCentralValueTest3() {
		
		try {
		//exercise
		Range range1 = new Range(3,3);
		double centralValue = 3.0;
				
		//verify
		assertEquals(range1.getCentralValue(), centralValue);
		}
		catch(Exception e) {
			assertEquals(5,7);//return false
		}
			
	}
	
	//Tests range value that is null 
	@Test
    void getCentralValueTest4() {
		try {
		//exercise
		Range range1 = null;
		double centralValue = 0;
				
		//verify
		
		assertEquals(range1.getCentralValue(), centralValue);
		}
		catch(Exception e){
			assertEquals(5,7);//return false
		}
	}
	

	//Tests length value with both int and double 
	@Test
    void getLengthTest1() {
		
		//exercise
		Range range1 = new Range(0, 10.0);
		double lengthValue = 10.0;
				
		//verify
		assertEquals(range1.getLength(), lengthValue);
			
	}
	
	//Tests char values as numbers to see if the length can be calculated 
	@Test
    void getLengthTest2() {
		
		//exercise
		Range range1 = new Range('0', '5');
		double lengthValue = 5.0;
				
		//verify
		assertEquals(range1.getLength(), lengthValue);
			
	}
	
	
	//Tests a really high value like 99999999 
	@Test
    void getLengthTest3() {
		
		//exercise
		Range range1 = new Range(0, 99999999);
		double lengthValue = 99999999;
				
		//verify
		assertEquals(range1.getLength(), lengthValue);
			
	}
	
	
	//Tests higher number in range first and then lower 
	@Test
    void getLowerBoundTest1() {
		
		
				
		//verify
		try {
		//exercise
		Range range1 = new Range(4,1);
		double lowerBoundValue = 1;
			
		assertEquals(range1.getLowerBound(), lowerBoundValue);
		}
		catch(Exception e) {
			assertEquals(5 , 7);//returns false test
		}
	}

	//Tests negative numbers in the range
	@Test
    void getLowerBoundTest2() {
		
		//exercise
		Range range1 = new Range(-5,1);
		double lowerBoundValue = -5;
				
		//verify
		assertEquals(range1.getLowerBound(), lowerBoundValue);
			
	}
	
	//Tests the same negative numbers in the range
	@Test
    void getLowerBoundTest3() {
		
		//exercise
		Range range1 = new Range(-5,-5);
		double lowerBoundValue = -5;
				
		//verify
		assertEquals(range1.getLowerBound(), lowerBoundValue);
			
	}
	
	
	//Tests negative numbers in the range
	@Test
    void getUpperBoundTest1() {
		
		//exercise
		Range range1 = new Range(-1,1);
		double upperBoundValue = 1.0;
				
		//verify
		assertEquals(range1.getUpperBound(), upperBoundValue);
		
		//test failed since it expected -1.0 even though 1.0 is obviosuly the higher value 
	}
	
	//Tests same numbers in the range
	@Test
    void getUpperBoundTest2() {
		
		//exercise
		Range range1 = new Range(1,1);
		double upperBoundValue = 1.0;
				
		//verify
		assertEquals(range1.getUpperBound(), upperBoundValue);
		
	}
	
	//Tests null value as range 
	@Test
    void getUpperBoundTest3() {
		
		//exercise
		Range range1 = null;
		double upperBoundValue = 0;
				
		//verify
		
		try {
			assertEquals(range1.getUpperBound(), upperBoundValue);
		}
		catch(Exception e) {
			assertEquals(5 , 7);//returns false test
		}
	}
	
	private Range comparedRange;
	@Test
	void testNotOverlappedRange() {
	 exampleRange = new Range(-1.0, 1.0);
	 assertFalse(exampleRange.intersects(2.0, 4.0));
	}
	
	@Test
	void testOverlappedRange() {
	 exampleRange = new Range(-2.0, 1.0);
	 assertTrue(exampleRange.intersects(-1.0, 3.0));
	}
	
	@Test
	void testShiftNoAllowZeroCrossing1() {
	 exampleRange = new Range(-1.0, 2.0);
	 comparedRange = new Range(0.0,3.0);
	 assertEquals(comparedRange,Range.shift(exampleRange, 2.0));
	}
	
	@Test
	void testShiftNoAllowZeroCrossing2() {
		 exampleRange = new Range(1.0, 3.0);
		 comparedRange = new Range(3.0,5.0);
		 assertEquals(comparedRange,Range.shift(exampleRange, 2.0));
	}
	@Test
	void testShiftNoAllowZeroCrossing3() {
		 exampleRange = new Range(-3.0, -1.0);
		 comparedRange = new Range(-1.0,0.0);
		 assertEquals(comparedRange,Range.shift(exampleRange, 2.0));
	}
	@Test
	void testShiftNoAllowZeroCrossing4() {
		 exampleRange = new Range(-3.0, -1.0);
		 comparedRange = new Range(0.0,0.0);
		 assertEquals(comparedRange,Range.shift(exampleRange, 4.0));
	}
	@Test
	void testShiftNoAllowZeroCrossingWithNullRange() {
		 exampleRange = null;
		 try {
		 comparedRange = Range.shift(exampleRange, 4.0);
		 
		 
		 if (exampleRange == null) {
			 throw  new InvalidParameterException("Null base Range parameter");
		 }else {
			 assertEquals(comparedRange,Range.shift(exampleRange, 4.0));
		 }
		 }
		 catch(Exception e) {
			 
		 }
	}
	
	
	@Test
	void testShiftAllowZeroCrossing1() {
	 exampleRange = new Range(-1.0, 2.0);
	 comparedRange = new Range(1.0,4.0);
	 assertEquals(comparedRange,Range.shift(exampleRange, 2.0, true));
	}
	
	@Test
	void testShiftAllowZeroCrossing2() {
		 exampleRange = new Range(1.0, 3.0);
		 comparedRange = new Range(3.0,5.0);
		 assertEquals(comparedRange,Range.shift(exampleRange, 2.0, true));
	}
	@Test
	void testShiftAllowZeroCrossing3() {
		 exampleRange = new Range(-3.0, -1.0);
		 comparedRange = new Range(-1.0,1.0);
		 assertEquals(comparedRange,Range.shift(exampleRange, 2.0, true));
	}
	@Test
	void testShiftAllowZeroCrossing4() {
		 exampleRange = new Range(-3.0, -1.0);
		 comparedRange = new Range(1.0,3.0);
		 assertEquals(comparedRange,Range.shift(exampleRange, 4.0, true));
	}
	@Test
	void testShiftAllowZeroCrossingWithNullRange() {
		 exampleRange = null;
		 try {
		 comparedRange = Range.shift(exampleRange, 4.0, true);
		 if (exampleRange == null) {
			 throw new InvalidParameterException("Null base Range parameter");
		 }else {
			 assertEquals(comparedRange,Range.shift(exampleRange, 4.0, true));
		 } 
		 }
		 catch(Exception e) {
			 assertEquals(5,5);
		 }
		 
	}
	
	@Test
	void testCompareDifferentRangeToString() {
		exampleRange = new Range(2.0, 4.0);
		comparedRange = new Range(2.0, 3.0);
		assertEquals(exampleRange.toString(), comparedRange.toString() );
	}
	
	@Test
	void testCompareSameRangeToString() {
		exampleRange = new Range(2.0, 4.0);
		comparedRange = new Range(2.0, 4.0);
		assertEquals(exampleRange.toString(), comparedRange.toString() );
	}
	
	@AfterEach
	void tearDown() throws Exception {
		
	}
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}
}