package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

public class TmpTest9 {

	public static void main(String[] args) throws IOException {

		File tmpScreenshotFile = new File("d:/auxiliary/tmp/blue02.png");
		BufferedImage image = ImageIO.read(tmpScreenshotFile);

		System.out.println("height: " + image.getHeight());
		System.out.println("width: " + image.getWidth());
		
		int clr = 0;
		Set<PixColor> colorSet = new HashSet<PixColor>();
		PixColor p = null;
		for (int x = 1; x < image.getWidth(); x++) {
			for (int y = 1; y < image.getHeight(); y++) {
				p = new PixColor();
				clr = image.getRGB(x, y);
				p.setRed((clr & 0x00ff0000) >> 16);
				p.setGreen((clr & 0x0000ff00) >> 8);
				p.setBlue(clr & 0x000000ff);
				colorSet.add(p);
			}
		}
		
		System.out.println(image.getHeight() * image.getWidth());
		System.out.println(colorSet.size());
		System.out.println(colorSet);

	}

}

class PixColor {
	private Integer red;
	private Integer green;
	private Integer blue;

	public Integer getRed() {
		return red;
	}

	public void setRed(Integer red) {
		this.red = red;
	}

	public Integer getGreen() {
		return green;
	}

	public void setGreen(Integer green) {
		this.green = green;
	}

	public Integer getBlue() {
		return blue;
	}

	public void setBlue(Integer blue) {
		this.blue = blue;
	}

	@Override
	public String toString() {
		return "pixColor [red=" + red + ", green=" + green + ", blue=" + blue + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blue == null) ? 0 : blue.hashCode());
		result = prime * result + ((green == null) ? 0 : green.hashCode());
		result = prime * result + ((red == null) ? 0 : red.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PixColor other = (PixColor) obj;
		if (blue == null) {
			if (other.blue != null)
				return false;
		} else if (!blue.equals(other.blue))
			return false;
		if (green == null) {
			if (other.green != null)
				return false;
		} else if (!green.equals(other.green))
			return false;
		if (red == null) {
			if (other.red != null)
				return false;
		} else if (!red.equals(other.red))
			return false;
		return true;
	}

	
}