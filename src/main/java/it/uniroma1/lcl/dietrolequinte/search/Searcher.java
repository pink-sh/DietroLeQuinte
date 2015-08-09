package it.uniroma1.lcl.dietrolequinte.search;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import it.uniroma1.lcl.dietrolequinte.Interrogazione;
import it.uniroma1.lcl.dietrolequinte.Utente;
import it.uniroma1.lcl.dietrolequinte.loader.LoaderUtils;
import it.uniroma1.lcl.dietrolequinte.utils.DietroLeQuinteUtils;

/**
 * Within this class all the search methods are implemented
 * @author Enrico Anello
 *
 */
public class Searcher {

	private static volatile Searcher searcher;
	private static String directory;
	
	private Searcher() {}
	/**
	 * Instantiate the class with a directory where the logs are stored
	 * @param dir
	 */
	private Searcher(String dir) {
		directory = dir;
	}
	
	/**
	 * Returns a searcher instance
	 * @param directory
	 * @return
	 */
	public static Searcher getIstanza(String directory) {
		if (searcher == null) {
			searcher = new Searcher(directory);
		}
		return searcher;
	}
	
	/**
	 * Returns all the users
	 * @return
	 */
	public Collection<Utente> getUsers() {
		LoaderUtils lu = new LoaderUtils();
		lu.parseDirectory(directory);
		return lu.getUsers();
	}
	
	/**
	 * Returns all the users within a single file
	 * @param file
	 * @return
	 */
	public Collection<Utente> getUsers(String file) {
		LoaderUtils lu = new LoaderUtils();
		lu.parseDirectory(directory, file);
		return lu.getUsers();
	}
	
	/**
	 * Search for a user
	 * @param u
	 * @return
	 */
	public Collection<SearchResult> search(String u) {
		Utente ut = new Utente();
		ut.setUser(u);
		return this.search(ut);
	}
	
	/**
	 * Search for a user who typed a specific query/message
	 * @param q
	 * @param u
	 * @return
	 */
	public Collection<SearchResult> search(String q, String u) {
		Utente ut = new Utente();
		ut.setUser(u);
		return this.search(q, ut);
	}
	
	/**
	 * Search for a user who typed a specific query/message within a single file
	 * @param q
	 * @param u
	 * @param file
	 * @return
	 */
	public Collection<SearchResult> search(String q, String u, String file) {
		Utente ut = new Utente();
		ut.setUser(u);
		return this.search(q, ut, file);
	}
	
	/**
	 * Search for a user who typed a specific query/message within a single file in a certain time frame
	 * @param q
	 * @param u
	 * @param file
	 * @param start
	 * @param end
	 * @return
	 */
	public Collection<SearchResult> search(String q, String u, String file, LocalDateTime start, LocalDateTime end) {
		Utente ut = new Utente();
		ut.setUser(u);
		return this.search(q, ut, file, start, end);
	}
	
	/**
	 * Search for a user who typed a specific query/message in a certain time frame
	 * @param q
	 * @param u
	 * @param start
	 * @param end
	 * @return
	 */
	public Collection<SearchResult> search(String q, String u, LocalDateTime start, LocalDateTime end) {
		Utente ut = new Utente();
		ut.setUser(u);
		return this.search(q, ut, start, end);
	}
	
	/**
	 * Returns a specific action of a specific user
	 * @param u
	 * @param action
	 * @return
	 */
	public Collection<SearchResult> searchCmdNum(String u, String action) {
		Utente ut = new Utente();
		ut.setUser(u);
		return this.searchCmdNum(ut, action);
	}
	
	/**
	 * Returns all the actions of a specific user in a specific time frame
	 * @param u
	 * @param action
	 * @param start
	 * @param end
	 * @return
	 */
	public Collection<SearchResult> searchCmdNum(String u, String action, LocalDateTime start, LocalDateTime end) {
		Utente ut = new Utente();
		ut.setUser(u);
		return this.searchCmdNum(ut, action, start, end);
	}
	
