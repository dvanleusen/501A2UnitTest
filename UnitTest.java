import org.junit.Test;
import java.lang.reflect.*;

/**
* Assignment 2
* @author Daniel Van Leusen
* Student id: 10064708
* E-mail: danvanleusen@yahoo.co.uk
* @version October 27, 2016
* <p>
* This is the Unit Test file to test reflection codes. 
*/

public class UnitTest {
   
	@Test
	// test declaring class name
    public void testDeclaringClass() {
		// declaring class is ClassA, "ClassA" is expected
        Object obj = new ClassA();
        Class objClass = obj.getClass();
        Inspector objInspector = new Inspector();
        Method[] m = objClass.getMethods();
        String dClass=objInspector.inspectDeclaringClass(m[0]);
        org.junit.Assert.assertEquals(dClass, "ClassA");
        
        // declaring class is ClassD, "ClassD" is expected
        obj = new ClassD();
        objClass = obj.getClass();
        m = objClass.getMethods();
        dClass=objInspector.inspectDeclaringClass(m[0]);
        org.junit.Assert.assertEquals(dClass, "ClassD");

        //
    }

	@Test  
	// test immediate superclass name
	public void testSuperClass() {
		// testing class is a string, so its immediate class is "java.lang.Object"
	    Object obj="Test String";
	    Class objClass = obj.getClass();
	    Inspector objInspector = new Inspector();
	    String strSuperClass=objInspector.inspectSuperClass(objClass);
	    org.junit.Assert.assertEquals("java.lang.Object",strSuperClass);
	    
	    // testing class is ClassB, so its immediate class "ClassC" is expected
	    // must have a try catch clause b/c there is a throw in ClassB
        try{
        	obj = new ClassB();
        }
        catch (Exception e){}
        objClass = obj.getClass();
	    strSuperClass=objInspector.inspectSuperClass(objClass);
	    org.junit.Assert.assertEquals("ClassC",strSuperClass);

	}
}
