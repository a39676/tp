package tmpTrans;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TakeScreenshotSaveDTO {

	private WebDriver driver;
	private WebElement ele;
	private Integer startX;
	private Integer startY;
	private Integer endX;
	private Integer endY;

	public WebDriver getDriver() {
		return driver;
	}

	public TakeScreenshotSaveDTO setDriver(WebDriver driver) {
		this.driver = driver;
		return this;
	}

	public WebElement getEle() {
		return ele;
	}

	public TakeScreenshotSaveDTO setEle(WebElement ele) {
		this.ele = ele;
		return this;
	}

	public Integer getStartX() {
		return startX;
	}

	public TakeScreenshotSaveDTO setStartX(Integer startX) {
		this.startX = startX;
		return this;
	}

	public Integer getStartY() {
		return startY;
	}

	public TakeScreenshotSaveDTO setStartY(Integer startY) {
		this.startY = startY;
		return this;
	}

	public Integer getEndX() {
		return endX;
	}

	public TakeScreenshotSaveDTO setEndX(Integer endX) {
		this.endX = endX;
		return this;
	}

	public Integer getEndY() {
		return endY;
	}

	public TakeScreenshotSaveDTO setEndY(Integer endY) {
		this.endY = endY;
		return this;
	}

	@Override
	public String toString() {
		return "TakeScreenshotSaveDTO [driver=" + driver + ", ele=" + ele + ", startX=" + startX + ", startY=" + startY
				+ ", endX=" + endX + ", endY=" + endY + "]";
	}

}
