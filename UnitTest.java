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
        dClass = objInspector.inspectDeclaringClass(m[0]);
        org.junit.Assert.assertEquals(dClass, "ClassD");

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
	    String expectedStr = "Method: setVal\n\tExceptions thrown: java.lang.Exception"+
	    		"\n\tParameter type(s): int\n\tReturn type: void\n\tModifier: public"+
	    		"\n\nMethod: run\n\tReturn type: void\n\tModifier: public"+
	    		"\n\nMethod: toString\n\tReturn type: java.lang.String\n\tModifier: public"+
	    		"\n\nMethod: getVal\n\tReturn type: int\n\tModifier: public"+
	    		"\n\nMethod: printSomething\n\tReturn type: void\n\tModifier: private\n\n";
	    org.junit.Assert.assertEquals(expectedStr,strMethods);
	    
	    // testing class is ClassB, so information about 3 methods is expected
	    // must have a try catch clause b/c there is a throw in ClassB
        try{
        	obj = new ClassB();
        }
        catch (Exception e){}
        objClass = obj.getClass();
        strMethods = objInspector.inspectMethods(objClass);
        expectedStr = "Method: run\n\tReturn type: void\n\tModifier: public"+
        "\n\nMethod: toString\n\tReturn type: java.lang.String\n\tModifier: public"+
        "\n\nMethod: func3\n\tParameter type(s): int\n\tReturn type: void\n\tModifier: public\n\n";
	    org.junit.Assert.assertEquals(expectedStr,strMethods);
	}
	
	@Test  
	// test names of constructors and their corresponding parameter types and modifiers
	public void testConstructors() {
		// testing class is ClassD, so 2 constructors names are expected
	    Object obj= new ClassD();
	    Class objClass = obj.getClass();
	    Inspector objInspector = new Inspector();
	    String strConstructors = objInspector.inspectConstructors(objClass);
	    String expectedStr = "Constructor: ClassD\n\tModifier: public"+
	    "\n\nConstructor: ClassD\n\tParameter type(s): int\n\tModifier: public\n\n";
	    org.junit.Assert.assertEquals(expectedStr,strConstructors);
	}
}
