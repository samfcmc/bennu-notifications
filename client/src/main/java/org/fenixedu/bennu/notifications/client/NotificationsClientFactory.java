package org.fenixedu.bennu.notifications.client;

public class NotificationsClientFactory {

	private static NotificationsClient instance;

	public static NotificationsClient getClient() {
		if (instance == null) {
			/*
			 * TODO: Get the right client based on configuration (file or admin
			 * interface) if the configuration says that the client is remote
			 * and which url to use, instantiate the remote one. if the
			 * configuration says that the client is local and we have the
			 * dependency of master module, instantiate the local client
			 */
		}
		return instance;
	}
}
