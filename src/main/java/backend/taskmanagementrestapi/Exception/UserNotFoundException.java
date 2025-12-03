package backend.taskmanagementrestapi.Exception;

public class UserNotFoundException extends NotFoundException{
    public UserNotFoundException(String email){
        super("User Not Found With ID:  " + email );
    }
}
