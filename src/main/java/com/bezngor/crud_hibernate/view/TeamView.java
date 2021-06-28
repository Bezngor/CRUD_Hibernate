package com.bezngor.crud_hibernate.view;

import com.bezngor.crud_hibernate.controller.TeamController;
import com.bezngor.crud_hibernate.model.Developer;
import com.bezngor.crud_hibernate.model.Skill;
import com.bezngor.crud_hibernate.model.TeamStatus;
import com.bezngor.crud_hibernate.repository.hibernate.JavaIOTeamRepositoryImpl;
import com.bezngor.crud_hibernate.utils.Constants;
import com.bezngor.crud_hibernate.utils.HibernateUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TeamView {
    static TeamController teamController = new TeamController(new JavaIOTeamRepositoryImpl());

    public void teamViewStart() {
        System.out.printf(Constants.START_MESSAGE.getValue(), "Team", Constants.EXIT.getValue());

        boolean isExist = false;
        String buf;
        Scanner scan = new Scanner(System.in);

        while (!isExist) {
            System.out.println(Constants.INSERT_CODE_OPERATION.getValue());
            buf = scan.nextLine();
            switch (buf) {
                case "1":
                    System.out.println(Constants.INSERT_NAME.getValue() + "Team:");
                    String name1 = scan.nextLine();
                    System.out.printf(Constants.INSERT_STATUS.getValue(), TeamStatus.ACTIVE, TeamStatus.UNDER_REVIEW, TeamStatus.DELETED);
                    String statusId1 = scan.nextLine();
                    TeamStatus status1 = HibernateUtil.getStatusTeam(statusId1);
                    System.out.printf(Constants.FOR_ADDING.getValue(), "Developer", Constants.END.getValue());
                    List<Developer> devs1 = new ArrayList<>();
                    boolean isNext1 = true;
                    while (isNext1) {
                        String str1 = scan.nextLine();
                        if (!str1.equals(Constants.END.getValue())) {
                            devs1.add(HibernateUtil.getDeveloperById(Integer.parseInt(str1)));
                            System.out.println(Constants.INSERT_ID_NEXT.getValue() + "Developer:");
                        } else isNext1 = false;
                    }

                    teamController.create(name1, status1, devs1);
                    break;
                case "2":
                    System.out.println(Constants.INSERT_ID_UPDATING.getValue() + "Team:");
                    Integer id2 = Integer.parseInt(scan.nextLine());
                    System.out.println(Constants.INSERT_NAME.getValue() + "Team:");
                    String name2 = scan.nextLine();
                    System.out.printf(Constants.INSERT_STATUS.getValue(), TeamStatus.ACTIVE, TeamStatus.UNDER_REVIEW, TeamStatus.DELETED);
                    String statusId2 = scan.nextLine();
                    TeamStatus status2 = HibernateUtil.getStatusTeam(statusId2);
                    System.out.printf(Constants.FOR_ADDING.getValue(), "Developer", Constants.END.getValue());
                    List<Developer> devs2 = new ArrayList<>();
                    boolean isNext2 = true;
                    while (isNext2) {
                        String str2 = scan.nextLine();
                        if (!str2.equals(Constants.END.getValue())) {
                            devs2.add(HibernateUtil.getDeveloperById(Integer.parseInt(str2)));
                            System.out.println(Constants.INSERT_ID_NEXT.getValue() + "Developer:");
                        } else isNext2 = false;
                    }
                    teamController.update(id2, name2, status2, devs2);
                    break;
                case "3":
                    System.out.println(Constants.INSERT_ID_CALLING.getValue() + "Team:");
                    Integer id3 = Integer.parseInt(scan.nextLine());
                    System.out.println(teamController.getById(id3));
                    break;
                case "4":
                    teamController.getAll().forEach(System.out::println);
                    break;
                case "5":
                    System.out.println(Constants.INSERT_ID_DELETING.getValue() + "Team:");
                    Integer id5 = Integer.parseInt(scan.nextLine());
                    teamController.deleteById(id5);
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

/*    TeamStatus getStatusTeam(String statusId) {
        return Arrays.stream(TeamStatus.values())
                .filter(v -> v.getValue() == Integer.parseInt(statusId))
                .findFirst()
                .orElse(null);
    }*/
}
