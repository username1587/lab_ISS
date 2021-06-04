package services;

import models.Bug;
import models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repositories.IBugRepo;
import repositories.IUserRepo;

import java.util.List;

public class Service{
    public Service(IUserRepo userRepo, IBugRepo bugRepo) {
        this.userRepo = userRepo;
        this.bugRepo = bugRepo;
    }

    IUserRepo userRepo;
    IBugRepo bugRepo;

    public User login(String username,String password){
        return userRepo.getUserByIdAndPassword(username,password);
    }

    public List<Bug> getBugs(){
        return bugRepo.getBugs();
    }

    public List<Bug> getActiveBugs(){
        return bugRepo.getActiveBugs();
    }

    public void addBug(Bug bug){
        bugRepo.addBug(bug);
    }

    public void solveBug(Bug bug){
        bugRepo.solveBug(bug);
    }
}
