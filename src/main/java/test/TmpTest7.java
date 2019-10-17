package test;

public class TmpTest7 {
	
	public static void main(String[] args) {
		String longMovieTitle = "最长一枪";
		String cnTitle = longMovieTitle.substring(0, longMovieTitle.indexOf(" "));
		System.out.println(cnTitle);
		
		System.out.println(longMovieTitle.indexOf(" "));
		System.out.println(longMovieTitle.substring(0, longMovieTitle.indexOf(" ")));
		System.out.println(longMovieTitle.substring(longMovieTitle.indexOf(" ") + 1, longMovieTitle.length()));
	}

}
