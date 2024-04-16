package hw5.util.mapper;

import hw5.dto.UserDto;
import hw5.model.User;

public final class UserDtoMapper {
    private UserDtoMapper() {
    }

    public static User fromDto(UserDto userDto) {
        var user = new User();

        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        return user;
    }

    public static User fromDto(UserDto userDto, Long id) {
        var user = new User();

        user.setId(id);
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        return user;
    }

    public static UserDto toDto(User user) {
        var userDto = new UserDto();

        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        return userDto;
    }
}
