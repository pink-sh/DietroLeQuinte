package it.uniroma1.lcl.dietrolequinte.loader;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import it.uniroma1.lcl.dietrolequinte.Interrogazione;
import it.uniroma1.lcl.dietrolequinte.Utente;
import it.uniroma1.lcl.dietrolequinte.utils.FileUtils;

/**
 * @author Enrico Anello
 *
 */
public class Chat implements LogTypeInterface {

	private long file_length = 0;
	private int num_lines = 0;
	private int processed_lines = 0;
	
	@Override
	public String HelloWorld() {
		return "Hello from Chat";
	}

	@Override
	public List<Utente> parseFile(File f) throws IOException, ParseException {
		List<Utente> users = new ArrayList<Utente>();
		List<String> lines = FileUtils.read(f);
		
		this.file_length = f.length();
		this.num_lines = lines.size();
		this.processed_lines = lines.size()-1;
		
		for (String line : lines) {
			users.add(this.parseLogLine(line));
		}

		return users;
	}

	@Override
	public long getFileLength() {
		return this.file_length;
	}

	@Override
	public int getNumberOfLines() {
		return this.num_lines;
	}

	@Override
	public int getNumberOfProcessedLines() {
		return this.processed_lines;
	}
	
	private Utente parseLogLine(String line) {
		Utente user = new Utente();
		Interrogazione query = new Interrogazione();
		String dateString = null;
		if(line.contains(" ")){
			dateString = line.substring(0, line.indexOf(" ")).trim(); 
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd'T'HH:mm:ss");
		query.setTime(LocalDateTime.parse(dateString, formatter));
		String user_msg = "";
		String newLine = line.substring(dateString.length()).trim().trim();
		
		if (newLine.startsWith("***")) {
			String head = newLine.substring(newLine.indexOf("***")).substring(4).trim();
			user.setUser(head.substring(0, head.indexOf(" ")).trim());
		} else if (newLine.startsWith("*")) {
			String head = newLine.substring(newLine.indexOf("*")).substring(2).trim();
			user.setUser(head.substring(0, head.indexOf(" ")).trim());
		} else if (newLine.startsWith("-!-")) {
			String head = newLine.substring(newLine.indexOf("-!-")).substring(4).trim();
			user.setUser(head.substring(0, head.indexOf(" ")).trim());
		} else if (newLine.startsWith("<")) {
			user.setUser(newLine.substring(1, newLine.indexOf(">")).trim());
			user_msg = newLine.substring(newLine.indexOf(">")+1).trim();
		}
		user.setClassName(this.getClass().getName());
		if (user.getUser() == null) {
			query.setType("action");
		} else if (line.contains("is now known")) {
			query.setType("rename");
		} else if (line.contains("has joined")) {
			query.setType("login/logout");
			query.setExtra("login");
			query.setUser(user.getUser());
		} else if (line.contains("has quit")) {
			query.setType("login/logout");
			query.setExtra("logout");
			query.setUser(user.getUser());
		} else if (line.contains("has left")) {
			query.setType("login/logout");
			query.setExtra("logout");
			query.setUser(user.getUser());
		} else {
			query.setType("message");
			query.setUser(user.getUser());
			query.setExtra(user_msg);
			query.setMessage("Utente: " + user.getUser() + "\nStringa Immessa: " + user_msg + "\nTempo: " + dateString + "\n");
		}
		
		if (!newLine.startsWith("***") && newLine.startsWith("*")) {
			query.setType("action");
		}
		
		List<Interrogazione> q = new ArrayList<Interrogazione>();
		q.add(query);
		user.setQueries(q);
		user.setUserType("CHAT");
				
		return user;
	}

	@Override
	public String getLogType() {
		return "chat";
	}

	@Override
	public String getLogClass() {
		return this.getClass().getName();
	}

}
