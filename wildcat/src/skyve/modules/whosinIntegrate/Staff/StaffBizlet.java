package modules.whosinIntegrate.Staff;

import modules.admin.domain.Contact;
import modules.admin.domain.Contact.ContactType;
import modules.whosinIntegrate.domain.Staff;

import org.skyve.CORE;
import org.skyve.metadata.customer.Customer;
import org.skyve.metadata.model.document.Bizlet;
import org.skyve.metadata.model.document.Document;
import org.skyve.metadata.module.Module;
import org.skyve.metadata.user.User;
import org.skyve.persistence.Persistence;

public class StaffBizlet extends Bizlet<Staff> {
	private static final long serialVersionUID = -5073318444540975484L;

	@Override
	public Staff newInstance(Staff bean) throws Exception {
		User u = CORE.getUser();
		Customer c = u.getCustomer();
		Module m = c.getModule(Contact.MODULE_NAME);
		Document d = m.getDocument(c, Contact.DOCUMENT_NAME);

		Contact contact = d.newInstance(u);
		contact.setContactType(ContactType.person);
		bean.setContact(contact);

		return bean;
	}

	public static boolean staffIsMe(Staff bean) {

		Persistence pers = CORE.getPersistence();
		boolean result = pers.getUser().getContactId().equals(bean.getContact().getBizId());
		return result;

	}

}
