package br.com.edu.aws.textract.service.dto;

import com.amazonaws.services.textract.model.Block;
import com.amazonaws.services.textract.model.BoundingBox;

public class BlockConverter {

	public static BlockDTO convertToDTO(final Block b) {
		final BlockDTO dto = new BlockDTO();
		dto.setBlockType(b.getBlockType());
		dto.setColumnIndex(b.getColumnIndex());
		dto.setColumnSpan(b.getColumnSpan());
		dto.setConfidence(b.getConfidence() != null ? b.getConfidence() : 0);

		if (b.getGeometry() != null && b.getGeometry().getBoundingBox() != null) {
			final BoundingBox box = b.getGeometry().getBoundingBox();
			dto.setHeight(box.getHeight());
			dto.setWidth(box.getWidth());
			dto.setTop(box.getTop());
			dto.setLeft(box.getLeft());
		}

		dto.setId(b.getId());
		dto.setRowIndex(b.getRowIndex());
		dto.setRowSpan(b.getRowSpan());
		dto.setText(b.getText());
		dto.setTextType(b.getTextType());

		return dto;
	}

}
