// package com.springboot.tradetrack.Utils;

// import com.springboot.tradetrack.Models.Role;
// import com.springboot.tradetrack.Dao.RoleDao;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// @Component
// public class DataInitializer implements CommandLineRunner {

//     private final RoleDao roleDao;

//     @Autowired
//     public DataInitializer(RoleDao roleDao) {
//         this.roleDao = roleDao;
//     }

//     @Override
//     public void run(String... args) throws Exception {
//         if (roleDao.findByName("USER").isEmpty()) {
//             roleDao.save(new Role("USER"));
//         }
//         if (roleDao.findByName("ADMIN").isEmpty()) {
//             roleDao.save(new Role("ADMIN"));
//         }
//     }
// }
