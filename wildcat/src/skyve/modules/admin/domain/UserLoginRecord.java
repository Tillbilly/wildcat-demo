package modules.admin.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.skyve.domain.types.DateTime;
import org.skyve.wildcat.domain.AbstractPersistentBean;
import org.skyve.wildcat.domain.types.jaxb.DateTimeMapper;

/**
 * UserLoginRecord
 * 
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class UserLoginRecord extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "admin";
	/** @hidden */
	public static final String DOCUMENT_NAME = "UserLoginRecord";

	/** @hidden */
	public static final String userNamePropertyName = "userName";
	/** @hidden */
	public static final String loginDateTimePropertyName = "loginDateTime";

	private String userName;
	private DateTime loginDateTime;

	@Override
	@XmlTransient
	public String getBizModule() {
		return UserLoginRecord.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return UserLoginRecord.DOCUMENT_NAME;
	}

	@Override
	@XmlTransient
	public String getBizKey() {
return getUserName() + " @ " + getLoginDateTime();
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof UserLoginRecord) && 
					this.getBizId().equals(((UserLoginRecord) o).getBizId()));
	}

	/**
	 * {@link #userName} accessor.
	 **/
	public String getUserName() {
		return userName;
	}

	/**
	 * {@link #userName} mutator.
	 * 
	 * @param userName	The new value to set.
	 **/
	@XmlElement
	public void setUserName(String userName) {
		preset(userNamePropertyName, userName);
		this.userName = userName;
	}

	/**
	 * {@link #loginDateTime} accessor.
	 **/
	public DateTime getLoginDateTime() {
		return loginDateTime;
	}

	/**
	 * {@link #loginDateTime} mutator.
	 * 
	 * @param loginDateTime	The new value to set.
	 **/
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(DateTimeMapper.class)
	@XmlElement
	public void setLoginDateTime(DateTime loginDateTime) {
		preset(loginDateTimePropertyName, loginDateTime);
		this.loginDateTime = loginDateTime;
	}
}
