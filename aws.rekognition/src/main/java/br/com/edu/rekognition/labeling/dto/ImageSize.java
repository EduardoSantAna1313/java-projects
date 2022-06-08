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
public class ImageSize {

	@SerializedName("width")
	private final int width;

	@SerializedName("height")
	private final int height;

	@SerializedName("depth")
	private final int depth;

}
