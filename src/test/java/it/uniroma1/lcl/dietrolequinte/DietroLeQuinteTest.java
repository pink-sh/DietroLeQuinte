package it.uniroma1.lcl.dietrolequinte;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import it.uniroma1.lcl.dietrolequinte.cmdLine.DietroLeQuinte;
import it.uniroma1.lcl.dietrolequinte.loader.LoaderUtils;
import it.uniroma1.lcl.dietrolequinte.search.Searcher;
import it.uniroma1.lcl.dietrolequinte.utils.FileUtils;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class DietroLeQuinteTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DietroLeQuinteTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DietroLeQuinteTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws IOException 
     * @throws URISyntaxException 
     */
    public void testApp() throws IOException, URISyntaxException
    {
    	ClassLoader classLoader = getClass().getClassLoader();
    	String directory = classLoader.getResource("samples").getPath() + File.separator + "dirLogs";
    	LoaderUtils lu = new LoaderUtils();
    	lu.parseDirectory(directory);
   
    	List<String> resFile = FileUtils.read(new File(classLoader.getResource("samples" + File.separator + "output_atteso_loader.txt").toURI()));
    	
    	boolean notFound = false;
    	for (String om : lu.getMessages()) {
    		boolean found = false;
			for (String rfs : resFile) {
				if (rfs.trim().equalsIgnoreCase(om.trim())) {
					found = true;
					break;
				}
			}
			if (!found) {
				notFound = true;
				break;
			}
		}
    	assertFalse(notFound);
    	
    	List<String> cmdLines = FileUtils.read(new File(classLoader.getResource("samples" + File.separator + "cmdsFile").toURI()));
    	List<String> resCmdLines = FileUtils.read(new File(classLoader.getResource("samples" + File.separator + "output_atteso.txt").toURI()));
    	
    	Searcher src = Searcher.getIstanza(directory);
    	int c = 0;
    	for (String cmd : cmdLines) {
    		String res = DietroLeQuinte.doCommandForTest(cmd, src);
    		assertEquals(res.trim(), resCmdLines.get(c).trim());
    		c+=1;
    	}
    	
    }
}
