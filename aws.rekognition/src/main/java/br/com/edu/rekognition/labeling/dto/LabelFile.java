/*
 * COPYRIGHT...
 */
package br.com.edu.rekognition.labeling.dto;

import com.google.gson.Gson;
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
public class LabelFile {

	@SerializedName("source-ref")
	private String sourceRef;

	@SerializedName("rekognition-custom-labels-training-0")
	private CustomLabel customLabel;

	@SerializedName("rekognition-custom-labels-training-0-metadata")
	private LabelMetadata labelsTraingMetadata;

	@SerializedName("rekognition-custom-labels-object-id")
	private int objectId;

	@SerializedName("line-idx")
	private int lineIndex;

	@Override
	public String toString() {
		final Gson gson = new Gson();
		return gson.toJson(this);
	}

	public static LabelFile fromJson(final String json) {
		final Gson gson = new Gson();

		return gson.fromJson(json, LabelFile.class);
	}

}
