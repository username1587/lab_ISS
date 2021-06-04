package repositories;

import models.User;

public interface IUserRepo {
    User getUserByIdAndPassword(String username,String password);
}
