package assignment.jongmin.producer.api;

import assignment.jongmin.producer.domain.User;
import assignment.jongmin.producer.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @GetMapping("/api/v1/users")
    public Result usersV1() {
        List<User> findUsers = userService.findUsers();
        List<UserDto> collect = findUsers.stream()
                .map(u -> new UserDto(
                        u.getId(),
                        u.getName(),
                        u.getAge(),
                        u.getStatus(),
                        u.getUpdated_dt()
                )).collect(Collectors.toList());
        return new Result(collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class UserDto{
        private Long id;
        private String name;
        private Integer age;
        private String status;
        private LocalDateTime updated_dt;
    }

    @PostMapping("/api/v1/users")
    public CreateUserResponse saveUserV1(@RequestBody CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setAge(request.getAge());
        user.setStatus("N");
        user.setUpdated_dt(LocalDateTime.now());
        Long id = userService.join(user);
        return new CreateUserResponse(id);
    }

    @Data
    static class CreateUserRequest {
        private String name;
        private int age;
    }

    @Data
    static class CreateUserResponse {
        private Long id;
        public CreateUserResponse(Long id) {
            this.id = id;
        }
    }
}
