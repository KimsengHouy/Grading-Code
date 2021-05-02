package com.students;

import java.io.*;

import java.util.ArrayList;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Grading {

    public static void main(String[] args) throws FileNotFoundException {

        int num = 0;
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("Select the number you want to do: (type 0 to exit)");
            System.out.println("1. Enter grade for a subject");
            System.out.println("2. Edit grade for a subject");
            System.out.println("3. Create a new subject");
            System.out.println("4. Delete a subject");
            num = in.nextInt();
            in.nextLine();

            if (num == 1) {
                enterGrade();
            } else if (num == 2) {
                editGrade();
            } else if (num == 3) {
                createSubject();
            } else if (num == 4) {
                deleteSubject();
            }
        } while (num != 0);
    }

    static String[] listStudent(String subjectName) throws FileNotFoundException {
        Scanner in =new Scanner(System.in);
        File f = new File("src/com/students/grades.txt");

        File tempFile = new File ("src/com/students/myTempFile.txt");
        PrintStream ps = new PrintStream(
                new FileOutputStream(tempFile, true));
        ArrayList<String> studentList = new ArrayList<String>();
        Scanner read = new Scanner(f);
        int i = 1;
        while (read.hasNext()) {
            String line = read.nextLine();
            String[] words = line.split(" ");
            System.out.println((i) + ". " + words[1]);
            studentList.add(words[1]);
            i++;
        }
//        System.out.println(studentList.toString());
        int studentName;
        studentName = in.nextInt();
        Scanner read2 = new Scanner(f);

        while (read2.hasNext()) {
            String line = read2.nextLine();
            String[] words = line.split(" ");
            if (studentList.get(studentName-1).equals(words[1])  &&  subjectName.equals(words[0])){
                return words;
            }
        }
        ps.close();
        read.close();
        read2.close();

        String[] jjjj = new String[50];
        return jjjj;


    }
    static void editGrade (String[] newStudentInfo) throws FileNotFoundException {
        File f = new File ("src/com/students/grades.txt");
        File tempFile = new File ("src/com/students/myTempFile2.txt");
        Scanner read3 = new Scanner(f);
        PrintStream sp = new PrintStream(
                new FileOutputStream(tempFile,true));
        while (read3.hasNextLine()) {
            String line = read3.nextLine();
            String[] words = line.split(" ");
//            System.out.println("isStudent: "+(words[0].equals(newStudentInfo[0]) && words[1].equals(newStudentInfo[1])));
            if (words[0].equals(newStudentInfo[0]) && words[1].equals(newStudentInfo[1])){
                for (int i = 0; i <newStudentInfo.length; i++){
                    sp.print(newStudentInfo[i]+" ");
                }
                sp.println("");
            }else {sp.println(line);}
        }
        read3.close();
        sp.close();
        boolean isDeleted = f.delete();
        boolean success = tempFile.renameTo(f);

    }











    static void createGrade(String subjectName, String studentName, double[] grade, File f ) {
        try {
            PrintStream sp = new PrintStream(
                    new FileOutputStream(f, true));

            sp.print (subjectName + " ");
            sp.print (studentName + " ");

            for (int i = 0; i <= 7; i++) {
                sp.print(grade[i] + " ");
            }
            sp.print(" \n");
            sp.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    static void deleteSubject(String subjectName) throws FileNotFoundException {
        File f = new File("src/com/students/subjects.txt");
        File tempFile = new File ("src/com/students/myTempFile.txt");
        PrintStream ps = new PrintStream(
                new FileOutputStream(tempFile,true));

        Scanner read = new Scanner(f);
        String temp;
        while (read.hasNextLine()) {
            String line = read.nextLine();
            Scanner lineScan = new Scanner(line);
            temp = lineScan.next();
            System.out.println(temp.equals(subjectName));
            if (temp.equals(subjectName)) continue;
            ps.println(line);
            lineScan.close();

        }
        ps.close();
        read.close();
        boolean isDeleted =  f.delete();
        boolean success = tempFile.renameTo(f);
    }

    static String[] listSubject (File f) throws FileNotFoundException {
        String[] subjects = new String[50];
        int i = 0;
        Scanner read = new Scanner(f);
        while (read.hasNextLine()) {
            String line = read.nextLine();
            Scanner lineScan = new Scanner(line);
            subjects[i] = lineScan.next();
            i+=1;
            lineScan.close();

        }

        read.close();
        return subjects;
    }





    static void createSubject(String subjectName, String[] cats, int[] weight, File f) {
        try {
            PrintStream ps = new PrintStream(
                    new FileOutputStream(f, true));
            for (int i = 0; i < 10; i++) {
                ps.print(subjectName + " " );
                ps.print(cats[i] + " ");
                ps.print(weight[i] + " ");
            }
            ps.print(" \n");
            ps.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }




    private static void deleteSubject() throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        File file = new File("src/com/students/subjects.txt");
        String[] subjects;
        subjects = listSubject(file);
        System.out.println("Select the subject you would like to delete: ");
        for ( int i = 0; i < subjects.length; i++ ) {
            try {
                if ( subjects[i] != null )
                    System.out.println((i + 1) + ". " + subjects[i]);
                else if(subjects[0] == null){
                    System.out.println("There is no subject available. Please create some first: ");
                    createSubject();
                }
            }
            catch (NullPointerException e) {
                System.err.println(e);
            }
        }
        int deleteChoice;
        deleteChoice = in.nextInt();
        in.nextLine();
        System.out.println(subjects[deleteChoice-1]);
        System.out.println("Please Enter \"DELETE\" ");
        String Delete;
        Delete = in.nextLine();
        deleteSubject(subjects[deleteChoice-1]);
        System.out.println(deleteChoice + " is delete successfully");
    }

    private static void editGrade() throws FileNotFoundException {
        String [] newStudentInfo = new String [10];
        Scanner in = new Scanner(System.in);
        File file = new File("src/com/students/subjects.txt");
        String[] subjects;
        subjects = listSubject(file);
        System.out.println("Choose the subject: ");
        for (int i = 0; i < subjects.length; i++) {
            try {
                if (subjects[i] != null)
                    System.out.println((i + 1) + ". " + subjects[i]);
                else if(subjects[0] == null) {
                    System.out.println("There is no subject available. Please create some first: ");
                    createSubject();
                }
            }
            catch (NullPointerException e) {
                System.err.println(e);
            }
        }
        int choose = 0;
        choose = in.nextInt();

        System.out.println("Choose the student: ");
        String[] studentInfo = listStudent(subjects[choose-1]);
        newStudentInfo[0] = studentInfo[0];
        newStudentInfo[1] = studentInfo[1];


        System.out.println(studentInfo.toString());
        System.out.println(studentInfo[2]);

        System.out.println("Choose which you want to change: ");
        System.out.println("1. All");
        System.out.println("2. Attendance");
        System.out.println("3. Assignments");
        System.out.println("4. Midterm");
        System.out.println("5. LabExam");
        int change = 0;
        change = in.nextInt();
        in.nextLine();



        if (change == 1) {

            System.out.println("Enter the new Attendance Grade: ");
            System.out.println("Previous: "+ (studentInfo[2]) + " New: "  );
            newStudentInfo[2] = in.nextLine();
            System.out.println("Enter the new Assignment1 Grade: ");
            System.out.println("Previous: " + studentInfo[4] + " New: ");
            newStudentInfo[4] = in.nextLine();
            System.out.println("Enter the new Assignment2 Grade: ");
            System.out.println("Previous: " + studentInfo[5] + " New: ");
            newStudentInfo[5] = in.nextLine();
            System.out.println("Enter the new Midterm Grade: ");
            System.out.println("Previous: " + studentInfo[7] + " New: ");
            newStudentInfo[7] = in.nextLine();
            System.out.println("Enter the new LabExam: ");
            System.out.println("Previous: " + studentInfo[8] + " New: ");
            newStudentInfo[8] = in.nextLine();

            double Assgn1 = Double.parseDouble(newStudentInfo[4]);
            double Assgn2 = Double.parseDouble(newStudentInfo[5]);
            double Assgn12 = Assgn1 + Assgn2 / 15 * 20;
            double Final = Double.parseDouble(newStudentInfo[2]) + Assgn12 + Double.parseDouble(newStudentInfo[7]) + Double.parseDouble(newStudentInfo[8]);

            char FinalG = ' ';
            if (Final >= 90) {
                FinalG = 'A';
            } else if (Final >= 80) {
                FinalG = 'B';
            } else if (Final >= 70) {
                FinalG = 'C';
            } else if (Final >= 60) {
                FinalG = 'D';
            } else {
                FinalG = 'F';
            }

            System.out.println();
            System.out.println(newStudentInfo[1] + " got the following grade: ");
            System.out.println("Attendance: " + newStudentInfo[2]);
            System.out.println("Midterm: " + newStudentInfo[7]);
            System.out.println("Lab Exam: " + newStudentInfo[8]);
            System.out.println("Final Grade: " + Final);
            System.out.println("Final Letter Grade: " + FinalG);


        } else {


            if (change == 2) {
                System.out.println("Enter the new Attendance Grade: ");
                System.out.println("Previous: " + studentInfo[2] + " New: ");
                newStudentInfo[2] = in.nextLine();
                double Assgn1 = Double.parseDouble(newStudentInfo[4]);
                double Assgn2 = Double.parseDouble(newStudentInfo[5]);
                double Assgn12 = Assgn1 + Assgn2 / 15 * 20;
                double Final = Double.parseDouble(newStudentInfo[2]) + Assgn12 + Double.parseDouble(newStudentInfo[7]) + Double.parseDouble(newStudentInfo[8]);



                char FinalG = ' ';
                if (Final >= 90) {
                    FinalG = 'A';
                } else if (Final >= 80) {
                    FinalG = 'B';
                } else if (Final >= 70) {
                    FinalG = 'C';
                } else if (Final >= 60) {
                    FinalG = 'D';
                } else {
                    FinalG = 'F';
                }

                System.out.println();
                System.out.println(newStudentInfo[1] + " got the following grade: ");
                System.out.println("Attendance: " + newStudentInfo[2]);
                System.out.println("Midterm: " + newStudentInfo[7]);
                System.out.println("Lab Exam: " + newStudentInfo[8]);
                System.out.println("Final Grade: " + Final);
                System.out.println("Final Letter Grade: " + FinalG);












            } else if (change == 3) {
                System.out.println("Enter the new Assignment1 Grade: ");
                System.out.println("Previous: " + studentInfo[3] + " New: ");
                newStudentInfo[3] = in.nextLine();
                System.out.println("Enter the new Assignment 2 Grade: ");
                System.out.println("Previous: " + studentInfo[4] + " New: ");
                newStudentInfo[4] = in.nextLine();

                double Assgn1 = Double.parseDouble(newStudentInfo[4]);
                double Assgn2 = Double.parseDouble(newStudentInfo[5]);
                double Assgn12 = Assgn1 + Assgn2 / 15 * 20;
                double Final = Double.parseDouble(newStudentInfo[2]) + Assgn12 + Double.parseDouble(newStudentInfo[7]) + Double.parseDouble(newStudentInfo[8]);



                char FinalG = ' ';
                if (Final >= 90) {
                    FinalG = 'A';
                } else if (Final >= 80) {
                    FinalG = 'B';
                } else if (Final >= 70) {
                    FinalG = 'C';
                } else if (Final >= 60) {
                    FinalG = 'D';
                } else {
                    FinalG = 'F';
                }

                System.out.println();
                System.out.println(newStudentInfo[1] + " got the following grade: ");
                System.out.println("Attendance: " + newStudentInfo[2]);
                System.out.println("Midterm: " + newStudentInfo[7]);
                System.out.println("Lab Exam: " + newStudentInfo[8]);
                System.out.println("Final Grade: " + Final);
                System.out.println("Final Letter Grade: " + FinalG);



            } else if (change == 4) {
                System.out.println("Enter the new Midterm Grade:  ");
                System.out.println("Previous: " + studentInfo[5] + " New: ");
                newStudentInfo[5] = in.nextLine();

                double Assgn1 = Double.parseDouble(newStudentInfo[4]);
                double Assgn2 = Double.parseDouble(newStudentInfo[5]);
                double Assgn12 = Assgn1 + Assgn2 / 15 * 20;
                double Final = Double.parseDouble(newStudentInfo[2]) + Assgn12 + Double.parseDouble(newStudentInfo[7]) + Double.parseDouble(newStudentInfo[8]);




                char FinalG = ' ';
                if (Final >= 90) {
                    FinalG = 'A';
                } else if (Final >= 80) {
                    FinalG = 'B';
                } else if (Final >= 70) {
                    FinalG = 'C';
                } else if (Final >= 60) {
                    FinalG = 'D';
                } else {
                    FinalG = 'F';
                }

                System.out.println();
                System.out.println(newStudentInfo[1] + " got the following grade: ");
                System.out.println("Attendance: " + newStudentInfo[2]);
                System.out.println("Midterm: " + newStudentInfo[7]);
                System.out.println("Lab Exam: " + newStudentInfo[8]);
                System.out.println("Final Grade: " + Final);
                System.out.println("Final Letter Grade: " + FinalG);




            } else if (change == 5) {
                System.out.println("Enter the new LabExam Grade: ");
                System.out.println("Previous: " + studentInfo[6] + " New: ");
                newStudentInfo[6] = in.nextLine();

                double Assgn1 = Double.parseDouble(newStudentInfo[4]);
                double Assgn2 = Double.parseDouble(newStudentInfo[5]);
                double Assgn12 = Assgn1 + Assgn2 / 15 * 20;
                double Final = Double.parseDouble(newStudentInfo[2]) + Assgn12 + Double.parseDouble(newStudentInfo[7]) + Double.parseDouble(newStudentInfo[8]);


                char FinalG = ' ';
                if (Final >= 90) {
                    FinalG = 'A';
                } else if (Final >= 80) {
                    FinalG = 'B';
                } else if (Final >= 70) {
                    FinalG = 'C';
                } else if (Final >= 60) {
                    FinalG = 'D';
                } else {
                    FinalG = 'F';
                }

                System.out.println();
                System.out.println(newStudentInfo[1] + " got the following grade: ");
                System.out.println("Attendance: " + newStudentInfo[2]);
                System.out.println("Midterm: " + newStudentInfo[7]);
                System.out.println("Lab Exam: " + newStudentInfo[8]);
                System.out.println("Final Grade: " + Final);
                System.out.println("Final Letter Grade: " + FinalG);






            }
        }
        editGrade(newStudentInfo);

    }



    private static void enterGrade() throws FileNotFoundException {
        double[] grade = new double[10];
        Scanner in = new Scanner(System.in);
        File file = new File("src/com/students/subjects.txt");
        File newgrade = new File("src/com/students/grades.txt");
        String[] subjects;
        subjects = listSubject(file);
        System.out.println("Choose the subject: " );
        for (int i = 0; i < subjects.length; i++) {
            try {
                if (subjects[i] != null)
                    System.out.println((i + 1) + ". " + subjects[i]);
                else if (subjects[0] == null) {
                    System.out.println("There is no subject available. Please create some first: ");
                    createSubject();
                }

            }
            catch (NullPointerException e) {
                System.err.println(e);
            }
        }

        int chooseSubject = 0;
        chooseSubject = in.nextInt();
        in.nextLine();

        System.out.println("Enter the name of the student: ");
        String name = in.nextLine();
        System.out.println("Enter the attendance: (out of 10)");
        try {
            grade[0] = in.nextDouble();
            if (grade[0] > 10) {
                System.out.println("Error! Please input the range of 0 to 10");
                System.out.println("Enter the attendance: (out of 10) ");
                grade[0] = in.nextDouble();
            }
        }catch(InputMismatchException e) {
            System.err.println();
            System.out.println("Enter number only!!: ");
            grade[0] = in.nextDouble();


        }


        System.out.println("Enter the assignment 1: (out of 10) ");
            grade[3] = in.nextDouble();
            if (grade[3] > 10) {
                System.out.println("Error! Please input the range of 0 to 10");
                System.out.println("Enter the assignment 1: (out of 10)");
                grade[3] = in.nextDouble();
            }

        System.out.println("Enter the assignment 2: (out of 5) ");
            grade[4] = in.nextDouble();
            if (grade[4] > 5) {
                System.out.println("Error! Please input the range of 0 to 5");
                System.out.println("Enter the assignment 2: (out of 5)");
                grade[4] = in.nextDouble();
            }



        System.out.println("Enter the quiz1: (out of 5) ");
            grade[1] = in.nextDouble();

            if (grade[1] > 5) {
                System.out.println("Error! Please input the range of 0 to 5");
                System.out.println("Enter the quiz1: (out of 5)");
                grade[1] = in.nextDouble();
            }


        System.out.println("Enter the Midterm Exam: (out of 46)");
            grade[5] = in.nextDouble();
            if (grade[5] > 46) {
                System.out.println("Error! Please input the range of 0 to 46");
                System.out.println("Enter the Midterm Exam: (out of 46)");
                grade[5] = in.nextDouble();
            }


        System.out.println("Enter the Lab Exam: (out of 32)");
            grade[6] = in.nextDouble();

            if (grade[6] > 32) {
                System.out.println("Error! Please input the range of 0 to 32");
                System.out.println("Enter the Lab Exam: (out of 32)");
                grade[6] = in.nextDouble();
            }



        double Assgn12 = ((grade[3] + grade[4]) / (10 + 5) * 20);
        double Final = grade[0] + Assgn12 + grade[1] + grade[5] + grade[6];
        char FinalG = ' ';
        if (Final >= 90) {
            FinalG = 'A';
        } else if (Final >= 80) {
            FinalG = 'B';
        } else if (Final >= 70) {
            FinalG = 'C';
        } else if (Final >= 60) {
            FinalG = 'D';
        } else {
            FinalG = 'F';
        }

        System.out.println();
        System.out.println(name + " got the following grade: ");
        System.out.println("Attendance: " + grade[0]);
        System.out.println("Assignment: " + Assgn12);
        System.out.println("Quiz: " + grade[1]);
        System.out.println("Midterm: " + grade[5]);
        System.out.println("Lab Exam: " + grade[6]);
        System.out.println("Final Grade: " + Final);
        System.out.println("Final Letter Grade: " + FinalG);

        createGrade(subjects[chooseSubject - 1], name, grade, newgrade);

    }


    private static void createSubject(){
        File file = new File("src/com/students/subjects.txt");
        Scanner in = new Scanner(System.in);
        String subject;
        int numOfCat;
        String[] cat = {"temp", "temp", "temp", "temp", "temp", "temp", "temp", "temp", "temp", "temp"};
        int[] catWeights = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        System.out.println("Enter the subject name: ");
        subject = in.nextLine();
        System.out.println("How many categories of grade you want: ");
        numOfCat = in.nextInt();
        in.nextLine();
        for (int i = 0; i < numOfCat; i++) {
            if (i == 0)
                System.out.println("Enter the name of the " + ((i + 1) + "st") + " category");
            else if (i == 1){
                System.out.println("Enter the name of the " + ((i+1) + "nd" + " categories"));
            } else if (i >= 2) {
                System.out.println("Enter the name of the " + ((i+1) + "th" + " categories"));
            }


            cat[i] = in.nextLine();
            System.out.println("Enter the weight of this category: ");
            catWeights[i] = in.nextInt();
            in.nextLine();

        }

        System.out.println("Name: " + subject);
        System.out.println("Num Of Category: " + numOfCat);
        for (int i = 0; i < cat.length; i++) {
            System.out.println("Category: " + cat[i]);
            System.out.println("Weight: " + catWeights[i]);
        }

        createSubject(subject, cat, catWeights, file);


    }

}
