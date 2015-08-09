package it.uniroma1.lcl.dietrolequinte;

import java.util.List;

/**
 * @author Enrico Anello
 * Basic object that represent a user
 */
public class Utente {
	
	private String user;
	private String className;
	private String userType;
	private List<Interrogazione> queries;
	
	/**
	 * Returns the user who performed the action
	 * @return java.lang.String
	 */
	public String getUser() {
		return user;
	}
	/**
	 * Sets the user who performed the action
	 * @param java.lang.String user
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * Returns the class name of the parser who populated this object 
	 * @return java.lang.String
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * Sets the class name of the parser who populated this object
	 * @param java.lang.String className
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * Returns the list of queries made by this user
	 * @return java.util.List<it.uniroma1.lcl.dietrolequinte.Interrogazione>
	 */
	public List<Interrogazione> getQueries() {
		return queries;
	}
	/**
	 * Sets the list of queries made by this user
	 * @param queries java.util.List<it.uniroma1.lcl.dietrolequinte.Interrogazione>
	 */
	public void setQueries(List<Interrogazione> queries) {
		this.queries = queries;
	}
	/**
	 * Returns the user type
	 * @return java.lang.String 
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * Sets the user type
	 * @param java.lang.String userType
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

}
