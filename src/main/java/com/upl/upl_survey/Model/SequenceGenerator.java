package com.upl.upl_survey.Model;

public class SequenceGenerator {

	private Long id;
	private Long seq_id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSeq_id() {
		return seq_id;
	}

	public void setSeq_id(Long seq_id) {
		this.seq_id = seq_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SequenceGenerator [id=");
		builder.append(id);
		builder.append(", seq_id=");
		builder.append(seq_id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}
