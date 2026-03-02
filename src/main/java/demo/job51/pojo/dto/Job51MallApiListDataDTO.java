package demo.job51.pojo.dto;

import java.util.List;

public class Job51MallApiListDataDTO {

	private List<Job51MallApiListDataElementDTO> list;

	public List<Job51MallApiListDataElementDTO> getList() {
		return list;
	}

	public void setList(List<Job51MallApiListDataElementDTO> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Job51MallApiListDataDTO [list=" + list + "]";
	}

}
