package it.uniroma1.lcl.dietrolequinte.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * File Utils
 * @author Enrico Anello
 *
 */
public class FileUtils {
	/**
	 * Returns the extension of a File file
	 * @param file
	 * @return
	 */
	public static String getFileExtension(File file) {
		return FileUtils.getFileExtension(file.getAbsolutePath());
	}
	/**
	 * Returns an extension of a String file
	 * @param file
	 * @return
	 */
	public static String getFileExtension(String file) {
		try {
			String extension = file.substring( file.lastIndexOf('.'));
			return extension;
		} catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 * Reads a file and returns a List<String> representing all the file's lines
	 * @param f
	 * @return
	 * @throws IOException
	 */
	public static List<String> read(File f) throws IOException {
		List<String> lines = new ArrayList<String>();
		
		BufferedReader br = null;
		String extension = FileUtils.getFileExtension(f);
		if (extension != null && extension.equalsIgnoreCase(".gz")) {
			br = readGzipFile(f);
		} else {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(f.getAbsolutePath()), "Cp1252"));
		}
		String line;
		while ((line = br.readLine()) != null) {
			lines.add(line);
		}
		br.close();
		
		return lines;
	}
	
	/**
	 * Reads a gzipped file
	 * @param f
	 * @return
	 * @throws IOException
	 */
	private static BufferedReader readGzipFile(File f) throws IOException {
		FileInputStream fin = new FileInputStream(f.getAbsolutePath());
		GZIPInputStream gzis = new GZIPInputStream(fin);
		InputStreamReader isr = new InputStreamReader(gzis);
		return new BufferedReader(isr);
	}
	
	/**
	 * Returns a directory name
	 * @param directory
	 * @return
	 */
	public static String DirectoryName (String directory) {
		return new File(directory).getName();
	}
}
