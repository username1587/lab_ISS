package repositories;

import models.Bug;

import java.util.List;

public interface IBugRepo {
    List<Bug> getBugs();
    List<Bug> getActiveBugs();
    void addBug(Bug bug);
    void solveBug(Bug bug);
}
