package mftplus.controller;

import mftplus.model.entity.Lesson;
import mftplus.model.service.LessonService;

import java.time.LocalDate;
import java.util.List;

public class LessonController {
    private static LessonController controller = new LessonController();

    private LessonController() {

    };

    public void save(int id, int personId, String title, int code, String teacher, String unit, LocalDate startDateTime) throws Exception {
        try {
         Lesson lesson=Lesson
                 .builder()
                 .id(id)
                 .personId(personId)
                 .title(title)
                 .code(code)
                 .teacher(teacher)
                 .unit(unit)
                 .startDateTime(startDateTime)
                 .build();
         LessonService.getService().save(lesson);
            System.out.println("Lesson saved successfully");
        }catch(Exception e) {
            System.out.println("Lesson save Failed " + e.getMessage());
        }
    }

    public void edit(int id, int personId, String title, int code, String teacher, String unit, LocalDate startDateTime) throws Exception {
        try {
            Lesson lesson=Lesson
                    .builder()
                    .id(id)
                    .personId(personId)
                    .title(title)
                    .code(code)
                    .teacher(teacher)
                    .unit(unit)
                    .startDateTime(startDateTime)
                    .build();
            LessonService.getService().edit(lesson);
            System.out.println("Lesson Edited Successfully");
        }catch(Exception e) {
            System.out.println("Lesson Edit Failed " + e.getMessage());
        }
    }

    public void delete(Integer id) throws Exception {
        try {
            LessonService.getService().delete(id);
            System.out.println("Lesson Deleted Successfully");
        }catch(Exception e) {
            System.out.println("Lesson Delete Failed " + e.getMessage());
        }
    }

    public List<Lesson> findAll() throws Exception {
        try {
            List<Lesson> lessonList = LessonService.getService().findAll();
            System.out.println("Lessons Find All Successfully");
            return lessonList;
        }catch(Exception e) {
            System.out.println("Lesson Find all Failed " + e.getMessage());
            return null;
        }
    }

    public Lesson findById(Integer id) throws Exception {
        try {
            Lesson lesson = LessonService.getService().findById(id);
            System.out.println("Lessons FindById"+id);
            return lesson;
        }catch(Exception e) {
            System.out.println("Lesson FindBy"+id+ "Failed " + e.getMessage());
            return null;
        }
    }
}
