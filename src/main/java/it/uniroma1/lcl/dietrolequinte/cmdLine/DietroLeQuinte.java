package it.uniroma1.lcl.dietrolequinte.cmdLine;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.uniroma1.lcl.dietrolequinte.search.SearchResult;
import it.uniroma1.lcl.dietrolequinte.search.Searcher;
import it.uniroma1.lcl.dietrolequinte.utils.DietroLeQuinteUtils;
import it.uniroma1.lcl.dietrolequinte.utils.FileUtils;

/**
 * @author Enrico Anello
 * User interface of the DietroLeQuinte project.
 */
public class DietroLeQuinte {
	
	private static String NOTHING = "Nessuno";
	
	private static int counter = 0;

	/**
	 * main function
	 * it checks the length of the parameters and lets the program enter
	 * in interactive mode or batch mode
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 1) {
			commandLineInteractions(args[0]);		
		} else if (args.length == 2) {
			commandLineBatch(args[0], args[1]);
		}
		
	}
	
	/**
	 * It is defining the interactive mode
	 * It enters in an infinite loop and gets input commands printing results
	 * @param path
	 */
	private static void commandLineInteractions(String path) {
		Scanner in = new Scanner(System.in);
		Searcher src = Searcher.getIstanza(path);
		while (true) {
			String inputString = DietroLeQuinteUtils.normalizeInput(in.nextLine());
			String out = doCommand(inputString, src);
			
			if (out.equals("EXIT")) {
				break;
			} else {
				System.out.println(out);
				counter += 1;
			}
		}
		System.out.println("Number of queries made by the user: " + Integer.toString(counter));
		in.close();
	}
	
	/**
	 * It is defining the batch mode
	 * It parses the batch file submitted by the user; it performs all the actions and prints all the results at once
	 * @param path
	 * @param inFile
	 */
	private static void commandLineBatch(String path, String inFile) {
		try {
			List<String> commands = FileUtils.read(new File(inFile));
			Searcher src = Searcher.getIstanza(path);
			String out = "";
			for (String command : commands) {
				out += doCommand(command, src) + "\n";
			}
			System.out.println(out);
		} catch (IOException e) {
			System.err.println("I/O Error reading file: " + inFile);
		}
		
	}
	
	public static String doCommandForTest(String inputString, Searcher src) {
		return doCommand(inputString, src);
	}
	
