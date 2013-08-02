package com.esofthead.mycollab.module.user.accountsettings.profile.view;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mortennobel.imagescaling.ResampleOp;

public class ImageUtil {
	private static Logger log = LoggerFactory.getLogger(ImageUtil.class);

	public static BufferedImage scaleImage(BufferedImage buffImage,
			float percenScale) {
		float width = buffImage.getWidth() * percenScale;
		float height = buffImage.getHeight() * percenScale;
		ResampleOp resampleOp = new ResampleOp((int) width, (int) height);

		BufferedImage rescaledImage = resampleOp.filter(buffImage, null);
		return rescaledImage;
	}

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
