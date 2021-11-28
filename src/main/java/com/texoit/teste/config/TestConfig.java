package com.texoit.teste.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.texoit.teste.entities.Movie;
import com.texoit.teste.repositories.MovieRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public void run(String... args) throws Exception {

		String fileCSV = "movielist.csv";
		BufferedReader br = null;
		String line = "";
		String separationLine = ";";

		try {

			List<Movie> movies = new ArrayList<Movie>();

			br = new BufferedReader(new FileReader(fileCSV));
			
			if (br.readLine() == null)
				return;
			while ((line = br.readLine()) != null) {

				String[] colArrays = line.split(separationLine);
				String winner = "";
				
				if (colArrays.length > 4)
					winner = colArrays[4];
				
				Movie m = new Movie(null, 
									Integer.parseInt(colArrays[0]), 
									colArrays[1], 
									colArrays[2], 
									colArrays[3],
									winner);
				
				movies.add(m);
				
			}
			
			movieRepository.saveAll(movies);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
