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
public class Query implements LogTypeInterface {
	
	private long file_length = 0;
	private int num_lines = 0;
	private int processed_lines = 0;
	
	@Override
	public String HelloWorld() {
		return "Hello from Query";
	}

	@Override
	public List<Utente> parseFile(File f) throws IOException, ParseException {
		List<Utente> users = new ArrayList<Utente>();
		List<String> lines = FileUtils.read(f);
		
		this.file_length = f.length();
		this.num_lines = lines.size();
		this.processed_lines = lines.size()-1;
		
		int lineCounter = 0;
		for (String line : lines) {
			if (lineCounter > 0) { //Avoid first heading line
				String[] split = line.split("\t");
				Utente user = new Utente();
				user.setUserType("QUERY");
				user.setClassName(this.getClass().getName());
				Interrogazione query = new Interrogazione();
				query.setType("interrogazioni");
				
				String message = "";
				
				if (split.length > 0) {
					user.setUser(split[0]);
					query.setUser(split[0]);
					message += "Utente: " + user.getUser() + "\n";
				}
				if (split.length > 1) {
					query.setMessage(split[1]);
					message += "Stringa immessa: " + query.getMessage() + "\n";
				}
				if (split.length > 2) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
					query.setTime(LocalDateTime.parse(split[2], formatter));
					message += "Tempo: " + split[2] + "\n";
				}
				if (split.length > 3) {
					query.setExtra(split[3]+"!~~!"+split[4]);
					message += "Link cliccato: " + split[4] + "\n";
					message += "Posizione link cliccato: " + split[3] + "\n";
				}
				query.setMessage(message);
				
				List<Interrogazione> q = new ArrayList<Interrogazione>();
				q.add(query);
				
				user.setQueries(q);
				users.add(user);
			}
			lineCounter += 1;
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

	@Override
	public String getLogType() {
		return "query";
	}

	@Override
	public String getLogClass() {
		return this.getClass().getName();
	}

}
