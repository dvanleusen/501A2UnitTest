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
//test declaring class name
    public void testDeclaringClass() {

		//Declaring class is ClassA, "ClassA" is expected
        Object obj = new ClassA();
        Class objClass = obj.getClass();
        Inspector objInspector = new Inspector();
        Method[] m = objClass.getMethods();
        String dClass=objInspector.inspectDeclaringClass(m[0]);
        org.junit.Assert.assertEquals(dClass, "ClassA");
        
        //Declaring class is ClassD, "ClassD" is expected
        obj = new ClassD();
        objClass = obj.getClass();
        objInspector = new Inspector();
        m = objClass.getMethods();
        dClass=objInspector.inspectDeclaringClass(m[0]);
        org.junit.Assert.assertEquals(dClass, "ClassD");

    }
}
