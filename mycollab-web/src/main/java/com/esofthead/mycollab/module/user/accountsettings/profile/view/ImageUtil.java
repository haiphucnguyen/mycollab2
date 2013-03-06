package com.esofthead.mycollab.module.user.accountsettings.profile.view;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

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
}
