package br.com.edu.postman.api.service.dto.collection;

import com.google.gson.annotations.SerializedName;

/**
 * @author Eduardo
 */
public class ProtocolProfileBehavior {

	@SerializedName("disableBodyPruning")
	private boolean disableBodyPruning;

	/**
	 * New instance of ProtocolProfileBehavior
	 */
	public ProtocolProfileBehavior() {
		super();
	}

	/**
	 * Copy constructor.
	 *
	 * @param protocol
	 */
	public ProtocolProfileBehavior(final ProtocolProfileBehavior protocol) {
		super();
		this.disableBodyPruning = protocol.isDisableBodyPruning();
	}

	public boolean isDisableBodyPruning() {
		return disableBodyPruning;
	}

	public void setDisableBodyPruning(final boolean disableBodyPruning) {
		this.disableBodyPruning = disableBodyPruning;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("ProtocolProfileBehavior [disableBodyPruning=");
		builder.append(disableBodyPruning);
		builder.append("]");
		return builder.toString();
	}

}
