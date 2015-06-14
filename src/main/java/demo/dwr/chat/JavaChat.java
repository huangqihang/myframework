package demo.dwr.chat;

import java.util.Collection;
import java.util.LinkedList;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaChat {
	public void addMessage(String text) {
		if (text != null && text.trim().length() > 0) {
			messages.addFirst(new Message(text));
			while (messages.size() > 10) {
				messages.removeLast();
			}
		}
		// Reverse Ajax code to be added here shortly
		WebContext wctx = WebContextFactory.get();
		String currentPage = wctx.getCurrentPage();

		// Clear the input box in the browser that kicked off this page only
		Util utilThis = new Util(wctx.getScriptSession());
		utilThis.setValue("text", "");

		// For all the browsers on the current page:
		Collection sessions = wctx.getScriptSessionsByPage(currentPage);
		Util utilAll = new Util(sessions);

		// Clear the list and add in the new set of messages
		utilAll.removeAllOptions("chatlog");
		utilAll.addOptions("chatlog", messages, "text");
	}

	private static LinkedList<Message> messages = new LinkedList<Message>();

	protected static final Logger log = LoggerFactory.getLogger(JavaChat.class);
}