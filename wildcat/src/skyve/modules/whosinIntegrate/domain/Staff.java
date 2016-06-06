package modules.whosinIntegrate.domain;

import com.vividsolutions.jts.geom.Geometry;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import modules.admin.domain.Contact;
import org.skyve.domain.types.DateOnly;
import org.skyve.domain.types.DateTime;
import org.skyve.domain.types.Enumeration;
import org.skyve.metadata.model.document.Bizlet.DomainValue;
import org.skyve.wildcat.domain.AbstractPersistentBean;
import org.skyve.wildcat.domain.types.jaxb.DateOnlyMapper;
import org.skyve.wildcat.domain.types.jaxb.DateTimeMapper;
import org.skyve.wildcat.domain.types.jaxb.GeometryMapper;

/**
 * Someone who works for this organisation
 * 
 * @depend - - - Status
 * @navhas n baseOffice 0..1 Office
 * @navhas n contact 0..1 Contact
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class Staff extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "whosinIntegrate";
	/** @hidden */
	public static final String DOCUMENT_NAME = "Staff";

	/** @hidden */
	public static final String contactPropertyName = "contact";
	/** @hidden */
	public static final String staffCodePropertyName = "staffCode";
	/** @hidden */
	public static final String dateOfBirthPropertyName = "dateOfBirth";
	/** @hidden */
	public static final String roleTitlePropertyName = "roleTitle";
	/** @hidden */
	public static final String baseOfficePropertyName = "baseOffice";
	/** @hidden */
	public static final String locationPropertyName = "location";
	/** @hidden */
	public static final String statusPropertyName = "status";
	/** @hidden */
	public static final String dueBackPropertyName = "dueBack";

	/**
	 * Status
	 **/
	@XmlEnum
	public static enum Status implements Enumeration {
		inTheOffice("inOffice", "In the Office"),
		outOfTheOffice("outOffice", "Out of the Office"),
		onLeave("onLeave", "On Leave"),
		atLunch("atLunch", "At Lunch");

		private String code;
		private String description;

		/** @hidden */
		private static List<DomainValue> domainValues;

		private Status(String code, String description) {
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

		public static Status fromCode(String code) {
			Status result = null;

			for (Status value : values()) {
				if (value.code.equals(code)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static Status fromDescription(String description) {
			Status result = null;

			for (Status value : values()) {
				if (value.description.equals(description)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static List<DomainValue> toDomainValues() {
			if (domainValues == null) {
				Status[] values = values();
				domainValues = new ArrayList<>(values.length);
				for (Status value : values) {
					domainValues.add(new DomainValue(value.code, value.description));
				}
			}

			return domainValues;
		}
	}

	private Contact contact = null;
	private String staffCode;
	private DateOnly dateOfBirth;
	/**
	 * The person's organisational title or role
	 **/
	private String roleTitle;
	/**
	 * The office this person usually operates from.
	 **/
	private Office baseOffice = null;
	private Geometry location;
	private Status status;
	/**
	 * If not in the office, when the staff member is due back.
	 **/
	private DateTime dueBack;

	@Override
	@XmlTransient
	public String getBizModule() {
		return Staff.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Staff.DOCUMENT_NAME;
	}

	@Override
	@XmlTransient
	public String getBizKey() {
return getContact().getName();
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Staff) && 
					this.getBizId().equals(((Staff) o).getBizId()));
	}

	/**
	 * {@link #contact} accessor.
	 **/
	public Contact getContact() {
		return contact;
	}

	/**
	 * {@link #contact} mutator.
	 * 
	 * @param contact	The new value to set.
	 **/
	@XmlElement
	public void setContact(Contact contact) {
		preset(contactPropertyName, contact);
		this.contact = contact;
	}

	/**
	 * {@link #staffCode} accessor.
	 **/
	public String getStaffCode() {
		return staffCode;
	}

	/**
	 * {@link #staffCode} mutator.
	 * 
	 * @param staffCode	The new value to set.
	 **/
	@XmlElement
	public void setStaffCode(String staffCode) {
		preset(staffCodePropertyName, staffCode);
		this.staffCode = staffCode;
	}

	/**
	 * {@link #dateOfBirth} accessor.
	 **/
	public DateOnly getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * {@link #dateOfBirth} mutator.
	 * 
	 * @param dateOfBirth	The new value to set.
	 **/
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(DateOnlyMapper.class)
	@XmlElement
	public void setDateOfBirth(DateOnly dateOfBirth) {
		preset(dateOfBirthPropertyName, dateOfBirth);
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * {@link #roleTitle} accessor.
	 **/
	public String getRoleTitle() {
		return roleTitle;
	}

	/**
	 * {@link #roleTitle} mutator.
	 * 
	 * @param roleTitle	The new value to set.
	 **/
	@XmlElement
	public void setRoleTitle(String roleTitle) {
		preset(roleTitlePropertyName, roleTitle);
		this.roleTitle = roleTitle;
	}

	/**
	 * {@link #baseOffice} accessor.
	 **/
	public Office getBaseOffice() {
		return baseOffice;
	}

	/**
	 * {@link #baseOffice} mutator.
	 * 
	 * @param baseOffice	The new value to set.
	 **/
	@XmlElement
	public void setBaseOffice(Office baseOffice) {
		preset(baseOfficePropertyName, baseOffice);
		this.baseOffice = baseOffice;
	}

	/**
	 * {@link #location} accessor.
	 **/
	public Geometry getLocation() {
		return location;
	}

	/**
	 * {@link #location} mutator.
	 * 
	 * @param location	The new value to set.
	 **/
	@XmlJavaTypeAdapter(GeometryMapper.class)
	@XmlElement
	public void setLocation(Geometry location) {
		preset(locationPropertyName, location);
		this.location = location;
	}

	/**
	 * {@link #status} accessor.
	 **/
	public Status getStatus() {
		return status;
	}

	/**
	 * {@link #status} mutator.
	 * 
	 * @param status	The new value to set.
	 **/
	@XmlElement
	public void setStatus(Status status) {
		preset(statusPropertyName, status);
		this.status = status;
	}

	/**
	 * {@link #dueBack} accessor.
	 **/
	public DateTime getDueBack() {
		return dueBack;
	}

	/**
	 * {@link #dueBack} mutator.
	 * 
	 * @param dueBack	The new value to set.
	 **/
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(DateTimeMapper.class)
	@XmlElement
	public void setDueBack(DateTime dueBack) {
		preset(dueBackPropertyName, dueBack);
		this.dueBack = dueBack;
	}

	@XmlTransient
	public boolean isCanChange() {
		return (isManager() || isMe());
	}

	public boolean isNotCanChange() {
		return (! isCanChange());
	}

	@XmlTransient
	public boolean isManager() {
		return (isUserInRole("whosin","Manager"));
	}

	public boolean isNotManager() {
		return (! isManager());
	}

	@XmlTransient
	public boolean isMe() {
		return (modules.whosinIntegrate.Staff.StaffBizlet.staffIsMe(this));
	}

	public boolean isNotMe() {
		return (! isMe());
	}
}
