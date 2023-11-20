package qa.devstatistics.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import qa.devstatistics.dao.Developer;
import qa.devstatistics.helpers.DeveloperType;
import qa.devstatistics.helpers.Status;
import qa.devstatistics.repos.DeveloperRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 29.09.2023
 */

@Service
public class DeveloperService {

    private final Status status;

    private final DeveloperRepo developerRepo;

    public DeveloperService(Status status, DeveloperRepo developerRepo) {
        this.status = status;
        this.developerRepo = developerRepo;
    }

    public Status addNewDeveloper(String name, String surname, String type) {
        if (developerRepo.existsDeveloperBySurnameIgnoreCaseAndNameIgnoreCase(surname, name)) {
            return status.badStatus(
                    String.format("Developer [Name: %s][Surname: %s] exist",
                    name, surname)
            );
        }

        Developer developer = new Developer();
        developer.setName(name);
        developer.setSurname(surname);
        developer.setType(type);
        developerRepo.save(developer);
        return status.goodStatus(
                String.format("Developer %s (%s) save",
                developer.getFullName(), type));
    }

    public Status dismissCurrentDeveloper(long id) {
        Developer developer = developerRepo.findDeveloperById(id);
        if (developer.isDismiss()) {
            developer.setUnDismiss();
        } else {
            developer.setDismiss();
        }
        developerRepo.save(developer);
        return status.goodStatus(
                String.format("Developer %s", developer.isDismiss() ? "dismiss" : "undismiss")
        );
    }

    public Status deleteCurrentDeveloper(long id) {
        Developer developer = developerRepo.findDeveloperById(id);

        if (developer.getTasksList().isEmpty()) {
            return status.badStatus(
                    String.format("Can't delete Developer %s! Cause: Include %s tasks!",
                            developer.getFullName(), developer.getCountTasks())
            );
        }

        developerRepo.deleteById(id);
        return status.goodStatus(
                String.format("Developer %s deleted",
                        developer.getFullName())
        );
    }

    public Developer findDeveloper(long id) {
        return developerRepo.findDeveloperById(id);
    }

    public List<Developer> getAllDevelopers() {
        return developerRepo.findAll();
    }

    public Page<Developer> getAllDevelopers(Pageable pageable) {
        return developerRepo.findAll(pageable);
    }

    public List<Developer> getAllNoDismissDevelopers() {
        return developerRepo.findAllByDismissFalse();
    }

    public void saveDeveloperInDB(Developer developer) {
        developerRepo.save(developer);
    }

    public List<DeveloperType> getDevelopersType() {
        return new ArrayList<>(List.of(DeveloperType.values()));
    }
}
