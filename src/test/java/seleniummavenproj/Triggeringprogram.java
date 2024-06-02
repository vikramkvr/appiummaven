package seleniummavenproj;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.testng.TestNG;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.collections.Lists;
import org.w3c.dom.Attr;

@Test
public class Triggeringprogram {
	
	Properties obj = new Properties();
	DataHandler dh = new DataHandler();
	@BeforeTest
	public void loadobjectfile() throws IOException
	{
	
	FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\objectrepo.properties");
	obj.load(fs);
	System.out.println("here in loadobjectfile method");
	}
	
	List<String> testcaseslist = new ArrayList<String>();
	
    public void test() throws IOException
    {
    	System.out.println("Here in Test");
    	final String xmlFilePath = System.getProperty("user.dir") + obj.getProperty("xmlfilepath");
    	//final String xmlFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\" + obj.getProperty("xmlfilepath");
    	
    	try 
    {
    		//System.out.println(System.getProperty("user.dir")+obj.getProperty("datasheet_path"));
    		testcaseslist = dh.runnableTestCases(System.getProperty("user.dir")+obj.getProperty("datasheet_path"), obj.getProperty("sheetname"));
    		//testcaseslist = dh.runnableTestCases(System.getProperty("user.dir")+"\\src\\test\\resources\\" + obj.getProperty("datasheet_path"), obj.getProperty("sheetname"));
    	
    		
    		List<String> testcases = new ArrayList<String>();
    		
    		for(int i=0;i<testcaseslist.size();i++)
    		{
    			testcases.add(testcaseslist.get(i).toString());
    		}
    		
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            org.w3c.dom.Document document = documentBuilder.newDocument();
                                     // root element
            org.w3c.dom.Element root = document.createElement("suite");
            document.appendChild(root);
            Attr suitename = document.createAttribute("name");
            suitename.setValue("testngTest");
            root.setAttributeNode(suitename);
            org.w3c.dom.Element test = document.createElement("test");
            root.appendChild(test);
            Attr testname = document.createAttribute("name");
            testname.setValue("dynamic test execution");
            test.setAttributeNode(testname);
            org.w3c.dom.Element classes = document.createElement("classes");
            test.appendChild(classes);
            org.w3c.dom.Element class1 = document.createElement("class");
            classes.appendChild(class1);
            Attr classname = document.createAttribute("name");
            classname.setValue("seleniummavenproj.TestCases");
            class1.setAttributeNode(classname);
            org.w3c.dom.Element methods = document.createElement("methods");
            class1.appendChild(methods);
            for(int i=0;i<testcases.size();i++)
            {
            	org.w3c.dom.Element include = document.createElement("include");
            	methods.appendChild(include);
            	Attr incname= document.createAttribute("name");
            	incname.setValue(testcases.get(i));
            	include.setAttributeNode(incname);}
            // create the xml file and transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
            // If you use StreamResult result = new StreamResult(System.out);the output will be pushed to the standard output ...You can use that for debugging 
            transformer.transform(domSource, streamResult);
    		} 
    	catch (ParserConfigurationException pce) 
    	{
    		pce.printStackTrace();
    	} 
    	catch (TransformerException tfe) 
    	{
    		tfe.printStackTrace();
    	}
    	}
    @AfterTest
    public void run()
    {
    	System.out.println("in the method to run......................................");
    	           List<String> suites = Lists.newArrayList();
                    //suites.add("C:\\Users\\ka8678\\workspace\\artifactid\\runtimeTestng.xml");
                    suites.add(System.getProperty("user.dir") + obj.getProperty("xmlfilepath"));
                    TestNG tng = new TestNG();
                    tng.setTestSuites(suites);
                    tng.run();
    }

}
