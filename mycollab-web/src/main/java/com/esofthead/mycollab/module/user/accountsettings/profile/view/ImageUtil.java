package com.esofthead.mycollab.module.user.accountsettings.profile.view;

import java.awt.image.BufferedImage;

import com.mortennobel.imagescaling.ResampleOp;

public class ImageUtil {
	public static BufferedImage scaleImage(BufferedImage buffImage,
			float percenScale) {
		float width = buffImage.getWidth() * percenScale;
		float height = buffImage.getHeight() * percenScale;
		ResampleOp resampleOp = new ResampleOp((int) width, (int) height);

		BufferedImage rescaledImage = resampleOp.filter(buffImage, null);
		return rescaledImage;
	}
}
