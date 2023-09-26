//package schule.bbs2.j2023.efi3b.computerroomreservation.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.model.User;
//import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.repository.UserRepository;
//
//@Controller
//public class TestController {
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public TestController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @GetMapping("/test")
//    @ResponseBody
//    @ResponseStatus(code = HttpStatus.CREATED)
//    public User getUser() {
//        return new User();
//    }
//}
