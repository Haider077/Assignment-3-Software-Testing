package org.jfree.data;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
class DataUtilitiesTest {
private Values2D value;

private double[] array = new double[5];
private double[][] structure = new double[5][5];
private Number[] arrayNumberObjects;
private Number[][] structureNumberObjects;
private KeyedValues data;
	@BeforeEach
	void setUp() throws Exception {
		value = mock(Values2D.class);
		when(value.getColumnCount()).thenReturn(4);
		when(value.getRowCount()).thenReturn(3);
		when(value.getValue(0, 2)).thenReturn(5);
		when(value.getValue(1, 2)).thenReturn(7);
		when(value.getValue(2, 2)).thenReturn(1);
		
		
		array[0] = 1.0;
		array[1] = 2.0;
		array[2] = 3.0;
		array[3] = 4.0;
		array[4] = 5.0;
		structure[0][1] = 1.0;
		structure[1][0] = 2.0;
	}
	//verifies that columns total is calculated correctly given a positive valid value
	@Test
	void calculateColumnTotalTest() {
		assertEquals(13, DataUtilities.calculateColumnTotal(value, 2), 0.01d);
		verify(value, times(3)).getValue(anyInt(), anyInt());
	}
	//verifies that columns total is calculated correctly given a negative value
	@Test
	void calculateColumnTotalTest1() {
		assertEquals(13, DataUtilities.calculateColumnTotal(value, -2), 0.01d);
		verify(value, times(3)).getValue(anyInt(), anyInt());
	}
	//verifies that columns total is calculated correctly given a invalid value
	@Test
	void calculateColumnTotalTest2() {
		assertEquals(13, DataUtilities.calculateColumnTotal(value, 785), 0.01d);
		verify(value, times(3)).getValue(anyInt(), anyInt());
	}
	//verifies that columns total is calculated correctly given a value of 0
	@Test
	void calculateColumnTotalTest3() {
		assertEquals(13, DataUtilities.calculateColumnTotal(value, 0), 0.01d);
		verify(value, times(3)).getValue(anyInt(), anyInt());
	}
	//verifies that columns total is calculated correctly given a experessive value
	@Test
	void calculateColumnTotalTest4() {
		assertEquals(13, DataUtilities.calculateColumnTotal(value, 5/7), 0.01d);
		verify(value, times(3)).getValue(anyInt(), anyInt());
	}
	//Tests a negative value to see if a total of zero will be returned
    @Test
    void calculateRowTotalTest1() {
    	
    	//test
        assertEquals(0, DataUtilities.calculateRowTotal(value, -2), .01d);
        
        //verify
        verify(value, times(3)).getValue(anyInt(), anyInt());
    }
    
    
    //Tests an invalid value that is higher than row count to see if a total of zero will be returned
    @Test
    void calculateRowTotalTest2() {
    	
    	//test
        assertEquals(0, DataUtilities.calculateRowTotal(value, 7), .01d);
        
        //verify
        verify(value, times(3)).getValue(anyInt(), anyInt());
    }
    
    
    //Tests a char value to see what will be returned
    @Test
    void calculateRowTotalTest3() {	
    	
    	//test
        assertEquals(0, DataUtilities.calculateRowTotal(value, '$'), .01d);
        
        //verify
        verify(value, times(3)).getValue(anyInt(), anyInt());
    }
    
    @Test
	void test1() {
		arrayNumberObjects = DataUtilities.createNumberArray(array);
		assertEquals(array.length, arrayNumberObjects.length);
		//check if the array of doubles and array of Number objects has the same length
	}
	@Test
	void test2() {
		arrayNumberObjects = DataUtilities.createNumberArray(array);
		assertSame(array[0], arrayNumberObjects[0]);
		//check if the first index of doubles array is the same as the first index of the Number objects array
	}
	
	@Test
	void test3() {
		arrayNumberObjects = DataUtilities.createNumberArray(array);
		assertSame(array[1], arrayNumberObjects[1]);
		//check if the second index of doubles array is the same as the second index of the Number objects array
	}
	
	@Test
	void test4() {
		structureNumberObjects = DataUtilities.createNumberArray2D(structure);
		assertEquals(structure[0][1], structureNumberObjects[0][1]);
		//check if the indexes in 2 structure arrays are the same as the corresponding indexes in 2 Number objects arrays
	}
	