	/**
	 * Returns a specific action of a specific user in a specific time frame
	 * @param u
	 * @param action
	 * @param start
	 * @param end
	 * @return
	 */
	public Collection<SearchResult> searchCmdNum(String u, String action, String start, String end) {
		Utente ut = new Utente();
		ut.setUser(u);
		return this.searchCmdNum(ut, action, DietroLeQuinteUtils.formatTimeString(start), DietroLeQuinteUtils.formatTimeString(end));
	}
	
	/**
	 * Returns a specific action of a specific user in a certain position
	 * @param u
	 * @param action
	 * @param position
	 * @return
	 */
	public Collection<SearchResult> searchCmdNum(String u, String action, String position) {
		Utente ut = new Utente();
		ut.setUser(u);
		return this.searchCmdNum(ut, action, position);
	}
	
	/**
	 * Returns a specific action of a specific user in a specific time frame and in a certain position
	 * @param u
	 * @param action
	 * @param position
	 * @param start
	 * @param end
	 * @return
	 */
	public Collection<SearchResult> searchCmdNum(String u, String action, String position, String start, String end) {
		Utente ut = new Utente();
		ut.setUser(u);
		return this.searchCmdNum(ut, action, position, DietroLeQuinteUtils.formatTimeString(start), DietroLeQuinteUtils.formatTimeString(end));
	}
	
	/**
	 * Returns a specific action of a specific user with a specific position
	 * @param u
	 * @param action
	 * @param position
	 * @return
	 */
	public Collection<SearchResult> searchCmdData(String u, String action, String position) {
		Utente ut = new Utente();
		ut.setUser(u);
		return this.searchCmdData(ut, action, position);
	}
	
	/**
	 * Returns a specific action of a specific user in a specific time frame and in a certain position
	 * @param u
	 * @param action
	 * @param position
	 * @param start
	 * @param end
	 * @return
	 */
	public Collection<SearchResult> searchCmdData(String u, String action, String position, String start, String end) {
		Utente ut = new Utente();
		ut.setUser(u);
		return this.searchCmdData(ut, action, position, DietroLeQuinteUtils.formatTimeString(start), DietroLeQuinteUtils.formatTimeString(end));
	}
	
	/**
	 * Returns all the actions of a specific user in a certain position
	 * @param u
	 * @param position
	 * @return
	 */
	public Collection<SearchResult> searchCmdQuery(String u, String position) {
		Utente ut = new Utente();
		ut.setUser(u);
		return this.searchCmdQuery(ut, position);
	}
	
	/**
	 * Returns all the actions of a specific user in a certain position in a certain click
	 * @param u
	 * @param position
	 * @param click
	 * @return
	 */
	public Collection<SearchResult> searchCmdQuery(String u, String position, String click) {
		Utente ut = new Utente();
		ut.setUser(u);
		return this.searchCmdQuery(ut, position, click);
	}
	
	/**
	 * Returns all the actions of a specific user in a certain position in a certain click in a specific time frame
	 * @param u
	 * @param position
	 * @param click
	 * @param start
	 * @param end
	 * @return
	 */
	public Collection<SearchResult> searchCmdQuery(String u, String position, String click, String start, String end) {
		Utente ut = new Utente();
		ut.setUser(u);
		return this.searchCmdQuery(ut, position, click, DietroLeQuinteUtils.formatTimeString(start), DietroLeQuinteUtils.formatTimeString(end));
	}
	
	/**
	 * Search for a user
	 * @param u
	 * @return
	 */
	public Collection<SearchResult> search(Utente u) {
		Collection<SearchResult> results = new ArrayList<SearchResult>();
		LoaderUtils lu = new LoaderUtils();
		lu.parseDirectory(directory);
		List<Interrogazione> res = (List<Interrogazione>) lu.getQueries();
		for (Interrogazione qr : res) {
			if (qr.getUser() != null && qr.getUser().equalsIgnoreCase(u.getUser())) {
				SearchResult sr = new SearchResult();
				sr.setMessage(qr.getMessage());
				sr.setTime(qr.getTime());
				sr.setType(qr.getType());
				sr.setUser(qr.getUser());
				sr.setExtra(qr.getExtra());
				results.add(sr);
			}
		}
		return results;
	}
	
