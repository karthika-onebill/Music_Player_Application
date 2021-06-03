/*  Performing CRUD operatio on Music player DB */
package com.onebill.module_test1.music_palyer_application;

import java.sql.DriverManager;

public class Operates_on_songs extends HenchForth {

	static void label(String label) {
		System.out.println(label);
	}

	/* Add new song */
	public static void addSong() {
		try {
			// issue the query

			String query = "insert into MusicFiles(Song_Title,Artist_Name,Album_Name,Song_Location,Description) values(?,?,?,?,?)";
			pstat = con.prepareStatement(query);

			label("Enter the Song Title : ");
			String song_title = sc.nextLine();
			
			label("Enter the  Artist Name : ");
			String artist_name = sc.nextLine();
		
			label("Enter the Album Name : ");
			String album_name = sc.next();
			label("Enter the  Song Location : ");
			String song_location = sc.next();
			sc.nextLine();
			label("Enter the Song Description: ");
			String song_desc = sc.nextLine();
			pstat.setString(1, song_title);
			pstat.setString(2, artist_name);
			pstat.setString(3, album_name);
			pstat.setString(4, song_location);
			pstat.setString(5, song_desc);

			int res = pstat.executeUpdate();
			if (res > 0) {
				System.err.println("\n\n New Songs Added succesfully!\n\n\n");
			}
		} catch (Exception e) {

			System.err.println("Coudn't reach the server!! Try again!");
		}

	}

	/* Editing a particular song based on location */
	public static void editSong() {
		try {
			// 1.load the driver
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			// 2.get conn db
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MusicPlayer?user=root&password=karthika13");

			String query = "update MusicFiles set Song_Title=?,Artist_Name=?,Album_Name=?,Song_Location=?,Description=? where Song_Location=?";
			pstat = con.prepareStatement(query);
			label("Enter the Song Location to update : ");
			String song_location_update = sc.nextLine();
			label("Enter the Song Title : ");
			String song_title = sc.nextLine();

			label("Enter the  Artist Name : ");
			String artist_name = sc.nextLine();
			label("Enter the Album Name : ");
			String album_name = sc.nextLine();
			label("Enter the  Song Location : ");
			String song_location = sc.nextLine();
			
			label("Enter the Song Description: ");
			String song_desc = sc.nextLine();
			pstat.setString(1, song_title);
			pstat.setString(2, artist_name);
			pstat.setString(3, album_name);
			pstat.setString(4, song_location);
			pstat.setString(5, song_desc);
			pstat.setString(6, song_location_update);
			int res = pstat.executeUpdate();
			if (res >= 0) {
				System.err.println("\n\n  Songs Updated succesfully!\n\n\n");
			}
		} catch (Exception e) {
			System.err.println("Coudn't reach the server!! Try again!");
		}

	}
	/* Deleting a particular song based on location */

	public static void deleteSong() {
		try {
			// 1.load the driver
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			// 2.get conn db
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MusicPlayer?user=root&password=karthika13");

			String query = "Delete from MusicFiles where Song_Location=?";
			pstat = con.prepareStatement(query);
			System.out.println("Enter the Song Location to Delete : ");
			String song_location_delete = sc.nextLine();

			pstat.setString(1, song_location_delete);

			int res = pstat.executeUpdate();
			if (res >= 0) {
				System.err.println("\n\n  Songs Deleted succesfully!\n\n\n");
			}
		} catch (Exception e) {
			System.out.println(e);
			System.err.println("Coudn't reach the server!! Try again!");
		}

	}

	/* Case 4 : Main method */
	static void input() throws Custom_Exception_Music {
		char ch_A;
		int exit = 0;
		while (exit != 1) {

			System.out.println(
					"Press A : Add Song\nPress B : Edit the Existing Song\nPress C : Delete the Existing Song\nPress D : Exit\nEnter the choices : ");
			ch_A = sc.next().charAt(0);
			sc.nextLine();
			if (ch_A >= 'A' && ch_A <= 'D') {

				switch (ch_A) {
				case 'A': // Add Song
					addSong();
					break;
				case 'B': // Edit the Existing song
					editSong();

					break;
				case 'C': // Delete the Existing song
					deleteSong();

					break;
				case 'D': // Exit
					exit = 1;
					break;

				}

			} else {
				throw new Custom_Exception_Music(
						"---------Wrong choices!!-------\n Please ensure to press valid inputs\n");
			}
		}
	}
}
