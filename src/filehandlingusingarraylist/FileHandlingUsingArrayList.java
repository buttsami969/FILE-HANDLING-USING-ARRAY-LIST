/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filehandlingusingarraylist;

import java.io.*;
import java.util.*;

/**
 *
 * @author sami
 */
public class FileHandlingUsingArrayList implements Serializable{

   private static final long serialVersionUID = -5336739634909585247L;
    private int empno;
    private String empname;
    private String empemail;
    private double empsalary;

    public FileHandlingUsingArrayList() {
        System.out.println("-----------------------------------------------");
        System.out.println("        CRUD OPERATION USING ARRAY LIST        ");
        System.out.println("-----------------------------------------------");
    }

    public FileHandlingUsingArrayList(int empno, String empname, double empsalary, String empemail) {
        this.empno = empno;
        this.empname = empname;
        this.empsalary = empsalary;
        this.empemail = empemail;
    }

    public int getEmpno() {
        return empno;
    }

    public String toString() {
        return "   " + empno + "                   " + empname + "                    " + empsalary + "                    " + empemail;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        ArrayList<FileHandlingUsingArrayList> e = new ArrayList<FileHandlingUsingArrayList>();
        FileHandlingUsingArrayList F = new FileHandlingUsingArrayList();

        File file = new File("employee.txt");

        ObjectOutputStream oss = null;
        ObjectInputStream os = null;

        int ch, empno;
        String empname, empemail;
        double empsal;
        boolean found = false;

        do {
            System.out.println("");
            System.out.println("--------------------------------");
            System.out.println("1.CREATE");
            System.out.println("2.READ");
            System.out.println("3.UPDATED");
            System.out.println("4.DELETE");
            System.out.println("5.SEARCH");
            System.out.println("6. SORT  EMPNO     ON THE SCREEN");
            System.out.println("7. SORT  EMPNO     ON THE FILE");
            System.out.println("8. SORT  EMPNAME   ON THE SCREEN");
            System.out.println("9. SORT  EMPNAME   ON THE FILE");
            System.out.println("10.SORT  EMPSALARY ON THE SCREEN");
            System.out.println("11.SORT  EMPSALRAY ON THE FILE");
            System.out.println("----------------------------------");
            System.out.println("");
            System.out.println("------------------");
            System.out.println("ENTER YOUR CHOICE:");
            System.out.println("------------------");
            ch = sc.nextInt();
            System.out.println("");
            switch (ch) {
                case 1:
                    System.out.println("-------------");
                    System.out.println("ENTER EMP ID:");
                    System.out.println("-------------");
                    empno = sc.nextInt();
                    System.out.println("---------------");
                    System.out.println("ENTER EMP NAME:");
                    System.out.println("---------------");
                    empname = sc.next();
                    System.out.println("-----------------");
                    System.out.println("ENTER EMP SALARY:");
                    System.out.println("-----------------");
                    empsal = sc.nextInt();
                    System.out.println("----------------");
                    System.out.println("ENTER EMP EMAIL:");
                    System.out.println("----------------");
                    empemail = sc.next();
                    e.add(new FileHandlingUsingArrayList(empno, empname, empsal, empemail));
                    oss = new ObjectOutputStream(new FileOutputStream(file));
                    oss.writeObject(e);
                    oss.close();
                    break;
                case 2:
                    if (file.isFile()) {
                        os = new ObjectInputStream(new FileInputStream(file));
                        e = (ArrayList<FileHandlingUsingArrayList>)os.readObject();
                        os.close();
                        System.out.println("");
                        Iterator i = e.iterator();
                        while (i.hasNext()) {
                            FileHandlingUsingArrayList e1 = (FileHandlingUsingArrayList) i.next();
                            System.out.println("-----------------------------------------------------------------------------------------------------");
                            System.out.println("|Employee No|       " + "|Employee Name|       " + "|Empolyee Salary|           " + "|Employee Email|      ");
                            System.out.println(e1);
                            System.out.println("-----------------------------------------------------------------------------------------------------");

                        }
                    } else {
                        System.out.println("File Not Exist...!");
                    }
                    System.out.println("");
                    break;

                case 3:
                    if (file.isFile()) {
                        os = new ObjectInputStream(new FileInputStream(file));
                        e = (ArrayList<FileHandlingUsingArrayList>) os.readObject();
                        System.out.println("");
                        System.out.println("-----------------------");
                        System.out.println("Enter EMP ID TO UPDATED");
                        System.out.println("-----------------------");
                        empno = sc.nextInt();
                        System.out.println("------------------------");
                        ListIterator<FileHandlingUsingArrayList> li = e.listIterator();
                        while (li.hasNext()) {
                            FileHandlingUsingArrayList e1 = li.next();
                            if (e1.getEmpno() == empno) {
                                System.out.println("");
                                System.out.println("-------------------");
                                System.out.println("ENTER NEW EMP NAME:");
                                System.out.println("-------------------");
                                empname = sc.next();
                                System.out.println("---------------------");
                                System.out.println("ENTER NEW EMP SALARY:");
                                System.out.println("---------------------");
                                empsal = sc.nextDouble();
                                System.out.println("--------------------");
                                System.out.println("ENTER NEW EMP EMAIL:");
                                System.out.println("--------------------");
                                empemail = sc.next();
                                li.set(new FileHandlingUsingArrayList(empno, empname, empsal, empemail));
                                found = true;
                            }

                        }
                        if (found) {
                            oss = new ObjectOutputStream(new FileOutputStream(file));
                            oss.writeObject(e);
                            oss.close();
                            System.out.println("------------------------------");
                            System.out.println("RECORD IS UPDATED SUCESSFULLY!");
                            System.out.println("------------------------------");

                        } else {
                            System.out.println("-----------------");
                            System.out.println("RECORD NOT FOUND!");
                            System.out.println("-----------------");

                        }
                    } else {
                        System.out.println("File  Not Exist...!");
                    }
                    System.out.println("");
                    break;
                case 4:
                    if (file.isFile()) {
                        os = new ObjectInputStream(new FileInputStream(file));
                        e = (ArrayList<FileHandlingUsingArrayList>) os.readObject();
                        System.out.println("");
                        System.out.println("-----------------------");
                        System.out.println("Enter EMP ID TO DELETED");
                        System.out.println("-----------------------");
                        empno = sc.nextInt();
                        Iterator<FileHandlingUsingArrayList> i2 = e.iterator();
                        while (i2.hasNext()) {
                            FileHandlingUsingArrayList e1 = i2.next();
                            if (e1.getEmpno() == empno) {
                                i2.remove();
                                found = true;
                            }

                        }
                        if (found) {
                            oss = new ObjectOutputStream(new FileOutputStream(file));
                            oss.writeObject(e);
                            oss.close();
                            System.out.println("-------------------------------");
                            System.out.println("RECORD IS DELELTED SUCESSFULLY!");
                            System.out.println("-------------------------------");

                        } else {
                            System.out.println("-----------------");
                            System.out.println("RECORD NOT FOUND!");
                            System.out.println("-----------------");

                        }
                    } else {
                        System.out.println("File  Not Exist...!");
                    }
                    System.out.println("");
                    break;
                case 5:
                    if (file.isFile()) {
                        os = new ObjectInputStream(new FileInputStream(file));
                        e = (ArrayList<FileHandlingUsingArrayList>) os.readObject();
                        os.close();
                        System.out.println("");
                        System.out.println("----------------------");
                        System.out.println("Enter EMP ID TO SEARCH");
                        System.out.println("----------------------");
                        empno = sc.nextInt();
                        System.out.println("");
                        Iterator<FileHandlingUsingArrayList> i3 = e.iterator();
                        while (i3.hasNext()) {
                            FileHandlingUsingArrayList e1 = i3.next();
                            if (e1.getEmpno() == empno) {
                                System.out.println("-----------------------------------------------------------------------------------------------------");
                                System.out.println("|Employee No|       " + "|Employee Name|       " + "|Empolyee Salary|           " + "|Employee Email|      ");
                                System.out.println(e1);
                                System.out.println("-----------------------------------------------------------------------------------------------------");

                            }
                            found = true;
                        }
                        if (!found) {

                            System.out.println("-----------------");
                            System.out.println("RECORD NOT FOUND!");
                            System.out.println("-----------------");
                        } else {
                            System.out.println("-----------------------------");
                            System.out.println("RECORD IS SEARCH SUCESSFULLY!");
                            System.out.println("-----------------------------");
                        }
                    } else {
                        System.out.println("File Not Exits....!");
                    }
                    System.out.println("");
                    break;
                case 6:
                    if (file.isFile()) {
                        os = new ObjectInputStream(new FileInputStream(file));
                        e = (ArrayList<FileHandlingUsingArrayList>) os.readObject();
                        os.close();
                        Collections.sort(e, new Comparator<FileHandlingUsingArrayList>() {
                            @Override
                            public int compare(FileHandlingUsingArrayList o1, FileHandlingUsingArrayList o2) {
                                return o1.empno - o2.empno;
                            }

                        });
                        System.out.println("");
                        Iterator<FileHandlingUsingArrayList> i3 = e.iterator();
                        while (i3.hasNext()) {
                            FileHandlingUsingArrayList e1 = i3.next();
                            System.out.println("-----------------------------------------------------------------------------------------------------");
                            System.out.println("|Employee No|       " + "|Employee Name|       " + "|Empolyee Salary|           " + "|Employee Email|      ");
                            System.out.println(e1);
                            System.out.println("-----------------------------------------------------------------------------------------------------");
                            found = true;
                        }
                        if (!found) {

                            System.out.println("-----------------");
                            System.out.println("RECORD NOT FOUND!");
                            System.out.println("-----------------");
                        } else {
                            System.out.println("-----------------------------");
                            System.out.println("RECORD IS SORTED!");
                            System.out.println("-----------------------------");
                        }
                    } else {
                        System.out.println("File Not Exits....!");
                    }
                    System.out.println("");
                    break;
                case 7:
                    if (file.isFile()) {
                        os = new ObjectInputStream(new FileInputStream(file));
                        e = (ArrayList<FileHandlingUsingArrayList>) os.readObject();
                        os.close();
                        Collections.sort(e, new Comparator<FileHandlingUsingArrayList>() {
                            @Override
                            public int compare(FileHandlingUsingArrayList o1, FileHandlingUsingArrayList o2) {
                                return o1.empno - o2.empno;
                            }
                        });
                        oss = new ObjectOutputStream(new FileOutputStream(file));
                        oss.writeObject(e);
                        oss.close();
                        System.out.println("");
                        Iterator<FileHandlingUsingArrayList> i3 = e.iterator();
                        while (i3.hasNext()) {
                            FileHandlingUsingArrayList e1 = i3.next();
                            System.out.println("-----------------------------------------------------------------------------------------------------");
                            System.out.println("|Employee No|       " + "|Employee Name|       " + "|Empolyee Salary|           " + "|Employee Email|      ");
                            System.out.println(e1);
                            System.out.println("-----------------------------------------------------------------------------------------------------");
                            found = true;
                        }
                        if (!found) {

                            System.out.println("-----------------");
                            System.out.println("RECORD NOT FOUND!");
                            System.out.println("-----------------");
                        } else {
                            System.out.println("-----------------------------");
                            System.out.println("RECORD IS SORTED!");
                            System.out.println("-----------------------------");
                        }
                    } else {
                        System.out.println("File Not Exits....!");
                    }
                    System.out.println("");
                    break;
                case 8:
                    if (file.isFile()) {
                        os = new ObjectInputStream(new FileInputStream(file));
                        e = (ArrayList<FileHandlingUsingArrayList>) os.readObject();
                        os.close();
                        Collections.sort(e, new Comparator<FileHandlingUsingArrayList>() {
                            @Override
                            public int compare(FileHandlingUsingArrayList o1, FileHandlingUsingArrayList o2) {
                                return o1.empname.compareTo(o2.empname);
                            }

                        });
                        System.out.println("");
                        Iterator<FileHandlingUsingArrayList> i3 = e.iterator();
                        while (i3.hasNext()) {
                            FileHandlingUsingArrayList e1 = i3.next();
                            System.out.println("-----------------------------------------------------------------------------------------------------");
                            System.out.println("|Employee No|       " + "|Employee Name|       " + "|Empolyee Salary|           " + "|Employee Email|      ");
                            System.out.println(e1);
                            System.out.println("-----------------------------------------------------------------------------------------------------");
                            found = true;
                        }
                        if (!found) {

                            System.out.println("-----------------");
                            System.out.println("RECORD NOT FOUND!");
                            System.out.println("-----------------");
                        } else {
                            System.out.println("-----------------------------");
                            System.out.println("RECORD IS SORTED!");
                            System.out.println("-----------------------------");
                        }
                    } else {
                        System.out.println("File Not Exits....!");
                    }
                    System.out.println("");
                    break;
                case 9:
                    if (file.isFile()) {
                        os = new ObjectInputStream(new FileInputStream(file));
                        e = (ArrayList<FileHandlingUsingArrayList>) os.readObject();
                        os.close();
                        Collections.sort(e, new Comparator<FileHandlingUsingArrayList>() {
                            @Override
                            public int compare(FileHandlingUsingArrayList o1, FileHandlingUsingArrayList o2) {
                                return o1.empname.compareTo(o2.empname);
                            }
                        });
                        oss = new ObjectOutputStream(new FileOutputStream(file));
                        oss.writeObject(e);
                        oss.close();
                        System.out.println("");
                        Iterator<FileHandlingUsingArrayList> i3 = e.iterator();
                        while (i3.hasNext()) {
                            FileHandlingUsingArrayList e1 = i3.next();
                            System.out.println("-----------------------------------------------------------------------------------------------------");
                            System.out.println("|Employee No|       " + "|Employee Name|       " + "|Empolyee Salary|           " + "|Employee Email|      ");
                            System.out.println(e1);
                            System.out.println("-----------------------------------------------------------------------------------------------------");
                            found = true;
                        }
                        if (!found) {

                            System.out.println("-----------------");
                            System.out.println("RECORD NOT FOUND!");
                            System.out.println("-----------------");
                        } else {
                            System.out.println("-----------------------------");
                            System.out.println("RECORD IS SORTED!");
                            System.out.println("-----------------------------");
                        }
                    } else {
                        System.out.println("File Not Exits....!");
                    }
                    System.out.println("");
                    break;
                case 10:
                    if (file.isFile()) {
                        os = new ObjectInputStream(new FileInputStream(file));
                        e = (ArrayList<FileHandlingUsingArrayList>) os.readObject();
                        os.close();
                        Collections.sort(e, new Comparator<FileHandlingUsingArrayList>() {
                            @Override
                            public int compare(FileHandlingUsingArrayList o1, FileHandlingUsingArrayList o2) {
                                return (int) (o1.empsalary - o2.empsalary);
                            }

                        });
                        System.out.println("");
                        Iterator<FileHandlingUsingArrayList> i3 = e.iterator();
                        while (i3.hasNext()) {
                            FileHandlingUsingArrayList e1 = i3.next();
                            System.out.println("-----------------------------------------------------------------------------------------------------");
                            System.out.println("|Employee No|       " + "|Employee Name|       " + "|Empolyee Salary|           " + "|Employee Email|      ");
                            System.out.println(e1);
                            System.out.println("-----------------------------------------------------------------------------------------------------");
                            found = true;
                        }
                        if (!found) {

                            System.out.println("-----------------");
                            System.out.println("RECORD NOT FOUND!");
                            System.out.println("-----------------");
                        } else {
                            System.out.println("-----------------------------");
                            System.out.println("RECORD IS SORTED!");
                            System.out.println("-----------------------------");
                        }
                    } else {
                        System.out.println("File Not Exits....!");
                    }
                    System.out.println("");
                    break;
                case 11:
                    if (file.isFile()) {
                        os = new ObjectInputStream(new FileInputStream(file));
                        e = (ArrayList<FileHandlingUsingArrayList>) os.readObject();
                        os.close();
                        Collections.sort(e, new Comparator<FileHandlingUsingArrayList>() {
                            @Override
                            public int compare(FileHandlingUsingArrayList o1, FileHandlingUsingArrayList o2) {
                                return (int) (o1.empsalary - o2.empsalary);
                            }
                        });
                        oss = new ObjectOutputStream(new FileOutputStream(file));
                        oss.writeObject(e);
                        oss.close();
                        System.out.println("");
                        Iterator<FileHandlingUsingArrayList> i3 = e.iterator();
                        while (i3.hasNext()) {
                            FileHandlingUsingArrayList e1 = i3.next();
                            System.out.println("-----------------------------------------------------------------------------------------------------");
                            System.out.println("|Employee No|       " + "|Employee Name|       " + "|Empolyee Salary|           " + "|Employee Email|      ");
                            System.out.println(e1);
                            System.out.println("-----------------------------------------------------------------------------------------------------");
                            found = true;
                        }
                        if (!found) {

                            System.out.println("-----------------");
                            System.out.println("RECORD NOT FOUND!");
                            System.out.println("-----------------");
                        } else {
                            System.out.println("-----------------------------");
                            System.out.println("RECORD IS SORTED!");
                            System.out.println("-----------------------------");
                        }
                    } else {
                        System.out.println("File Not Exits....!");
                    }
                    System.out.println("");
                    break;
                default:
                    System.out.println("-----------------------");
                    System.out.println("USER ENTER WRONG INPUT:");
                    System.out.println("-----------------------");
            }

        } while (ch != 0);
    }

    
}