	/**
	 * Search for a user who typed a specific query/message
	 * @param q
	 * @param u
	 * @return
	 */
	public Collection<SearchResult> search(String q, Utente u) {
		Collection<SearchResult> results = new ArrayList<SearchResult>();
		LoaderUtils lu = new LoaderUtils();
		lu.parseDirectory(directory);
		List<Interrogazione> res = (List<Interrogazione>) lu.getQueries();
		for (Interrogazione qr : res) {
			if (qr.getUser() != null && qr.getUser().equalsIgnoreCase(u.getUser())) {
				if (qr.getMessage() != null && qr.getMessage().toLowerCase().contains(q.toLowerCase())) {
					SearchResult sr = new SearchResult();
					sr.setMessage(qr.getMessage());
					sr.setTime(qr.getTime());
					sr.setType(qr.getType());
					sr.setUser(qr.getUser());
					sr.setExtra(qr.getExtra());
					results.add(sr);
				}
			}
		}
		return results;
	}
	
	/**
	 * Search for a user who typed a specific query/message within a single file
	 * @param q
	 * @param u
	 * @param file
	 * @return
	 */
	public Collection<SearchResult> search(String q, Utente u, String file) {
		Collection<SearchResult> results = new ArrayList<SearchResult>();
		LoaderUtils lu = new LoaderUtils();
		lu.parseDirectory(directory, file);
		List<Interrogazione> res = (List<Interrogazione>) lu.getQueries();
		for (Interrogazione qr : res) {
			if (qr.getUser() != null && qr.getUser().equalsIgnoreCase(u.getUser())) {
				if (qr.getMessage() != null && qr.getMessage().toLowerCase().contains(q.toLowerCase())) {
					SearchResult sr = new SearchResult();
					sr.setMessage(qr.getMessage());
					sr.setTime(qr.getTime());
					sr.setType(qr.getType());
					sr.setUser(qr.getUser());
					sr.setExtra(qr.getExtra());
					results.add(sr);
				}
			}
		}
		return results;
	}
	
	/**
	 * Search for a user who typed a specific query/message within a single file in a certain time frame
	 * @param q
	 * @param u
	 * @param file
	 * @param start
	 * @param end
	 * @return
	 */
	public Collection<SearchResult> search(String q, Utente u, String file, LocalDateTime start, LocalDateTime end) {
		Collection<SearchResult> results = new ArrayList<SearchResult>();
		Collection<SearchResult> full = this.search(q, u, file);
		Iterator<SearchResult> sri = full.iterator();
		while (sri.hasNext()) {
			SearchResult t = sri.next();
			if (t.getTime().isAfter(start) && t.getTime().isBefore(end)) {
				results.add(t);
			}
		}
		return results;
	}
	
	/**
	 * Search for a user who typed a specific query/message in a certain time frame
	 * @param q
	 * @param u
	 * @param start
	 * @param end
	 * @return
	 */
	public Collection<SearchResult> search(String q, Utente u, LocalDateTime start, LocalDateTime end) {
		Collection<SearchResult> results = new ArrayList<SearchResult>();
		Collection<SearchResult> full = this.search(q, u);
		Iterator<SearchResult> sri = full.iterator();
		while (sri.hasNext()) {
			SearchResult t = sri.next();
			if (t.getTime().isAfter(start) && t.getTime().isBefore(end)) {
				results.add(t);
			}
		}
		return results;
	}
	
	/**
	 * Returns a specific action of a specific user
	 * @param u
	 * @param action
	 * @return
	 */
	public Collection<SearchResult> searchCmdNum(Utente u, String action) {
		Collection<SearchResult> results = new ArrayList<SearchResult>();
		Collection<SearchResult> full = this.search(u);
		Iterator<SearchResult> sri = full.iterator();
		while (sri.hasNext()) {
			SearchResult t = sri.next();
			if (t.getType() != null) {
				if (t.getType().equalsIgnoreCase(action)) {
					results.add(t);
				} else if (t.getExtra() != null) {
					if (((String) t.getExtra()).equalsIgnoreCase(action)) {
						results.add(t);
					}
				}
			}
		}
		return results;
	}
	
