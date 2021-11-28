package com.texoit.teste.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texoit.teste.entities.Movie;
import com.texoit.teste.entities.Result;
import com.texoit.teste.repositories.MovieRepository;

@Service
public class MovieService {

	private String producerPrevious="";
	private Integer yearPrevious=0;
	private Integer intervalWinMax=0;
	private Integer intervalWinMin=10000;
	
	@Autowired
	private MovieRepository repository;

	public List<Movie> findAll() {
		return repository.findAll();
	}

	public List<Movie> findByWinner(String winner){
			
		List<Movie> list = repository.findByWinner(winner); 
		return list;
		
	}
	
	public List<Result> getRequirements(){
	
		List<Result> listResult = new ArrayList<>();
		String winner = "yes";
		List<Movie> list = findByWinner(winner);

		Collections.sort(list, new Comparator<Movie>() {
		     @Override
		     public int compare(Movie movie1, Movie movie2){
		           if (movie1.getProducer().contains(movie2.getProducer())){
		        	   return Integer.compare(movie1.getYear(), movie2.getYear());
		           }else {
		        	   return movie1.getProducer().compareTo(movie2.getProducer());
		           }
		      }
		});

		List<Result> resultMin = produtorComPremioMaisRapido(list);
		listResult.addAll(resultMin);
		
		List<Result> resultMax = produtorComMaiorIntervaloEntre2PremiosConsecutivos(list);
		listResult.addAll(resultMax);

		return listResult;

	}
	
	public List<Result> produtorComPremioMaisRapido(List<Movie> list){

		yearPrevious = 0;
		producerPrevious="";
		
		List<Result> listResult = new ArrayList<>();
		
		list.forEach(p -> {
			if(producerPrevious!="" && p.getProducer().contains(producerPrevious)) {
				var intervalWin = (Integer)p.getYear() - yearPrevious;
				if (intervalWinMin >= intervalWin) {
					if(intervalWinMin > intervalWin)					
						listResult.clear();
					
					intervalWinMin = intervalWin;
					Result r = new Result("min",
											producerPrevious,
											intervalWin, 
											yearPrevious,
											p.getYear());
					listResult.add(r);
				}
			}

			yearPrevious = (Integer)p.getYear();
			producerPrevious = p.getProducer();
			
		});
		
		return listResult;
	}
	public List<Result> produtorComMaiorIntervaloEntre2PremiosConsecutivos(List<Movie> list){
		
		yearPrevious = 0;
		producerPrevious="";
		List<Result> listResult = new ArrayList<>();
		
		list.forEach(p -> {
			if(producerPrevious!="" && p.getProducer().contains(producerPrevious)) {
				var intervalWin = (Integer)p.getYear() - yearPrevious;
				if (intervalWinMax <= intervalWin) {
					if(intervalWinMax < intervalWin)					
						listResult.clear();
					
					intervalWinMax = intervalWin;
					Result r = new Result("max",
											producerPrevious,
											intervalWin, 
											yearPrevious,
											p.getYear());
					listResult.add(r);
				}
			}

			yearPrevious = (Integer)p.getYear();
			producerPrevious = p.getProducer();
			
		});
		
		return listResult;
	}
	
}