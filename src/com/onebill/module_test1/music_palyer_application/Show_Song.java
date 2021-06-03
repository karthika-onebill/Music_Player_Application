/*
 * 
 *    Play all songs one by one 
 */
package com.onebill.module_test1.music_palyer_application;

public class Show_Song extends HenchForth {

	static void play_all_songs() {
		Search_Song s = new Search_Song();
		try {

			// 3.issue the queries
			String query = "select * from MusicFiles order by Song_Title asc";
			stat = con.createStatement();
			// 4.process the result
			res = stat.executeQuery(query);
			s.displayViewTable(res);
			/*
			 * while (res.next()) { String song_title = res.getString("Song_Title");
			 * System.err.println("Playing song................."+song_title);
			 * Thread.sleep(20); }
			 */

		} catch (Exception e) {

			System.err.println("Coudn't reach the server!! Try again!");
		}

	}
}
