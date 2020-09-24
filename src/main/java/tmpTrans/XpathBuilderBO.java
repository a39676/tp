package tmpTrans;

/**
 * 
 * help for build xpath
 * 未规范, 暂以bo存放
 * 
 */
/*
 * about xpath 
 * https://www.swtestacademy.com/xpath-selenium/
 */
public class XpathBuilderBO {

	private String xpath;
	
	public String getXpath() {
		return xpath;
	}

	public XpathBuilderBO setXpath(String xpath) {
		this.xpath = xpath;
		return this;
	}

	public XpathBuilderBO start(String tagName) {
		if(tagName == null) {
			tagName = "*";
		}
		this.xpath = "//" + tagName;
		return this;
	}
	
	public XpathBuilderBO start() {
		return start(null);
	}
	
	public XpathBuilderBO addAttribute(String attributeName, String attributeValue) {
		this.xpath = this.xpath + String.format("[@%s='%s']", attributeName, attributeValue);
		return this;
	}
	
	public XpathBuilderBO addClass(String attributeValue) {
		this.xpath = this.xpath + String.format("[@class='%s']", attributeValue);
		return this;
	}
	
	public XpathBuilderBO addId(String id) {
		this.xpath = this.xpath + String.format("[@id='%s']", id);
		return this;
	}
	
	public XpathBuilderBO addType(String type) {
		this.xpath = this.xpath + String.format("[@type='%s']", type);
		return this;
	}
	
	public XpathBuilderBO addName(String name) {
		this.xpath = this.xpath + String.format("[@name='%s']", name);
		return this;
	}
	
	public XpathBuilderBO addAttributeText(String text) {
		this.xpath = this.xpath + String.format("[text()='%s']", text);
		return this;
	}
	
	public XpathBuilderBO addContainsText(String text) {
		this.xpath = this.xpath + String.format("[contains(text(), '%s')]", text);
		return this;
	}
	
	public XpathBuilderBO addAttributesOr(String attributeName1, String attributeValue1, String attributeName2, String attributeValue2) {
		this.xpath = this.xpath + String.format("[@%s,'%s' or @%s,'%s']", attributeName1, attributeValue2, attributeName2, attributeValue2);
		return this;
	}
	
	public XpathBuilderBO addAttributesAnd(String attributeName1, String attributeValue1, String attributeName2, String attributeValue2) {
		this.xpath = this.xpath + String.format("[@%s,'%s' and @%s,'%s']", attributeName1, attributeValue2, attributeName2, attributeValue2);
		return this;
	}
	
	public XpathBuilderBO addAttributeContain(String attributeName, String attributeValue) {
		this.xpath = this.xpath + String.format("[contains(@%s,'%s')]", attributeName, attributeValue);
		return this;
	}
	
	public XpathBuilderBO addAttributeStartWith(String attributeName, String attributeValue) {
		this.xpath = this.xpath + String.format("[starts-with(@%s,'%s')]", attributeName, attributeValue);
		return this;
	}
	
	public XpathBuilderBO findChild(String childTagName) {
		return findChild(childTagName, null);
	}
	
	public XpathBuilderBO findChild() {
		return findChild(null, null);
	}
	
	public XpathBuilderBO findChild(String childTagName, Integer childIndex) {
		if(childTagName == null) {
			childTagName = "*";
		}
		if(childIndex == null) {
			this.xpath = this.xpath + String.format("/child::%s", childTagName);
		} else {
			this.xpath = this.xpath + String.format("/child::%s[%d]", childTagName, childIndex);
		}
		return this;
	}
	
	public XpathBuilderBO findParent(String parentTagName) {
		this.xpath = this.xpath + String.format("//parent::%s", parentTagName);
		return this;
	}
	
	public XpathBuilderBO findParent() {
		this.xpath = this.xpath + String.format("//..");
		return this;
	}
	
	public XpathBuilderBO findParent(int index) {
		if(index <= 0) {
			return this;
		}
		for(; index > 0; index--) {
			this.xpath = this.xpath + "//..";
		}
		return this;
	}
	
	/**	暂时理解成 tab 键点击后, 切换到的下一个元素 */
	public XpathBuilderBO findFllowing(String tagName, Integer index) {
		return tabRelated("following", tagName, index);
	}
	
	/**	暂时理解成 shift + tab 键点击后, 切换到的下一个元素 */
	public XpathBuilderBO findAncestor(String tagName, Integer index) {
		return tabRelated("ancestor", tagName, index);
	}
	
	/**	暂时理解成 shift + tab 键点击后, 能切换到的元素(所有?) */
	public XpathBuilderBO findPreceding(String tagName, Integer index) {
		return tabRelated("preceding", tagName, index);
	}
	
	private XpathBuilderBO tabRelated(String methodKeyWord, String tagName, Integer index) {
		if(tagName == null) {
			tagName = "*";
		}
		if(index == null) {
			this.xpath = this.xpath + String.format("//%s::%s", tagName);
		} else {
			this.xpath = this.xpath + String.format("//%s::%s[%d]", methodKeyWord, tagName, index);
		}
		return this;
	}

	@Override
	public String toString() {
		return "XpathBuilderBO [xpath=" + xpath + "]";
	}
	
}
