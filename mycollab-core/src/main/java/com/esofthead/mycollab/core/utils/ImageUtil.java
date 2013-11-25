/**
 * This file is part of mycollab-core.
 *
 * mycollab-core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-core.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.core.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mortennobel.imagescaling.ResampleOp;

/**
 * Utility class to process image
 * 
 */
public class ImageUtil {
	private static Logger log = LoggerFactory.getLogger(ImageUtil.class);

	/**
	 * 
	 * @param buffImage
	 * @param percenScale
	 * @return
	 */
	public static BufferedImage scaleImage(BufferedImage buffImage,
			float percenScale) {
		float width = buffImage.getWidth() * percenScale;
		float height = buffImage.getHeight() * percenScale;
		ResampleOp resampleOp = new ResampleOp((int) width, (int) height);

		BufferedImage rescaledImage = resampleOp.filter(buffImage, null);
		return rescaledImage;
	}

	/**
	 * 
	 * @param buffImage
	 * @param scaleWidth
	 * @param scaleHeight
	 * @return
	 */
	public static BufferedImage scaleImage(BufferedImage buffImage,
			int scaleWidth, int scaleHeight) {
		int imgHeight = buffImage.getHeight();
		int imgWidth = buffImage.getWidth();

		float destHeight = scaleHeight;
		float destWidth = scaleWidth;

		if ((imgWidth >= imgHeight) && (imgWidth > scaleWidth)) {
			destHeight = imgHeight * ((float) scaleWidth / imgWidth);
		} else if ((imgWidth < imgHeight) && (imgHeight > scaleHeight)) {
			destWidth = imgWidth * ((float) scaleHeight / imgHeight);
		} else {
			return buffImage;
		}

		ResampleOp resampleOp = new ResampleOp((int) destWidth,
				(int) destHeight);

		BufferedImage rescaledImage = resampleOp.filter(buffImage, null);
		return rescaledImage;
	}

	/**
	 * 
	 * @param pngData
	 * @return
	 */
	public static byte[] convertJpgToPngFormat(byte[] pngData) {
		try {
			BufferedImage image = ImageIO
					.read(new ByteArrayInputStream(pngData));
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			ImageIO.write(image, "png", outStream);
			return outStream.toByteArray();
		} catch (Exception e) {
			log.error("Exception to convert jpg file format to png", e);
			return null;
		}
	}

	/**
	 * 
	 * @param image
	 * @return
	 */
	public static byte[] convertImageToByteArray(BufferedImage image) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			return imageInByte;
		} catch (IOException e) {
			log.error("Exception to convert Image to Byte Array: ", e);
			return null;
		}
	}
}
