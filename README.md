- OK babak oveisi : educations (id, person_id, university, grade, average, start_date, end_date)    

- OK mohadese saeedi : jobs (id, person_id, organisation, job_title, start_date, end_date, description)

- -5 zahra moradi nejhad : driver_licenses (id, person_id, serial, license_type, city, register_date, expire_date)
 (dao/repository)

- -5 saray qorbani : military_cards : (id, person_id, card_serial, license_type, city, organisation, duration)

- -1 alireza nourahmadi : marriages (id, person_id, name, family, marriage_date, is_alive, childes)

- -5 tina aghaei : childes (id, person_id, name, family, birth_date, is_alive, status)
  (controller)

- OK samin salehzadeh : skills (id ,person_id, title, institute, duration, register_date, score)

- OK mobina mokhbery : medicals (id, person_id, disease, medicine, doctor, visit_date, status)

- OK amirsobhan ghandizadeh:  : salary (id, person_id, weekly_hour, pay_per_hour, start_date, end_date, employment_type)

- OK mobina rahi : sim_cards (id, person_id, title, number, operator, register_date, status)

- -1 shayan aminaei : cars (id, person_id, name, brand, man_date, color, plate)

- OK seyed arian sadat : properties (id, person_id, name, brand, serial, count, date_time)

- mahdiar naseri : accounts (id, person_id, bank, branch, account_id, card_number, register_date)

- -5 milad jaafari : contacts (id, person_id, title, contact_id, contact_type, description, status)
  (polymorphism)

- -5 romina mirfatahi : lessons (id, person_id, title, code, teacher, unit, start_date_time)
  (POJO/entity/bean)

- -5 hedieh khodaei : user (id, person_id, username, password, nickName, locked, register_date)