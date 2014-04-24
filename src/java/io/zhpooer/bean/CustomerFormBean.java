package io.zhpooer.bean;

import java.util.HashMap;
import java.util.Map;

public class CustomerFormBean {
	private String id;
	private String name;
	private String gender;
	private String birthday;
	private String email;
	private String cellphone;
	private String hobby;
	private String type;
	private String description;

	private Map<String, String> errors = new HashMap<String, String>();

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean validation() {
		// 邮箱
//		if (email == null || email.equals("")) {
//			errors.put("email", "输入邮箱");
//		} else {
//
//			if (!email.matches("\\w{2,10}@\\w{2,10}\\.\\w{2,10}")) {
//				errors.put("email", "输入正确格式邮箱");
//			}
//		}
//		// 出生日期
//		if (birthday == null || birthday.equals("")) {
//			errors.put("birthday", "输入出生日期");
//		} else {
//			try {
//				new DateLocaleConverter(Locale.CHINESE).convert(birthday);
//			} catch (Exception e) {
//				errors.put("birthday", "输入正确格式日期" + Locale.CHINESE);
//			}
//		}
//		return errors.isEmpty();
		return true;
	}
}
