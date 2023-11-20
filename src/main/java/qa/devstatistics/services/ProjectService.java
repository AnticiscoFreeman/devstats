package qa.devstatistics.services;

import org.springframework.stereotype.Service;
import qa.devstatistics.dao.Project;
import qa.devstatistics.helpers.Status;
import qa.devstatistics.repos.ProjectRepo;

import java.util.List;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 29.09.2023
 */

@Service
public class ProjectService {

    private final Status status;

    private final ProjectRepo projectRepo;

    public ProjectService(Status status, ProjectRepo projectRepo) {
        this.status = status;
        this.projectRepo = projectRepo;
    }

    public Status addNewProject(String name) {
        if (projectRepo.existsProjectByNameIgnoreCase(name)) {
            return status.badStatus(
                    String.format("%s is exist", name)
            );
        }

        Project project = new Project(name);
        projectRepo.save(project);
        return status.goodStatus(
                String.format("%s creating", project.getName())
        );
    }

    public Status deleteCurrentProject(long id) {
        Project project = projectRepo.findProjectById(id);

        if (!project.isEmpty()) {
            return status.badStatus(
                    String.format("%s not deleted! Cause: Include %s tasks",
                            project.getName(),
                            project.getCountTasks())
            );
        }
        projectRepo.delete(project);
        return status.goodStatus(
                String.format("%s deleting!", project.getName())
        );
    }

    public void saveProjectInDB(Project project) {
        projectRepo.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    public Project getProject(long projectId) {
        return projectRepo.findProjectById(projectId);
    }
}
