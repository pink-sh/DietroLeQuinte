package it.uniroma1.lcl.dietrolequinte.loader;

/**
 * @author Enrico Anello
 *
 */
public class Loader {
	
	public static void main(String[] args) {
		if (args.length > 0) {
			LoaderUtils lu = new LoaderUtils();
			lu.parseDirectory(args[0]);
			
			for (String om : lu.getMessages()) {
				System.out.println(om);
			}
		}
	}
	

}
