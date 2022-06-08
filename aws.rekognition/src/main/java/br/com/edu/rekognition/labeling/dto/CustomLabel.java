/*
 * COPYRIGHT...
 */
package br.com.edu.rekognition.labeling.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Class to .
 *
 * @author Eduardo
 */
@Getter
@Setter
public class CustomLabel {

	@SerializedName("annotations")
	private List<LabelAnnotation> annotations;

	@SerializedName("image_size")
	private List<ImageSize> imageSize;

	public void addAnnotion(final LabelAnnotation annotation) {

		if (this.annotations == null) {
			this.annotations = new ArrayList<>();
		}

		this.annotations.add(annotation);

	}

	public void addImageSize(final ImageSize imageSize) {

		if (this.imageSize == null) {
			this.imageSize = new ArrayList<>();
		}

		this.imageSize.add(imageSize);

	}

}
