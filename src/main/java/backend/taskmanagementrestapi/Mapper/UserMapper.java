package backend.taskmanagementrestapi.Mapper;

import backend.taskmanagementrestapi.Dto.UserResponse;
import backend.taskmanagementrestapi.Dto.UserSignUp;
import backend.taskmanagementrestapi.Entity.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    AppUser toAppUser(UserSignUp userSignUp);

    UserResponse toUserResponse(AppUser appUser);
}
