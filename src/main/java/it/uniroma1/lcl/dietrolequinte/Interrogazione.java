package it.uniroma1.lcl.dietrolequinte;

import java.time.LocalDateTime;

/**
 * @author Enrico Anello
 * Basic object that represent a query
 */
public class Interrogazione implements Comparable<Interrogazione>{
	private String user;
	private String type;
	private LocalDateTime time;
	private String message;
	
	private Object extra;
	/**
	 * Returns the user who performed the action/query
	 * @return java.lang.String
	 */
	public String getUser() {
		return user;
	}
	/**
	 * Sets the user who performed the action/query
	 * @param java.lang.String user
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * Returns the type of action
	 * @return java.lang.String
	 */
	public String getType() {
		return type;
	}
	/**
	 * Sets the type of action
	 * @param java.lang.String type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * Returns the time of action as java.time.LocalDateTime
	 * @return java.time.LocalDateTime
	 */
	public LocalDateTime getTime() {
		return time;
	}
	/**
	 * Sets the time of action
	 * @param java.time.LocalDateTime time
	 */
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	/**
	 * Returns the message submitted
	 * @return java.lang.String message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * Sets the message submitted
	 * @param java.lang.String message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * Extra is a field that can be used by the developer to 
	 * store different kind of information. At this stage it can
	 * store just Integer, String or Float
	 * @return java.lang.Object extra
	 */
	public Object getExtra() {
		if (extra instanceof Integer) {
			return (Integer) extra;
		}
		if (extra instanceof String) {
			return (String) extra;
		}
		if (extra instanceof Float) {
			return (Float) extra;
		}
		return extra;
	}
	/**
	 * Sets extra as java.lang.Object
	 * @param java.lang.Object extra
	 */
	public void setExtra(Object extra) {
		this.extra = extra;
	}
	/**
	 * Sets extra as java.lang.String
	 * @param java.lang.String extra
	 */
	public void setExtra(String extra) {
		this.extra = (Object) extra;
	}
	/**
	 * Sets extra as java.lang.Integer
	 * @param java.lang.Integer extra
	 */
	public void setExtra(Integer extra) {
		this.extra = (Object) extra;
	}
	/**
	 * Sets extra as java.lang.Float
	 * @param java.lang.Float extra
	 */
	public void setExtra(Float extra) {
		this.extra = (Object) extra;
	}
	
	@Override
	public int compareTo(Interrogazione o) {
		return getTime().compareTo(o.getTime());
	}
}
