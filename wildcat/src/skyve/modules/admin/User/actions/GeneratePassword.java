package modules.admin.User.actions;

import org.skyve.metadata.controller.ServerSideAction;
import org.skyve.metadata.controller.ServerSideActionResult;
import org.skyve.web.WebContext;

import modules.admin.PasswordGenerator;
import modules.admin.domain.User;

public class GeneratePassword implements ServerSideAction<User> {
	/**
	 * For Serialization.
	 */
	private static final long serialVersionUID = 3904239033808385824L;

	@Override
	public ServerSideActionResult execute(User user, WebContext webContext) throws Exception {

		generatePassword(user);

		return new ServerSideActionResult(user);
		// form
	}

	@SuppressWarnings({ "unused", "static-access" })
	public static void generatePassword(User user) throws Exception {
		user.setClearTextPassword(new PasswordGenerator().generate());

		user.setNewPassword(user.getClearTextPassword());
		user.setConfirmPassword(user.getClearTextPassword());
		user.setPasswordExpired(Boolean.TRUE);

	}
}
