package demo.finance.cryptoCoin.pojo.dto;

import java.util.List;

import demo.finance.cryptoCoin.pojo.type.CryptoCoinTagType;

public class CryptoCoinDetailDTO {

	private Double cap;
	private Integer rank;
	private Integer circulatingSupply;
	private Integer maxSupply;
	private List<CryptoCoinTagType> tags;

	public Double getCap() {
		return cap;
	}

	public void setCap(Double cap) {
		this.cap = cap;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getCirculatingSupply() {
		return circulatingSupply;
	}

	public void setCirculatingSupply(Integer circulatingSupply) {
		this.circulatingSupply = circulatingSupply;
	}

	public Integer getMaxSupply() {
		return maxSupply;
	}

	public void setMaxSupply(Integer maxSupply) {
		this.maxSupply = maxSupply;
	}

	public List<CryptoCoinTagType> getTags() {
		return tags;
	}

	public void setTags(List<CryptoCoinTagType> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "CryptoCoinDetailDTO [cap=" + cap + ", rank=" + rank + ", circulatingSupply=" + circulatingSupply
				+ ", maxSupply=" + maxSupply + ", tags=" + tags + "]";
	}

}
