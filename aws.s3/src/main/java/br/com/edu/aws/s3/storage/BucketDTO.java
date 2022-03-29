package br.com.edu.aws.s3.storage;

import java.util.Date;

import com.amazonaws.services.s3.model.Bucket;

public class BucketDTO {

	private String name;

	private Date created;

	private String owner;

	private String ownerId;

	public BucketDTO() {
		super();
	}

	public BucketDTO(final Bucket bucket) {
		super();
		this.name = bucket.getName();
		this.created = bucket.getCreationDate();

		if (bucket.getOwner() != null) {
			this.owner = bucket.getOwner().getDisplayName();
			this.ownerId = bucket.getOwner().getId();
		}

	}

	public String getName() {
		return name;
	}

	public Date getCreated() {
		return created;
	}

	public String getOwner() {
		return owner;
	}

	public String getOwnerId() {
		return ownerId;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("BucketDTO [\n\tname: ");
		builder.append(name);
		builder.append(",\n\tcreated: ");
		builder.append(created);
		builder.append(",\n\towner: ");
		builder.append(owner);
		builder.append(",\n\townerId: ");
		builder.append(ownerId);
		builder.append("\n]");
		return builder.toString();
	}

}
