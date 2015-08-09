package it.uniroma1.lcl.dietrolequinte.loader;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.uniroma1.lcl.dietrolequinte.Interrogazione;
import it.uniroma1.lcl.dietrolequinte.Utente;
import it.uniroma1.lcl.dietrolequinte.utils.FileUtils;
import it.uniroma1.lcl.dietrolequinte.utils.FileWalker;

/**
 * Parses a source folder for any kind of log file
 * For each of them tries to find a matching parser class
 * @author Enrico Anello
 *
 */
public class LoaderUtils {
	
	/**
	 * Accepted extensions
	 */
	private static String[] VALID_EXTENSIONS = new String[] {"gz", "log", "txt"};
	
	/**
	 * Initialize the LoaderUtils class with different file extensions
	 * @param valid_extensions
	 */
	public LoaderUtils(String[] valid_extensions) {
		VALID_EXTENSIONS = valid_extensions.clone();
	}
	public LoaderUtils() {}
	
	private static String MAIN_PACKAGE = "it.uniroma1.lcl.dietrolequinte.loader";
	
	private int counter = 0;
	private long lineCounter = 0;
	private long byteCounter = 0;
	private Collection<Utente> results = new ArrayList<Utente>();
	private Collection<Interrogazione> queries = new ArrayList<Interrogazione>();
	private List<String> outMessages = new ArrayList<String>();
	
	/**
	 * Parses a directory
	 * @param directory
	 */
	public void parseDirectory(String directory) {
		this.parseDirectory(directory, null);
	}
	
	/**
	 * Parses a single file inside a directory
	 * This implementation actually overrides parseDirectory(String directory)
	 * because it parses an entire directory giving back the file chosen (if exists)
	 * if the file parameter is set to null the entire directory is given back
	 * @param directory
	 * @param file
	 */
	public void parseDirectory(String directory, String file) {
		
		FileWalker fw = new FileWalker(VALID_EXTENSIONS);
		fw.walk(directory);
		LinkedList<File> list;
		try {
			list = fw.getList();
			Map<String, Integer> types = new TreeMap<String, Integer>(); //using TreeMap to keep the keys sorted
			for (File f : list) {
				if (file == null || f.getName().equals(file)) {
					LogTypeInterface rInt = getInterface(f);
					if (rInt != null) {
						try {
							List<Utente> parsedFile = (List<Utente>) rInt.parseFile(f);
							counter += 1;
							lineCounter += rInt.getNumberOfLines();
							byteCounter += rInt.getFileLength();
							
							if (types.containsKey(rInt.getLogType())) {
								int cur = types.get(rInt.getLogType()) + 1;
								types.remove(rInt.getLogType());
								types.put(rInt.getLogType(), cur);
							} else {
								types.put(rInt.getLogType(), 1);
							}
							
							results = addToList(parsedFile, (List<Utente>)results);
							
							String FileX = f.getName();
							outMessages.add(FileX + " Tipo di file: " + rInt.getLogType() );
							outMessages.add(FileX + " Numero totale di byte: " + Long.toString(rInt.getFileLength()) );
							outMessages.add(FileX + " Numero totale di righe: " + Long.toString(rInt.getNumberOfLines()));
							
							Map<String, Integer> logPrint = new TreeMap<String, Integer>(); //using TreeMap to keep the keys sorted
							for (Utente u : parsedFile) {
								if (logPrint.containsKey(u.getQueries().get(0).getType())) {
									int cur = logPrint.get(u.getQueries().get(0).getType()) + 1;
									logPrint.remove(u.getQueries().get(0).getType());
									logPrint.put(u.getQueries().get(0).getType(), cur);
								} else {
									logPrint.put(u.getQueries().get(0).getType(), 1);
								}
							}
							
							for (String k : logPrint.keySet()) {
								if (k.equalsIgnoreCase("action")) { 
									outMessages.add(FileX + " Numero totale di azioni: " + Integer.toString(logPrint.get(k)));
								} else if (k.equalsIgnoreCase("message")) { 
									outMessages.add(FileX + " Numero totale di messaggi: " + Integer.toString(logPrint.get(k)));
								} else if (k.equalsIgnoreCase("rename")) {
								} else {
									outMessages.add(FileX + " Numero totale di " + k + ": " + Integer.toString(logPrint.get(k)));
								}
							}
						} catch (IOException e) {
							e.printStackTrace();
						} catch (ParseException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			for (Utente result : results) {
				queries.addAll(result.getQueries());
			}
			Collections.sort((List<Interrogazione>) queries);
			
			outMessages.add(0, "Numero di file in " + FileUtils.DirectoryName(directory) + ": " + Integer.toString(counter));
			int idx = 1;
			for (String key : types.keySet()) {
				outMessages.add(idx, "Numero di file di tipo " + key + ": " + Integer.toString(types.get(key)));
				idx += 1;
			}
			outMessages.add(idx, "Numero totale di byte: " + Long.toString(byteCounter));
			outMessages.add(idx+1, "Numero totale di righe: " + Long.toString(lineCounter));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public Collection<Utente> getUsers() {
		return results;
	}
	public Collection<Interrogazione> getQueries() {
		return queries;
	}
	public List<String> getMessages() {
		return outMessages;
	}
	public int getNumberOfProcessedFiles() {
		return counter;
	}
	public long getNumberOfProcessedLines() {
		return lineCounter;
	}
	public long getNumberOfProcessedBytes() {
		return byteCounter;
	}
		
	/**
	 * Adds a file result to the directory results
	 * @param temp
	 * @param results
	 * @return
	 */
	private static Collection<Utente> addToList(List<Utente> temp, List<Utente> results) {
		for (Utente user : temp) {
			boolean found = false;
			int i = 0;
			for (Utente result : results) {
				if (result.getUser() != null && user.getUser() != null && result.getUser().equals(user.getUser())) {
					Utente t = results.get(i);
					List<Interrogazione> ti = new ArrayList<Interrogazione>();
					ti.addAll(t.getQueries());
					ti.add(user.getQueries().get(0));
					t.setQueries(ti);
					results.remove(i);
					results.add(i, t);
					found = true;
					break;
				}
				i += 1;
			}
			if (!found) {
				results.add(user);
			}
		}
		
		return results;
	}
	
	/**
	 * It does reflection
	 * from the name of the file it tries to catch a valid class that can perform the parsing
	 * @param file
	 * @return
	 */
	private static LogTypeInterface getInterface(File file) {
		String type = file.getName().split("\\.")[0].toLowerCase();
		type = type.substring(0, 1).toUpperCase() + type.substring(1);
		
		try {
			Class<?> c = Class.forName(MAIN_PACKAGE + "." + type);
			LogTypeInterface i = (LogTypeInterface) c.newInstance();
			return i;
		} 
		catch (ClassNotFoundException e) {} 
		catch (InstantiationException e) {} 
		catch (IllegalAccessException e) {}
		
		return null;
	}
}
