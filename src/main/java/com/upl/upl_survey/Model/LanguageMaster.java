package com.upl.upl_survey.Model;

public class LanguageMaster {
	private int id;
	private String language;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LanguageMaster [id=");
		builder.append(id);
		builder.append(", language=");
		builder.append(language);
		builder.append("]");
		return builder.toString();
	}

}
