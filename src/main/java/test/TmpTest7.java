package test;

public class TmpTest7 {
	
	public static void main(String[] args) {
		String reg = ".*((headtopic_\\d)|(anc)).gif$";
		
		String s = "images/wind/file/headtopic_2.gif";
		System.out.println(s + " " + s.matches(reg));
		
		s = "images/wind/thread/anc.gif";
		System.out.println(s + " " + s.matches(reg));
		
		s = "images/wind/thread/headtopic_a.gif";
		System.out.println(s + " " + s.matches(reg));
		
		s = "images/wind/thread/headtopic_n.gif";
		System.out.println(s + " " + s.matches(reg));
		
		s = "images/wind/thread/anb.gif";
		System.out.println(s + " " + s.matches(reg));
		
		s = "images/wind/thread/headtopic_b.gif";
		System.out.println(s + " " + s.matches(reg));
		
		s = "images/wind/thread/headtopic_|.gif";
		System.out.println(s + " " + s.matches(reg));
	}

}
