package test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Tmp33 {

	public static void main(String[] args) throws IOException, URISyntaxException {
		List<D> l = new ArrayList<>();
		D d = new D();
		d.s = "1";
		l.add(d);
		d.s = "2";
		l.add(d);

		System.out.println(l);
	}
}

class D {

	public String s;

	@Override
	public String toString() {
		return "D [s=" + s + "]";
	}

}