	/**
	 * Returns all the actions of a specific user in a specific time frame
	 * @param u
	 * @param action
	 * @param start
	 * @param end
	 * @return
	 */
	public Collection<SearchResult> searchCmdNum(Utente u, String action, LocalDateTime start, LocalDateTime end) {
		Collection<SearchResult> results = new ArrayList<SearchResult>();
		Collection<SearchResult> full = this.search(u);
		Iterator<SearchResult> sri = full.iterator();
		while (sri.hasNext()) {
			SearchResult t = sri.next();
			if (t.getType() != null) {
				if (t.getType().equalsIgnoreCase(action)) {
					if (t.getTime().isAfter(start) && t.getTime().isBefore(end)) {
						results.add(t);
					}
				} else if (t.getExtra() != null) {
					if (((String) t.getExtra()).equalsIgnoreCase(action)) {
						if (t.getTime().isAfter(start) && t.getTime().isBefore(end)) {
							results.add(t);
						}
					}
				}
			}
		}
		return results;
	}
	
	/**
	 * Returns a specific action of a specific user in a certain position
	 * @param u
	 * @param action
	 * @param position
	 * @return
	 */
	public Collection<SearchResult> searchCmdNum(Utente u, String action, String position) {
		Collection<SearchResult> results = new ArrayList<SearchResult>();
		Collection<SearchResult> full = this.search(u);
		Iterator<SearchResult> sri = full.iterator();
		while (sri.hasNext()) {
			SearchResult t = sri.next();
			if (t.getType().equalsIgnoreCase(action)) {
				if (t.getExtra() != null) {
					String split[] = ((String) t.getExtra()).split("!~~!");
					if (split[0].equalsIgnoreCase(position)) {
						results.add(t);
					}
				}
			}
		}
		return results;
	}
	
	/**
	 * Returns a specific action of a specific user in a specific time frame and in a certain position
	 * @param u
	 * @param action
	 * @param position
	 * @param start
	 * @param end
	 * @return
	 */
	public Collection<SearchResult> searchCmdNum(Utente u, String action, String position, LocalDateTime start, LocalDateTime end) {
		Collection<SearchResult> results = new ArrayList<SearchResult>();
		Collection<SearchResult> full = this.search(u);
		Iterator<SearchResult> sri = full.iterator();
		while (sri.hasNext()) {
			SearchResult t = sri.next();
			if (t.getType().equalsIgnoreCase(action)) {
				if (t.getTime().isAfter(start) && t.getTime().isBefore(end)) {
					if (t.getExtra() != null) {
						String split[] = ((String) t.getExtra()).split("!~~!");
						if (split[0].equalsIgnoreCase(position)) {
							results.add(t);
						}
					}
				}
			}
		}
		return results;
	}

	/**
	 * Returns a specific action of a specific user with a specific position
	 * @param u
	 * @param action
	 * @param position
	 * @return
	 */
	public Collection<SearchResult> searchCmdData(Utente u, String action, String position) {
		Collection<SearchResult> results = new ArrayList<SearchResult>();
		Collection<SearchResult> full = this.search(u);
		Iterator<SearchResult> sri = full.iterator();
		int counter = 0;
		int pos = Integer.parseInt(position);
		while (sri.hasNext()) {
			SearchResult t = sri.next();
			if (t.getType() != null) {
				if (t.getType().equalsIgnoreCase(action)) {
					counter += 1;
					if (counter == pos) {
						results.add(t);
					}
				} else if (t.getExtra() != null) {
					if (((String) t.getExtra()).equalsIgnoreCase(action)) {
						counter += 1;
						if (counter == pos) {
							results.add(t);
						}
					}
				}
			}
		}
		return results;
	}
	
