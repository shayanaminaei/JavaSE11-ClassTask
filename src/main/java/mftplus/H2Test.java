package mftplus;

import mftplus.controller.EducationController;
import mftplus.model.entity.Education;
import mftplus.model.service.EducationService;
import mftplus.model.service.PersonService;
import mftplus.model.tools.ConnectionProvider;
import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.SQLException;

public class H2Test {
    public static void main(String[] args) throws Exception {
//        Server webServer = Server.createWebServer("-webAllowOthers", "-webPort", "8080");;
        Server webServer = Server.createWebServer("-web","-webAllowOthers", "-webPort", "8082");;
//
        Connection connection = ConnectionProvider.getProvider().getH2Connection();
//
//
//
        connection.close();
//        System.out.println(EducationService.getService().findAll().get(0).getPerson().getName());

        System.out.println(EducationService.getService().findByPersonId(1));

    }
}
