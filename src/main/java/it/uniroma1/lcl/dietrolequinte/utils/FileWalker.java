package it.uniroma1.lcl.dietrolequinte.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Walks through a directory, deeply, to find all the files
 * @author Enrico Anello
 *
 */
public class FileWalker {
	
	private List<File> visited;
	private List<String> extensionFilters;
	
	/**
	 * Initialize FileWalker without filters
	 */
	public FileWalker() {
		visited = new LinkedList<File>();
		extensionFilters = new ArrayList<String>();
	}
	/**
	 * Initialize FileWalker with a file extension's filter
	 * @param extensions
	 */
	public FileWalker(String[] extensions) {
		visited = new LinkedList<File>();
		extensionFilters = new ArrayList<String>();
		for (String extension : extensions) {
			if (!extension.startsWith(".")) {
				extensionFilters.add("." + extension.toLowerCase());
			} else {
				extensionFilters.add(extension.toLowerCase());
			}
		}
	}
	
	/**
	 * Walk through the directory applying filters (walk as a graph)
	 * @param path
	 */
	public void walk( String path ) {
		
        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
            }
            else {
            	if (extensionFilters.size() < 1) {
            		visited.add(f);
            	} else {
            		String extension = FileUtils.getFileExtension(f);
            		if (extension != null) {
	            		for (String filter : extensionFilters) {
	            			if (filter.equals(extension.toLowerCase())) {
	            				visited.add(f);
	            				break;
	            			}
	            		}
            		}
            	}
            }
        }
    }
	
	/**
	 * Returns the list of visited files
	 * @return
	 * @throws IOException 
	 */
	public LinkedList<File> getList() throws IOException {
		List<String> filenames = new ArrayList<String>();
		for (File f : visited) {
			filenames.add(f.getCanonicalPath());
		}
		Collections.sort(filenames);
		LinkedList<File> retFile = new LinkedList<File>();
		for (String f : filenames) {
			retFile.add(new File(f));
		}
		return retFile;
	}
}
