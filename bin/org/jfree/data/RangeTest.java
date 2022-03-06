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

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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
        array[0] = 1.0;
        array[1] = 2.0;
        array[2] = 3.0;
        array[3] = 4.0;
        array[4] = 5.0;

    }
	
	//Tests whether combine function can return null
	@Test
	void combineNullTest(){
			Range range1 = null;
			Range range2 = null;
			
			assertEquals(exampleRange.combine(range1, range2), null);
			
	}
		//Tests whether other range is returned if one range is null
	@Test
	void combineRangeReturnIfNullTest(){
			Range range1 = null;
			Range range2 = new Range(199.9, 200.0);
			
		assertEquals(exampleRange.combine(range1, range2), range2);
			
	}
	


	//Tests whether subset ranges are combined correctly
	@Test
	void combineSubsetRangeTest(){
		Range range1 = new Range(80.0, 90.0);
		Range range2 = new Range(50.0, 200.0);
		Range rangeComb = new Range(50.0,200.0);
		
		assertEquals(rangeComb,exampleRange.combine(range1, range2));
	}
	
	@Test
	//Tests whether same ranges are combined correctly
	void combineSameRangeTest() {
		Range range1 = new Range(80.0, 90.0);
		Range range2 = new Range(80.0, 90.0);
		Range rangeComb = new Range(80.0, 90.0);
		
		assertEquals(rangeComb,exampleRange.combine(range1, range2));
		
		
	}
	//Tests whether overlapping ranges are combined correctly
	@Test
	void combineOverlappingRangesTest(){
		Range range1 = new Range(80.0, 90.0);
		Range range2 = new Range(50.0, 60.0);

//Your expected range for combineTest5 is incorrect. Resolved

		Range rangeComb = new Range(50.0,90.0);
		
		assertEquals(rangeComb,exampleRange.combine(range1, range2));
	}
	
	
	//Tests low range returns are equal to the lowest possible value in the range
	@Test
	void constrainLowRangeValueTest() {
		Range range1 = new Range(80.0, 90.0);
		double lowRangeValue = 5.0;
		
		assertEquals(range1.constrain(lowRangeValue),80.0);
	}
	
	//Tests high range returns are equal to the highest possible value in the range
	@Test
	void constrainHighRangeValueTest() {
		Range range1 = new Range(80.0, 90.0);
		double highRangeValue = 105.0;
		
		assertEquals(range1.constrain(highRangeValue),90.0);
	}
	//Tests internal range returns are equal to the closests possible value in the range
	@Test
	void constrainInternalRangeTest() {
		Range range1 = new Range(80.0, 90.0);
		double internalRangeValue = 84.0;
		
		assertEquals(range1.constrain(internalRangeValue),84.0);
	}
	//Tests if contains value is true
	@Test
	void containTrueTest() {
		Range range1 = new Range(80.0, 90.0);
		double internalValue = 84.0;
		assertTrue(range1.contains(internalValue) == true);
	}

	//Tests if contains external value is false
	@Test
	void containExternalValueFalseTest() {
		Range range1 = new Range(80.0, 90.0);
		double externalValue = 55.0;
		assertFalse(range1.contains(externalValue) == true);
	}
	
	//Tests if this method works for any objects as suggested by its documentation
	@Test
	void equalObjectTest() {
		Range test = new Range(0,100);
		Range test1 = new Range(0,50);
		
		assertFalse(test.equals(test1));
		
	}
	
	//Tests if this method correctly equates objects
	@Test
	void equalSameRangeTest() {
		Range test = new Range(0,100);
		Range test1 = new Range(0,100);
		
		assertTrue(test.equals(test1) == true);
		
	}
	
	//Tests if this method correctly equates objects
	@Test
	void equalNullRangeTest() {
		Range test = null;
		Range test1 = new Range(0,100);
		
		assertFalse(test1.equals(test) == true);
		
	}
	
	//Tests whether expand can handle null values.
	@Test
	void expandNullTest() {
		
		Range test = new Range(0,100);
		
		assertThrows(InvalidParameterException.class,()->{test.expand(null,0,1);});
		
		
	
	}
	
	//tests null to see if it throws a InvalidParameterException 
	@Test
	void expandInvalidParameterExceptionTest() {
		Range test = null;
		exampleRange = new Range(0,100);
		assertThrows(InvalidParameterException.class,()->{test.expand(exampleRange,0,1);});
		
	}
	//test negative lower bounds
	@Test
	void expandNegativeLowerBoundTest() {
		Range test =  new Range(0,100);
		exampleRange = new Range(0,100);
		
		int ngtive = -1;

			test.expand(exampleRange,ngtive,100);
			assertEquals(exampleRange, test); // return true

	}
	//test negative lower bounds
	@Test
	void expandNegativeLowerBoundTest2() {
		Range test =  new Range(0,100);
		exampleRange = new Range(0,100);
		
		int ngtive = -1;

			test.expand(exampleRange,0,ngtive);
			assertEquals(test,exampleRange); // return true

	}
	//test char numbers
	@Test
	void expandCharNumbersTest() {
		Range test =  new Range('0','9');
		exampleRange = new Range('0','5');


			test.expand(exampleRange,'0','5');
			assertEquals(test, exampleRange); // return true
		

	}
	
	//Tests if range is expanded correctly
	@Test
	void expandRangeTest() {
		Range test =  new Range(2,6);

		Range expectedRange = new Range(1,8);
		assertEquals(test.expand(test,0.25,0.5), (expectedRange));
	}
	
	
	
	//Tests null value in the range
	@Test
    void expandToIncludeNullValueTest() {
		
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
    void expandToIncludeSameNumbersTest() {
		
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
    void expandToIncludeCharValuesTest() {
		
		//exercise
		Range range1 = new Range('1', '5');


		double specifiedValue = 0;
		
		Range rangeTest = range1.expandToInclude(range1, specifiedValue);
		
		Range expectedRange = new Range(0,5);

				
		//verify
		assertEquals(range1.expandToInclude(range1, specifiedValue), expectedRange);
			
	}
	
	@Test
	void testIfNumberTrue() {
        Range range2 = new Range(199.9, 200.0);
        double value = 1000000;
        range2.expandToInclude(range2,value);

        if(range2.contains(value / 2) == false) {

        	range2.expandToInclude(range2, value/2);
            assertEquals(range2.expandToInclude(range2,value).getUpperBound(), value);


        }

    }
	
	
	//Tests a negative number in the specified value
	@Test
    void expandToIncludeNegativeNumberTest() {
		
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
    void expandToIncludeValueWithinRangeTest() {
		
		//exercise
		Range range1 = new Range(0,5);


		double specifiedValue = 4;
		
		Range rangeTest = range1.expandToInclude(range1, specifiedValue);
		
		Range expectedRange = new Range(0,5);

				
		//verify
		assertEquals(range1.expandToInclude(range1, specifiedValue), expectedRange);
			
	}
	

	
	
	//Tests a specified value that is greater than the upper bound of the range
	
	//new test to fix this:
	
//  else if (value > range.getUpperBound()) {
//  return new Range(range.getLowerBound(), value);
//}
	
	@Test
    void expandToIncludeValueGreaterThanRangeUpperBoundTest() {
		
		//exercise
		Range range1 = new Range(0,5);


		double specifiedValue = 6;
		
		Range rangeTest = range1.expandToInclude(range1, specifiedValue);
		
		Range expectedRange = new Range(0,6);

				
		//verify
		assertEquals(range1.expandToInclude(range1, specifiedValue), expectedRange);
			
	}
	
	//Tests integer values for range to see if the central value is double
	@Test
    void getCentralValueIntegerRangeValuesTest() {
		
		//exercise
		Range range1 = new Range(3,7);
		double centralValue = 5.0;
				
		//verify
		assertEquals(range1.getCentralValue(), centralValue);
			
	}
	

	//Tests range value that is lower in the first value and higher in the second to see if it work
	@Test
    void getCentralValueHigherRangeValueInLowerBoundTest() {

		//exercise
		Range range1 = new Range(1,5);	
		double centralValue = 3.0;
			
		//verify
		assertEquals(range1.getCentralValue(), centralValue);

	}
	
	//Tests range values that are equal
	//getCentralValueEqualRangeValueTest(): I do not understand why you are using try-catches. Looks like you are overusing try-catches.

	@Test
    void getCentralValueEqualRangeTest() {
		

		//exercise
		Range range1 = new Range(3,3);
		double centralValue = 3.0;
				
		//verify
		assertEquals(range1.getCentralValue(), centralValue);
		

			
	}
	

	
	
	
	//Tests range value that is null 
	//getCentralValueTest4() is incorrect. You cannot call a method from a null input. Also, getUpperBoundTest3()

//	@Test
//    void getCentralValueNullRangeTest() {
//		try {
//		//exercise
//		Range range1 = null;
//		double centralValue = 0;
//				
//		//verify
//		
//		assertEquals(range1.getCentralValue(), centralValue);
//		}
//		catch(Exception e){
//			assertEquals(5,7);//return false
//		}
//	}
	

	//Tests length value with both int and double 
	@Test
    void getLengthIntAndDoubleValueTest() {
		
		//exercise
		Range range1 = new Range(0, 10.0);
		double lengthValue = 10.0;
				
		//verify
		assertEquals(range1.getLength(), lengthValue);
			
	}
	
	//Tests char values as numbers to see if the length can be calculated 
	@Test
    void getLengthTestCharNumberRange() {
		
		//exercise
		Range range1 = new Range('0', '5');
		double lengthValue = 5.0;
				
		//verify
		assertEquals(range1.getLength(), lengthValue);
			
	}
	
	
	//Tests a really high value like 99999999 
	@Test
    void getLengthTestHighValue() {
		
		//exercise
		Range range1 = new Range(0, 99999999);
		double lengthValue = 99999999;
				
		//verify
		assertEquals(range1.getLength(), lengthValue);
			
	}
	
	
	//Tests lower number in range first and then higher 
	@Test
    void getLowerBoundLowerNumberTest() {
		
		
				

		Range range1 = new Range(1,4);
		double lowerBoundValue = 1;
			
		assertEquals(range1.getLowerBound(), lowerBoundValue);
		

	}

	//Tests negative numbers in the range
	@Test
    void getLowerBoundNegativeNumberTest() {
		
		//exercise
		Range range1 = new Range(-5,1);
		double lowerBoundValue = -5;
				
		//verify
		assertEquals(range1.getLowerBound(), lowerBoundValue);
			
	}
	
	//Tests the same negative numbers in the range
	@Test
    void getLowerBoundSameNegativeNumberTest() {
		
		//exercise
		Range range1 = new Range(-5,-5);
		double lowerBoundValue = -5;
				
		//verify
		assertEquals(range1.getLowerBound(), lowerBoundValue);
			
	}
	
	
	//Tests negative numbers in the range
	@Test
    void getUpperBoundNegativeNumberTest() {
		
		//exercise
		Range range1 = new Range(-1,1);
		double upperBoundValue = 1.0;
				
		//verify
		assertEquals(range1.getUpperBound(), upperBoundValue);
		
		//test failed since it expected -1.0 even though 1.0 is obviosuly the higher value 
	}
	
	//Tests same numbers in the range
	@Test
    void getUpperBoundSameNumberTest() {
		
		//exercise
		Range range1 = new Range(1,1);
		double upperBoundValue = 1.0;
				
		//verify
		assertEquals(range1.getUpperBound(), upperBoundValue);
		
	}
	
	
	
	private Range comparedRange;


	

	 
	    
	//new test
	@Test
	void testIntersectsValidValue() {
	 exampleRange = new Range(2.0, 7.0);

	 boolean actualValue = exampleRange.intersects(1.0, 5.0);
	 boolean expectedValue = true;
	 
	 assertEquals(expectedValue, actualValue);	 
	}
	

	//new test
	@Test
	void testIntersectsUpperBoundary() {
	 exampleRange = new Range(2.0, 7.0);

	 boolean actualValue = exampleRange.intersects(7.0, 7.0);
	 boolean expectedValue = false;
	 
	 assertEquals(expectedValue, actualValue);	 
	}
	
	//new test
	@Test
	void testIntersectsLowerBoundary() {
	 exampleRange = new Range(2.0, 7.0);

	 boolean actualValue = exampleRange.intersects(2.0, 2.0);
	 boolean expectedValue = false;
	 
	 assertEquals(expectedValue, actualValue);	 
	}
	
	@Test
    void testIntersectsOverlappedRange() {
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

		
			assertThrows(IllegalArgumentException.class, ()-> { 		 
				 exampleRange = null;

				
				comparedRange = Range.shift(exampleRange, 4.0);
			});

		 
		 
//		 if (exampleRange == null) {
//			 throw  new InvalidParameterException("Null base Range parameter");
//		 }else {
//			 assertEquals(comparedRange,Range.shift(exampleRange, 4.0));
//		 }
		 
	
	}
	
	
	private Number[] arrayNumberObjects;

	private double[] array = new double [5];

	

	//new test
    @Test
    void testLengthOfTwoArrays() {
        arrayNumberObjects = DataUtilities.createNumberArray(array);
        assertEquals(array.length, arrayNumberObjects.length);
        //check if the array of doubles and array of Number objects has the same length
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
//		 if (exampleRange == null) {
//			 throw new InvalidParameterException("Null base Range parameter");
//		 }else {
//			 assertEquals(comparedRange,Range.shift(exampleRange, 4.0, true));
//		 } 
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
	
	//new test
	@Test
	void constructorTestSameValue(){
		Range actualRange = new Range(1.0,1.0);
		Range expectedRange = new Range(1.0,1.0);
		
		assertEquals(expectedRange, actualRange);
	}
	
	//new test
	@Test
	void constructorTestNegativeValue(){
		Range actualRange = new Range(-2.0,-1.0);
		Range expectedRange = new Range(-2.0,-1.0);
		
		assertEquals(expectedRange, actualRange);
	}
	
	
	//new test
	@Test
    void testRangeCombineMultipleRanges() {

        Range range1 = new Range(0,0);
        Range range2 = new Range(1,5);
        Range range3 = new Range(4,10);
        Range range4 = new Range(0,3);

        Range[] r = new Range[4];

        r[0] = range1;
        r[1] = range2;
        r[2] = range3;
        r[3] = range4;

        for(int i = 0; i < 4; i ++) {

            range1.combine(range1, r[i]);
            


        }

        assertEquals(range1, new Range(0,10));


    }

	
	
	@AfterEach
	void tearDown() throws Exception {
		
	}
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}
}
