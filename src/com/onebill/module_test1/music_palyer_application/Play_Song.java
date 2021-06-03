/*
 *   CASE 1 : Play Songs based on user choices
 * 
 */
package com.onebill.module_test1.music_palyer_application;


public class Play_Song extends HenchForth {

	/* Case 1 : Play all songs based on Song_title */
	static void play_all_songs() {
		try {

			// issue the queries
			String query = "select * from MusicFiles order by Song_Title asc";
			stat = con.createStatement();
			// process the result
			res = stat.executeQuery(query);

			while (res.next()) {
				String song_title = res.getString("Song_Title");
				System.err.println("Playing song................." + song_title);
				Thread.sleep(3000); // controlling to play the song
			}

		} catch (Exception e) {

			System.err.println("Coudn't reach the server!! Try again!");
		}

	}

	/* Case 2 : Play songs randomly ==== invoking RAND() sql function*/
	static void play_Random_Songs() {
		try {

			// issue the queries
			String query = "select * from MusicFiles order by RAND()";
			stat = con.createStatement();
			// process the result
			res = stat.executeQuery(query);

			while (res.next()) {
				String song_title = res.getString("Song_Title");
				System.err.println("Playing song................." + song_title);
				Thread.sleep(3000); // controlling to play the song
			}

		} catch (Exception e) {

			System.err.println("Coudn't reach the server!! Try again!");
		}

	}

	/* play the particular song based on region matches SongTitle */
	static void play_particular_Songs() {
		try {

			// issue the queries
			String query = "select * from MusicFiles where Song_Title=? order by Artist_Name asc";

			pstat = con.prepareStatement(query);
			System.out.println("Enter the song title :");
			// read the song title name from user
			String song_title_name = sc.nextLine();

			pstat.setString(1, song_title_name);
			res = pstat.executeQuery();

			while (res.next()) {
				String song_title = res.getString("Song_Title");
				System.err.println("Playing song................." + song_title);
				Thread.sleep(3000);// controlling to play the song
			}

		} catch (Exception e) {

			System.err.println("Coudn't reach the server!! Try again!");
		}

	}

	/* main method for case 1 play songs */
	static void input() throws Custom_Exception_Music {
		char ch_A; // choices
		int exit = 0; // control variable
		while (exit != 1) {

			System.out.println(
					"Press A : Play All Songs\nPress B : Play Songs Randomly\nPress C : Play Particular Song \nPress D : Exit\nEnter the choices : ");
			ch_A = sc.next().charAt(0);
			sc.nextLine();
			if (ch_A >= 'A' && ch_A <= 'D') {

				switch (ch_A) {
				case 'A': // play all songs
					play_all_songs();

					break;
				case 'B': // play songs randomly

					play_Random_Songs();
					break;
				case 'C': // play specific song
					play_particular_Songs();

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
