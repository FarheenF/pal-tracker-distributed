package io.pivotal.pal.tracker.timesheets;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestOperations;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

public class ProjectClient {

	private final RestOperations restOperations;
	private final String endpoint;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final Map<Long, ProjectInfo> projectsCache = new ConcurrentHashMap<>();

	public ProjectClient(RestOperations restOperations, String registrationServerEndpoint) {
		this.restOperations = restOperations;
		this.endpoint = registrationServerEndpoint;
	}

	@CircuitBreaker(name = "project", fallbackMethod = "getProjectFromCache")
	public ProjectInfo getProject(long projectId) {
		ProjectInfo project = restOperations.getForObject(endpoint + "/projects/" + projectId, ProjectInfo.class);

		projectsCache.put(projectId, project);

		return project;
	}

	public ProjectInfo getProjectFromCache(long projectId, Throwable cause) {
		logger.info("Getting project with id {} from cache", projectId);
		return projectsCache.get(projectId);
	}
}
