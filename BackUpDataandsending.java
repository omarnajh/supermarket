/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marktingprograme.Gmail;

import java.awt.HeadlessException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author omar
 */
public class BackUpDataandsending {

    public BackUpDataandsending() {
       
    }
    

       
public  void Backupdbtosql() {
    try {

        /*NOTE: Getting path to the Jar file being executed*/
        /*NOTE: YourImplementingClass-> replace with the class executing the code*/
//        CodeSource codeSource = MainGUIMarktingController.class.getProtectionDomain().getCodeSource();
//        File jarFile = new File(codeSource.getLocation().toURI().getPath());
//        String jarDir = jarFile.getParentFile().getPath();


        /*NOTE: Creating Database Constraints*/
        String dbName = "SuperMarktting";
        String dbUser = "root";
        String dbPass = "1989";

        /*NOTE: Creating Path Constraints for folder saving*/
        /*NOTE: Here the backup folder is created for saving inside it*/
//        String folderPath = jarDir + "\\backup";
//
//        /*NOTE: Creating Folder if it does not exist*/
//        File f1 = new File(folderPath);
//        f1.mkdir();
        String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime());
        String subject = "Backup_"+timeStamp;
        System.out.println("Time ="+subject);
        /*NOTE: Creating Path Constraints for backup saving*/
        /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
         String savePath =  "E:/Data/backup/"+ subject+".sql";
        /*NOTE: Used to create a cmd command*/
        String executeCmd = "C:/Program Files/MariaDB 10.2/bin/mysqldump.exe -u" + dbUser + " -p" + dbPass + " --database " + dbName + " -r " + savePath;

        /*NOTE: Executing the command here*/
        Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
        int processComplete = runtimeProcess.waitFor();

        /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
        if (processComplete == 0) {
            System.out.println("Backup Complete");
            Gmailsender gmailsender = new Gmailsender(savePath);
            gmailsender.sendEmail();
        } else {
            System.out.println("Backup Failure");
             Gmailsender gmailsender = new Gmailsender(savePath);
            gmailsender.sendEmail();
        }

    } catch (IOException | InterruptedException ex) {
        JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
    }
   } 

public  void Restoredbfromsql() {
        try {
            /*NOTE: String s is the mysql file name including the .sql in its name*/
            /*NOTE: Getting path to the Jar file being executed*/
            /*NOTE: YourImplementingClass-> replace with the class executing the code*/
//            CodeSource codeSource = MainGUIMarktingController.class.getProtectionDomain().getCodeSource();
//            File jarFile = new File(codeSource.getLocation().toURI().getPath());
//            String jarDir = jarFile.getParentFile().getPath();

            /*NOTE: Creating Database Constraints*/
             String dbName = "SuperMarktting";
             String dbUser = "root";
             String dbPass = "1989";

            /*NOTE: Creating Path Constraints for restoring*/
            String restorePath =OpenFileimage();

            /*NOTE: Used to create a cmd command*/
            /*NOTE: Do not create a single large string, this will cause buffer locking, use string array*/
            String[] executeCmd = new String[]{"C:/Program Files/MariaDB 10.2/bin/mysql.exe", dbName, "-u" + dbUser, "-p" + dbPass, "-e", " source " + restorePath};

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "Successfully restored from SQL : " +restorePath);
            } else {
                JOptionPane.showMessageDialog(null, "Error at restoring");
            }


        } catch (IOException | InterruptedException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error at Restoredbfromsql" + ex.getMessage());
        }

    }
     private String OpenFileimage(){
                    FileChooser fileChooser = new FileChooser();
                     return  fileChooser.showOpenDialog(null).getAbsolutePath();
                     }
}