	@Test
	void test5() {
		structureNumberObjects = DataUtilities.createNumberArray2D(structure);
		assertEquals(structure[1][0], structureNumberObjects[1][0]);
		//check if the indexes in 2 structure arrays are the same as the corresponding indexes in 2 Number objects arrays
		
	}
	
	@Test
	void test6() {
		structureNumberObjects = DataUtilities.createNumberArray2D(structure);
		assertEquals(structure.length, structureNumberObjects.length);
		//check if length of the structure containing double primitives is the same as the length of the array of arrays of Number object
	}
	//Tests if cumulative is the same as expected value
	@Test
	void getCumulativePercentagesTest() {
		
		
		KeyedValues data = mock (KeyedValues.class);
		
		
		when(data.getItemCount()).thenReturn(3);
        when(data.getKey(0)).thenReturn(5);
        when(data.getKey(1)).thenReturn(9);
        when(data.getKey(2)).thenReturn(2);
        when(data.getValue(0)).thenReturn(0);
        when(data.getValue(1)).thenReturn(2);
        when(data.getValue(2)).thenReturn(2);
        when(data.getIndex(0)).thenReturn(0);
        when(data.getIndex(1)).thenReturn(1);
        when(data.getIndex(2)).thenReturn(2);
        
        double[] poetentialValues = {0.0,0.5,1.0};
        KeyedValues offical = DataUtilities.getCumulativePercentages(data);
        double[] cumulative = {0,1.0,2};
        
        for(int i = 0; i < cumulative.length; i ++) {
        	cumulative[i] = (double) offical.getValue(i);
        }
        
        assertEquals(cumulative[0] - poetentialValues[0] , 0);
        assertEquals(cumulative[1] - poetentialValues[1] , 0);
        assertEquals(cumulative[2] - poetentialValues[2] , 0);
        
	}
	//Tests if null data is possible
	@Test
	void getCumulativePercentagesTest1() {
		
		
		KeyedValues data = mock (KeyedValues.class);
		
		data = null;
		
		try{
		when(data.getItemCount()).thenReturn(3);
        when(data.getKey(0)).thenReturn(5);
        when(data.getKey(1)).thenReturn(9);
        when(data.getKey(2)).thenReturn(2);
        when(data.getValue(0)).thenReturn(0);
        when(data.getValue(1)).thenReturn(2);
        when(data.getValue(2)).thenReturn(2);
        when(data.getIndex(0)).thenReturn(0);
        when(data.getIndex(1)).thenReturn(1);
        when(data.getIndex(2)).thenReturn(2);
        
        double[] poetentialValues = {0.0,0.5,1.0};
        KeyedValues offical = DataUtilities.getCumulativePercentages(data);
        double[] cumulative = {0,1.0,2};
        
        for(int i = 0; i < cumulative.length; i ++) {
        	cumulative[i] = (double) offical.getValue(i);
        }
        
        assertEquals(-cumulative[0] + poetentialValues[0] , 0);
        assertEquals(-cumulative[1] + poetentialValues[1] , 0);
        assertEquals(-cumulative[2] + poetentialValues[2] , 0);
		}
	
		catch(Exception e) {
			assertEquals(5,7);
			
		}
	}
	//Tests with alternate values and percentages
		@Test
		void getCumulativePercentagesTest2() {
			
			
			KeyedValues data = mock (KeyedValues.class);
			
			
			when(data.getItemCount()).thenReturn(3);
	        when(data.getKey(0)).thenReturn(5);
	        when(data.getKey(1)).thenReturn(9);
	        when(data.getKey(2)).thenReturn(2);
	        when(data.getValue(0)).thenReturn(0);
	        when(data.getValue(1)).thenReturn(2);
	        when(data.getValue(2)).thenReturn(6);
	        when(data.getIndex(0)).thenReturn(0);
	        when(data.getIndex(1)).thenReturn(1);
	        when(data.getIndex(2)).thenReturn(2);
	        
	        double[] poetentialValues = {0.0,0.25,1.0};
	        KeyedValues offical = DataUtilities.getCumulativePercentages(data);
	        double[] cumulative = {0,1.0,2};
	        
	        for(int i = 0; i < cumulative.length; i ++) {
	        	cumulative[i] = (double) offical.getValue(i);
	        }
	        
	        assertEquals(-cumulative[0] + poetentialValues[0] , 0);
	        assertEquals(-cumulative[1] + poetentialValues[1] , 0);
	        assertEquals(-cumulative[2] + poetentialValues[2] , 0);
	        
		}
	
	
}