	/**
	 * It gets an input string and split it in chunks to perform the required action.
	 * It returns the result of the action.
	 * if the action is not recognized an error message is given back.
	 * @param inputString
	 * @param src
	 * @return
	 */
	private static String doCommand(String inputString, Searcher src) {
		int nPar = DietroLeQuinteUtils.getNumberOfParameters(inputString);
		String command = DietroLeQuinteUtils.getInputCommand(inputString);
		String out = "";
		if (command != null) {
			switch (command) {
			case "echo": 
				out = DietroLeQuinteUtils.getParameters(inputString);
				break;
			case "num_login":
				if (nPar == 1) {
					out = Integer.toString(src.searchCmdNum(DietroLeQuinteUtils.getParameters(inputString), "login").size());
				} else if (nPar > 1 && nPar <= 3) {
					out = Integer.toString(src.searchCmdNum(	
														DietroLeQuinteUtils.getSingleParameter(inputString, 1), 
									   					"login",
									   					DietroLeQuinteUtils.getSingleParameter(inputString, 2),
									   					DietroLeQuinteUtils.getSingleParameter(inputString, 3)).size());
				} else {
					out += "num_login wrong number of parameters, should be";
					out += "num_login |user|";
					out += "num_login |user| |start date| |end date|";
				}
				break;
			case "num_logout":
				if (nPar == 1) {
					out += Integer.toString(src.searchCmdNum(DietroLeQuinteUtils.getParameters(inputString), "logout").size());
				} else if (nPar > 1 && nPar <= 3) {
					out += Integer.toString(src.searchCmdNum(	
														DietroLeQuinteUtils.getSingleParameter(inputString, 1), 
									   					"logout",
									   					DietroLeQuinteUtils.getSingleParameter(inputString, 2),
									   					DietroLeQuinteUtils.getSingleParameter(inputString, 3)).size());
				} else {
					out += "num_logout wrong number of parameters, should be";
					out += "num_logout |user|";
					out += "num_logout |user| |start date| |end date|";
				}
				break;
			case "num_messaggi":
				if (nPar == 1) {
					out += Integer.toString(src.searchCmdNum(DietroLeQuinteUtils.getParameters(inputString), "message").size());
				} else if (nPar > 1 && nPar <= 3) {
					out += Integer.toString(src.searchCmdNum(	
														DietroLeQuinteUtils.getSingleParameter(inputString, 1), 
									   					"message",
									   					DietroLeQuinteUtils.getSingleParameter(inputString, 2),
									   					DietroLeQuinteUtils.getSingleParameter(inputString, 3)).size());
				} else {
					out += "num_messaggi wrong number of parameters, should be";
					out += "num_messaggi |user|";
					out += "num_messaggi |user| |start date| |end date|";
				}
				break;
			case "num_azioni":
				if (nPar == 1) {
					out += Integer.toString(src.searchCmdNum(DietroLeQuinteUtils.getParameters(inputString), "action").size());
				} else if (nPar > 1 && nPar <= 3) {
					out += Integer.toString(src.searchCmdNum(	
														DietroLeQuinteUtils.getSingleParameter(inputString, 1), 
									   					"action",
									   					DietroLeQuinteUtils.getSingleParameter(inputString, 2),
									   					DietroLeQuinteUtils.getSingleParameter(inputString, 3)).size());
				} else {
					out += "num_azioni wrong number of parameters, should be";
					out += "num_azioni |user|";
					out += "num_azioni |user| |start date| |end date|";
				}
				break;
			case "num_query":
				if (nPar == 1) {
					out += Integer.toString(src.searchCmdNum(DietroLeQuinteUtils.getParameters(inputString), "interrogazioni").size());
				} else if (nPar > 1 && nPar <= 2) {
					out += Integer.toString(src.searchCmdNum(	
														DietroLeQuinteUtils.getSingleParameter(inputString, 1), 
									   					"interrogazioni",
									   					DietroLeQuinteUtils.getSingleParameter(inputString, 2)).size());
				} else if (nPar > 2 && nPar <= 4) {
					out += Integer.toString(src.searchCmdNum(	
														DietroLeQuinteUtils.getSingleParameter(inputString, 1), 
									   					"interrogazioni",
									   					DietroLeQuinteUtils.getSingleParameter(inputString, 2),
									   					DietroLeQuinteUtils.getSingleParameter(inputString, 3),
									   					DietroLeQuinteUtils.getSingleParameter(inputString, 4)).size());
				} else {
					out += "num_query wrong number of parameters, should be";
					out += "num_query |user|";
					out += "num_query |user| |position|";
					out += "num_query |user| |position| |start date| |end date|";
				}
				break;
			case "data_di_login":
				if (nPar == 2) {
					List<SearchResult> ret = (List<SearchResult>)
							src.searchCmdData(DietroLeQuinteUtils.getSingleParameter(inputString, 1), 
							"login",
							DietroLeQuinteUtils.getSingleParameter(inputString, 2));
					if (ret.size() > 0) {
						out += ret.get(0).getTime();
					} else {
						out += NOTHING;
					}
				} else if (nPar > 2 && nPar <= 4) {
					List<SearchResult> ret = (List<SearchResult>) 
							src.searchCmdData(DietroLeQuinteUtils.getSingleParameter(inputString, 1), 
							"login",
							DietroLeQuinteUtils.getSingleParameter(inputString, 2),
							DietroLeQuinteUtils.getSingleParameter(inputString, 3),
							DietroLeQuinteUtils.getSingleParameter(inputString, 4));
					if (ret.size() > 0) {
						out += ret.get(0).getTime();
					} else {
						out += NOTHING;
					}
				} else {
					out += "data_di_login wrong number of parameters, should be";
					out += "data_di_login |user| |position|";
					out += "data_di_login |user| |position| |start date| |end date|";
				}
				break;
			case "data_di_logout":
				if (nPar == 2) {
					List<SearchResult> ret = (List<SearchResult>)
							src.searchCmdData(DietroLeQuinteUtils.getSingleParameter(inputString, 1), 
							"logout",
							DietroLeQuinteUtils.getSingleParameter(inputString, 2));
					if (ret.size() > 0) {
						out += ret.get(0).getTime();
					} else {
						out += NOTHING;
					}
				} else if (nPar > 2 && nPar <= 4) {
					List<SearchResult> ret = (List<SearchResult>) 
							src.searchCmdData(DietroLeQuinteUtils.getSingleParameter(inputString, 1), 
							"logout",
							DietroLeQuinteUtils.getSingleParameter(inputString, 2),
							DietroLeQuinteUtils.getSingleParameter(inputString, 3),
							DietroLeQuinteUtils.getSingleParameter(inputString, 4));
					if (ret.size() > 0) {
						out += ret.get(0).getTime();
					} else {
						out += NOTHING;
					}
				} else {
					out += "data_di_logout wrong number of parameters, should be";
					out += "data_di_logout |user| |position|";
					out += "data_di_logout |user| |position| |start date| |end date|";
				}
				break;
			case "messaggio":
				if (nPar == 2) {
					List<SearchResult> ret = (List<SearchResult>)
							src.searchCmdData(DietroLeQuinteUtils.getSingleParameter(inputString, 1), 
							"message",
							DietroLeQuinteUtils.getSingleParameter(inputString, 2));
					if (ret.size() > 0) {
						out += ret.get(0).getExtra();
					} else {
						out += NOTHING;
					}
				} else if (nPar > 2 && nPar <= 4) {
					List<SearchResult> ret = (List<SearchResult>) 
							src.searchCmdData(DietroLeQuinteUtils.getSingleParameter(inputString, 1), 
							"message",
							DietroLeQuinteUtils.getSingleParameter(inputString, 2),
							DietroLeQuinteUtils.getSingleParameter(inputString, 3),
							DietroLeQuinteUtils.getSingleParameter(inputString, 4));
					if (ret.size() > 0) {
						out += ret.get(0).getExtra();
					} else {
						out += NOTHING;
					}
				} else {
					out += "messaggio wrong number of parameters, should be";
					out += "messaggio |user| |position|";
					out += "messaggio |user| |position| |start date| |end date|";
				}
				break;
			case "query":
				if (nPar == 2) {
					List<SearchResult> ret = (List<SearchResult>)
							src.searchCmdQuery(DietroLeQuinteUtils.getSingleParameter(inputString, 1), 
							DietroLeQuinteUtils.getSingleParameter(inputString, 2));
					if (ret.size() > 0) {
						Pattern pattern = Pattern.compile("Stringa immessa:(.*?)Tempo");
					    Matcher matcher = pattern.matcher(ret.get(0).getMessage().replace("\n", " ").trim());
					    while (matcher.find()) {
					    	out += matcher.group(1).trim();
					    }
					} else {
						out += NOTHING;
					}
				} else if (nPar == 3) {
					List<SearchResult> ret = (List<SearchResult>) 
							src.searchCmdQuery(DietroLeQuinteUtils.getSingleParameter(inputString, 1), 
							DietroLeQuinteUtils.getSingleParameter(inputString, 2),
							DietroLeQuinteUtils.getSingleParameter(inputString, 3));
					if (ret.size() > 0) {
						Pattern pattern = Pattern.compile("Stringa immessa:(.*?)Tempo");
					    Matcher matcher = pattern.matcher(ret.get(0).getMessage().replace("\n", " ").trim());
					    while (matcher.find()) {
					    	out += matcher.group(1).trim();
					    }
					} else {
						out += NOTHING;
					}
				}  else if (nPar == 5) {
					List<SearchResult> ret = (List<SearchResult>) 
							src.searchCmdQuery(DietroLeQuinteUtils.getSingleParameter(inputString, 1), 
							DietroLeQuinteUtils.getSingleParameter(inputString, 2),
							DietroLeQuinteUtils.getSingleParameter(inputString, 3),
							DietroLeQuinteUtils.getSingleParameter(inputString, 4),
							DietroLeQuinteUtils.getSingleParameter(inputString, 5));
					if (ret.size() > 0) {
						Pattern pattern = Pattern.compile("Stringa immessa:(.*?)Tempo");
					    Matcher matcher = pattern.matcher(ret.get(0).getMessage().replace("\n", " ").trim());
					    while (matcher.find()) {
					    	out += matcher.group(1).trim();
					    }
					} else {
						out += NOTHING;
					}
				} else {
					out += "query wrong number of parameters, should be";
					out += "query |user| |position|";
					out += "query |user| |position| |click|";
					out += "query |user| |position| |click| |start date| |end date|";
				}
				break;
			case "exit":
				out += "EXIT";
				break;
			case "":
				break;
			case " ":
				break;
			default: out += "Commnad unrecognized."; break;
			}
		}
		return out;
	}

}
