package it.uniroma1.lcl.dietrolequinte.loader;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * This object represent a log file
 * @author Enrico Anello
 *
 */
public class LogFile {
	private String type;
	private long bytes;
	private int numLines;
	private int processedLines;
	private LinkedList<HashMap<String, Object>> entries;
	private HashMap<String, Object> extras;
	
	public long getBytes() {
		return bytes;
	}
	public void setBytes(long l) {
		this.bytes = l;
	}
	public int getNumLines() {
		return numLines;
	}
	public void setNumLines(int numLines) {
		this.numLines = numLines;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getProcessedLines() {
		return processedLines;
	}
	public void setProcessedLines(int processedLines) {
		this.processedLines = processedLines;
	}
	public LinkedList<HashMap<String, Object>> getEntries() {
		return entries;
	}
	public void setEntries(LinkedList<HashMap<String, Object>> entries) {
		this.entries = entries;
	}
	public HashMap<String, Object> getExtras() {
		return extras;
	}
	public void setExtras(HashMap<String, Object> extras) {
		this.extras = extras;
	}

}