	/**
	 * Returns a specific action of a specific user in a specific time frame and in a certain position
	 * @param u
	 * @param action
	 * @param position
	 * @param start
	 * @param end
	 * @return
	 */
	public Collection<SearchResult> searchCmdData(Utente u, String action, String position, LocalDateTime start, LocalDateTime end) {
		Collection<SearchResult> results = new ArrayList<SearchResult>();
		Collection<SearchResult> full = this.search(u);
		Iterator<SearchResult> sri = full.iterator();
		int counter = 0;
		int pos = Integer.parseInt(position);
		while (sri.hasNext()) {
			SearchResult t = sri.next();
			if (t.getType() != null) {
				if (t.getType().equalsIgnoreCase(action)) {
					counter += 1;
					if (counter == pos && t.getTime().isAfter(start) && t.getTime().isBefore(end)) {
						results.add(t);
					}
				} else if (t.getExtra() != null) {
					if (((String) t.getExtra()).equalsIgnoreCase(action)) {
						counter += 1;
						if (counter == pos && t.getTime().isAfter(start) && t.getTime().isBefore(end)) {
							results.add(t);
						}
					}
				}
			}
		}
		return results;
	}
	
	/**
	 * Returns all the actions of a specific user in a certain position
	 * @param u
	 * @param position
	 * @return
	 */
	public Collection<SearchResult> searchCmdQuery(Utente u, String position) {
		Collection<SearchResult> results = new ArrayList<SearchResult>();
		Collection<SearchResult> full = this.search(u);
		Iterator<SearchResult> sri = full.iterator();
		int counter = 0;
		int pos = Integer.parseInt(position);
		while (sri.hasNext()) {
			SearchResult t = sri.next();
			if (t.getType() != null) {
				if (t.getType().equalsIgnoreCase("interrogazioni")) {
					counter += 1;
					if (counter == pos) {
						results.add(t);
					}
				}
			}
		}
		return results;
	}
	
	/**
	 * Returns all the actions of a specific user in a certain position in a certain click
	 * @param u
	 * @param position
	 * @param click
	 * @return
	 */
	public Collection<SearchResult> searchCmdQuery(Utente u, String position, String click) {
		Collection<SearchResult> results = new ArrayList<SearchResult>();
		Collection<SearchResult> full = this.search(u);
		Iterator<SearchResult> sri = full.iterator();
		int counter = 0;
		int pos = Integer.parseInt(position);
		while (sri.hasNext()) {
			SearchResult t = sri.next();
			if (t.getType() != null) {
				if (t.getType().equalsIgnoreCase("interrogazioni")) {
					if (t.getExtra() != null) {
						String split[] = ((String) t.getExtra()).split("!~~!");
						if (split[0].equalsIgnoreCase(click)) {
							counter += 1;
							if (counter == pos) {
								results.add(t);
							}
						}
					}
				}
			}
		}
		return results;
	}

	/**
	 * Returns all the actions of a specific user in a certain position in a certain click in a specific time frame
	 * @param u
	 * @param position
	 * @param click
	 * @param start
	 * @param end
	 * @return
	 */
	public Collection<SearchResult> searchCmdQuery(Utente u, String position, String click, LocalDateTime start, LocalDateTime end) {
		Collection<SearchResult> results = new ArrayList<SearchResult>();
		Collection<SearchResult> full = this.search(u);
		Iterator<SearchResult> sri = full.iterator();
		int counter = 0;
		int pos = Integer.parseInt(position);
		while (sri.hasNext()) {
			SearchResult t = sri.next();
			if (t.getType() != null) {
				if (t.getType().equalsIgnoreCase("interrogazioni")) {
					if (t.getExtra() != null) {
						String split[] = ((String) t.getExtra()).split("!~~!");
						if (split[0].equalsIgnoreCase(click)) {
							counter += 1;
							if (counter == pos) {
								if (t.getTime().isAfter(start) && t.getTime().isBefore(end)) {
									results.add(t);
								}
							}
						}
					}
				}
			}
		}
		return results;
	}
}
