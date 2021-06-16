package niit.start.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import niit.start.dao.UserDao;
import niit.start.entity.User;

import javax.annotation.Resource;

@RestController
@RequestMapping("api")
public class UserController {
    @Resource(name = "userDao")
    private UserDao UserDao;

    @RequestMapping("/getUser")
    @ResponseBody
    public List<User> findAll() {
        List<User> list;
        list = UserDao.findAll();
        return list;
    }

    @RequestMapping("/getUserById")
    @ResponseBody
    public User getUserById(Integer id) {
        User user = UserDao.getUserById(id);
        return user;
    }

    @RequestMapping("/getUserByName")
    @ResponseBody
    public User getUserByName(String name) {
        User user = UserDao.getUserByName(name);
        return user;
    }

    @RequestMapping("/checkUser")
    @ResponseBody
    public String checkUser(String name) {
        User user = UserDao.getUserByName(name);
        return user.getPassword();
    }

    @PostMapping("/addUser")
    @ResponseBody
    public void addUser(String name, String password, String gender, String location, String birthday) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setRole("user");
        user.setGender(gender);
        user.setLocation(location);
        user.setBirthday(birthday);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(new Date());
        user.setCreated(date);
        UserDao.save(user);
    }

    @Transactional
    @RequestMapping("/deleteUserById")
    @ResponseBody
    public void deleteUserById(Integer id) {
        UserDao.deleteUserById(id);
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public void getUserById(Integer id, String name, String gender, String location, String birthday) {
        User user = UserDao.getUserById(id);
        user.setName(name);
//        user.setPassword(password);
        user.setGender(gender);
        user.setLocation(location);
        user.setBirthday(birthday);
        UserDao.save(user);
    }
}
