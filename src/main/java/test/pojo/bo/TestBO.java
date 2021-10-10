package test.pojo.bo;

import test.pojo.type.TestEnum;

public class TestBO {

	private TestEnum te;

	public TestEnum getTe() {
		return te;
	}

	public void setTe(TestEnum te) {
		this.te = te;
	}

	@Override
	public String toString() {
		return "TestBO [te=" + te + "]";
	}

}
