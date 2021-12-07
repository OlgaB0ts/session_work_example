package com.gmail.olgabots.session.simple.example.model;

import java.util.Objects;

public class SessionAttribute {
	
	private String name;
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public int hashCode() {
		return Objects.hash(name, value);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessionAttribute other = (SessionAttribute) obj;
		return Objects.equals(name, other.name) && Objects.equals(value, other.value);
	}
	public SessionAttribute(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	@Override
	public String toString() {
		return "SessionAttribute [name=" + name + ", value=" + value + "]";
	}
	public SessionAttribute() {
		super();
	}
	
	

}
