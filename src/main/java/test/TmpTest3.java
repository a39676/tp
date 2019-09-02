package test;

import java.io.FileNotFoundException;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TmpTest3 {

	static int total = 10;
	public void call() {
		System.out.println(total);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Supplier<String> i = () -> "Car";
		Consumer<String> c = x -> System.out.print(x.toLowerCase()); 
		Consumer<String> d = x -> System.out.print(x.toUpperCase());
		c.andThen(d).accept(i.get());
	}
}

interface in {
	void f();
}

abstract class TestC<S> implements in {
	@Override
	public abstract void f();
	
}
