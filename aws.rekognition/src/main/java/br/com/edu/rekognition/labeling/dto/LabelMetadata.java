/*
 * COPYRIGHT...
 */
package br.com.edu.rekognition.labeling.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
public class LabelMetadata {

	@SerializedName("job-name")
	private String jobName;

	@SerializedName("class-map")
	private Map<String, String> classMap;

	@SerializedName("human-annotated")
	private String humanAnnotated;

	@SerializedName("objects")
	private List<LabelObject> objects;

	@SerializedName("creation-date")
	private String creationDate;

	@SerializedName("type")
	private String type;

	public void addObject(final LabelObject o) {

		if (objects == null) {
			objects = new ArrayList<>();
		}

		objects.add(o);
	}

}
