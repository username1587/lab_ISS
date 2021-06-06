package services;

import models.Bug;
import models.User;
import observer.Observable;
import observer.Observer;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repositories.IBugRepo;
import repositories.IUserRepo;

import java.util.ArrayList;
import java.util.List;

public class Service implements Observable {
    public Service(IUserRepo userRepo, IBugRepo bugRepo) {
        this.userRepo = userRepo;
        this.bugRepo = bugRepo;
    }

    IUserRepo userRepo;
    IBugRepo bugRepo;

    public User login(String username, String password) {
        return userRepo.getUserByIdAndPassword(username, password);
    }

    public List<Bug> getBugs() {
        return bugRepo.getBugs();
    }

    public List<Bug> getActiveBugs() {
        return bugRepo.getActiveBugs();
    }

    public void addBug(Bug bug) {
        bugRepo.addBug(bug);
        notifyObservers();
    }

    public void solveBug(Bug bug,User user) {
        bug.setSolvedBy(user);
        bugRepo.solveBug(bug);
        notifyObservers();
    }

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(x -> x.update());
    }

}
