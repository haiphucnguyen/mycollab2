package com.esofthead.mycollab.core;

import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
public class PlatformManagerImpl extends PlatformManager {
	private static Logger log = LoggerFactory
			.getLogger(PlatformManagerImpl.class);

	private Cache cache;

	private Map<String, NotExpiredSessionResource> holdNotExpiredSessions = new Hashtable<String, NotExpiredSessionResource>();

	public PlatformManagerImpl() {
		CacheManager cacheManager = new CacheManager(PlatformManager.class
				.getClassLoader().getResourceAsStream("ehcache.xml"));
		cache = cacheManager.getCache("engroupCache");
	}

	@Override
	public void holdUserSessionExpiried(String userSessionId, String appId) {
		NotExpiredSessionResource notExpiredSessionResource = holdNotExpiredSessions
				.get(userSessionId);
		if (notExpiredSessionResource != null) {
			notExpiredSessionResource.getAppIds().add(appId);
		} else {
			notExpiredSessionResource = new NotExpiredSessionResource(
					userSessionId);
			notExpiredSessionResource.getAppIds().add(appId);
			holdNotExpiredSessions
					.put(userSessionId, notExpiredSessionResource);
		}
	}

	@Override
	public void releaseUserSessionExpired(String userSessionId, String appId) {
		NotExpiredSessionResource notExpiredSessionResource = holdNotExpiredSessions
				.get(userSessionId);
		if (notExpiredSessionResource != null) {
			if (notExpiredSessionResource.getAppIds().contains(appId)) {
				notExpiredSessionResource.getAppIds().remove(appId);
				if (notExpiredSessionResource.getAppIds().size() == 0) {
					holdNotExpiredSessions.remove(userSessionId);
				}
			}
		}
	}

	public synchronized Session createUserSession(String username) {
		Session sessionCache = new Session(username);
		Element userCache = new Element(sessionCache.getSessionid(),
				sessionCache);
		cache.put(userCache);
		return sessionCache;
	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	public Session getSession(String userSessionId) {
		return getUserSession(userSessionId);
	}

	@SuppressWarnings("unchecked")
	List<String> getSessionKeys() {
		return cache.getKeys();
	}

	public void removeUserSession(String userSessionId) {
		if (holdNotExpiredSessions.get(userSessionId) != null) {
			return;
		}
		Session session = getUserSession(userSessionId);
		Collection<Object> values = session.attributeValues();
		for (Object attrValue : values) {
			if (attrValue instanceof CleanableResource) {
				try {
					((CleanableResource) attrValue).cleanup();
				} catch (Exception e) {
					log.error("Exception occured while cleanup resource", e);
				}
			}
		}
		cache.remove(userSessionId);
	}

	private synchronized Session getUserSession(String userSessionId) {
		Element userCache = cache.get(userSessionId);
		if (userCache == null) {
			throw new EngroupSecurityException("User session " + userSessionId
					+ " is expired");
		}

		return (Session) userCache.getValue();
	}

	@Override
	public String getUsername(String userSessionId) {
		Session session = getSession(userSessionId);
		return (session == null) ? null : session.getRemoteUser();
	}
}
