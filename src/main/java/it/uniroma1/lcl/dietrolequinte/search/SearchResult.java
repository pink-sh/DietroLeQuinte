package it.uniroma1.lcl.dietrolequinte.search;

import java.time.LocalDateTime;

/**
 * This object represent a single search result
 * @author Enrico Anello
 *
 */
public class SearchResult {
	private String user;
	private String type;
	private LocalDateTime time;
	private String message;
	private Object extra;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
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
	public void setExtra(Object extra) {
		this.extra = extra;
	}
	public void setExtra(String extra) {
		this.extra = (Object) extra;
	}
	public void setExtra(Integer extra) {
		this.extra = (Object) extra;
	}
	public void setExtra(Float extra) {
		this.extra = (Object) extra;
	}
}
