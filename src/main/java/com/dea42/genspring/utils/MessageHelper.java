package com.dea42.genspring.utils;

import static com.dea42.genspring.utils.Message.MESSAGE_ATTRIBUTE;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public final class MessageHelper {

	// common message keys to help avoid typos
	public static final String view_index_title = "view.index.title";
	public static final String signup_success = "signup.success";
	public static final String signin_success = "signin.success";
	public static final String signin_failed = "signin.failed";
	public static final String signup_failed = "signup.failed";
	public static final String signin_title = "signin.title";
	public static final String signup_title = "signup.title";
	public static final String signin_rememberMe = "signin.rememberMe";
	public static final String signin_email = "signin.email";
	public static final String signin_password = "signin.password";
	public static final String signin_signin = "signin.signin";
	public static final String signin_signup = "signin.signup";
	public static final String signin_newHere = "signin.newHere";
	public static final String signin_haveAccount = "signin.haveAccount";
	public static final String signin_logout = "signin.logout";
	public static final String password_mismatch = "password.mismatch";
	public static final String update_sucessful = "update.sucessful";
	public static final String form_errors = "form.errors";
	public static final String email_unique = "email.unique";
	public static final String save_success = "save.success";
	public static final String save_cancelled = "save.cancelled";
	public static final String db_failed = "db.failed";
	public static final String app_name = "app.name";
	public static final String index_greeting = "index.greeting";
	public static final String lang_change = "lang.change";
	public static final String lang_en = "lang.en";
	public static final String lang_fr = "lang.fr";
	public static final String lang_de = "lang.de";
	public static final String header_home = "header.home";
	public static final String header_restApi = "header.restApi";
	public static final String header_gui = "header.gui";
	public static final String dialog_close = "dialog.close";
	public static final String notBlank_message = "javax.validation.constraints.NotBlank.message";
	public static final String email_message = "email.message";
	public static final String edit_listView = "edit.listView";
	public static final String edit_list = "edit.list";
	public static final String edit_new = "edit.new";
	public static final String edit_edit = "edit.edit";
	public static final String edit_delete = "edit.delete";
	public static final String edit_save = "edit.save";
	public static final String edit_cancel = "edit.cancel";
	public static final String edit_actions = "edit.actions";
	public static final String class_Account = "class.Account";
	public static final String Account_role = "Account.role";
	public static final String Account_created = "Account.created";
	public static final String Account_id = "Account.id";
	public static final String Account_email = "Account.email";
	public static final String Account_password = "Account.password";
	public static final String Account_passwordConfirm = "Account.passwordConfirm";
	public static final String HISTORY_VIOLATION = "HISTORY_VIOLATION";
	public static final String ILLEGAL_WORD = "ILLEGAL_WORD";
	public static final String ILLEGAL_WORD_REVERSED = "ILLEGAL_WORD_REVERSED";
	public static final String ILLEGAL_DIGEST_WORD = "ILLEGAL_DIGEST_WORD";
	public static final String ILLEGAL_DIGEST_WORD_REVERSED = "ILLEGAL_DIGEST_WORD_REVERSED";
	public static final String ILLEGAL_MATCH = "ILLEGAL_MATCH";
	public static final String ALLOWED_MATCH = "ALLOWED_MATCH";
	public static final String ILLEGAL_CHAR = "ILLEGAL_CHAR";
	public static final String ALLOWED_CHAR = "ALLOWED_CHAR";
	public static final String ILLEGAL_QWERTY_SEQUENCE = "ILLEGAL_QWERTY_SEQUENCE";
	public static final String ILLEGAL_ALPHABETICAL_SEQUENCE = "ILLEGAL_ALPHABETICAL_SEQUENCE";
	public static final String ILLEGAL_NUMERICAL_SEQUENCE = "ILLEGAL_NUMERICAL_SEQUENCE";
	public static final String ILLEGAL_USERNAME = "ILLEGAL_USERNAME";
	public static final String ILLEGAL_USERNAME_REVERSED = "ILLEGAL_USERNAME_REVERSED";
	public static final String ILLEGAL_WHITESPACE = "ILLEGAL_WHITESPACE";
	public static final String ILLEGAL_NUMBER_RANGE = "ILLEGAL_NUMBER_RANGE";
	public static final String ILLEGAL_REPEATED_CHARS = "ILLEGAL_REPEATED_CHARS";
	public static final String INSUFFICIENT_UPPERCASE = "INSUFFICIENT_UPPERCASE";
	public static final String INSUFFICIENT_LOWERCASE = "INSUFFICIENT_LOWERCASE";
	public static final String INSUFFICIENT_ALPHABETICAL = "INSUFFICIENT_ALPHABETICAL";
	public static final String INSUFFICIENT_DIGIT = "INSUFFICIENT_DIGIT";
	public static final String INSUFFICIENT_SPECIAL = "INSUFFICIENT_SPECIAL";
	public static final String INSUFFICIENT_CHARACTERISTICS = "INSUFFICIENT_CHARACTERISTICS";
	public static final String INSUFFICIENT_COMPLEXITY = "INSUFFICIENT_COMPLEXITY";
	public static final String INSUFFICIENT_COMPLEXITY_RULES = "INSUFFICIENT_COMPLEXITY_RULES";
	public static final String SOURCE_VIOLATION = "SOURCE_VIOLATION";
	public static final String TOO_LONG = "TOO_LONG";
	public static final String TOO_SHORT = "TOO_SHORT";
	public static final String TOO_MANY_OCCURRENCES = "TOO_MANY_OCCURRENCES";

	private MessageHelper() {

	}

	public static void addSuccessAttribute(RedirectAttributes ra, String message, Object... args) {
		addAttribute(ra, message, Message.Type.SUCCESS, args);
	}

	public static void addErrorAttribute(RedirectAttributes ra, String message, Object... args) {
		addAttribute(ra, message, Message.Type.DANGER, args);
	}

	public static void addInfoAttribute(RedirectAttributes ra, String message, Object... args) {
		addAttribute(ra, message, Message.Type.INFO, args);
	}

	public static void addWarningAttribute(RedirectAttributes ra, String message, Object... args) {
		addAttribute(ra, message, Message.Type.WARNING, args);
	}

	private static void addAttribute(RedirectAttributes ra, String message, Message.Type type, Object... args) {
		ra.addFlashAttribute(MESSAGE_ATTRIBUTE, new Message(message, type, args));
	}

	public static void addSuccessAttribute(Model model, String message, Object... args) {
		addAttribute(model, message, Message.Type.SUCCESS, args);
	}

	public static void addErrorAttribute(Model model, String message, Object... args) {
		addAttribute(model, message, Message.Type.DANGER, args);
	}

	public static void addInfoAttribute(Model model, String message, Object... args) {
		addAttribute(model, message, Message.Type.INFO, args);
	}

	public static void addWarningAttribute(Model model, String message, Object... args) {
		addAttribute(model, message, Message.Type.WARNING, args);
	}

	private static void addAttribute(Model model, String message, Message.Type type, Object... args) {
		model.addAttribute(MESSAGE_ATTRIBUTE, new Message(message, type, args));
	}
}
