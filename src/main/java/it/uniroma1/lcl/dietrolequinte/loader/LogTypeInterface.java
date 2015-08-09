package it.uniroma1.lcl.dietrolequinte.loader;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;

import it.uniroma1.lcl.dietrolequinte.Utente;

/**
 * Basic interface for any Loader's package
 * Any Loader's package MUST implement this interface!!!
 * @author Enrico Anello
 *
 */
public interface LogTypeInterface {
	/**
	 * Test method
	 * @return
	 */
	public String HelloWorld();
	/**
	 * Parse a given log file
	 * @param f
	 * @return java.util.Collection<it.uniroma1.lcl.dietrolequinte.Utente>
	 * @throws IOException
	 * @throws ParseException
	 */
	public Collection<Utente> parseFile(File f) throws IOException, ParseException;
	
	/**
	 * Returns the file length
	 * @return long
	 */
	public long getFileLength();
	/**
	 * Returns the number of lines inside the given file
	 * @return int
	 */
	public int getNumberOfLines();
	/**
	 * Returns the number of processed lines inside the file
	 * @return int
	 */
	public int getNumberOfProcessedLines();
	/**
	 * Returns the type of parser who implemented this interface
	 * @return java.lang.String
	 */
	public String getLogType();
	/**
	 * Returns the class who implemented this interface
	 * @return java.lang.String
	 */
	public String getLogClass();
}
