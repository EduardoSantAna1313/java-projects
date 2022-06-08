/*
 * COPYRIGHT...
 */
package br.com.edu.rekognition.labeling.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Class to .
 *
 * @author Eduardo
 */
@Getter
@Setter
@ToString
@Builder
public class LabelAnnotation {

	@SerializedName("left")
	private final int left;

	@SerializedName("top")
	private final int top;

	@SerializedName("width")
	private final int width;

	@SerializedName("height")
	private final int height;

	@SerializedName("class_id")
	private final int classid;

}
