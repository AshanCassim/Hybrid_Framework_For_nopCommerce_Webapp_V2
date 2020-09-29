package app.nopcommerce.testCases;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;



public class T {

	public static void main(String[] args) throws FilloException 
	{
		
		Fillo f = new Fillo();
		
		Connection connection = f.getConnection("TestData/LoginData.xlsx");
		
		String strQuery = "Select * from Sheet1";
		
		Recordset recordset=connection.executeQuery(strQuery); // Execute Query and store result in Recordset
    
		System.out.println(recordset.getCount());
		
		int i =1;
		
		while(recordset.next())
		{  // Condition till record set has values
			
			
			
			System.out.println(i);
			
			System.out.println(recordset.getField("username"));
			System.out.println(recordset.getField("password"));
			i++;
		}
			
				
		

	}

}
