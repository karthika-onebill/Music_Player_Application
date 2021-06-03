/*
 *    Java Application for Simulation of Music Player using Mysql server
 *    
 *    DBNAME : MusicPlayer
 *    TABLE : MusicFiles
 *    
 *    @author : KarthikaVelmurugan
 *    version : 1.1.0
 *    Date    : 03-06-2021
 * 
 */
package com.onebill.module_test1.music_palyer_application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HenchForth {

	static Connection con; // creating DB connection
	static Statement stat; // creating statement object for static queries
	static ResultSet res; // creating res object
	static PreparedStatement pstat; // creating prepared statement object
	static Scanner sc = new Scanner(System.in);
	/*
	 * DB and table creation
	 */

	static boolean toDbtablecreate(String sql_query, String commands) {
		boolean status = false;
		try {

			// issue the queries
			String query = sql_query;
			stat = con.createStatement();
			// process the result
			int res = stat.executeUpdate(query);

			if (res >= 0) {
				// Music player database/table created succesfully!!
				status = true;
			}
		} catch (Exception e) {
			status = true;
			System.err.println(commands);
		}
		return status;
	}

	public static void main(String[] args) {

		int choice; // choice for main action
		int exit = 0; // for controlling main action
		boolean status_for_DBcreation; // status flag for DB creation
		boolean status_for_tablecreation; // status flag for table creation
		String DB_url = "jdbc:mysql://localhost:3306/";

		try {

			System.out.println("---------------------------------------------------\n");
			System.out.println("\tWELCOME TO MUSIC WORLD\t\t");
			System.out.println("\n---------------------------------------------------\n\n");

			/*
			 * DB Connection and creation (user :root ; pwd:karthika13)
			 * 
			 */

			// 1. load the driver
			Class.forName("com.mysql.jdbc.Driver");
			// 2.establish db connection
			con = DriverManager.getConnection(DB_url, "root", "karthika13");
			status_for_DBcreation = toDbtablecreate("create database MusicPlayer", "DB already Exist\n\n");

			con = DriverManager.getConnection(DB_url + "MusicPlayer", "root", "karthika13");
			status_for_tablecreation = toDbtablecreate(
					"create table MusicFiles(Song_ID int(10) Auto_Increment Primary key,Song_Title varchar(50),Artist_Name varchar(50),Album_Name varchar(50),Song_Location varchar(50),Description varchar(250))",
					"table already exist!");

			if (status_for_DBcreation && status_for_tablecreation) {
				while (exit != 1) {

					System.out.println(
							"Press 1 : Play a Song\nPress 2 : Search a Song\nPress 3 : Show all Songs\nPress 4 : Operates on Songs Database\nPress 5 : Exit the Application\n\nEnter your choice : ");
					// read the choice from user

					choice = sc.nextInt();
					sc.nextLine();
					if (choice >= 1 && choice <= 5) {

						switch (choice) {
						case 1: // play a song
							Play_Song.input();

							break;
						case 2: // search a song
							Search_Song.input();

							break;
						case 3: // show all songs
							Show_Song.play_all_songs();
							break;
						case 4: // operates on songs DB
							Operates_on_songs.input();
							break;
						case 5: // Exit the Application
							exit = 1;
							System.err.println(
									"\n\n------------THANK YOU FOR USING MUSIC WORL APPLICATION------------\n\n");
							break;
						default:
							throw new Custom_Exception_Music(
									"---------Wrong Choices!!-----------\nPlease ensure to press valid choices!!");

						}
					} else {
						throw new Custom_Exception_Music(
								"---------Wrong Choices!!-----------\nPlease ensure to press valid choices!!");
					}
				}

			} else {
				throw new Custom_Exception_Music("-------Server Error!! \n Couldnot reach the server---------\n");
			}
		} catch (Custom_Exception_Music e) {
			System.err.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.err.println("---------Wrong Choices!!-----------\\nPlease ensure to press valid choices!!");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (sc != null)
					sc.close();
				if (con != null)
					con.close();
				if (stat != null)
					stat.close();
				if (pstat != null)
					pstat.close();
				if (res != null)
					res.close();
			} catch (Exception e) {
			}
		}
	}

}
