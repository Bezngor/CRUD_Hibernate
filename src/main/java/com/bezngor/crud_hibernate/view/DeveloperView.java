package com.bezngor.crud_hibernate.view;

import com.bezngor.crud_hibernate.controller.DeveloperController;
import com.bezngor.crud_hibernate.model.Skill;
import com.bezngor.crud_hibernate.repository.hibernate.JavaIODeveloperRepositoryImpl;
import com.bezngor.crud_hibernate.repository.hibernate.JavaIOSkillRepositoryImpl;
import com.bezngor.crud_hibernate.utils.Constants;
import com.bezngor.crud_hibernate.utils.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperView {
    static JavaIODeveloperRepositoryImpl developerRepo = new JavaIODeveloperRepositoryImpl();
    static DeveloperController devController = new DeveloperController(developerRepo);

    public void devViewStart() {
        System.out.printf(Constants.START_MESSAGE.getValue(), "Developer", Constants.EXIT.getValue());

        boolean isExist = false;
        String buf;
        Scanner scan = new Scanner(System.in);

        while (!isExist){
            System.out.println(Constants.INSERT_CODE_OPERATION.getValue());
            buf = scan.nextLine();
            switch (buf) {
                case "1":
                    System.out.println(Constants.INSERT_FIRSTNAME.getValue() + "Developer:");
                    String firstName1 = scan.nextLine();
                    System.out.println(Constants.INSERT_LASTNAME.getValue() + "Developer:");
                    String lastName1 = scan.nextLine();

                    System.out.printf(Constants.FOR_ADDING.getValue(), "Skill", Constants.END.getValue());
                    List<Skill> skills1 = new ArrayList<>();
                    boolean isNext1 = true;
                    while (isNext1) {
                        String str1 = scan.nextLine();
                        if (!str1.equals("end")) {
                            skills1.add(developerRepo.getSkillById(Integer.parseInt(str1)));
                            System.out.println(Constants.INSERT_ID_NEXT.getValue() + "Skill:");
                        } else isNext1 = false;
                    }

                    devController.create(firstName1, lastName1, skills1);
                    break;
                case "2":
                    System.out.println(Constants.INSERT_ID_UPDATING.getValue() + "Developer:");
                    Integer id2 = Integer.parseInt(scan.nextLine());
                    System.out.println(Constants.INSERT_NAME.getValue() + "Developer:");
                    String firstName2 = scan.nextLine();
                    System.out.println(Constants.INSERT_LASTNAME.getValue() + "Developer:");
                    String lastName2 = scan.nextLine();

                    System.out.printf(Constants.FOR_ADDING.getValue(), "Skill", Constants.END.getValue());
                    List<Skill> skills2 = new ArrayList<>();
                    boolean isNext2 = true;
                    while (isNext2) {
                        String str2 = scan.nextLine();
                        if (!str2.equals("end")) {
                            skills2.add(developerRepo.getSkillById(Integer.parseInt(str2)));
                            System.out.println(Constants.INSERT_ID_NEXT.getValue() + "Skill:");
                        } else isNext2 = false;
                    }

                    devController.update(id2, firstName2, lastName2, skills2);
                    break;
                case "3":
                    System.out.println(Constants.INSERT_ID_CALLING.getValue() + "Developer:");
                    Integer id3 = Integer.parseInt(scan.nextLine());
                    System.out.println(devController.getById(id3));
                    break;
                case "4":
                    devController.getAll().forEach(System.out::println);
                    break;
                case "5":
                    System.out.println(Constants.INSERT_ID_DELETING.getValue() + "Developer:");
                    Integer id5 = Integer.parseInt(scan.nextLine());
                    devController.deleteById(id5);
                    break;
                case "exit":
                    isExist = true;
                    break;
                default:
                    System.out.println(Constants.WRONG_CODE.getValue());
                    break;
            }
        }
    }
}
