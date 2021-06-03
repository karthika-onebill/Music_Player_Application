/*
 *   Case 2 : Search song and display based on song id(unique id)
 * 
 */
package com.onebill.module_test1.music_palyer_application;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Search_Song extends HenchForth {
	String song_name; // user input
	String[] mat = new String[3]; // for storing result set
	int p = 0;

	void display(String msg, int i) {

		mat[i] = msg; // for copy the resultset

	}

	/*
	 * To serach the particular song based on songname (Region matches are allowed)
	 * ---> ex : Melody means user type melo it will display
	 */
	void toSearch(String song_name) {
		try {
			this.song_name = song_name;

			// issue the queries
			String query = "select * from MusicFiles where Song_Title Like ? ";

			pstat = con.prepareStatement(query);

			pstat.setString(1, "%" + this.song_name + "%");
			res = pstat.executeQuery();
			/* view the table */
			displayViewTable(res);

			System.out.println("Enter the Song id : ");
			int ch_id = sc.nextInt();
			sc.nextLine();
			play_particular_Songs(ch_id);

		} catch (Exception e) {

			System.err.println("Coudn't reach the server!! Try again!");
		}

	}

	void displayViewTable(ResultSet res) {
		/* view the table */
		System.err.println("----------------------------------------------------");
		System.err.println("\tFile Name\t\tAttribute\t\t\tPlay\n");
		System.err.println("----------------------------------------------------");

		try {
			while (res.next()) {
				// process the results
				int song_id = res.getInt("Song_ID");
				String song_title = res.getString("Song_Title");
				String artist_name = res.getString("Artist_Name");
				String album_name = res.getString("Album_Name");
				String song_location = res.getString("Song_Location");

				display(song_location+"\t", 0);

				display("\t\tSong Title : " + song_title + "\n\t\tArtist Name  : " + artist_name + "\n\t\t\tAlbum Name : "
						+ album_name + "\t\t", 1);
				display("Song ID : " + song_id + "\n", 2);

				for (int k = 0; k < mat.length; k++) {
					System.out.print(mat[k]);
				}
				System.out.println("-----------------------------------------------------");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* Play particular song based on song id */
	static void play_particular_Songs(int song_id) {
		try {

			// issue the queries
			String query = "select * from MusicFiles where Song_ID=? order by Artist_Name asc";

			pstat = con.prepareStatement(query);

			pstat.setInt(1, song_id);
			res = pstat.executeQuery();

			while (res.next()) {
				String song_title = res.getString("Song_Title");
				System.err.println("Playing song................." + song_title);
				Thread.sleep(20); // for controlling music play
			}

		} catch (Exception e) {

			System.err.println("Coudn't reach the server!! Try again!");
		}

	}

	/* case :2 main method */
	static void input() throws Custom_Exception_Music {
		String song_name = null;
		System.out.println("Enter the Song Name : ");
		song_name = sc.nextLine();
		if (song_name != null) {
			Search_Song d = new Search_Song();
			d.toSearch(song_name);

		} else {
			throw new Custom_Exception_Music("------Enter the valid Song name-------------");
		}

	}

}
