package br.com.edu.aws.textract.service.dto;

public class BlockDTO {

	private String blockType;

	private float confidence;

	private String text;

	private String textType;

	private Integer rowIndex;

	private Integer columnIndex;

	private Integer rowSpan;

	private Integer columnSpan;

	private float left;

	private float top;

	private float width;

	private float height;

	private String id;

	public String getBlockType() {
		return blockType;
	}

	public void setBlockType(final String blockType) {
		this.blockType = blockType;
	}

	public float getConfidence() {
		return confidence;
	}

	public void setConfidence(final float confidence) {
		this.confidence = confidence;
	}

	public String getText() {
		return text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public String getTextType() {
		return textType;
	}

	public void setTextType(final String textType) {
		this.textType = textType;
	}

	public Integer getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(final Integer rowIndex) {
		this.rowIndex = rowIndex;
	}

	public Integer getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(final Integer columnIndex) {
		this.columnIndex = columnIndex;
	}

	public Integer getRowSpan() {
		return rowSpan;
	}

	public void setRowSpan(final Integer rowSpan) {
		this.rowSpan = rowSpan;
	}

	public Integer getColumnSpan() {
		return columnSpan;
	}

	public void setColumnSpan(final Integer columnSpan) {
		this.columnSpan = columnSpan;
	}

	public float getLeft() {
		return left;
	}

	public void setLeft(final float left) {
		this.left = left;
	}

	public float getTop() {
		return top;
	}

	public void setTop(final float top) {
		this.top = top;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(final float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(final float height) {
		this.height = height;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public boolean isLine() {
		return !this.getBlockType().equals("LINE");
	}

}
