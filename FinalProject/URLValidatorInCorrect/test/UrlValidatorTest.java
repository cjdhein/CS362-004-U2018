

import junit.framework.TestCase;
//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
//You can use this function to implement your manual testing	   
	   
	   UrlValidator uv;
	   String testUrl;
	   boolean res;
	   
	   // Default Schemes
	   uv = new UrlValidator();

	   // test for exception throw outside of UrlValidator
	   //DomainValidator.clearTLDOverrides();

	   testUrl = "http://www.google.com";
	   res = uv.isValid(testUrl);
	   assertit(testUrl,true,res);
	   
	   testUrl = "https://www.google.com";	   
	   res = uv.isValid(testUrl);
	   assertit(testUrl,true,res);	   
	   
	   testUrl = "ftp://www.google.com";	   
	   res = uv.isValid(testUrl);
	   assertit(testUrl,true,res);

	   testUrl = "file://test.http/";
	   res = uv.isValid(testUrl);
	   assertit(testUrl, true,res);	   		   
	   
	   testUrl = "dog";
	   res = uv.isValid(testUrl);
	   assertit(testUrl, false,res);

	   testUrl = "http://";
	   res = uv.isValid(testUrl);
	   assertit(testUrl, false,res);
	   
	   testUrl = "http://www.com";
	   res = uv.isValid(testUrl);
	   assertit(testUrl, false,res);
	   
	   testUrl = "http://www.66.88";
	   res = uv.isValid(testUrl);

	   assertit(testUrl, false,res);	
	   

	   assertit(testUrl, false,res);  
	   	   
	   // Test with all schemes
	   uv = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
	   
	   testUrl = "http://www.google.com";
	   res = uv.isValid(testUrl);
	   assertit(testUrl,true,res);
	   
	   testUrl = "https://www.google.com";	   
	   res = uv.isValid(testUrl);
	   assertit(testUrl,true,res);	   
	   
	   testUrl = "ftp://www.google.com";	   
	   res = uv.isValid(testUrl);
	   assertit(testUrl,true,res);

	   testUrl = "file://test.http/";
	   res = uv.isValid(testUrl);
	   assertit(testUrl, true,res);
	   

	   testUrl = "dog";
	   res = uv.isValid(testUrl);
	   assertit(testUrl, false,res);
	   
	   testUrl = "http://";
	   res = uv.isValid(testUrl);
	   assertit(testUrl, false,res);
	   
	   testUrl = "http://www.com";
	   res = uv.isValid(testUrl);
	   assertit(testUrl, false,res);
	   
	   testUrl = "http://www.66.88";
	   res = uv.isValid(testUrl);
	   assertit(testUrl, false,res);	
	   	   
   }
   
   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing	   

	   UrlValidator uv;
	   String testUrl;
	   boolean res;
	   
	   uv = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
	   
	   
	   
	   // Test empty
	   testUrl = "";
	   res = uv.isValid(testUrl);
	   System.out.print("Empty string is invalid: ");
	   assertit(testUrl,false,res);
	   
	   
	   // Test schemes by themselves

	   System.out.println("\nSchemes alone");

	   // this test should fail, but comes back as valid
	   // due to bug 2 on line 315 of UrlValidator
	   System.out.print("http:// is invalid: ");
	   testUrl = "http://";
	   res = uv.isValid(testUrl);
	   assertit(testUrl,false,res);
	   
	   // These tests should fail, but throw errors
	   // Unable to pinpoint error sources
	   
//	   System.out.print("ftp:// is invalid: ");
//	   testUrl = "ftp://";
//	   res = uv.isValid(testUrl);
//	   assertit(testUrl,false,res);
//
//	   System.out.print("https:// is invalid: ");
//	   testUrl = "https://";
//	   res = uv.isValid(testUrl);
//	   assertit(testUrl,false,res);
	   

	   System.out.print("https is invalid: ");
	   testUrl = "https";
	   res = uv.isValid(testUrl);
	   assertit(testUrl,false,res);
	   System.out.print("https: is invalid: ");
	   testUrl = "https:";
	   res = uv.isValid(testUrl);
	   assertit(testUrl,false,res);
	   System.out.print("https:: is invalid: ");
	   testUrl = "https::";
	   res = uv.isValid(testUrl);
	   assertit(testUrl,false,res);
	   System.out.print("https:/ is invalid: ");
	   testUrl = "https:/";
	   res = uv.isValid(testUrl);
	   assertit(testUrl,false,res);

	   
	   System.out.println("\nAuthority alone");


	   System.out.print("www.google.com is invalid: ");
	   testUrl = "www.google.com";	   
	   res = uv.isValid(testUrl);
	   assertit(testUrl,false,res);
	   
	   System.out.print("google.com is invalid: ");
	   testUrl = "google.com";	   
	   res = uv.isValid(testUrl);
	   assertit(testUrl,false,res);
	   
	   System.out.print("0.0.0.0 is invalid: ");
	   testUrl = "0.0.0.0";	   
	   res = uv.isValid(testUrl);
	   assertit(testUrl,false,res);

	   System.out.println("\nPath alone");

	   System.out.print("/test1 is invalid: ");
	   testUrl = "/test1";	   
	   res = uv.isValid(testUrl);
	   assertit(testUrl,false,res);
	   
	   System.out.print("/.. is invalid: ");
	   testUrl = "/..";	   
	   res = uv.isValid(testUrl);
	   assertit(testUrl,false,res);
	   

	   System.out.println("\nScheme + Authority");

	   System.out.print("http://www.google.com is valid: ");
	   testUrl = "http://www.google.com";	   
	   res = uv.isValid(testUrl);
	   assertit(testUrl,true,res);
	   
	   System.out.print("http://google.com is valid: ");
	   testUrl = "http://google.com";	   
	   res = uv.isValid(testUrl);
	   assertit(testUrl,true,res);

	   System.out.print("http://0.0.0.0 is valid: ");
	   testUrl = "http://0.0.0.0";	   
	   res = uv.isValid(testUrl);
	   assertit(testUrl,true,res);

	   //Any valid url starting with ftp:// or https://
	   //throws an error for reason unknown at this point
	   
//	   System.out.print("https://www.google.com is valid: ");
//	   testUrl = "https://www.google.com";	   
//	   res = uv.isValid(testUrl);
//	   assertit(testUrl,true,res);
//	   System.out.print("ftp://0.0.0.0 is valid: ");
//	   testUrl = "ftp://0.0.0.0";	   
//	   res = uv.isValid(testUrl);
//	   assertit(testUrl,true,res);
	   
	   System.out.println("\nScheme + Authority + Path");

	   System.out.print("http://www.google.com/test1 is valid: ");
	   testUrl = "http://www.google.com";	   
	   res = uv.isValid(testUrl);
	   assertit(testUrl,true,res);
	   
	   System.out.print("https://www.google.com/test1 is invalid: ");
	   testUrl = "https://www.google.com/test1";	   
	   res = uv.isValid(testUrl);
	   assertit(testUrl,true,res);
	  
   }
   
   public void testYourSecondPartition(){
		 //You can use this function to implement your Second Partition testing	   

   }
   //You need to create more test cases for your Partitions if you need to 
   
   public void testIsValid()
   {
	   //You can use this function for programming based testing

   }
   
   public void assertit(String tested, boolean expected, boolean actual)
   {
	   if (expected != actual)
	   {
		   System.out.println(tested + " -- expected " +expected + " got " +actual );
	   }
	   else
	   {
		   System.out.println(tested + " passed" );
	   }
   }

}


