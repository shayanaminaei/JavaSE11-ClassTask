# JavaSE11-ClassTask

![Java](https://img.shields.io/badge/Java-SE11-blue?style=flat-square)
![Status](https://img.shields.io/badge/Status-Active-green?style=flat-square)
![Fork](https://img.shields.io/badge/Forked-Yes-orange?style=flat-square)
![Contributor](https://img.shields.io/badge/Contributor-Shayan%20Aminaei-blue?style=flat-square)

This repository is a **Java SE class project** demonstrating **Object-Oriented Programming (OOP)** concepts.  
It includes **entity management, repositories, and service layers**, implemented by multiple contributors.

---

## 🗂 Modules & Contributors

| Contributor | Module | Description | Key Functions | GitHub Link |
|------------|--------|------------|---------------|------------|
| Babak Oveisi | `educations` | Education records | `save`, `edit`, `delete`, `findAll` | - |
| Mohadese Saeedi | `jobs` | Job history | `save`, `edit`, `delete`, `findAll` | - |
| Zahra Moradi Nejhad | `driver_licenses` | Driver licenses | `save`, `edit`, `delete`, `findAll` | - |
| Saray Qorbani | `military_cards` | Military service cards | `save`, `edit`, `delete`, `findAll` | - |
| Alireza Nourahmadi | `marriages` | Marriage records | `save`, `edit`, `delete`, `findAll` | - |
| Tina Aghaei | `childes` | Child information | `save`, `edit`, `delete`, `findAll` | - |
| Samin Salehzadeh | `skills` | Skills & certificates | `save`, `edit`, `delete`, `findAll` | - |
| Mobina Mokhbery | `medicals` | Medical records | `save`, `edit`, `delete`, `findAll` | - |
| AmirSobhan Ghandizadeh | `salaries` | Salary information | `save`, `edit`, `delete`, `findAll` | - |
| Mobina Rahi | `sim_cards` | SIM card info | `save`, `edit`, `delete`, `findAll` | - |
| **+13/15Shayan Aminaei** | `cars` | Car information | `save`, `edit`, `delete`, `findAll`, `findByBrand` | [GitHub](https://github.com/shayanaminaei) |
| Seyed Arian Sadat | `properties` | Properties owned | `save`, `edit`, `delete`, `findAll` | - |
| Mahdiar Naseri | `accounts` | Bank accounts | `save`, `edit`, `delete`, `findAll` | - |
| Milad Jaafari | `contacts` | Contact info | `save`, `edit`, `delete`, `findAll` | - |

---

## ⚙️ Features

- **CRUD operations**: add, edit, delete, and query records in memory.  
- **Singleton Service pattern**: ensures consistent access to services.  
- **Lombok annotations**: reduces boilerplate code (`@Getter`, `@Setter`, `@Builder`).  
- **In-memory repositories**: all data is stored in lists before integrating with database.  
- **Example module (Cars)**: supports searching by ID, plate, and brand.

---

## 🔗 Useful Links

- [Original Project](https://github.com/AhmadMessbah/JavaSE_346252) – Forked source  
- [Cars Module Entity](src/main/java/mftplus/model/entity/Cars.java)  
- [Cars Service](src/main/java/mftplus/model/service/CarsService.java)  
- [Cars Repository](src/main/java/mftplus/model/repository/CarsRepository.java)  
- [Cars Test](src/test/java/CarsTest.java)  

---

**Forked from:** [JavaSE_346252](https://github.com/AhmadMessbah/JavaSE_346252)  
**Contributor:** [Shayan Aminaei](https://github.com/shayanaminaei)
