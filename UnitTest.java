import org.junit.Test;
import java.lang.reflect.*;
import java.util.*;

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
        String dClass=objInspector.inspectDeclaringClass(obj);
        org.junit.Assert.assertEquals(dClass, "ClassA");
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
	    strSuperClass = objInspector.inspectSuperClass(objClass);
	    org.junit.Assert.assertEquals("ClassC",strSuperClass);
	}
	
	@Test  
	// test names of interfaces implemented
	public void testInterfaces() {
		// testing class is ClassA, so "java.io.Serializable, Runnable" is expected
	    Object obj= new ClassA();
	    Class objClass = obj.getClass();
	    Inspector objInspector = new Inspector();
	    String strInterfaces = objInspector.inspectInterfaces(objClass);
	    org.junit.Assert.assertEquals("java.io.Serializable, java.lang.Runnable",strInterfaces);
	    
	    // testing class is ClassB, so "Runnable" is expected
	    // must have a try catch clause b/c there is a throw in ClassB
        try{
        	obj = new ClassB();
        }
        catch (Exception e){}
        objClass = obj.getClass();
	    strInterfaces = objInspector.inspectInterfaces(objClass);
	    org.junit.Assert.assertEquals("java.lang.Runnable",strInterfaces);
	}
	
	@Test  
	// test names of Methods and their corresponding exceptions, parameter types, return type, and modifiers
	public void testMethods() {
		// testing class is ClassA, so information about 5 methods is expected
	    Object obj= new ClassA();
	    Class objClass = obj.getClass();
	    Inspector objInspector = new Inspector();
	    String strMethods = objInspector.inspectMethods(objClass);
	    boolean b=strMethods.contains("Method: setVal\n\tExceptions thrown: java.lang.Exception");
	    b=b&strMethods.contains("Parameter type(s): int\n\tReturn type: void\n\tModifier: public"); 
	    b=b&strMethods.contains("Method: run\n\tReturn type: void\n\tModifier: public"); 
	    b=b&strMethods.contains("Method: toString\n\tReturn type: java.lang.String\n\tModifier: public"); 
	    b=b&strMethods.contains("Method: getVal\n\tReturn type: int\n\tModifier: public"); 
	    b=b&strMethods.contains("Method: printSomething\n\tReturn type: void\n\tModifier: private"); 
	    org.junit.Assert.assertTrue(b);	    
	    
	    // testing class is ClassB, so information about 3 methods is expected
	    // must have a try catch clause b/c there is a throw in ClassB
        try{
        	obj = new ClassB();
        }
        catch (Exception e){}
        objClass = obj.getClass();
        strMethods = objInspector.inspectMethods(objClass);
        
        b=strMethods.contains("Method: run\n\tReturn type: void\n\tModifier: public");
        b=b&strMethods.contains("Method: toString\n\tReturn type: java.lang.String\n\tModifier: public");
        b=b&strMethods.contains("Method: func3\n\tParameter type(s): int\n\tReturn type: void\n\tModifier: public");
        org.junit.Assert.assertTrue(b);	 
	}
	
	@Test  
	// test names of constructors and their corresponding parameter types and modifiers
	public void testConstructors() {
		// testing class is ClassD, so 2 constructors names are expected
	    Object obj= new ClassD();
	    Class objClass = obj.getClass();
	    Inspector objInspector = new Inspector();
	    String strConstructors = objInspector.inspectConstructors(objClass);
	    boolean b=strConstructors.contains("Constructor: ClassD\n\tModifier: public");
	    b=b&strConstructors.contains("Constructor: ClassD\n\tParameter type(s): int\n\tModifier: public");
	    org.junit.Assert.assertTrue(b);	 
	}
	
    @Test
    // test names of fields the class declares and their type and modifiers
    // if the field is an object reference and recursive is set to false, print out reference value
    // if it's an array, name, component type, length, and all its contents are printed where valid
    public void testField () {    
    	try{
	        ClassTest cls=new ClassTest();
	        Class objClass = cls.getClass();
	        Inspector objInspector = new Inspector();
	        
	        Vector objectsToInspect = new Vector();
	        String returnString=objInspector.inspectFields(cls,objClass,objectsToInspect); 
		    boolean b=returnString.contains("Field: valArray\n\tType: [I\n\tModifier: private\n\tComponent type: int\n\tLength: 3\n\tValues: 1, 2, 3");
		    b=b&returnString.contains("Field: valClass\n\tType: ClassA\n\tModifier: private\n\tReference value: ClassA 96639997");
		    b=b&returnString.contains("Field: vallarray\n\tType: [LClassA;\n\tModifier: private\n\tComponent type: ClassA\n\tLength: 10");
		    org.junit.Assert.assertTrue(b);
		    }
	    
	    catch (Exception e){}
    }
}
