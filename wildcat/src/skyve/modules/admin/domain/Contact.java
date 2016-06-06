package modules.admin.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.skyve.domain.types.Enumeration;
import org.skyve.metadata.model.document.Bizlet.DomainValue;
import org.skyve.wildcat.domain.AbstractPersistentBean;

/**
 * Contact
 * 
 * @depend - - - ContactType
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class Contact extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "admin";
	/** @hidden */
	public static final String DOCUMENT_NAME = "Contact";

	/** @hidden */
	public static final String namePropertyName = "name";
	/** @hidden */
	public static final String contactTypePropertyName = "contactType";
	/** @hidden */
	public static final String email1PropertyName = "email1";
	/** @hidden */
	public static final String mobilePropertyName = "mobile";
	/** @hidden */
	public static final String imagePropertyName = "image";

	/**
	 * Whether this contact is a person or an organisation.
	 **/
	@XmlEnum
	public static enum ContactType implements Enumeration {
		person("Person", "Person"),
		organisation("Organisation", "Organisation");

		private String code;
		private String description;

		/** @hidden */
		private static List<DomainValue> domainValues;

		private ContactType(String code, String description) {
			this.code = code;
			this.description = description;
		}

		@Override
		public String toCode() {
			return code;
		}

		@Override
		public String toDescription() {
			return description;
		}

		public static ContactType fromCode(String code) {
			ContactType result = null;

			for (ContactType value : values()) {
				if (value.code.equals(code)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static ContactType fromDescription(String description) {
			ContactType result = null;

			for (ContactType value : values()) {
				if (value.description.equals(description)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static List<DomainValue> toDomainValues() {
			if (domainValues == null) {
				ContactType[] values = values();
				domainValues = new ArrayList<>(values.length);
				for (ContactType value : values) {
					domainValues.add(new DomainValue(value.code, value.description));
				}
			}

			return domainValues;
		}
	}

	private String name;
	/**
	 * Whether this contact is a person or an organisation.
	 **/
	private ContactType contactType;
	private String email1;
	private String mobile;
	private String image;

	@Override
	@XmlTransient
	public String getBizModule() {
		return Contact.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Contact.DOCUMENT_NAME;
	}

	@Override
	@XmlTransient
	public String getBizKey() {
return modules.admin.Contact.ContactBizlet.bizKey(this);
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Contact) && 
					this.getBizId().equals(((Contact) o).getBizId()));
	}

	/**
	 * {@link #name} accessor.
	 **/
	public String getName() {
		return name;
	}

	/**
	 * {@link #name} mutator.
	 * 
	 * @param name	The new value to set.
	 **/
	@XmlElement
	public void setName(String name) {
		preset(namePropertyName, name);
		this.name = name;
	}

	/**
	 * {@link #contactType} accessor.
	 **/
	public ContactType getContactType() {
		return contactType;
	}

	/**
	 * {@link #contactType} mutator.
	 * 
	 * @param contactType	The new value to set.
	 **/
	@XmlElement
	public void setContactType(ContactType contactType) {
		preset(contactTypePropertyName, contactType);
		this.contactType = contactType;
	}

	/**
	 * {@link #email1} accessor.
	 **/
	public String getEmail1() {
		return email1;
	}

	/**
	 * {@link #email1} mutator.
	 * 
	 * @param email1	The new value to set.
	 **/
	@XmlElement
	public void setEmail1(String email1) {
		preset(email1PropertyName, email1);
		this.email1 = email1;
	}

	/**
	 * {@link #mobile} accessor.
	 **/
	public String getMobile() {
		return mobile;
	}

	/**
	 * {@link #mobile} mutator.
	 * 
	 * @param mobile	The new value to set.
	 **/
	@XmlElement
	public void setMobile(String mobile) {
		preset(mobilePropertyName, mobile);
		this.mobile = mobile;
	}

	/**
	 * {@link #image} accessor.
	 **/
	public String getImage() {
		return image;
	}

	/**
	 * {@link #image} mutator.
	 * 
	 * @param image	The new value to set.
	 **/
	@XmlElement
	public void setImage(String image) {
		preset(imagePropertyName, image);
		this.image = image;
	}
}
